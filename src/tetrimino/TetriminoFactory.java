package tetrimino;

import java.util.Random;
import java.util.logging.Level;

import tetris.LogManager;

public class TetriminoFactory {
	
	private static final Random random = new Random();

	public static Tetrimino genRandomTetrimino() {

		int type = random.nextInt(7);

		switch (type) {
		case 0:
			return new OShape(); // Square (O)
		case 1:
			return new IShape(); // Line (I)
		case 2:
			return new TShape(); // T
		case 3:
			return new LShape(); // L
		case 4:
			return new JShape(); // Reverse L (J)
		case 5:
			return new SShape(); // S
		case 6:
			return new ZShape(); // Z
		default:
			return new OShape(); // for security I return an Square
		}
	}
}
