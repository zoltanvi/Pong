package GameObject;

import Game.GamePanel;

import java.awt.*;

public class HumanPlayer extends Player {


	public HumanPlayer(float x, float y, int width, int height, Color color) {
		super(x, y, width, height, 0, 0, color);
		awake();
	}

	@Override
	public void awake() {
		super.awake();
	}

	/**
	 * Updates the player's position.
	 */
	@Override
	public void update() {
		mouseMove(gm.mouseY);
		moveCollider();
	}

	/**
	 * Sets the player's vertical position next to the cursor.
	 * @param mouseY is the vertical position of the cursor.
	 */
	public void mouseMove(float mouseY) {
		if (mouseY >= gm.borderTop + (height / 2) &&
				mouseY <= GamePanel.HEIGHT - gm.borderBottom - (height / 2)) {
			y = mouseY - (height / 2);
		} else if (mouseY < gm.borderTop + (height / 2)) {
			y = gm.borderTop;
		} else if (mouseY > GamePanel.HEIGHT - gm.borderBottom - (height / 2)) {
			y = GamePanel.HEIGHT - gm.borderBottom - height;
		}
	}

}
