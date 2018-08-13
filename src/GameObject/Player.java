package GameObject;

import Game.GameManager;

import java.awt.*;

public abstract class Player extends GameObject {

	// This shape represents the player's collider
	public Rectangle collider;
	private Color color;
	protected GameManager gm;

	public Player(float x, float y, int width, int height, float vx, float vy, Color color) {
		super(x, y, width, height, vx, vy);
		collider = new Rectangle((int) x, (int) y, width, height);
		this.color = color;
	}

	@Override
	public void awake() {
		gm = GameManager.getInstance();
	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(collider);
	}

	protected void moveCollider() {
		collider.x = (int) x;
		collider.y = (int) y;
	}

}
