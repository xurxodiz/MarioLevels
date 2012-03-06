package es.diz.mario.game;

import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.RootPaneContainer;
import javax.swing.Timer;

import dk.itu.mario.engine.DataRecorder;

import es.diz.mario.engine.PlayerMarioComponent;

public class PlayerGame {

	protected String playerName;
	protected PlayerMarioComponent mario = null;
	protected RootPaneContainer app = null;
	protected PlayerLogin login;
	protected PlayerSurvey survey;
	
	public PlayerGame(RootPaneContainer app) {
		this.app = app;
		login = new PlayerLogin(this);
		app.setContentPane(login);
		login.show();
	}
	
	public void setName(String playerName) {

		this.playerName = playerName;
		
		killPanel(login);
		
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
	
	public void levelSurvey(DataRecorder recorder) {
		mario.stop();
		survey = new PlayerSurvey(recorder, this);
		app.setContentPane(survey);
		app.getContentPane().repaint();
		setFocusTimer(app.getContentPane());
	}
	
	public void continuePlaying() {
		
		killPanel(survey);
		
		app.setContentPane(mario);
		setFocusTimer(app.getContentPane());
		mario.newGame();
	}
	
	protected void killPanel(JPanel panel) {
		// kill it with fire
		panel.setEnabled(false);
		panel.setFocusable(false);
		panel.setVisible(false);
		panel = null;
	}
	
	

}
