package Game;

import GameState.GameStateManager;
import Util.Time;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import static Util.Time.OPTIMAL_TIME;


public class GamePanel extends JPanel implements Runnable, KeyListener {

	public static final int HEIGHT = 720;
	public static final int WIDTH = 960;
	public static final int SCALE = 1;

	private BufferedImage image;
	private Graphics2D g;
	private GameManager gm;
	private GameStateManager gsm;
	private Thread thread;


	public GamePanel() {
		super();
		setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
		setFocusable(true);
		requestFocus();

	}

	/**
	 * Initializes the game panel.
	 */
	private void awake() {
		gm = GameManager.getInstance();
		image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		g = (Graphics2D) image.getGraphics();
		gsm = new GameStateManager();

	}

	/**
	 * Runs the game loop in a fixed time rate.
	 */
	public void gameLoop() {
		long lastFrame = System.nanoTime();
		long lastFpsTime = 0;
		int fps = 0;

		awake();

		while (true) {
			long now = System.nanoTime();
			long updateLength = now - lastFrame;

			lastFrame = now;
			Time.deltaTime = ((float) updateLength / (float) OPTIMAL_TIME) / 100;
			lastFpsTime += updateLength;
			fps++;

			if (lastFpsTime >= 1000000000) {
				System.out.println("(FPS: " + fps + ")");
				lastFpsTime = 0;
				fps = 0;
			}

			update();
			draw();
			drawToScreen();

			try {
				if ((lastFrame - System.nanoTime() + OPTIMAL_TIME) / 1000000 > 0) {
					Thread.sleep((lastFrame - System.nanoTime() + OPTIMAL_TIME) / 1000000);
				}
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}

	}

	/**
	 * Updates everything in the game.
	 * This is the main update method.
	 */
	private void update() {
		updateMousePos();
		gsm.update();
	}

	/**
	 * Draws (renders) everything in the game.
	 * This is the main draw method.
	 */
	private void draw() {
		gsm.draw(g);
	}

	/**
	 * Draws the rendered picture into the game panel.
	 */
	private void drawToScreen() {
		Graphics g2 = getGraphics();
		g2.drawImage(image, 0, 0, WIDTH * SCALE, HEIGHT * SCALE, null);
		g2.dispose();
	}

	public void addNotify() {
		super.addNotify();
		if (thread == null) {
			thread = new Thread(this);
			addKeyListener(this);
			thread.start();
		}
	}

	/**
	 * Updates the stored mouse position (x, y).
	 */
	public void updateMousePos() {
		Point mouse = MouseInfo.getPointerInfo().getLocation();
		SwingUtilities.convertPointFromScreen(mouse, this);

		gm.mouseX = mouse.x;
		gm.mouseY = mouse.y;
	}

	/**
	 * Runs the game loop.
	 */
	@Override
	public void run() {
		gameLoop();
	}


	@Override
	public void keyTyped(KeyEvent e) {
	}


	/**
	 * Handles every key event in the game.
	 * This is the main key handler method.
	 * @param e is the key event
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		gsm.keyPressed(e.getKeyCode());
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}


}
