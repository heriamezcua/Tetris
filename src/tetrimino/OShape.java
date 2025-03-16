package tetrimino;

/**
 * Represents the O-shaped Tetrimino in Tetris. This piece has a specific
 * initial configuration and can rotate.
 */
public class OShape extends Tetrimino {

	/**
	 * Constructs a OShape Tetrimino with its default shape.
	 */
	public OShape() {
		super(new int[][]
		// @formatter:off
				{ { 1, 1 },
				{ 1, 1 } });
		// @formatter:on

	}

	/**
	 * The rotation of the square is the same
	 */
	@Override
	public void rotate() {
		// The rotation is the same
	}

}
