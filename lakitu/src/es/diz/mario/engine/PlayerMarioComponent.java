package es.diz.mario.engine;

import dk.itu.mario.engine.MarioComponent;
import dk.itu.mario.engine.sprites.Mario;
import es.diz.mario.scene.PlayerLevelSceneTest;

public class PlayerMarioComponent extends MarioComponent {
	
	private static final long serialVersionUID = 1L;
	protected String playerName;

	public PlayerMarioComponent(String playerName) {
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

}
