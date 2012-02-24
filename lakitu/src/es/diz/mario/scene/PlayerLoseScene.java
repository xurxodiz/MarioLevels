package es.diz.mario.scene;

import dk.itu.mario.engine.sprites.Mario;
import dk.itu.mario.scene.LoseScene;
import es.diz.mario.game.PlayerMarioComponent;

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
            mario.reset();
        }
        if (keys[Mario.KEY_JUMP])
        {
            wasDown = false;
        }
    }
}
