package main;

import java.awt.*;

import javax.swing.*;

import gamestates.Gamestate;
import gamestates.Menu;
import gamestates.Playing;
import inputs.KeyboardInputs;

public class Game extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int REFRESH_RATE = 120;
	
	private GamePanel gamePanel;
	private GameWindow gameWindow;
	private Playing playing;
	private Menu menu;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;
	public final static int TILES_IN_WIDTH = 16;
	public final static int TILES_IN_HEIGHT = 9;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public Game() {
		initClasses();
		gamePanel = new GamePanel(this);
		gameWindow = new GameWindow(gamePanel);
		gamePanel.setFocusable(true);
		startThread();
	}
	
	private void initClasses() {
		menu = new Menu(this);
		playing = new Playing(this);
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					update();
					gamePanel.repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					}
					catch (InterruptedException ex) { }
				}
			}
		};
		gameThread.start();
	}
	
	public void update() {
		switch(Gamestate.state) {
			case MENU:
				menu.update();
				break;
			case PLAYING:
				playing.update();
				break;
			default:
				break;
		}
	}
	
	public void render(Graphics g) {
		super.paintComponent(g);
		switch(Gamestate.state) {
			case MENU:
				menu.draw(g);
				break;
			case PLAYING:
				playing.draw(g);
				break;
			default:
				break;
		}
	}
	
	public Menu getMenu() {
		return menu;
	}
	
	public Playing getPlaying() {
		return playing;
	}
}
