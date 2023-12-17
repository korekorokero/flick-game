package gamestates;

import entities.Player;
import levels.LevelManager;
import main.Game;
import main.GameArea;
import static main.Game.GAME_HEIGHT;
import static main.Game.GAME_WIDTH;

import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

public class Playing extends State implements Statemethods {
	private GameArea area;
	private Player player;
	private LevelManager levelManager;
	private boolean switchMode = true;
	private boolean currentMode = false;
	
	public Playing(Game game) {
		super(game);
		initClasses();
	}
	
	private void initClasses() {
		levelManager = new LevelManager(game);
		area = new GameArea(0, 0, GAME_WIDTH, GAME_HEIGHT);
		player = new Player(0, 0, 32, 32, 4);
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
		player.update();
		levelManager.update();
	}

	@Override
	public void draw(Graphics g) {
		area.draw(g);
		player.draw(g);
		levelManager.draw(g);
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
