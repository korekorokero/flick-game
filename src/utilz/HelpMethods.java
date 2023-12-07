package utilz;

import main.GamePanel;

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
		if (x < 0 || x >= GamePanel.GAME_WIDTH) {
			return true;
		}
		
		if (y < 0 || y >= GamePanel.GAME_HEIGHT) {
			return true;
		}
		
		float xIndex = x / GamePanel.TILES_SIZE;
		float yIndex;
		if (!gameMode) {			
			yIndex = y / GamePanel.TILES_SIZE;
		}
		else {
			yIndex = (y + GamePanel.GAME_HEIGHT) / GamePanel.TILES_SIZE;
		}
		
		int value = lvlData[(int)xIndex][(int)yIndex];
		
		if (value != -1) {
			return true;
		}
		
		return false;
	}
}
