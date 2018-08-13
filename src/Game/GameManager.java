package Game;

import java.awt.*;

public class GameManager {

	// stores the mouses current position (in window space, not global)
	public float mouseX;
	public float mouseY;

	// stores the balls current position
	public float ballX;
	public float ballY;

	// the bot AI difficulty
	public Difficulty difficulty = Difficulty.MEDIUM;

	// borders of the game area
	public int borderLeft = 0;
	public int borderRight = 0;
	public int borderTop = 10;
	public int borderBottom = 10;

	// player and ball sizes
	public int playerWidth = 15;
	public int playerHeight = 120;
	public int ballSize = 15;
	public int ballSpeed = 900;

	// the time the ball waits before each round in seconds
	public final float ballResetTime = 1.5f;

	// the scores
	public int humanScore = 0;
	public int botScore = 0;


	public final Color borderColor = new Color(187, 187, 187);
	public final Color humanPlayerColor = new Color(0, 217, 255);
	public final Color botPlayerColor = new Color(223, 59, 0);
	public final Color bgColor = new Color(0, 0, 0);
	public final Color scoreColor = new Color(238, 238, 238);
	public final Color selectedMenuColor = new Color(192, 255, 0);
	public Color backColor = new Color(0, 255, 215, 255);


	public final Font menuFont = new Font("Helvetica", Font.BOLD, 32);
	public final Font titleFont = new Font("Helvetica", Font.BOLD, 85);
	public final Font scoreFont = new Font("Helvetica", Font.BOLD, 80);
	public final Font victoryFont = new Font("Helvetica", Font.BOLD, 65);
	public final Font backFont = new Font("Helvetica", Font.BOLD, 28);
	public final Font versionFont = new Font("Helvetica", Font.PLAIN, 20);

	// main menu options
	public final String[] mainMenuOptions = {
			"Start Game",
			"Difficulty settings",
			"Exit Game"
	};

	// difficulty menu options
	public final String[] difficultyOptions = {
			"Easy",
			"Medium",
			"Hard",
			"Unfair",
			"Unbeatable"
	};


	// singleton for the game manager
	private static GameManager instance;

	private GameManager() {
	}

	public static GameManager getInstance() {
		if (instance == null) {
			instance = new GameManager();
		}
		return instance;
	}

	/**
	 * Resets the scores before each new game.
	 */
	public void resetScores() {
		humanScore = 0;
		botScore = 0;
	}

	/**
	 * Gives a point for the human player.
	 */
	public void incrementHumanScore() {
		humanScore++;
	}

	/**
	 * Gives a point for the bot player.
	 */
	public void incrementBotScore() {
		botScore++;
	}

	/**
	 * Sets the game's difficulty.
	 * @param d is the difficulty.
	 */
	public void setDifficulty(Difficulty d) {
		this.difficulty = d;
	}

}
