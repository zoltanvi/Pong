package GameState.Menu;

import Game.Drawable;
import Game.GamePanel;
import GameObject.Square;
import Util.Random;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MenuBackground implements Drawable {

	private float x;
	private float y;
	private int alpha;
	private Color[] colors;
	private int squareCount;

	List<Square> squares = new ArrayList<>();

	public MenuBackground(int squareCount, int alpha) {
		this.squareCount = squareCount;
		this.alpha = alpha;
		awake();
	}


	public void awake() {
		colors = new Color[8];
		colors[0] = new Color(36, 233, 255, alpha);
		colors[1] = new Color(0, 255, 44, alpha);
		colors[2] = new Color(255, 181, 0, alpha);
		colors[3] = new Color(254, 255, 0, alpha);
		colors[4] = new Color(255, 0, 214, alpha);
		colors[5] = new Color(255, 0, 79, alpha);
		colors[6] = new Color(202, 79, 255, alpha);
		colors[7] = new Color(180, 255, 0, alpha);

		for (int i = 0; i < squareCount; i++) {
			int negX = Random.sign();
			int negY = Random.sign();

			squares.add(
					new Square(
							Random.nextInt(0, GamePanel.WIDTH),
							Random.nextInt(0, GamePanel.HEIGHT),
							GamePanel.HEIGHT / 70,
							GamePanel.HEIGHT / 70,
							Random.nextInt(10, 30) * 10 * negX,
							Random.nextInt(10, 30) * 10 * negY,
							colors[Random.nextInt(0, colors.length - 1)]
					)
			);
		}
	}

	@Override
	public void update() {
		for (Square s : squares) {
			s.update();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		g.setBackground(Color.BLACK);
		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);

		for (Square s : squares) {
			s.draw(g);
		}

	}
}
