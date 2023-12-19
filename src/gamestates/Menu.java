package gamestates;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

import main.Game;
import utilz.LoadSave;

public class Menu extends State implements Statemethods {
	private BufferedImage img;
	private boolean mouseHold = false;
	
	public Menu(Game game) {
		super(game);
		img = LoadSave.getSpriteAtlas(LoadSave.MENU_ATLAS);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		g.drawImage(img.getSubimage(0, 32, 64, 32), 384, 356, 256, 128, null);
		g.drawImage(img.getSubimage(0, 0, 64, 32), 256, 48, 512, 256, null);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if((e.getX() >= 384 && e.getX() <= 639) && (e.getY() >= 356 && e.getY() <= 483)) {
			playGame();
		}
		System.out.println(e.getX() + " " + e.getY());
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if((e.getX() >= 384 && e.getX() <= 639) && (e.getY() >= 356 && e.getY() <= 483)) {
			mouseHold = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(mouseHold && (e.getX() >= 384 && e.getX() <= 639) && (e.getY() >= 356 && e.getY() <= 483)) {
			playGame();
			mouseHold = false;
		}
		else {
			mouseHold = false;
		}
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void playGame() {
		Gamestate.state = Gamestate.PLAYING;
	}
}
