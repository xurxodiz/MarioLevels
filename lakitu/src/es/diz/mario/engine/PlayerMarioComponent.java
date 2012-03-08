package es.diz.mario.engine;

import java.awt.Dimension;

import dk.itu.mario.engine.MarioComponent;
import dk.itu.mario.engine.sprites.Mario;
import es.diz.mario.game.PlayerGame;
import es.diz.mario.scene.EndScene;
import es.diz.mario.scene.PlayerLevelSceneTest;

public class PlayerMarioComponent extends MarioComponent {
	
	private static final long serialVersionUID = 1L;
	protected String playerName;
	protected PlayerGame game;

	public PlayerMarioComponent(PlayerGame game, String playerName) {
		super(640, 480, false);
		this.setPreferredSize(new Dimension(640, 480));
		this.game = game;
		this.playerName = playerName;
	}
	
    public void toRandomGame(){
    	toGame();
    }

    public void toCustomGame(){
    	toGame();
    }
    
    public void toGame() {
    	randomLevel = new PlayerLevelSceneTest(graphicsConfiguration, this, playerName);

    	Mario.fire = false;
    	Mario.large = false;
    	Mario.coins = 0;
    	Mario.lives = 3;

    	randomLevel.init();
    	randomLevel.setSound(sound);
    	scene = randomLevel;
   
    }
    
    public void lose() {
        end();
    }
    

    public void win() {
    	end();
    }
    
    protected void end() {
        scene = new EndScene();
        scene.setSound(sound);
        scene.init();
        game.levelSurvey(PlayerLevelSceneTest.recorder);
    }
    
    public void getRunning() {
    	running = true;
    }
    
    public void newGame() {
    	run();
    }

}
