package es.diz.mario.scene;

import dk.itu.mario.engine.sprites.Mario;
import dk.itu.mario.scene.LoseScene;
import es.diz.mario.engine.PlayerMarioComponent;

public class PlayerLoseScene extends LoseScene {
	
	protected PlayerMarioComponent mario;

	public PlayerLoseScene(PlayerMarioComponent mario) {
		this.mario = mario;
	}

    private boolean wasDown = true;
    public void tick()
    {
        tick++;
        if (!wasDown && keys[Mario.KEY_JUMP])
        {
            mario.newGame();
        }
        if (keys[Mario.KEY_JUMP])
        {
            wasDown = false;
        }
    }
}
