package GameObject;

import java.awt.*;


public class SquareParticle extends GameObject {

	private Color color;


	public SquareParticle(float x, float y, int width, int height, float vx, float vy, Color color) {
		super(x, y, width, height, vx, vy);
		Square.particleCount++;
		this.color = color;

	}

	@Override
	public void awake() {
	}

	/**
	 * Fades away the particle as it gets older.
	 */
	@Override
	public void update() {
		try {
			if (color.getAlpha() - 10 < 0) {
				color = new Color(0, 0, 0, 0);
			} else {
				color = new Color(
						color.getRed(),
						color.getGreen(),
						color.getBlue(),
						color.getAlpha() - 10);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void draw(Graphics2D g) {

		g.setColor(color);
		g.fillRect((int) x, (int) y, width, height);

	}


}
