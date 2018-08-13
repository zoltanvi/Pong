package GameState;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameStateManager {

	private List<GameState> gameStates;
	private int currentState;

	public static final int MENU_STATE = 0;
	public static final int IN_GAME_STATE = 1;


	public GameStateManager() {
		gameStates = new ArrayList<>();
		awake();
	}

	private void awake() {
		currentState = MENU_STATE;
		gameStates.add(new MenuState(this));
		gameStates.add(new InGameState(this));
	}

	public void setState(int state) {
		currentState = state;
		gameStates.get(currentState).awake();
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void draw(Graphics2D g) {
		gameStates.get(currentState).draw(g);

	}

	public void keyPressed(int k) {
		gameStates.get(currentState).keyPressed(k);
	}

}
