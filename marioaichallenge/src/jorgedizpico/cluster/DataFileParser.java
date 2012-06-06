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
	
	public static void main(String[] args) {
		
		if (args.length < 2) {
			System.out.println("Usage: DataFileParser <data_folder> <data_file>");
			return;
		}
		
		try {
			File dataFolder = new File(args[0]);
			File arffFile = new File(args[1]);
			
			FileWriter arffWriter = new FileWriter(arffFile);
			writeHeader(arffWriter);
			writeDataStartHeader(arffWriter);
			
			File[] entries = dataFolder.listFiles();
			Arrays.sort(entries, new Filters().new FileComparator());
			 
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
			System.out.println("Unable to parse Files: " + e.getMessage());
		}
	}
	
	protected static void writeHeader(FileWriter file) throws IOException {
		Class<? extends GamePlay> cl = GamePlay.class;
		Field[] flds = cl.getFields();
		Arrays.sort(flds, new Filters().new FieldComparator());
		
		file.write("@relation playerdata\n");
		for (Field f : flds)
			if (!(Filters.isSkippedField(f.getName())))
				file.write("@ATTRIBUTE\t" + f.getName() + "\tNUMERIC\n");
	}
	
	protected static void writeDataStartHeader(FileWriter file) throws IOException {
		file.write("@data\n");
	}
	
	protected static void dumpFileEntry(File entry, FileWriter file) {
		try{
		FileInputStream fis = new FileInputStream(entry);
		ObjectInputStream in = new ObjectInputStream(fis);
		GamePlay gp = (GamePlay)in.readObject();
		
		Field[] flds = GamePlay.class.getFields();
		Arrays.sort(flds, new Filters().new FieldComparator());
		
		for (Field f : flds)
			if (!(Filters.isSkippedField(f.getName())))
					file.write(f.get(gp).toString()+",");
		
	    file.write("\n");		
		} catch (Exception e) {
			System.out.println("Unable to read file " + entry.getAbsolutePath() + ", skipped.");
		}
	}

}
