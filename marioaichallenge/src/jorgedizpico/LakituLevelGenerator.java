package jorgedizpico;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Random;

import weka.clusterers.Clusterer;
import weka.core.Instance;
import weka.core.SparseInstance;

import jorgedizpico.auto.Automaton;
import jorgedizpico.cluster.DataFileParser;

import dk.itu.mario.MarioInterface.GamePlay;
import dk.itu.mario.MarioInterface.LevelGenerator;
import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.level.Level;

public class LakituLevelGenerator implements LevelGenerator {
	
	public static String clusterFile = System.getProperty("user.dir") + "/data/cluster.dat";
  	protected static Random rand = new Random();
  	protected LakituLevel lvl;
	
	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		try {
						
			Clusterer cl = readClusters(clusterFile);			
			Instance inst = makeInstance(playerMetrics);
			int cluster = cl.clusterInstance(inst);
			
			switch (cluster) {
				case 0: // intermediate
					lvl.setType(Level.TYPE_OVERGROUND);
					break;
				case 1: // speeder
					lvl.setType(Level.TYPE_CASTLE);
					break;
				case 2: // explorer
					lvl.setType(Level.TYPE_UNDERGROUND);
					break;
			}
			
			Automaton aut = new Automaton(cluster);
			
			LakituLevel lvl = aut.buildLevel();
			
			if (null != lvl)
				throw new Exception("Error while building level from genes");
				
			return lvl;
			
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public LevelInterface generateLevel(String detailedInfo) {
		return null;
	}
	
	public static Clusterer readClusters(String fileName){
		FileInputStream fis = null;
	    ObjectInputStream in = null;
	    Clusterer cl =  null;
		try {
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			cl = (Clusterer)in.readObject();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cl;
	}

	protected Instance makeInstance(GamePlay gp) {
		try {
			Double d = 0.0;
			Field[] flds = GamePlay.class.getFields();
			SparseInstance inst = new SparseInstance(flds.length);
			Arrays.sort(flds, new DataFileParser().new FieldComparator());
			
			for (int i = 0; i < flds.length; i++){
				
				if (flds[i].getType() == Integer.TYPE)
					d = new Double((Integer)flds[i].get(gp));
				else if (flds[i].getType() == Double.TYPE)
					d = (Double) flds[i].get(gp);
					
				inst.setValue(i, d);
			}
			
			return inst;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
}
