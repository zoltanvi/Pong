package GameObject;

import Game.GameManager;
import Game.GamePanel;
import Util.Random;
import Util.Time;
import Util.Utils;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;
import java.util.List;

public class Ball extends GameObject {


	private Ellipse2D collider;
	private Color color;
	private GameManager gm;
	private float prevSpeed;
	private float speed;
	private float bounceAngle;

	// 75 degree
	public static final float MAX_BOUNCE_ANGLE = (float) (5 * Math.PI / 12);
	private List<Player> players;

	public Ball(float x, float y, int width, int height, float speed, Color color) {
		super(x, y, width, height, 0, 0);
		this.speed = speed;
		this.color = color;
		awake();
	}


	/**
	 * Initializes the ball.
	 * It gets a random velocity to a random angle.
	 */
	@Override
	public void awake() {
		players = new ArrayList<>();
		gm = GameManager.getInstance();
		start();
		collider = new Ellipse2D.Float(x, y, width, height);
		prevSpeed = speed;
	}

	/**
	 * Adds a player to the collision check list.
	 * @param p is the player
	 */
	public void addPlayerToCollisionCheck(Player p) {
		players.add(p);
	}


	/**
	 * Updates the ball position and velocity.
	 */
	@Override
	public void update() {
		super.update();

		gm.ballX = x;
		gm.ballY = y;

		// if collide with a wall, change velocity angle
		if (y + (vy * Time.deltaTime) <= gm.borderTop ||
				y + height + (vy * Time.deltaTime) >= GamePanel.HEIGHT - gm.borderBottom) {
			vy *= -1;
		}

		move();
		moveCollider();
		collisionCheck();


	}

	/**
	 * Resets the ball's speed, position and sets its velocity to zero.
	 */
	public void reset() {
		speed = prevSpeed;
		x = gm.ballX;
		y = gm.ballY;
		vx = 0;
		vy = 0;
	}

	/**
	 * Sets the ball's velocity to a random angle.
	 */
	public void start() {
		vx = Random.nextFloat((prevSpeed / 2), prevSpeed) * Random.sign();
		vy = (prevSpeed - Math.abs(vx)) * Random.sign();
	}


	/**
	 * Draws the ball.
	 */
	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fill(collider);
	}

	/**
	 * Moves the ball's collider along with the ball itself.
	 */
	private void moveCollider() {
		collider.setFrame(x, y, width, height);
	}

	/**
	 * Iterates through the collision checklist (which contains players) and checks if a collision happened.
	 * Checks the collision location, and sets a bounce angle according to it.
	 * Then sets the ball's velocity calculated from the bounce angle.
	 */
	public void collisionCheck() {
		// Iterates through the collision check list
		for (int i = 0; i < players.size(); i++) {
			// Checks if collision happened
			if (collider.intersects(players.get(i).collider)) {
				// Calculates the collision location
				float relativeIntersect = players.get(i).y + (players.get(i).height / 2) - centerY;

				if (Math.abs(relativeIntersect) <= players.get(i).height) {

					float normalizedIntersect = relativeIntersect / (players.get(i).height / 2);

					normalizedIntersect = Utils.clampNegative(normalizedIntersect);

					if (Math.abs(normalizedIntersect) > 0.3f && Math.abs(normalizedIntersect) < 1f) {
						speed = prevSpeed + (Math.abs(normalizedIntersect) * speed);
					} else {
						speed = prevSpeed;
					}

					// Sets the bounce angle calculated from the collision location and a max bounce angle
					bounceAngle = normalizedIntersect * Ball.MAX_BOUNCE_ANGLE;
					int n = vx < 0 ? 1 : -1;

					// Sets the ball's velocity calculated from the bounce angle
					vx = (float) (speed * Math.cos(bounceAngle)) * n;
					vy = (float) (speed * -Math.sin(bounceAngle));

					// Moves the ball
					move();

					// Fixes the collision bug when the ball stuck into the player
					if (rightX > GamePanel.WIDTH - players.get(i).width + 1) {
						x = GamePanel.WIDTH - players.get(i).width - 1 - width;
						vx *= -1;
					}

				}
			}
		}

	}

}
