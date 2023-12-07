package main;

import java.awt.*;

import javax.swing.*;

import entities.Player;
import inputs.KeyboardInputs;
import levels.LevelManager;

public class GamePanel extends JPanel {
	private static final long serialVersionUID = 1L;
	private static final int REFRESH_RATE = 120;
	private GameArea area;
	private Player player;
	private KeyboardInputs keyboard = new KeyboardInputs(this);
	private LevelManager levelManager;
	
	public final static int TILES_DEFAULT_SIZE = 32;
	public final static float SCALE = 2f;
	public final static int TILES_IN_WIDTH = 16;
	public final static int TILES_IN_HEIGHT = 9;
	public final static int TILES_SIZE = (int)(TILES_DEFAULT_SIZE * SCALE);
	public final static int GAME_WIDTH = TILES_SIZE * TILES_IN_WIDTH;
	public final static int GAME_HEIGHT = TILES_SIZE * TILES_IN_HEIGHT;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(GAME_WIDTH, GAME_HEIGHT));
		initClasses();
		
		this.addKeyListener(keyboard);
		this.setFocusable(true);
		
		startThread();
	}
	
	private void initClasses() {
		levelManager = new LevelManager(this);
		area = new GameArea(0, 0, GAME_WIDTH, GAME_HEIGHT);
		player = new Player(0, 0, 32, 32, 4);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
	}
	
	public void startThread() {
		Thread gameThread = new Thread() {
			public void run() {
				while (true) {
					player.update();
					area.update();
					levelManager.update();
					repaint();
					try {
						Thread.sleep(1000 / REFRESH_RATE);
					}
					catch (InterruptedException ex) { }
				}
			}
		};
		gameThread.start();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		area.draw(g);
		levelManager.draw(g);
		player.draw(g);
	}
	
	public Player getPlayer() {
		return player;
	}
	
	public GameArea getGameArea() {
		return area;
	}
	
	public LevelManager getLevelManager() {
		return levelManager;
	}
}
