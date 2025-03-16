package tetrimino;

/**
 * Represents the S-shaped Tetrimino in Tetris.
 * This piece has a specific initial configuration and can rotate.
 */
public class SShape extends Tetrimino {

    /**
     * Constructs a SShape Tetrimino with its default shape.
     */
	public SShape() {
		super(new int[][]
		// @formatter:off
				{ { 0, 1, 1 },
				  { 1, 1, 0 },
				  { 0, 0, 0 }});
		// @formatter:on

	}


    /**
     * Rotates the SShape Tetrimino 90 degrees clockwise.
     */
	@Override
	public void rotate() {
		int size = getShape().length;
		int[][] rotated = new int[size][size];

		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				rotated[j][size - 1 - i] = getShape()[i][j];
			}
		}
		setShape(rotated);
	}

}
