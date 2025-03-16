package tetrimino;

/**
 * Represents the L-shaped Tetrimino in Tetris.
 * This piece has a specific initial configuration and can rotate.
 */
public class LShape extends Tetrimino {

    /**
     * Constructs a LShape Tetrimino with its default shape.
     */
	public LShape() {
		super(new int[][]
		// @formatter:off
				{ { 1, 0, 0 },
				  { 1, 0, 0 },
				  { 1, 1, 0 }});
		// @formatter:on

	}


    /**
     * Rotates the LShape Tetrimino 90 degrees clockwise.
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
