package GameObject;

import Game.Drawable;
import Util.Time;


public abstract class GameObject implements Drawable {

	// Position
	protected float x;
	protected float y;
	// Velocity
	protected float vx;
	protected float vy;
	// Size
	protected int width;
	protected int height;

	// Helper positions, calculated from x and y
	protected float rightX;
	protected float bottomY;
	protected float centerX;
	protected float centerY;

	public GameObject(float x, float y, int width, int height, float vx, float vy) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.vx = vx;
		this.vy = vy;
		bottomY = y + height;
		rightX = x + width;
		centerY = y + (height / 2);
		centerX = x + (width / 2);

		awake();
	}

	/**
	 * Moves the object according to its velocity.
	 */
	public void move() {
		x += (vx * Time.deltaTime);
		y += (vy * Time.deltaTime);
	}

	/**
	 * Recalculates the helper positions.
	 */
	@Override
	public void update() {
		bottomY = y + height;
		rightX = x + width;
		centerY = y + (height / 2);
		centerX = x + (width / 2);
	}

	public abstract void awake();


}
