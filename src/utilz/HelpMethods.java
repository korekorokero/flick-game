package utilz;

import main.Game;

public class HelpMethods {
	public static boolean canMoveHere(float x, float y, int width, int height, int[][] lvlData, boolean gameMode) {
		width--;
		height--;
		if (!isSolid(x, y, lvlData, gameMode)) {
			if (!isSolid(x + width, y + height, lvlData, gameMode)) {
				if (!isSolid(x + width, y, lvlData, gameMode)) {
					if (!isSolid(x, y + height, lvlData, gameMode)) {
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private static boolean isSolid(float x, float y, int[][] lvlData, boolean gameMode) {
		int maxHW = lvlData[0].length * Game.TILES_SIZE / 2;
		if (x < 0 || x >= maxHW) {
			return true;
		}
		
		if (y < 0 || y >= maxHW) {
			return true;
		}
		
		float xIndex = x / Game.TILES_SIZE;
		float yIndex;
		if (!gameMode) {			
			yIndex = y / Game.TILES_SIZE;
		}
		else {
			yIndex = (y + maxHW) / Game.TILES_SIZE;
		}
		
		int value = lvlData[(int)xIndex][(int)yIndex];
		
		if (value >= 0 && value <= 63) {
			return true;
		}
		
		return false;
	}
}
