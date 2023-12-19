package entities;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import gamestates.Playing;
import main.Game;
import utilz.LoadSave;
import static utilz.HelpMethods.canMoveHere;;

public class Player extends Entity {
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	private BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
	private int multiplier;
	private int[][] lvlData;
	private Playing playing;
	private Rectangle goalBox;
	
	public Player(float x, float y, int width, int height, float speed, Playing playing) {
		super(x, y, width, height, speed);
		multiplier = 0;
		this.playing = playing;
		goalBox = new Rectangle(LoadSave.endX * 96, LoadSave.endY * 96, Game.TILES_SIZE, Game.TILES_SIZE);
	}
	
	public void update() {
		inWall();
		if(isDead) {
			return;
		}
		updatePos();
		updateHitbox();
		goalChecker();
	}

	public void updatePos() {
		if (!leftPressed && !rightPressed && !upPressed && !downPressed) {
			return;
		}
		
		float xSpeed = 0, ySpeed = 0;
		
		if (rightPressed && !leftPressed) {
			xSpeed = speed;
			multiplier = 2;
		}
		else if (leftPressed && !rightPressed) {
			xSpeed = -speed;
			multiplier = 3;
		}
		else if (upPressed && !downPressed) {
			ySpeed = -speed;
			multiplier = 0;
		}
		else if (downPressed && !upPressed) {
			ySpeed = speed;
			multiplier = 1;
		}
		
		if(canMoveHere(x + xSpeed, y + ySpeed, finalWidth, finalHeight, lvlData, gameMode)) {
			x += xSpeed;
			y += ySpeed;
		}
	}
	
	public void draw(Graphics g, int xLvlOffset, int yLvlOffset) {
		int change;
		if (gameMode) {
			change = 32;
		}
		else {
			change = 0;
		}
		
		BufferedImage subImg = img.getSubimage(0 + 32 * multiplier, change, 32, 32);
		
		g.drawImage(subImg, (int)x - xLvlOffset, (int)y - yLvlOffset, finalWidth, finalHeight, null);
//		drawHitbox(g);
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	private void inWall() {
		if(!canMoveHere(x, y, finalWidth, finalHeight, lvlData, gameMode) && !isDead) {
			isDead = true;
			playing.setGameOver(true);
		}
	}
	
	private void goalChecker() {
		if(!isDead && (x >= goalBox.getMinX() && x <= goalBox.getMaxX()) && (y >= goalBox.getMinY() && y <= goalBox.getMaxY()) && gameMode){
			playing.setGameCompleted(true);
		}
	}
}
