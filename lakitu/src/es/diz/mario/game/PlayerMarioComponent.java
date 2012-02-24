package es.diz.mario.game;

import dk.itu.mario.engine.MarioComponent;
import dk.itu.mario.engine.sprites.Mario;
import dk.itu.mario.scene.LoseScene;
import es.diz.mario.scene.PlayerLevelSceneTest;
import es.diz.mario.scene.PlayerWinScene;

public class PlayerMarioComponent extends MarioComponent {
	
	private static final long serialVersionUID = 1L;
	protected String playerName;
	protected PlayerApplet app;
	protected Thread thread;

	public PlayerMarioComponent(String playerName, PlayerApplet app) {
		super(640, 480, false);
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
    
    public void reset() {
        app.reset();
    }
    
    public void lose(){
        scene = new LoseScene();
        scene.setSound(sound);
        scene.init();
    }

    public void win(){
        scene = new PlayerWinScene(this);
        scene.setSound(sound);
        scene.init();
    }

    
    
    

}
