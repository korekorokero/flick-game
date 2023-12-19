package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import main.GameArea;
import utilz.LoadSave;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {
	private GameArea area;
	private Player player;
	private LevelManager levelManager;
	private boolean switchMode = true;
	private boolean currentMode = false;
	private boolean gameOver = false;
	private boolean gameCompleted = false;
	
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
		player = new Player(LoadSave.startX * 96, LoadSave.startY * 96, Game.TILES_SIZE, Game.TILES_SIZE, Game.TILES_SIZE / 16, this);
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
		if(gameOver) {
			drawGameOverOverlay(g);
		}
		else if(gameCompleted) {
			drawGameCompletedOverlay(g);
		}
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
				if(!gameOver && !gameCompleted) {					
					player.upPressed = true;
				}
				break;
			case KeyEvent.VK_S:
				if(!gameOver && !gameCompleted) {					
					player.downPressed = true;
				}
				break;
			case KeyEvent.VK_A:
				if(!gameOver && !gameCompleted) {					
					player.leftPressed = true;
				}
				break;
			case KeyEvent.VK_D:
				if(!gameOver && !gameCompleted) {					
					player.rightPressed = true;
				}
				break;
			case KeyEvent.VK_SPACE:
				if(!gameOver && !gameCompleted) {					
					setCurrentMode(switchMode);
					player.gameMode = area.gameMode = levelManager.gameMode = currentMode;
				}
				break;
			case KeyEvent.VK_ENTER:
				if(gameOver) {					
					gameOver = false;
					initClasses();
				}
				break;
			case KeyEvent.VK_ESCAPE:
				if(gameOver || gameCompleted) {
					gameOver = gameCompleted = false;
					Gamestate.state = Gamestate.MENU;
					initClasses();
				}
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
	
	public void setGameOver(boolean gameOver) {
		this.gameOver = gameOver;
	}
	
	public void setGameCompleted(boolean gameCompleted) {
		this.gameCompleted = gameCompleted;
	}
	
	private void drawGameOverOverlay(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		g.setColor(Color.white);
		g.drawString("Game Over", Game.GAME_WIDTH / 2, 150);
		g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH / 2, 300);
		g.drawString("Press enter to try again!", Game.GAME_WIDTH / 2, 350);
	}
	
	private void drawGameCompletedOverlay(Graphics g) {
		g.setColor(new Color(0, 0, 0, 200));
		g.fillRect(0, 0, Game.GAME_WIDTH, Game.GAME_HEIGHT);

		g.setColor(Color.white);
		g.drawString("Game Completed", Game.GAME_WIDTH / 2, 150);
		g.drawString("Press esc to enter Main Menu!", Game.GAME_WIDTH / 2, 300);
	}
}
