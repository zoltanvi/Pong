package GameState;

import Game.GameManager;
import Game.GamePanel;
import GameObject.Ball;
import GameObject.BotPlayer;
import GameObject.HumanPlayer;
import GameObject.Player;
import Util.Time;

import java.awt.*;
import java.awt.event.KeyEvent;

public class InGameState extends GameState {

	private GameStateManager gsm;
	private GameManager gm;
	private Player humanPlayer;
	private Player botPlayer;
	private Ball ball;
	private InGameUI ui;

	private float timer;
	private boolean reset;
	private boolean win;

	public InGameState(GameStateManager gsm) {
		this.gsm = gsm;
		this.gm = GameManager.getInstance();
		awake();
	}

	/**
	 * Initializes the game.
	 */
	@Override
	public void awake() {
		try {
			ui = new InGameUI();
			humanPlayer = new HumanPlayer(
					GamePanel.WIDTH - gm.borderRight - gm.playerWidth,
					(GamePanel.HEIGHT / 2) - (gm.playerHeight / 2),
					gm.playerWidth,
					gm.playerHeight,
					gm.humanPlayerColor
			);

			botPlayer = new BotPlayer(
					gm.borderLeft + 1,
					(GamePanel.HEIGHT / 2) - (gm.playerHeight / 2),
					gm.playerWidth,
					gm.playerHeight,
					gm.botPlayerColor
			);

			gm.ballX = (GamePanel.WIDTH / 2) - (gm.ballSize / 2);
			gm.ballY = (GamePanel.HEIGHT / 2) - (gm.ballSize / 2);

			ball = new Ball(
					(GamePanel.WIDTH / 2) - (gm.ballSize / 2),
					(GamePanel.HEIGHT / 2) - (gm.ballSize / 2),
					gm.ballSize,
					gm.ballSize,
					gm.ballSpeed,
					Color.WHITE
			);

			ball.addPlayerToCollisionCheck(humanPlayer);
			ball.addPlayerToCollisionCheck(botPlayer);

			gm.resetScores();
			win = false;
			reset = true;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Override
	public void keyPressed(int k) {
		if (k == KeyEvent.VK_ESCAPE) {
			gsm.setState(GameStateManager.MENU_STATE);
		}
	}


	@Override
	public void update() {
		ui.update();
		humanPlayer.update();
		botPlayer.update();

		if (!win) {
			ball.update();
			checkBallScore();
		}
	}

	@Override
	public void draw(Graphics2D g) {
		ui.draw(g);
		humanPlayer.draw(g);
		botPlayer.draw(g);
		ball.draw(g);
	}


	private void checkBallScore() {
		// If someone has 10 points, ends the game.
		if (gm.humanScore >= 10 || gm.botScore >= 10) {
			win = true;
		}

		// If the ball is out, increment the score and reset the ball.
		if (gm.ballX + gm.ballSize > GamePanel.WIDTH - gm.borderRight || gm.ballX < gm.borderLeft) {
			if (gm.ballX + gm.ballSize > GamePanel.WIDTH - gm.borderRight) {
				gm.incrementBotScore();
			} else if (gm.ballX < gm.borderLeft) {
				gm.incrementHumanScore();
			}

			gm.ballX = (GamePanel.WIDTH / 2) - (gm.ballSize / 2);
			gm.ballY = (GamePanel.HEIGHT / 2) - (gm.ballSize / 2);

			reset = true;
		}

		// The ball rests in the given amount of time.
		if (reset) {
			ball.reset();
			timer += Time.deltaTime;
			if (timer >= gm.ballResetTime) {
				ball.start();
				reset = false;
				timer = 0;
			}
		}
	}

}
