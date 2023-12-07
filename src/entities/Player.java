package entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import utilz.LoadSave;
import static utilz.HelpMethods.canMoveHere;;

public class Player extends Entity {
	public boolean upPressed, downPressed, leftPressed, rightPressed;
	private BufferedImage img = LoadSave.getSpriteAtlas(LoadSave.PLAYER_ATLAS);
	private int multiplier;
	private int[][] lvlData;
	
	public Player(float x, float y, int width, int height, float speed) {
		super(x, y, width, height, speed);
		multiplier = 0;
	}
	
	public void update() {
		updatePos();
		updateHitbox();
		inWall();
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
	
	public void draw(Graphics g) {
		int change;
		if (gameMode) {
			change = 32;
		}
		else {
			change = 0;
		}
		
		BufferedImage subImg = img.getSubimage(0 + 32 * multiplier, change, width, height);
		
		g.drawImage(subImg, (int)x, (int)y, finalWidth, finalHeight, null);
		drawHitbox(g);
//		System.out.println((x + 63) + " " + (y + 63));
	}
	
	public void loadLvlData(int[][] lvlData) {
		this.lvlData = lvlData;
	}
	
	private void inWall() {
		if(!canMoveHere(x, y, finalWidth, finalHeight, lvlData, gameMode)) {
			x = 0;
			y = 0;
		}
	}
}
