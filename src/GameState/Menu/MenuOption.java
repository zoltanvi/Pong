package GameState.Menu;

import Game.Drawable;
import Game.GameManager;
import GameState.GameStateManager;
import GameState.MenuState;

import java.awt.*;
import java.awt.event.KeyEvent;

public abstract class MenuOption implements Drawable {

	protected String[] options;
	protected int currentChoice = 0;
	protected Font font;
	protected Color color;
	protected GameStateManager gsm;
	protected MenuState menuState;
	protected GameManager gm;

	public MenuOption(String[] options, Font font, Color color, GameStateManager gsm, MenuState menuState) {
		this.options = options;
		this.font = font;
		this.color = color;
		this.gsm = gsm;
		this.menuState = menuState;
		this.gm = GameManager.getInstance();
	}

	public abstract void awake();

	protected abstract void select();


	public void keyPressed(int k) {
		switch (k) {
			case KeyEvent.VK_ENTER:
				select();
				break;

			case KeyEvent.VK_UP:
				currentChoice--;
				if (currentChoice == -1) currentChoice = options.length - 1;
				break;

			case KeyEvent.VK_DOWN:
				currentChoice++;
				if (currentChoice == options.length) currentChoice = 0;
				break;
		}
	}


}
