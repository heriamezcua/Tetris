package tetrimino;

import java.util.Random;

/**
 * Factory class for generating random Tetrimino pieces.
 */
public class TetriminoFactory {
	
	private static final Random random = new Random();

    /**
     * Generates a random Tetrimino shape.
     *
     * @return A randomly selected Tetrimino instance.
     */
	public static Tetrimino genRandomTetrimino() {

		int type = random.nextInt(7);

		switch (type) {
		case 0:
			return new IShape(); // Line (I)
		case 1:
			return new SShape(); // S
		case 2:
			return new ZShape(); // Z
		case 3:
			return new OShape(); // Square (O)
		case 4:
			return new TShape(); // T
		case 5:
			return new LShape(); // L
		case 6:
			return new JShape(); // Reverse L (J)
		default:
			return new OShape(); // for security I return an Square
		}
	}
}