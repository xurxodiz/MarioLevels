package jorgedizpico.level;

public class BuilderFlags {

	protected static final int I_START_PLATFORM = 10;
	protected static final int I_END_PLATFORM = 10;
	protected static final int I_EXIT_OFFSET = 5; // offset always smaller than end platform
	
	protected static final int I_HEIGHT_MARGIN = 2;
	protected static final int I_HEIGHT_MIN = 2;
	
	protected static final int I_LEN = 2;
	
	protected static final int I_GROUND = 2;
	
	/*protected static final int I_JUMP_OFFSET = -5;
	protected static final int I_JUMP_RANGE = 10;*/
	
	protected static final int I_HOVER_HEIGHT = 4;
	protected static final int I_PIPE_HEIGHT = 2;
	protected static final int I_CANNON_HEIGHT = 2;
	
	public static final int GROUND_UP = 0;
	public static final int GROUND_DOWN = 1;
	
	public static final int STAIRS_UP = 0;
	public static final int STAIRS_DOWN = 1;
	public static final int STAIRS_UPUP = 2;
	public static final int STAIRS_DOWNDOWN = 3;
	
	public static final int ENEMY_GOOMBA = 0;	
	public static final int ENEMY_REDTURTLE = 1;	
	public static final int ENEMY_GREENTURTLE = 2;	
	public static final int ENEMY_SPIKY = 3;	
	
	public static final int ENEMY_GOOMBA_WINGED = 4;	
	public static final int ENEMY_REDTURTLE_WINGED = 5;	
	public static final int ENEMY_GREENTURTLE_WINGED = 6;	
	public static final int ENEMY_SPIKY_WINGED = 7;

	public static final int NOPIRANHA = 0;
	public static final int PIRANHA = 1;
	
	public static final int BLOCK_EMPTY = 0;
	public static final int BLOCK_COIN = 1;
	public static final int BLOCK_POWERUP = 2;
	
}
