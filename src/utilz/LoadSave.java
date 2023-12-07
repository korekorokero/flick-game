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
		int[][] lvlData = new int[16][18];
		BufferedImage img = getSpriteAtlas(level);
		
		for(int i = 0; i < 16; i++) {
			for(int j = 0; j < 18; j++) {
				Color color = new Color(img.getRGB(i, j));
				int value = color.getRed();
				if(value >= 34) {
					value = -1;
				}
				lvlData[i][j] = value;
			}
		}
		return lvlData;
	}
}