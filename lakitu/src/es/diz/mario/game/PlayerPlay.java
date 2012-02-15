package es.diz.mario.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import es.diz.mario.engine.PlayerMarioComponent;

public class PlayerPlay {

	public static void main(String[] args)
	    {
			String playerName = "jorge";
			
	    	JFrame frame = new JFrame("Mario Experience Showcase");
			PlayerMarioComponent mario = new PlayerMarioComponent(playerName);

	    	frame.setContentPane(mario);
	    	frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();

	        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        frame.setLocation((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2);

	        frame.setVisible(true);

	        mario.start();
	   
	}
	
}
