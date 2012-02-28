package es.diz.mario.scene;

import dk.itu.mario.engine.sprites.Mario;
import dk.itu.mario.scene.WinScene;
import es.diz.mario.engine.PlayerMarioComponent;

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
            mario.newGame();
        }
        if (keys[Mario.KEY_JUMP])
        {
            wasDown = false;
        }
    }
}
