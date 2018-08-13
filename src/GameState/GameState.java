package GameState;

import Game.Drawable;

public abstract class GameState implements Drawable {

	protected GameStateManager gsm;

	public abstract void awake();

	public abstract void keyPressed(int k);

}
