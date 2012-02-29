package es.diz.mario.game;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.RootPaneContainer;
import javax.swing.Timer;

import es.diz.mario.engine.PlayerMarioComponent;

public class PlayerGame {

	protected String playerName;
	protected PlayerMarioComponent mario = null;
	protected RootPaneContainer app = null;
	protected PlayerLogin login;
	
	public PlayerGame(RootPaneContainer app) {
		this.app = app;
		login = new PlayerLogin(this);
		app.setContentPane(login);
	}
	
	public void setName(String playerName) {

		this.playerName = playerName;
		
		// kill it with fire
		login.setEnabled(false);
		login.setFocusable(false);
		login.setVisible(false);
		login = null;
		
		mario = new PlayerMarioComponent(this);
		mario.setPlayer(playerName);
		app.setContentPane(mario);
		setFocusTimer(app.getContentPane());
		mario.start();
	}
	
	public void start() {
		// ...
	}
	
	public void setFocusTimer(final Container container) {
		Timer timer = new Timer (250, new ActionListener () 
    	{ 
    	    public void actionPerformed(ActionEvent e) 
    	    { 
    	    	container.requestFocusInWindow();
    	     } 
    	}); 
    	timer.start();
	}

}
