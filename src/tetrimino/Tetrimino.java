package tetrimino;

import java.util.Arrays;

/**
 * Abstract class representing a Tetrimino piece in a Tetris game. A Tetrimino
 * has a position (x, y) and a shape defined as a matrix. Each subclass must
 * implement the rotate method to handle specific rotations.
 */
public abstract class Tetrimino {

	private int x; // Horizontal position
	private int y; // Vertical position
	private int[][] shape; // Matrix representing the shape of the Tetrimino

	/**
	 * Constructs a Tetrimino with the given shape. A deep copy of the shape is
	 * stored to ensure immutability.
	 *
	 * @param shape 2D integer array representing the Tetrimino's shape
	 */
	public Tetrimino(int[][] shape) {
		this.shape = shape;
	};

	/**
	 * Sets the horizontal position.
	 *
	 * @param x New x-coordinate
	 */
	public void setX(int x) {
		this.x = x;
	}

	/**
	 * Sets the vertical position.
	 *
	 * @param y New y-coordinate
	 */
	public void setY(int y) {
		this.y = y;
	}

	/**
	 * Gets the horizontal position.
	 *
	 * @return Current x-coordinate
	 */
	public int getX() {
		return x;
	}

	/**
	 * Gets the vertical position.
	 *
	 * @return Current y-coordinate
	 */
	public int getY() {
		return y;
	}

	/**
	 * Returns a deep copy of the shape matrix to prevent external modifications.
	 *
	 * @return 2D array representing the Tetrimino's shape
	 */
	public int[][] getShape() {
		int[][] originalShape = this.shape;
		int[][] copy = new int[originalShape.length][];

		for (int i = 0; i < originalShape.length; i++) {
			copy[i] = Arrays.copyOf(originalShape[i], originalShape[i].length);
		}

		return copy;
	}

	/**
	 * Sets the shape of the Tetrimino using a deep copy to ensure data integrity.
	 *
	 * @param newShape 2D integer array representing the new shape
	 */
	public void setShape(int[][] newShape) {
		int[][] copy = new int[newShape.length][];

		for (int i = 0; i < newShape.length; i++) {
			copy[i] = Arrays.copyOf(newShape[i], newShape[i].length);
		}

		this.shape = copy;
	}

	/**
	 * Abstract method to handle the rotation of the Tetrimino. Each subclass must
	 * provide its own implementation.
	 */
	public abstract void rotate(); // to manage each tetrimino rotations

	/**
	 * Returns a string representation of the Tetrimino, including its position and
	 * shape.
	 *
	 * @return String describing the Tetrimino
	 */
	@Override
	public String toString() {
		return "Tetrimino[x=" + x + ",y=" + y + ",shape=" + Arrays.deepToString(shape) + "]";
	}
}