package utilz;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

public class LoadSave {
	public static final String PLAYER_ATLAS = "player.png";
	public static final String LEVEL_ATLAS = "level.png";
	public static final String LEVEL_ONE_DATA = "level1.png";
	public static final String TEST_LEVEL_DATA = "test.png";
	public static final String MENU_ATLAS = "menu.png";
	public static final String GAME_OVER_LAYOUT = "game_over.png";
	public static final String GAME_COMPLETED_LAYOUT = "game_completed.png";
	
	public static int startX, startY, endX, endY;
	
	public static BufferedImage getSpriteAtlas(String filename) {
		BufferedImage img = null;
		InputStream is = LoadSave.class.getResourceAsStream("/" + filename);
		
		try {
			img = ImageIO.read(is);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return img;
	}
	
	public static int[][] getLevelData(String level) {
		BufferedImage img = getSpriteAtlas(level);
		int[][] lvlData = new int[img.getWidth()][img.getHeight()];
		
		for(int i = 0; i < img.getWidth(); i++) {
			for(int j = 0; j < img.getHeight(); j++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if(value >= 66) {
					value = -1;
				}
				lvlData[i][j] = value;
				
				if(value == 64) {
					startX = i;
					startY = j;
				}
				else if(value == 65) {
					endX = i;
					endY = j - 20;
				}
			}
		}
		return lvlData;
	}
}