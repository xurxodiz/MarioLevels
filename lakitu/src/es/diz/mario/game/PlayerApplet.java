package es.diz.mario.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class PlayerApplet extends JApplet {
	
	private static final long serialVersionUID = 1L;
	
	protected static PlayerApplet app;
	
	protected String playerName = "jorge";
	protected PlayerMarioComponent mario = null;
	protected boolean isApplet = false;
	protected JFrame frame = null;
	protected boolean reposition = false;
	
	public static void main(String[] args) {
		PlayerApplet app = new PlayerApplet();
		app.frame = new JFrame("Mario Experience Showcase");
		app.reposition = true;
		app.start();
	}
	
	public void stop() {
		if (null != mario)
			mario.stop();
	}
	
	public void destroy() {
		// ...
	}
	
	public void reset() {
		stop();
		start();
	}
	
	public void init() {
		isApplet = true;
		setSize(640, 480);
	}
	
	public void start() {
		
		mario = new PlayerMarioComponent(playerName, this);
		
		if (isApplet) {
	    	this.setContentPane(mario);
	    	
		} else {
			
	    	frame.setContentPane(mario);
	    	frame.setResizable(false);
	        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        frame.pack();
	        frame.setVisible(true);
	
	        if (reposition) {
	        	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	        	frame.setLocation((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2);
	        	reposition = false;
	        }
	        mario.requestFocusInWindow();
		}
        
        mario.start();
		
	}

	
}
