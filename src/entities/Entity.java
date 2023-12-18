package entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Entity {
	protected float x, y, speed;
	protected int width, height, finalWidth, finalHeight;
	public boolean gameMode = false;
	public final static int sizeMultiplier = 1;
	protected Rectangle hitbox;
	
	public Entity(float x, float y, int width, int height, float speed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = speed;
		this.finalWidth = width * sizeMultiplier;
		this.finalHeight = height * sizeMultiplier;
		initHitbox();
	}
	
	protected void drawHitbox(Graphics g) {
		g.setColor(Color.pink);
		g.drawRect(hitbox.x, hitbox.y, hitbox.width, hitbox.height);
	}
	
	private void initHitbox() {
		hitbox = new Rectangle((int)x, (int)y, finalWidth, finalHeight);
	}
	
	protected void updateHitbox() {
		hitbox.x = (int)x;
		hitbox.y = (int)y;
	}
	
	public Rectangle getHitbox() {
		return hitbox;
	}
}
