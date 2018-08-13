package Util;

import Game.GamePanel;

import java.awt.*;

public class Utils {


	private Utils() {
	}

	/**
	 * Draws the given text aligned to the center of the canvas.
	 * If x = 0 and y != 0, the text is aligned to the center horizontally.
	 * If y = 0 and x != 0, the text is aligned to the center vertically.
	 * If x != 0 and y != 0, the text is not aligned.
	 * @param g is the graphics object.
	 * @param color is the text color.
	 * @param font is the text font.
	 * @param text is the text.
	 * @param x is the horizontal position.
	 * @param y is the vertical position.
	 */
	public static void drawCenterText(Graphics2D g, Color color, Font font, String text, int x, int y) {
		g.setColor(color);
		g.setFont(font);
		int lengthInPixel = g.getFontMetrics().stringWidth(text);
		int heightInPixel = g.getFontMetrics().getHeight();
		if (x == 0) {
			g.drawString(text, (GamePanel.WIDTH / 2) - (lengthInPixel / 2), y);
		} else if (y == 0) {
			g.drawString(text, x, (GamePanel.HEIGHT / 2) - (heightInPixel / 2));
		}
	}

	/**
	 * Draws the given text aligned to the center of the left half of the canvas.
	 * @param g is the graphics object.
	 * @param color is the text color.
	 * @param font is the text font.
	 * @param text is the text.
	 * @param y is the vertical position.
	 */
	public static void drawLeftMiddleText(Graphics2D g, Color color, Font font, String text, int y) {
		g.setColor(color);
		g.setFont(font);
		int lengthInPixel = g.getFontMetrics().stringWidth(text);
		g.drawString(text, (GamePanel.WIDTH / 4) - (lengthInPixel / 2), y);
	}

	/**
	 * Draws the given text aligned to the center of the right half of the canvas.
	 * @param g is the graphics object.
	 * @param color is the text color.
	 * @param font is the text font.
	 * @param text is the text.
	 * @param y is the vertical position.
	 */
	public static void drawRightMiddleText(Graphics2D g, Color color, Font font, String text, int y) {
		g.setColor(color);
		g.setFont(font);
		int lengthInPixel = g.getFontMetrics().stringWidth(text);
		g.drawString(text, ((GamePanel.WIDTH / 4) * 3) - (lengthInPixel / 2), y);
	}

	public static void drawBottomRightText(Graphics2D g, Color color, Font font, String text) {
		g.setColor(color);
		g.setFont(font);
		int lengthInPixel = g.getFontMetrics().stringWidth(text);
		int heightInPixel = g.getFontMetrics().getHeight();
		g.drawString(text, GamePanel.WIDTH - lengthInPixel - 15, GamePanel.HEIGHT - heightInPixel - 5);
	}

	public static float lerp(float point1, float point2, float alpha) {
		alpha = clamp(alpha);
		return point1 + (point2 - point1) * alpha;
	}

	public static float smoothLerp(float p1, float p2, float alpha) {
		return lerp(p1, p2, cubic_scurve3(alpha));
	}

	public static float cubic_scurve3(float alpha) {
		return alpha * alpha * (3.0f - 2.0f * alpha);
	}


	public static float clamp(float value) {
		if (value < 0F)
			return 0F;
		else if (value > 1F)
			return 1F;
		else
			return value;
	}

	public static float clampNegative(float value) {
		if (value < -1f) {
			return -1f;
		} else if (value > 1f) {
			return 1f;
		} else {
			return value;
		}
	}

	public static float distance(float x1, float y1, float x2, float y2) {
		float v0 = x2 - x1;
		float v1 = y2 - y1;
		return (float) Math.sqrt(v0 * v0 + v1 * v1);
	}


}
