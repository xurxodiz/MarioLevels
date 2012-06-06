package jorgedizpico.cluster;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import weka.clusterers.ClusterEvaluation;
import weka.clusterers.Clusterer;
import weka.clusterers.DensityBasedClusterer;
import weka.clusterers.EM;
import weka.clusterers.FilteredClusterer;
import weka.clusterers.MakeDensityBasedClusterer;
import weka.clusterers.SimpleKMeans;
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
		
		if (args.length < 3) {
			System.out.println("Usage: ClusterGenerator <arffInput> <clusterOutput> <csvOutput>");
		}
		
		try {
			
			String dataFile = args[0];
			String clusterFile = args[1];
			String csvFile = args[2];
					
			Instances data = DataSource.read(dataFile);
			    
			// number of clusters
			String[] clOptions = {"-N", "3"};
			EM cl   = new EM();
			cl.setOptions(clOptions);
			
			DensityBasedClusterer dbc = new MakeDensityBasedClusterer(cl);
			dbc.buildClusterer(data);
			    
			ClusterEvaluation eval = new ClusterEvaluation();
			eval.setClusterer(dbc);
			eval.evaluateClusterer(new Instances(data));
			System.out.println(eval.clusterResultsToString()); 
			
			writeLogDensities(dbc, data, csvFile);
			
			System.out.println("Performing cross-validation...");
		    double logllh = ClusterEvaluation.crossValidateModel(
		           dbc, data, 10, data.getRandomNumberGenerator(1));
		    System.out.println("\nLog likelihood: " + logllh);
			
			write(dbc, clusterFile);
			

			
		} catch (Exception e) {
			System.out.println("Unable to generate clusters: " + e.getMessage());
		}
	}
	
	public static void writeLogDensities(DensityBasedClusterer dbc, Instances data, String csvFile)
		throws Exception {
		
		FileWriter csvWriter = new FileWriter(new File(csvFile));
		
		for (int i = 0; i < data.numInstances(); i++) {
			double[] prob =	dbc.logDensityPerClusterForInstance(data.instance(i));
			for (int j = 0; j < prob.length; j++) {
				csvWriter.write(Double.toString(prob[j]));
				if (j < prob.length-1) csvWriter.write(",");
			}
			csvWriter.write("\n");
		}
		
		csvWriter.close();
	}
	
	public static void write(DensityBasedClusterer dbc, String clusterFile) throws IOException {
		FileOutputStream fos = new FileOutputStream(clusterFile);
		ObjectOutputStream out =  new ObjectOutputStream(fos);
		out.writeObject(dbc);
		out.close();
		fos.close();
	}
	
}
