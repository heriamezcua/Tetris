package tetrimino;

/**
 * Represents the I-shaped Tetrimino in Tetris.
 * This piece has a specific initial configuration and can rotate.
 */
public class IShape extends Tetrimino {

    /**
     * Constructs a LShape Tetrimino with its default shape.
     */
	public IShape() {
        super(new int[][] {
        	// @formatter:off
        	{1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
            // @formatter:on
        });

	}


    /**
     * Rotates the IShape Tetrimino 90 degrees clockwise.
     */
	@Override
	public void rotate() {
        int[][] shape = getShape();
        int size = shape.length;
        int[][] rotated = new int[size][size];

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                rotated[j][size - 1 - i] = shape[i][j];
            }
        }
        setShape(rotated);
	}

}
