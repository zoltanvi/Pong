package GameObject;

import Game.GamePanel;
import Util.Time;
import Util.Utils;

import java.awt.*;
import java.util.LinkedList;

public class Square extends GameObject {

	private Color color;

	private float lastX;
	private float lastY;

	public static int count = 0;
	public static int particleCount = 0;

	LinkedList<SquareParticle> trail = new LinkedList<>();


	public Square(float x, float y, int width, int height, float vx, float vy, Color color) {
		super(x, y, width, height, vx, vy);
		this.color = color;
		count++;
		lastX = x;
		lastY = y;
	}


	@Override
	public void awake() {
		trail = new LinkedList<>();
	}

	/**
	 * Moves the square and spawn its trail particle.
	 * Also removes its invisible, unused trail particles.
	 */
	@Override
	public void update() {

		if (trail.size() > 25) {
			trail.poll();
			particleCount--;
		}

		if (x - 1 <= 0 || x + width + 1 >= GamePanel.WIDTH) {
			vx *= -1;
		}

		if (y - 1 <= 0 || y + height + 1 >= GamePanel.HEIGHT) {
			vy *= -1;
		}

		float offset = (width / 3) * 2;
		float distance = Utils.distance(lastX, lastY, x, y);

		if (distance > offset) {
			lastX = x;
			lastY = y;
			trail.add(
					new SquareParticle(x, y, width, height, 0, 0, color)
			);
		}

		move();

		if (x - 1 + (vx * Time.deltaTime) < 0) {
			x = 0;
		}

		if (x + 1 + (vx * Time.deltaTime) > GamePanel.WIDTH) {
			x = GamePanel.WIDTH - width;
		}

		if (y - 1 + (vy * Time.deltaTime) < 0) {
			y = 0;
		}

		if (y + 1 + (vy * Time.deltaTime) > GamePanel.HEIGHT) {
			y = GamePanel.HEIGHT - height;
		}

		for (SquareParticle p : trail) {
			p.update();
		}

	}

	@Override
	public void draw(Graphics2D g) {
		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

		for (SquareParticle p : trail) {
			p.draw(g);
		}
	}

}
