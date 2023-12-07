package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import main.GamePanel;

public class KeyboardInputs implements KeyListener {
	private GamePanel gamePanel;
	private boolean switchMode = true;
	private boolean currentMode = false;
	
	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			gamePanel.getPlayer().upPressed = true;
		}
		if(code == KeyEvent.VK_S) {
			gamePanel.getPlayer().downPressed = true;
		}
		if(code == KeyEvent.VK_A) {
			gamePanel.getPlayer().leftPressed = true;
		}
		if(code == KeyEvent.VK_D) {
			gamePanel.getPlayer().rightPressed = true;
		}
		if(code == KeyEvent.VK_SPACE) {
			setCurrentMode(switchMode);
			gamePanel.getPlayer().gameMode = gamePanel.getGameArea().gameMode = gamePanel.getLevelManager().gameMode = currentMode;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		int code = e.getKeyCode();
		
		if(code == KeyEvent.VK_W) {
			gamePanel.getPlayer().upPressed = false;
		}
		if(code == KeyEvent.VK_S) {
			gamePanel.getPlayer().downPressed = false;
		}
		if(code == KeyEvent.VK_A) {
			gamePanel.getPlayer().leftPressed = false;
		}
		if(code == KeyEvent.VK_D) {
			gamePanel.getPlayer().rightPressed = false;
		}
		if(code == KeyEvent.VK_SPACE) {
			switchMode = !switchMode;
		}
	}

	public boolean isCurrentMode() {
		return currentMode;
	}

	public void setCurrentMode(boolean currentMode) {
		this.currentMode = currentMode;
	}
}
