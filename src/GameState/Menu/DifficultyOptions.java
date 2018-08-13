package GameState.Menu;

import Game.Difficulty;
import Game.GameManager;
import GameState.GameStateManager;
import GameState.MenuState;
import Util.Utils;

import java.awt.*;

public class DifficultyOptions extends MenuOption {


	public DifficultyOptions(GameStateManager gsm, MenuState menuState) {

		super(
				GameManager.getInstance().difficultyOptions,
				GameManager.getInstance().menuFont,
				GameManager.getInstance().selectedMenuColor,
				gsm,
				menuState
		);
		awake();
	}

	@Override
	public void awake() {
		currentChoice = gm.difficulty.ordinal();
	}

	@Override
	protected void select() {
		switch (currentChoice) {
			case 0:
				gm.setDifficulty(Difficulty.EASY);
				break;
			case 1:
				gm.setDifficulty(Difficulty.MEDIUM);
				break;
			case 2:
				gm.setDifficulty(Difficulty.HARD);
				break;
			case 3:
				gm.setDifficulty(Difficulty.UNFAIR);
				break;
			case 4:
				gm.setDifficulty(Difficulty.UNBEATABLE);
				break;
		}
		menuState.setMenu(MenuState.MAIN_MENU);
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
