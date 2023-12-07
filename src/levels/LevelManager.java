package levels;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import main.GamePanel;
import utilz.LoadSave;

public class LevelManager {
	private GamePanel gamePanel;
	private BufferedImage[] levelSprite;
	public final static int size = 64;
	private Level levelOne;
	public boolean gameMode = false;
	
	public LevelManager(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
		importOutsideSprites();
		levelOne = new Level(LoadSave.getLevelData(LoadSave.TEST_LEVEL_DATA));
	}
	
	private void importOutsideSprites() {
		BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.LEVEL_ATLAS);
		levelSprite = new BufferedImage[34];
		
		for(int i = 0; i < 2; i++) {
			for(int j = 0; j < 16; j++) {
				int index = j + i * 16;
				levelSprite[index] = img.getSubimage(j * 32, i * 32, 32, 32);
			}
			levelSprite[i + 32] = img.getSubimage(i * 32, 64, 32, 32);
		}
		
	}
	
	public void draw(Graphics g) {
		for(int i = 0; i < 16; i++) {			
			for(int j = 0; j < 18; j++) {
				int index = levelOne.getSpriteIndex(i, j);
				if (index >= 0) {
					if (!gameMode && index < 16) {						
						g.drawImage(levelSprite[index], (i * GamePanel.TILES_SIZE), (j * GamePanel.TILES_SIZE), size, size, null);
					}
					else if (gameMode && index >= 16) {
						g.drawImage(levelSprite[index], (i * GamePanel.TILES_SIZE), ((j - 9) * GamePanel.TILES_SIZE), size, size, null);
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
