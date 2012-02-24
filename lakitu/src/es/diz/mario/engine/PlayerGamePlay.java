package es.diz.mario.engine;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import dk.itu.mario.MarioInterface.GamePlay;

public class PlayerGamePlay extends GamePlay {
	
	private static final long serialVersionUID = 1L;
	
	protected String playerName;
	protected long timeStamp;
	
	public PlayerGamePlay(String playerName, long timeStamp) {
		this.playerName = playerName;
		this.timeStamp = timeStamp;
	}

	public static PlayerGamePlay load(String playerName, long timeStamp){
		FileInputStream fis = null;
	    ObjectInputStream in = null;
	    PlayerGamePlay gp =  null;
		String fileName = "players/" + playerName + "/" + timeStamp + ".gp";
		try {
			fis = new FileInputStream(fileName);
			in = new ObjectInputStream(fis);
			gp = (PlayerGamePlay)in.readObject();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return gp;
	}
	
	public void save() {
		
		ObjectOutputStream out = null;
		String fileName = "players/" + playerName + "/" + timeStamp + ".gp";
		try {
			File file = new File(fileName);
			File playerFolder = file.getParentFile();

			if (null != playerFolder)
			    playerFolder.mkdirs();

			FileOutputStream fos = new FileOutputStream(file);
			out =  new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();
			fos.close();
			
			saveXml();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void saveXml() {
		// TODO 
	}
	
}
