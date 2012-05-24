package jorgedizpico.cluster;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.DensityBasedClusterer;
import weka.clusterers.EM;
import weka.clusterers.FilteredClusterer;
import weka.clusterers.MakeDensityBasedClusterer;
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
		
		if (args.length < 2) {
			System.out.println("Usage: ClusterGenerator <arffInput> <clusterOutput>");
		}
		
		try {
			
			String dataFile = args[0];
			String clusterFile = args[1];
					
			Instances data = DataSource.read(dataFile);
			    
			// three clusters, a hundred iterations
			String[] clOptions = {"-N", "3"};
			EM cl   = new EM();
			cl.setOptions(clOptions);
			
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
			fc.setFilter(remove);
			fc.setClusterer(cl);
			DensityBasedClusterer dbc = new MakeDensityBasedClusterer(fc);
			dbc.buildClusterer(data);
			    
			ClusterEvaluation eval = new ClusterEvaluation();
			eval.setClusterer(dbc);
			eval.evaluateClusterer(new Instances(data));
			System.out.println(eval.clusterResultsToString()); 
			
			for (int i = 0; i < data.numInstances(); i++) {
				double[] prob =	dbc.logDensityPerClusterForInstance(data.instance(i));
				System.out.println("Instance " + i + ":");
				for (int j = 0; j < prob.length; j++)
					System.out.println("cluster " + j + ": " + prob[j] + ", ");
			}
			
			System.out.println("Performing cross-validation...");
		    double logllh = ClusterEvaluation.crossValidateModel(
		           dbc, data, 10, data.getRandomNumberGenerator(1));
		    System.out.println("\nLog likelihood: " + logllh);
			
			write(new MakeDensityBasedClusterer(fc), clusterFile);
			

			
		} catch (Exception e) {
			System.out.println("Unable to generate clusters: " + e.getMessage());
		}
	}
	  
	public static void write(DensityBasedClusterer cl, String clusterFile) throws IOException {
		FileOutputStream fos = new FileOutputStream(clusterFile);
		ObjectOutputStream out =  new ObjectOutputStream(fos);
		out.writeObject(cl);
		out.close();
		fos.close();
	}
	
}
