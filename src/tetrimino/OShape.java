package tetrimino;

public class OShape extends Tetrimino {

	public OShape() {
		super(new int[][]
		// @formatter:off
				{ { 1, 1 },
				{ 1, 1 } });
		// @formatter:on

	}

	@Override
	public void rotate() {
		// The rotation is the same
	}

}
