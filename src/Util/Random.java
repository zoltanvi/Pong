package Util;

public class Random {

	private static java.util.Random r = new java.util.Random();

	// Can not be instantiated
	private Random() {
	}

	/**
	 * Returns a random integer number between min [inclusive] and max [inclusive].
	 * @param min is the minimum limit.
	 * @param max is the maximum limit.
	 * @return a random integer between min and max values.
	 */
	public static int nextInt(int min, int max) {
		return r.nextInt((max - min) + 1) + min;
	}

	/**
	 * Returns a random float number between min [inclusive] and max [inclusive].
	 * @param min is the minimum limit.
	 * @param max is the maximum limit.
	 * @return a random float between min and max values.
	 */
	public static float nextFloat(float min, float max) {
		float range = (max - min) + 1;
		return (float) (Math.random() * range) + min;
	}

	/**
	 * Returns a random integer number chosen from -1 or 1.
	 * @return -1 or 1.
	 */
	public static int sign() {
//		int c = Random.nextInt(0, 1);
//		return (c == 0) ? -1 : 1;
		return Random.nextInt(0, 1) * 2 - 1;
	}


}
