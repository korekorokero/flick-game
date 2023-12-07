package main;

import java.awt.Color;
import java.awt.Graphics;

public class GameArea {
	int minX;
	int maxX;
	int minY;
	int maxY;
	public boolean gameMode;
	
	private Color colorFilled;
	
	public GameArea(int x, int y, int width, int height) {
		this.minX = x;
		this.minY = y;
		this.maxX = x + width;
		this.maxY = y + height;
	}
	
	public void update() {
		changeMode();
	}
	
	public void changeMode() {
		if(gameMode) {
			colorFilled = Color.black;
		}
		else {
			colorFilled = Color.white;
		}
	}
	
	public void set(int x, int y, int width, int height) {
		minX = x;
		minY = y;
		maxX = x + width;
		maxY = y + height;
	}
	
	public void draw(Graphics g) {
		g.setColor(colorFilled);
		g.fillRect(minX, minY, maxX, maxY);
	}
}
