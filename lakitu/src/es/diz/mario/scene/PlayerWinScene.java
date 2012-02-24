package es.diz.mario.scene;

import dk.itu.mario.engine.sprites.Mario;
import dk.itu.mario.scene.WinScene;
import es.diz.mario.game.PlayerMarioComponent;

public class PlayerWinScene extends WinScene {
	
	protected PlayerMarioComponent mario;

	public PlayerWinScene(PlayerMarioComponent mario) {
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
