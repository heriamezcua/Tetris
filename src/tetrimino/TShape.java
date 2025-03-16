package tetrimino;

/**
 * Represents the T-shaped Tetrimino in Tetris. This piece has a specific
 * initial configuration and can rotate.
 */
public class TShape extends Tetrimino {

	/**
	 * Constructs a TShape Tetrimino with its default shape.
	 */
	public TShape() {
		super(new int[][]
		// @formatter:off
				{ { 1, 1, 1 },
				  { 0, 1, 0 },
				  { 0, 0, 0 }});
		// @formatter:on

	}

	/**
	 * Rotates the TShape Tetrimino 90 degrees clockwise.
	 */
	@Override
	public void rotate() {

		// 1 get the shape of the tetrimino
		int size = getShape().length;

		// 2 create a new empty matrix
		int[][] rotated = new int[size][size];

		// 3 Fill the new matrix with the original tetrimino shape rotating it 90
		// degrees
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				rotated[j][size - 1 - i] = getShape()[i][j];
			}
		}

		// 4 Update the shape
		setShape(rotated);
	}

}
