package jorgedizpico.cluster;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.EM;
import weka.clusterers.FilteredClusterer;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.unsupervised.attribute.Remove;


public class ClusterGenerator {
	
	/*
	 * taken from
	 * https://svn.scms.waikato.ac.nz/svn/weka/branches/stable-3-6...
	 * .../wekaexamples/src/main/java/wekaexamples/clusterers/ClusteringDemo.java
	 * 
	 * mixed with
	 * http://old.nabble.com/Ignore-attributes-in-Clustering-and-getting-results-td29809118.html
	 * 
	 */
	
	public static void main(String[] args) {
		
		try {
			
			String dataFile = args[0];
			String clusterFile = args[1];
					
			Instances data = DataSource.read(dataFile);
			    
			// three clusters, a hundred iterations
			String[] emOptions = {"-N", "3", "-I", "100"};
			EM cl   = new EM();
			cl.setOptions(emOptions);
			
			String[] remOptions = new String[2];
			remOptions[0] = "-R"; // "range"
			remOptions[1] = "1,2,3,5,7,15,17,27,28,29,30,32,33,34"; 
			// ArmoredTurtlesKilled
			// CannonBallKilled
			// ChompFlowersKilled
			// GreenTurtlesKilled
			// RedTurtlesKilled
			// EnemyKillByKickingShell
			// kickedShells
			// timesOfDeathByArmoredTurtle
			// timesOfDeathByCannonBall
			// timesOfDeathByChompFlower
			// timesOfDeathByFallingIntoGap
			// timesOfDeathByGreenTurtle
			// timesOfDeathByJumpFlower
			// timesOfDeathByRedTurtle
			
			// we ignore the attributes that are zero for all cases
			Remove remove = new Remove();
			remove.setOptions(remOptions);
			remove.setInputFormat(data);
			
			FilteredClusterer fc = new FilteredClusterer();
			fc.setFilter(remove); //add filter to remove attributes
			fc.setClusterer(cl); //bind FilteredClusterer to original clusterer
			fc.buildClusterer(data);
			    
			ClusterEvaluation eval = new ClusterEvaluation();
			eval.setClusterer(fc);
			eval.evaluateClusterer(new Instances(data));
			System.out.println(eval.clusterResultsToString()); 
			
			System.out.println("Performing cross-validation...");
		    cl = new EM();
		    double logllh = ClusterEvaluation.crossValidateModel(
		           cl, data, 10, data.getRandomNumberGenerator(1));
		    System.out.println("\nLog likelihood: " + logllh);
			    
			write(fc, clusterFile);
			
		} catch (Exception e) {
			System.out.println("Unable to generate clusters: " + e.getMessage());
		}
	}
	  
	public static void write(Clusterer cl, String clusterFile) throws IOException {
		FileOutputStream fos = new FileOutputStream(clusterFile);
		ObjectOutputStream out =  new ObjectOutputStream(fos);
		out.writeObject(cl);
		out.close();
		fos.close();
	}
	
}
