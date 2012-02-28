package es.diz.mario.game;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JComponent;
import javax.swing.RootPaneContainer;
import javax.swing.Timer;

import es.diz.mario.engine.PlayerMarioComponent;

public class PlayerGame {

	protected String playerName = "jorge";
	protected PlayerMarioComponent mario = null;
	protected RootPaneContainer app = null;
	
	public PlayerGame(RootPaneContainer app) {
		this.app = app;
		newComponent();
	}
	
	public void newComponent() {
		app.getContentPane().removeAll();
		app.getContentPane().repaint();
		mario = new PlayerMarioComponent(this);
		app.getContentPane().add(mario);
		app.getContentPane().repaint();
    	mario.addFocusListener(mario);
    	mario.addMouseListener(mario);
    	mario.addKeyListener(mario);

    	mario.setFocusable(true);
    	mario.setEnabled(true);
	}
	
	public void newGame() {
		//newComponent();
		System.out.println("her we are");
		app.getContentPane().remove(mario);
		app.getContentPane().repaint();
		app.getContentPane().list();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mario = new PlayerMarioComponent(this);
		app.getContentPane().add(mario);
		app.getContentPane().repaint();
		mario.start();
	}
	
	public void start() {
		//if (playerName.equals("")) askPlayerLogin();
		mario.setPlayer(playerName);
		Timer timer = new Timer (250, new ActionListener () 
    	{ 
    	    public void actionPerformed(ActionEvent e) 
    	    { 
    	    	mario.requestFocusInWindow();
    	     } 
    	}); 
    	timer.start();
		mario.start();
	}
	
	public void setFocusTimer(final JComponent comp) {
		Timer timer = new Timer (250, new ActionListener () 
    	{ 
    	    public void actionPerformed(ActionEvent e) 
    	    { 
    	    	comp.requestFocusInWindow();
    	     } 
    	}); 
    	timer.start();
	}

}
