package jorgedizpico;


import java.util.ArrayList;

import dk.itu.mario.MarioInterface.LevelInterface;
import dk.itu.mario.level.Level;

public class LakituLevel extends Level implements LevelInterface {
	
	protected int[] ground;
	public int type = Level.TYPE_OVERGROUND; // default
   
    public LakituLevel() {
    	super(320, 15);
    	ground = new int[320];
    }
    
    public void setType(int type) {
    	if (type < 0) type = 0;
    	if (type > 2) type = 2;
    	this.type = type;
}
    
    public int capX(int x) {
    	if (x < 0) return 0;
    	if (x >= width) return width-1;
    	return x;
    }
    
    public void setExit(int x) {
    	this.xExit = x;
    	this.yExit = ground[x];
    }
    
    public void setGroundHeight(int x, int y) {
    	x = capX(x);
    	for (int i = y; i < height; i++)
    		setBlock (x, i, Level.GROUND);
    	ground[x] = y;
    }
    
    public int getGroundHeight(int x) {
        return ground[capX(x)];
    }
    
    public ArrayList<int[]> getFlatlands() {
    	ArrayList<int[]> flats = new ArrayList<int[]>();
    	int x, y;
    	int xx = 0, yy = getGroundHeight(xx);
    	
    	for (x = 0; x < width; x++) {
    		y = getGroundHeight(x);
    		if (y != yy) {
    			flats.add(new int[]{xx, x-xx});
    			xx = x;
    			yy = y;
    		}
    	}
    	return flats;
    }
    
    public ArrayList<int[]> getFlatlandsFiltered() {
		ArrayList<int[]> flats = getFlatlands();
		// remove first and last flatlands
		// (usually start/end platforms)
		flats.remove(flats.size()-1);
		flats.remove(0);
		// remove gaps
		for (int[] coord : flats)
			if (height == coord[1])
				flats.remove(coord);
		return flats;
    }
    
    public void addFlatLand(int x, int length, int y) {
    	for (int i = 0; i < length; i++)
    		setGroundHeight(x+i, y);
    }
    
    public void addGap(int x, int length) {
    	for (int i = 0; i < length; i++)
    		setGroundHeight(x+i, height);
    }

    public void fixWalls() {
	    boolean[][] blockMap = new boolean[width + 1][height + 1];
	
	    for (int x = 0; x < width + 1; x++) {
	        for (int y = 0; y < height + 1; y++) {
	            int blocks = 0;
	            for (int xx = x - 1; xx < x + 1; xx++) {
	                for (int yy = y - 1; yy < y + 1; yy++) {
	                    if (getBlockCapped(xx, yy) == GROUND) {
	                        blocks++;
	                    }
	                }
	            }
	            blockMap[x][y] = blocks == 4;
	        }
	    }
	    blockify(this, blockMap, width + 1, height + 1);
	}

	protected void blockify(Level level, boolean[][] blocks, int width,
	                      int height) {
	    int to = 0;
	    if (type == LevelInterface.TYPE_CASTLE) {
	        to = 4 * 2;
	    } else if (type == LevelInterface.TYPE_UNDERGROUND) {
	        to = 4 * 3;
	    }
	
	    boolean[][] b = new boolean[2][2];
	
	    for (int x = 0; x < width; x++) {
	        for (int y = 0; y < height; y++) {
	            for (int xx = x; xx <= x + 1; xx++) {
	                for (int yy = y; yy <= y + 1; yy++) {
	                    int _xx = xx;
	                    int _yy = yy;
	                    if (_xx < 0) _xx = 0;
	                    if (_yy < 0) _yy = 0;
	                    if (_xx > width - 1) _xx = width - 1;
	                    if (_yy > height - 1) _yy = height - 1;
	                    b[xx - x][yy - y] = blocks[_xx][_yy];
	                }
	            }
	
	            if (b[0][0] == b[1][0] && b[0][1] == b[1][1]) {
	                if (b[0][0] == b[0][1]) {
	                    if (b[0][0]) {
	                        level.setBlock(x, y, (byte) (1 + 9 * 16 + to));
	                    } else {
	                        // KEEP OLD BLOCK!
	                    }
	                } else {
	                    if (b[0][0]) {
	                        //down grass top?
	                        level.setBlock(x, y, (byte) (1 + 10 * 16 + to));
	                    } else {
	                        //up grass top
	                        level.setBlock(x, y, (byte) (1 + 8 * 16 + to));
	                    }
	                }
	            } else if (b[0][0] == b[0][1] && b[1][0] == b[1][1]) {
	                if (b[0][0]) {
	                    //right grass top
	                    level.setBlock(x, y, (byte) (2 + 9 * 16 + to));
	                } else {
	                    //left grass top
	                    level.setBlock(x, y, (byte) (0 + 9 * 16 + to));
	                }
	            } else if (b[0][0] == b[1][1] && b[0][1] == b[1][0]) {
	                level.setBlock(x, y, (byte) (1 + 9 * 16 + to));
	            } else if (b[0][0] == b[1][0]) {
	                if (b[0][0]) {
	                    if (b[0][1]) {
	                        level.setBlock(x, y, (byte) (3 + 10 * 16 + to));
	                    } else {
	                        level.setBlock(x, y, (byte) (3 + 11 * 16 + to));
	                    }
	                } else {
	                    if (b[0][1]) {
	                        //right up grass top
	                        level.setBlock(x, y, (byte) (2 + 8 * 16 + to));
	                    } else {
	                        //left up grass top
	                        level.setBlock(x, y, (byte) (0 + 8 * 16 + to));
	                    }
	                }
	            } else if (b[0][1] == b[1][1]) {
	                if (b[0][1]) {
	                    if (b[0][0]) {
	                        //left pocket grass
	                        level.setBlock(x, y, (byte) (3 + 9 * 16 + to));
	                    } else {
	                        //right pocket grass
	                        level.setBlock(x, y, (byte) (3 + 8 * 16 + to));
	                    }
	                } else {
	                    if (b[0][0]) {
	                        level.setBlock(x, y, (byte) (2 + 10 * 16 + to));
	                    } else {
	                        level.setBlock(x, y, (byte) (0 + 10 * 16 + to));
	                    }
	                }
	            } else {
	                level.setBlock(x, y, (byte) (0 + 1 * 16 + to));
	            }
	        }
	    }
	}
}