package tetrimino;

public class LShape extends Tetrimino {

	public LShape() {
		super(new int[][]
		// @formatter:off
				{ { 1, 0, 0 },
				  { 1, 0, 0 },
				  { 1, 1, 0 }});
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
