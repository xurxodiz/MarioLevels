package jorgedizpico.cluster;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;

import dk.itu.mario.MarioInterface.GamePlay;

public class DataFileParser {
	
	public static String dataFolderPath = System.getProperty("user.dir") + "/data/";
	
	public static void main(String[] args) {
		try {
			
			File arffFile = new File(dataFolderPath + "data.arff");
			FileWriter arffWriter = new FileWriter(arffFile);
			
			writeHeader(arffWriter);
			
			writeDataStartHeader(arffWriter);
			
			File dataFolder = new File(dataFolderPath);
			File[] entries = dataFolder.listFiles();
			Arrays.sort(entries, new DataFileParser().new FileComparator());
			 
			 for (File playerFolder : entries) {
				 
				if (!playerFolder.isDirectory() ||
					(".".equals(playerFolder.getName()) || "..".equals(playerFolder.getName())))
			      continue;  // Ignore non-folders and self and parent aliases.
	
			    for (File playerFile : playerFolder.listFiles()) {
			    	if ("player.txt".equals(playerFile.getName()))
			    		dumpFileEntry(playerFile, arffWriter);
			    }
			    
			 }
			 
			 arffWriter.close();
			 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	protected static void writeHeader(FileWriter file) {
		try {
			Class<? extends GamePlay> cl = GamePlay.class;
			Field[] flds = cl.getFields();
			Arrays.sort(flds, new DataFileParser().new FieldComparator());
			
			file.write("@relation playerdata\n");
			for (Field f : flds)
				file.write("@ATTRIBUTE\t" + f.getName() + "\tNUMERIC\n");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	protected static void writeDataStartHeader(FileWriter file) throws IOException {
		file.write("@data\n");
	}
	
	protected static void dumpFileEntry(File entry, FileWriter file) {
		try {
			FileInputStream fis = new FileInputStream(entry);
			ObjectInputStream in = new ObjectInputStream(fis);
			GamePlay gp = (GamePlay)in.readObject();
			
			Field[] flds = GamePlay.class.getFields();
			Arrays.sort(flds, new DataFileParser().new FieldComparator());
			
			for (Field f : flds)
				file.write(f.get(gp).toString()+",");
			
		    file.write("\n");
		} catch (Exception e) {
			System.out.println(entry.getAbsolutePath());
			e.printStackTrace();
		}
		
	}
	
	class FieldComparator implements Comparator<Field> {
		   
	    public int compare(Field f1, Field f2){

	        String name1 = f1.getName();        
	        String name2 = f2.getName();
	       
	        return name1.compareTo(name2);
	    }
	   
	}
	
	class FileComparator implements Comparator<File> {
		   
	    public int compare(File f1, File f2){

	        String name1 = f1.getName();        
	        String name2 = f2.getName();
	       
	        return name1.compareTo(name2);
	    }
	   
	}

}
