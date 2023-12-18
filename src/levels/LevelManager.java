package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class LevelManager {
	private Game game;
	private BufferedImage[] levelSprite;
	public int size = Game.TILES_SIZE;
	private Level levelOne;
	public boolean gameMode = false;
	
	public LevelManager(Game game) {
		this.game = game;
		importOutsideSprites();
		levelOne = new Level(LoadSave.getLevelData(LoadSave.TEST_LEVEL_DATA));
	}
	
	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[66];
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 32; j++) {
				int index = j + i * 32;
				if(i == 0 && index >= 12) {
					levelSprite[index] = img.getSubimage((j - 12) * 32, 2 * 32, 32, 32);
				}
				else if(i == 1 && index >= 44) {
					levelSprite[index] = img.getSubimage((j - 12) * 32, 3 * 32, 32, 32);
				}
				else {					
					levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);
				}
			}
			levelSprite[i + 64] = img.getSubimage(12 * 32, i * 32, 32, 32);
		}
		
	}
	
	public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
		for(int i = 0; i < 20; i++) {			
			for(int j = 0; j < 40; j++) {
				int index = levelOne.getSpriteIndex(i, j);
				if (index >= 0) {
					if (!gameMode && (index < 32 || index == 64)) {						
						g.drawImage(levelSprite[index], (i * size) - xLvlOffset, (j * size) - yLvlOffset, size, size, null);
					}
					else if (gameMode && (index >= 32 || index == 65)) {
						g.drawImage(levelSprite[index], (i * size) - xLvlOffset, ((j - 20) * size) - yLvlOffset, size, size, null);
					}
				}
			}
		}
	}
	
	public void update() {
		
	}
	
	public Level getCurrentLevel() {
		return levelOne;
	}
}
