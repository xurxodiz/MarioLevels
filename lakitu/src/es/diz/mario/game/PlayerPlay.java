package es.diz.mario.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;


public class PlayerPlay {
	
	public static String playerName = "jorge";
	public static PlayerMarioComponent mario = null;
	public static JFrame frame = null;

	public static void main(String[] args) {
		reset();
	}
	
	public static void reset() {
		
		boolean reposition = false;
		
		if (null != mario)
			mario.stop();
		
		if (null == frame) {
			frame = new JFrame("Mario Experience Showcase");
			reposition = true;
		}
		
		mario = new PlayerMarioComponent(playerName);
		
    	frame.setContentPane(mario);
    	frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        
        if (reposition) {
        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        	frame.setLocation((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2);
        }
        
        frame.setVisible(true);
        
        mario.start();
        mario.requestFocusInWindow();
	}
	
}
