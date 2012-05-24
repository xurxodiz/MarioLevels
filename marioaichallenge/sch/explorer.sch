initial			= hub 
				;

flat			= FLAT hub 
				;

hub				= coins flat, 17
				| pipe flat , 12
				| blocks flat , 20
				| enemies flat , 20
				| cannon flat , 0.1
				| gaps flat , 19
				;
		
flat 			= FLAT flat, 5
				| FLAT hub , 95
				;

coins 			= COINS , 20
				| COINS coins , 80
				;

pipe 			= PIPE , 2
				| PIPEPIRANHA 
				;
		
blocks			= singleblock , 20
				| singleblock blocks , 80 
				;
		
singleblock		= BLOCKPP , 0.01
				| BLOCKCC , 5
				| BLOCKEE , 15
				| BLOCKPC , 0.02
				| BLOCKPE , 0.5
				| BLOCKCE , 10
				;
		
enemies			= singlenemy
				| singlenemy enemies
				;
				
singlenemy		= singlenemynowinged , 30
				| singlenemywinged
				;
				
singlenemynowinged = GOOMBA , 40
				| REDTURTLE , 5
				| GREENTURTLE , 15
				| SPIKY
				;
				
singlenemywinged = GOOMBAWINGED , 40
				| REDTURTLEWINGED , 5
				| GREENTURTLEWINGED , 15
				| SPIKYWINGED ;
				
cannon			= CANNON ;

gaps			= GAP , 5
				| STAIRSUP GAP STAIRSDOWN , 20;