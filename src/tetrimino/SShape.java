package tetrimino;

public class SShape extends Tetrimino {

	public SShape() {
		super(new int[][]
		// @formatter:off
				{ { 0, 1, 1 },
				  { 1, 1, 0 },
				  { 0, 0, 0 }});
		// @formatter:on

	}

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
