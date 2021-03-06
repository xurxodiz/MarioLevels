The Lakitu Level Generator
==========================

This prototype implements adaptive (customized for each user) procedural (randomized each time it's run) levels for the Mario game – specifically, for the platform provided by the [Mario AI Championship](http://marioai.org).

Holds 1st place at the all-time points and percentage tally of all participants at the [IEEE Computational Intelligence and Games 2012](http://geneura.ugr.es/cig2012/) in Granada, Spain. You can find the table with the results for the 13 participants from all over the world [at the official website](http://noorshaker.com/marioComp2012/results.php), though I placed a copy here in `results.html` for archiving purposes.

In the `slides` folder you can find a tiny presentation that was attached to my entry, explaining how it works.

How does it work?
-----------------

### Preliminar steps

First, the system processes a bunch of collected user gameplay data and clusterizes them in two groups. This can be done once, offline. The two groups we are looking for are *explorers* (players that beat every enemy and collect every coin) and *speeders* (players that try to rush to the end as quickly as possible).

As a second, also offline process, two *schematics* (level templates) are parsed into probablistic automata; each one corresponds to one of the two groups mentioned above.

When a new player arrives he should play a test level; we'll use this records to classify him.

### Customized play

The records from the test level from the user are fed to the clusterer. It returns probabilistic membership percentages for both the explorer and speeder groups.

Then, the most likely group is chosen, and the generator starts at the initial state of its corresponding automaton. The automaton is traversed, picking a next state according to the weights determined for it in the schematic.

Now here's the key: at any point that the next state shares a name with a state in the other group's automaton, it's assumed to represent the same concept of level part. Therefore, it's randomly decided to proceed either in this automaton or to *phase* into the other and continue deriving there. This decision is not purely random as it is made according to the membership percentages calculated before. A player strongly belonging to one of the groups will have most of its states picked from that particular group.

Some states will produce as output a small slice of the level (called a *chunk*). The concatenation, left to righ, of those chunks, is called a *trace*, and it uniquely identifies each level. In the following step, a builder module will construct the level from the trace to be played.

### Alternative

An alternative method to the *trace phasing*, also present in the code, is to create two different levels, per the explorer and speeder schematics, and then pick chunks randomly from each, according to the membership percentages. This method is called *trace mixing*.

How was the data collected?
--------------------------

The file `mariodistpackage.zip` was spread on social networks, and people were asked to play the test level it generated and mail back the results. Note that this package contains just an unadultered, as-downloaded version of the Mario AI platform along with a helper launch script.

What does each schematic favor?
-------------------------------

*Speeders* reject interaction. Every element that requires extra time to be taken care of is shunned. Therefore, their schematic has more coins (since they can be picked just be running over them) and flatter landscapes. Blocks and enemies are scattered in small doses, and pipes, gaps and cannons (whose full extent of interaction is being jumped over) are more frequent.

*Explorers*, on the other hand, are all about interacting with the level. The landscape is more abrupt, coins (whose interaction is poorer) are more scarce, but enemies and blocks come in longer rows. They also have a higher rate of coin and powerup blocks, as well of turtles related to goombas. Gaps are minimal, as well as pipes. Cannons are quite rare.

Instruction booklet
-------------------

Those are the ant commands available:

* `ant clean` restores the project to clean slate, removing all automatically generated files
* `ant init` creates the initial folder structure after cleaning, this should only be used in a fresh install since it creates the external dependencies jar files
* `ant grammar` creates the Java files needed to parse the schematics (but doesn't parse)
* `ant build` compiles all sources
* `ant schematics` uses the compiled Java parsing files to read the schematics and output the automata files
* `ant data` reads all gameplay records and creates a single output file from them
* `ant cluster` reads the data file and creates a reference cluster file
* `ant play` executes the game with default behavior (as per the platform)
* `ant playc` executes the customized game using the Lakitu Level Generator

(for debug purposes, an `ant weka` command is also available, launching Weka's GUI)

On a fresh installed project, commands should be issued in this order to ensure proper performance. That is:

`ant clean init grammar build schematics data cluster playc`

Inner workings
--------------

### General system flow

LevelSceneTest acts as linking point. The platform, here, invokes LakituLevelGenerator to create a level from the user data.

LakituLevelGenerator reads the stored clusters and uses Weka to find membersip percentages of the user data. Then, calls Executor with those percentages and asks for a phase-generated trace (see above, *How it works*).

Executor loads up the Automata on construction, specifically the PhaseAutomata that has to be built from the base speeder and explorer Automata. Then, it inits and traverses the PhaseAutomata as any other finite state machine (as per the FSM interface), building up a Trace. 

At each step, the PhaseAutomata picks an automata to check if it has the next State. This picking is done weighted by the membership odds provided on construction. When it finds the first automata to have it, it executes the State.

A State can be either a basic Chunk, the base constituent of Traces, or a Dummy. Dummies simply pick one of its derivation Chains and dump them on the stack. The Chain is chosen according to the weights specified by the schematic when the Automata is built. Let's clarify: the Dummies and their derivation Chains are kept by the Automata, that are created by the Constructor class when called by `ant schematics` (see above for more on Ant commands).

The Automata's steps keep being Executed until we get a Trace of the desired length. Then, LakituLevelGenerator calls the Builder module to process all Chunks. Each Chunk contains a call to a specific level building function. By calling all functions sequentially, we create a LakituLevel out of the Trace, to be finally returned and displayed by the platform to be played :)

### Schematic and automata

To read the schematics, its grammar-defining file `schematics.abnf` is read by aParse, and it outputs Java files to process schematics into the src/jorgedizpico/grammar folder. This is done by the `ant grammar` job.

Now, if you build them (`ant build`) and then call `ant schematics`, you'll invoke the Constructor class. It will visit, iteratively, the tree of each schematic file (in `src/jorgedizpico/res/*.sch`), reading the rules defined by it and the weights associated, and create a probabilistic Automaton out of it. It will then dump it into a file for the main game to load up in customized play (in `src/jorgedizpico/res/*.sch.auto`). 

### Clustering

Gameplay records are stored anonymously in the `extra/data/` folder. There are two kinds of file on each player folder: global statistics (`player.txt`) and detailed activity (`DetailedInfo.txt`). Only the statistics are read by DataFileParser.java in `ant data` to create a centralized file of the records. Some fields are skipped, according to the definitions in Filters.java, since the platform's test levels lack some game features.

When `ant cluster` is called, ClusterGenerator.java reads that file and passes it to Weka. Clustering is made with the EM method, looking for two groups (explorers and speeders). Cluster results object is stored into a file for the main system to read afterwards.

ClusterGenerator also creates a CSV file that can be used to create graphs or statistics in other programs.

File hierarchy
---------------

Project is contained in /marioaichallenge. Inside you can find:

* `src/` with all the source code. Platform code is untouched except for:
    * function called in `LevelSceneTest.Java:63` refers now to `LakituLevelGenerator`, corresponding `import` was added as well
    * line in `LevelScene.java:488` is commented because of bug in original platform
    * my additions can be found under package `jorgedizpico`. specifically, schematics and clustering data are looked for by the code in `jorgedizpico/res/`
* `res/` contains some image and sound files provided by the platform
* `lib/` contains a single jar file with all the external dependencies of the project; it is built by running `ant jarify` from the files in the `ext folder`
* `extra/` contains two things:
    * the external dependencies; note this is only for reference as they are grouped into a single jar file in `lib/` by running `ant init`
    * the reference schematics; they are also copied to `src/jorgedizpico/res` upon `ant init`
* `data/` contains all collected user gameplay records

External libraries
------------------

The Lakitu Level Generator depends on [aParse](http://www.parse2.com/) for schematic parsing into automata and [weka](http://www.cs.waikato.ac.nz/ml/weka/) for clustering purposes.

Both of those libraries are included in the `extra/` folder for reference, but then compiled into a single file in `lib/jorgedizpico.jar` for distribution purposes (when executing `ant init`).

Contact
--------

Any doubts or questions? I'll be delighted to help! Hit me up on **jorge «a» diz «.» es**
