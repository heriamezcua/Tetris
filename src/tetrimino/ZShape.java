package tetrimino;

/**
 * Represents the Z-shaped Tetrimino in Tetris.
 * This piece has a specific initial configuration and can rotate.
 */
public class ZShape extends Tetrimino {

    /**
     * Constructs a ZShape Tetrimino with its default shape.
     */
	public ZShape() {
		super(new int[][]
		// @formatter:off
				{ { 1, 1, 0 },
				  { 0, 1, 1 },
				  { 0, 0, 0 } });
		// @formatter:on

	}


    /**
     * Rotates the ZShape Tetrimino 90 degrees clockwise.
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
