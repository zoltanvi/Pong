package GameState.Menu;

import Game.GameManager;
import GameState.GameStateManager;
import GameState.MenuState;
import Util.Utils;

import java.awt.*;

public class MainMenuOptions extends MenuOption {


	public MainMenuOptions(GameStateManager gsm, MenuState menuState) {

		super(
				GameManager.getInstance().mainMenuOptions,
				GameManager.getInstance().menuFont,
				GameManager.getInstance().selectedMenuColor,
				gsm,
				menuState
		);

	}

	@Override
	public void awake() {
		currentChoice = 0;
	}


	@Override
	protected void select() {

		try {
			switch (currentChoice) {
				// Start Game
				case 0:
					System.out.println("set State to another");
					if (gsm == null) {
						System.out.println("gsm = null");
					}
					gsm.setState(GameStateManager.IN_GAME_STATE);
					break;
				// Difficulty settings
				case 1:
					menuState.setMenu(MenuState.DIFFICULTY);
					break;
				// Exit Game
				case 2:
					System.exit(0);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}


	@Override
	public void update() {
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.setFont(font);
		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice) {
				Utils.drawCenterText(g, color, font, options[i], 0, 400 + (i * 50));
			} else {
				Utils.drawCenterText(g, Color.WHITE, font, options[i], 0, 400 + (i * 50));
			}
		}
	}


}
