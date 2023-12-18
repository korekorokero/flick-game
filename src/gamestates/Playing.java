package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import main.GameArea;
import utilz.LoadSave;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {
	private GameArea area;
	private Player player;
	private LevelManager levelManager;
	private boolean switchMode = true;
	private boolean currentMode = false;
	
	private int xLvlOffset, yLvlOffset;
	private int leftBorder = (int)(0.25 * Game.GAME_WIDTH);
	private int rightBorder = (int)(0.75 * Game.GAME_WIDTH) - Game.TILES_SIZE;
	private int upBorder = (int)(0.25 * Game.GAME_HEIGHT);
	private int downBorder = (int)(0.75 * Game.GAME_HEIGHT) - Game.TILES_SIZE;
	private int lvlTiles = LoadSave.getLevelData(LoadSave.TEST_LEVEL_DATA)[0].length / 2;
	private int maxTilesOffsetX = lvlTiles - Game.TILES_IN_WIDTH;
	private int maxTilesOffsetY = lvlTiles - Game.TILES_IN_HEIGHT;
	private int maxLvlOffsetX = maxTilesOffsetX * Game.TILES_SIZE + (int)(Game.GAME_WIDTH * (Game.SCALE - 1));
	private int maxLvlOffsetY = maxTilesOffsetY * Game.TILES_SIZE + (int)((Game.GAME_WIDTH + 128) * (Game.SCALE - 1)) / 2;
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		levelManager = new LevelManager(game);
		area = new GameArea(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);
		player = new Player(96, 96, Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE / 16);
		player.loadLvlData(levelManager.getCurrentLevel().getLevelData());
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

	@Override
	public void update() {
		area.update();
		levelManager.update();
		player.update();
		checkCloseToBorder();
	}
	
	private void checkCloseToBorder() {
		int playerX = player.getHitbox().x;
		int playerY = player.getHitbox().y;
		int diffX = playerX - xLvlOffset;
		int diffY = playerY - yLvlOffset;
		
		if(diffX > rightBorder) {
			xLvlOffset += diffX - rightBorder;
		}
		else if(diffX < leftBorder) {
			xLvlOffset += diffX - leftBorder;
		}
		
		if(diffY < upBorder) {
			yLvlOffset += diffY - upBorder;
		}
		else if(diffY > downBorder) {
			yLvlOffset += diffY - downBorder;
		}
		
		if(xLvlOffset > maxLvlOffsetX) {
			xLvlOffset = maxLvlOffsetX;
		}
		else if(xLvlOffset < 0) {
			xLvlOffset = 0;
		}
		
		if(yLvlOffset > maxLvlOffsetY) {
			yLvlOffset = maxLvlOffsetY;
		}
		else if(yLvlOffset < 0) {
			yLvlOffset = 0;
		}
	}

	@Override
	public void draw(Graphics g) {
		area.draw(g);
		levelManager.draw(g, xLvlOffset, yLvlOffset);
		player.draw(g, xLvlOffset, yLvlOffset);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		switch(code) {
			case KeyEvent.VK_W:
				player.upPressed = true;
				break;
			case KeyEvent.VK_S:
				player.downPressed = true;
				break;
			case KeyEvent.VK_A:
				player.leftPressed = true;
				break;
			case KeyEvent.VK_D:
				player.rightPressed = true;
				break;
			case KeyEvent.VK_SPACE:
				setCurrentMode(switchMode);
				player.gameMode = area.gameMode = levelManager.gameMode = currentMode;
				break;
			case KeyEvent.VK_BACK_SPACE:
				Gamestate.state = Gamestate.MENU;
				break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			player.upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			player.downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			player.leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			player.rightPressed = false;
		}
		if(code == KeyEvent.VK_SPACE) {
			switchMode = !switchMode;
		}
	}

	private void setCurrentMode(boolean currentMode) {
		this.currentMode = currentMode;
	}
}
