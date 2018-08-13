package GameState;

import Game.Drawable;
import Game.GameManager;
import Game.GamePanel;
import Util.Utils;

import java.awt.*;

public class InGameUI implements Drawable {

	GameManager gm;
	int alpha = -3;

	public InGameUI() {
		gm = GameManager.getInstance();
	}

	public void awake() {
		gm = GameManager.getInstance();
		gm.resetScores();
	}

	/**
	 * Fades the return text in and out.
	 */
	@Override
	public void update() {

		if (gm.humanScore >= 10 || gm.botScore >= 10) {
			if (gm.backColor.getAlpha() + alpha < 0 || gm.backColor.getAlpha() + alpha > 255) {
				alpha *= -1;
			}

			gm.backColor = new Color(
					gm.backColor.getRed(),
					gm.backColor.getGreen(),
					gm.backColor.getBlue(),
					gm.backColor.getAlpha() + alpha);

		}

	}

	@Override
	public void draw(Graphics2D g) {
		// Draws background
		g.setBackground(gm.bgColor);
		g.clearRect(0, 0, GamePanel.WIDTH, GamePanel.HEIGHT);
		g.setColor(gm.borderColor);


		// Draws top border
		g.fillRect(0, 0, GamePanel.WIDTH, gm.borderTop);

		// Draws left border
		if (gm.borderLeft > 0) {
			g.fillRect(0, 0, gm.borderLeft, GamePanel.HEIGHT);
		}

		// Draws bottom border
		g.fillRect(0, GamePanel.HEIGHT - gm.borderBottom, GamePanel.WIDTH, gm.borderBottom);

		// Draws right border
		if (gm.borderRight > 0) {
			g.fillRect(GamePanel.WIDTH - gm.borderRight, 0, gm.borderRight, GamePanel.HEIGHT);
		}

		// Draws center line
		g.setColor(gm.borderColor);
		g.fillRect((GamePanel.WIDTH / 2) - 2, 0, 4, GamePanel.HEIGHT);


		// Draws scores
		Utils.drawLeftMiddleText(g, gm.scoreColor, gm.scoreFont, Integer.toString(gm.botScore), GamePanel.HEIGHT / 8);
		Utils.drawRightMiddleText(g, gm.scoreColor, gm.scoreFont, Integer.toString(gm.humanScore), GamePanel.HEIGHT / 8);

		// Draws win text
		if (gm.humanScore >= 10) {
			Utils.drawRightMiddleText(g, Color.GREEN, gm.victoryFont, "YOU WON!", GamePanel.HEIGHT / 2);

		}

		if (gm.botScore >= 10) {
			Utils.drawLeftMiddleText(g, Color.RED, gm.victoryFont, "BOT WON!", GamePanel.HEIGHT / 2);
		}

		// Draws return text
		if (gm.humanScore >= 10 || gm.botScore >= 10) {
			Utils.drawCenterText(g, gm.backColor, gm.backFont, "Press Esc to return to the main menu!", 0, (GamePanel.HEIGHT / 5) * 4);

		}

	}
}
