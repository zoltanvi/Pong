package GameObject;

import Game.Difficulty;
import Game.GameManager;
import Game.GamePanel;
import Util.Time;

import java.awt.*;

import static Util.Utils.*;

public class BotPlayer extends Player {

	private float speed;

	public BotPlayer(float x, float y, int width, int height, Color color) {
		super(x, y, width, height, 0, 0, color);
		gm = GameManager.getInstance();
		awake();
	}


	/**
	 * Initializes the bot player.
	 * Sets its difficulty.
	 */
	@Override
	public void awake() {
		super.awake();
		switch (gm.difficulty) {
			case EASY:
				speed = 22;
				break;

			case MEDIUM:
				speed = 28;
				break;

			case HARD:
				speed = 34;
				break;

			case UNFAIR:
				speed = 44;
				break;

			case UNBEATABLE:
				speed = -1;
				break;
		}
	}

	/**
	 * Updates the bot position.
	 * It uses linear interpolation to approach the ball's vertical position smoothly.
	 * The approach speed depends on the difficulty.
	 */
	@Override
	public void update() {
		float nextY;
		if (gm.difficulty == Difficulty.UNBEATABLE) {

			nextY = (gm.ballY + (gm.ballSize / 2)) - (height / 2);

		} else {

			nextY = smoothLerp(y, gm.ballY, speed * Time.deltaTime);
			float clamped = clamp(speed * Time.deltaTime);
			clamped = cubic_scurve3(clamped);
			nextY -= ((height / 2) * clamped);

		}


		if (nextY < GamePanel.HEIGHT - gm.borderBottom - height &&
				nextY > gm.borderTop) {
			y = nextY;
		}

		moveCollider();
	}

}
