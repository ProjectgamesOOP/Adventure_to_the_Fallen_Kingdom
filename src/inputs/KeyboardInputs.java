package inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import gamestates.Gamestate;
import main.Game;
import main.GamePanel;


public class KeyboardInputs implements KeyListener {

	private GamePanel gamePanel;

	public KeyboardInputs(GamePanel gamePanel) {
		this.gamePanel = gamePanel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().keyReleased(e);
			case PLAYING:
				gamePanel.getGame().getPlaying().keyReleased(e);
			default:
				break;
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		switch (Gamestate.state) {
			case MENU:
				gamePanel.getGame().getMenu().keyPressed(e);
			case PLAYING:
				gamePanel.getGame().getPlaying().keyPressed(e);
				break;
			case OPTIONS:
				gamePanel.getGame().getGameOptions().keyPressed(e);
			default:
				break;
		}

	}

}