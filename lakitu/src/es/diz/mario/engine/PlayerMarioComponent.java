package es.diz.mario.engine;

import dk.itu.mario.engine.MarioComponent;
import dk.itu.mario.engine.sprites.Mario;
import es.diz.mario.game.PlayerGame;
import es.diz.mario.scene.PlayerLevelSceneTest;
import es.diz.mario.scene.PlayerLoseScene;
import es.diz.mario.scene.PlayerWinScene;

public class PlayerMarioComponent extends MarioComponent {
	
	private static final long serialVersionUID = 1L;
	protected String playerName;
	protected PlayerGame game;

	public PlayerMarioComponent(PlayerGame game) {
		super(640, 480, false);
		this.game = game;
	}
	
	public void setPlayer(String playerName) {
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
        scene = new PlayerLoseScene(this);
        scene.setSound(sound);
        scene.init();
    }

    public void win() {
        scene = new PlayerWinScene(this);
        scene.setSound(sound);
        scene.init();
    }
    
    public void getRunning() {
    	running = true;
    }
    
    public void newGame() {
    	run();
    }

}
