package GameState;

import Game.GameManager;
import GameState.Menu.DifficultyOptions;
import GameState.Menu.MainMenuOptions;
import GameState.Menu.MenuBackground;
import GameState.Menu.MenuOption;
import Util.Utils;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuState extends GameState {

	private MenuBackground bg;

	private List<MenuOption> menuOptions;
	private int currentMenu;

	public static final int MAIN_MENU = 0;
	public static final int DIFFICULTY = 1;

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		bg = new MenuBackground(20, 250);
		menuOptions = new ArrayList<>();
		awake();
	}

	public void setMenu(int menu) {
		currentMenu = menu;
		menuOptions.get(currentMenu).awake();
	}

	@Override
	public void awake() {
		currentMenu = MAIN_MENU;
		menuOptions.add(new MainMenuOptions(gsm, this));
		menuOptions.add(new DifficultyOptions(gsm, this));
	}

	@Override
	public void update() {
		bg.update();
	}

	@Override
	public void draw(Graphics2D g) {
		bg.draw(g);

		Utils.drawCenterText(g, Color.WHITE, GameManager.getInstance().titleFont, "PONG GAME", 0, 200);


		Utils.drawBottomRightText(g, Color.LIGHT_GRAY, GameManager.getInstance().versionFont, "Created by zoltanvi");
		menuOptions.get(currentMenu).draw(g);
	}

	@Override
	public void keyPressed(int k) {
		menuOptions.get(currentMenu).keyPressed(k);
	}

}
