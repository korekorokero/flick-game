package inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import gamestates.Gamestate;
import main.GamePanel;

public class MouseInputs implements MouseListener {
	private GamePanel gamePanel;
	
	public MouseInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseClicked(e);
			break;
		case PLAYING:
			break;
		default:
			break;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mousePressed(e);
			break;
		case PLAYING:
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		switch(Gamestate.state) {
		case MENU:
			gamePanel.getGame().getMenu().mouseReleased(e);
			break;
		case PLAYING:
			break;
		default:
			break;
		}
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
