package tetrimino;

public class IShape extends Tetrimino {

	public IShape() {
        super(new int[][] {
        	// @formatter:off
            {0, 0, 0, 0},
            {1, 1, 1, 1},
            {0, 0, 0, 0},
            {0, 0, 0, 0}
            // @formatter:on
        });

	}

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
