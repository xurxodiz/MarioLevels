package es.diz.mario.game;

import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.RootPaneContainer;
import javax.swing.Timer;

import dk.itu.mario.engine.DataRecorder;

import es.diz.mario.data.Survey;
import es.diz.mario.data.Login;
import es.diz.mario.engine.PlayerMarioComponent;

public class PlayerGame {

	protected String playerName;
	protected PlayerMarioComponent mario = null;
	protected RootPaneContainer app = null;
	protected Login login;
	protected Survey survey;
	
	public PlayerGame(RootPaneContainer app) {
		this.app = app;
		login = new Login(this);
		app.setContentPane(login);
		login.setDefaults();
		setFocusTimer(app.getContentPane());
	}
	
	public void setName(String playerName) {

		this.playerName = playerName;
		
		killComponent(login);
		
		startNewMario();
	}
	
	public void setFocusTimer(final Container container) {
		Timer timer = new Timer (250, new ActionListener () 
    	{ 
    	    public void actionPerformed(ActionEvent e) 
    	    { 
    	    	container.requestFocusInWindow();
    	     } 
    	}); 
		timer.setRepeats(false);
    	timer.start();
	}
	
	public void levelSurvey(DataRecorder recorder) {
		
		mario.stop();
		killComponent(mario);
		
		survey = new Survey(recorder, this);
		app.setContentPane(survey);
		survey.setDefaults();
		setFocusTimer(app.getContentPane());
	}
	
	public void continuePlaying() {
		
		killComponent(survey);
		
		startNewMario();
	}
	
	protected void killComponent(Component panel) {
		// kill it with fire
		panel.setEnabled(false);
		panel.setFocusable(false);
		panel.setVisible(false);
		panel = null;
	}
	
	protected void startNewMario() {
		mario = new PlayerMarioComponent(this, playerName);
		app.setContentPane(mario);
		setFocusTimer(app.getContentPane());
		
		mario.start();
	}
	
	

}
