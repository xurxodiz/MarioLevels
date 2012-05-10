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

public class LakituLevelGenerator implements LevelGenerator {
	
	public static String clusterFile = System.getProperty("user.dir") + "/data/cluster.dat";
  	protected static Random rand = new Random();
  	protected LakituLevel lvl;
	
	@Override
	public LevelInterface generateLevel(GamePlay playerMetrics) {
		try {
			lvl = new LakituLevel();
	
			Clusterer cl = readClusters(clusterFile);
			Instance inst = makeInstance(playerMetrics);
			
			// classify playerMetrics
			// pass type to automaton on creation
			int cluster = cl.clusterInstance(inst);
			
			lvl.setType(cluster);
			
			Automaton aut = new Automaton(cluster);
			Builder lkb = new Builder(lvl);
			
			aut.execute(lkb);
			lkb.fixLevel();
			
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
				@SuppressWarnings("rawtypes")
				Class cl = flds[i].getType();
				if (cl.isInstance(new Integer(2)))
					d = new Double((Integer)flds[i].get(gp));
				else if (cl.isInstance(new Double(2)))
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
