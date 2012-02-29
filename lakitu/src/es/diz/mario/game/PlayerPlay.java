package es.diz.mario.game;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JApplet;
import javax.swing.JFrame;

public class PlayerPlay extends JApplet {
	
	private static final long serialVersionUID = 1L;
	
	protected PlayerGame game;
	
	protected boolean isApplet = false;
	protected JFrame frame = new JFrame("Mario Experience Showcase");
	protected boolean reposition = false;
	
	public PlayerPlay() {
		// ...
	}
	
	public static void main(String[] args) {
		new PlayerPlay().start();
	}
	
	public void stop() {
		// ...
	}
	
	public void destroy() {
		// ...
	}
	
	public void init() {
		isApplet = true;
	}
	
	public void start() {
		if (isApplet) {
			setSize(640, 480);
			game = new PlayerGame(this);
		} else {
			frame.setSize(640, 480);
			game = new PlayerGame(frame);
			frameInit();
		}
        game.start();
	}
	
	private void frameInit() {
    	frame.setResizable(false);
    	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    	frame.setVisible(true);
    	Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    	frame.setLocation((screenSize.width-frame.getWidth())/2, (screenSize.height-frame.getHeight())/2);

	}
	
}
