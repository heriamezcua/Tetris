package tetrimino;

import java.util.Arrays;

public abstract class Tetrimino {

	private int x; // horizontal position
	private int y; // vertical position

	private int[][] shape; // Matrix that represents the shape of the tetrimino

	public Tetrimino(int[][] shape) {
		this.shape = shape;
	};

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int[][] getShape() {
	    int[][] originalShape = this.shape;
	    int[][] copy = new int[originalShape.length][];
	    
	    for (int i = 0; i < originalShape.length; i++) {
	        copy[i] = Arrays.copyOf(originalShape[i], originalShape[i].length);
	    }

	    return copy; // Devuelve una copia en lugar de la referencia original
	}

	public void moveRight() {
		x++;
	};

	public void moveLeft() {
		x--;
	};

	public void moveBottom() {
		y--;
	};

	public void moveBottomFaster() {
		y++;
	};

	public abstract void rotate(); // to manage each tetrimino rotations

	@Override
	public String toString() {
		return "Tetrimino[x=" + x + ",y=" + y + ",shape=" + Arrays.deepToString(shape) + "]";
	}
}