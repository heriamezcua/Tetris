package tetris;

import java.util.Arrays;

import tetrimino.Tetrimino;
import tetrimino.TetriminoFactory;

public class Board {

	private int[][] gameBoard;
	private Tetrimino currentTetrimino;

	// Boundary constants
	private static final int MIN_X = 0;
	private static final int MAX_X = 9; // Maximum horizontal boundary (assuming board width is 10)
	private static final int MIN_Y = 0;
	private static final int MAX_Y = 19; // Maximum vertical boundary (assuming board height is 20)

	/**
	 * Constructs a new Board for the Tetris game with the specified size.
	 * Initializes a two-dimensional array representing the game board.
	 *
	 */
	public Board() {
		this.gameBoard = new int[20][10];
		System.out.println(Arrays.deepToString(gameBoard));
	}

	public void spawnTetrimino() {
		currentTetrimino = TetriminoFactory.genRandomTetrimino();

		// Set the tetrimino in the top center
		setHorizontal(currentTetrimino, 4);
		setVertical(currentTetrimino, MIN_Y);
	}

	// Try to move the tetrimino down. false if bottom reached.
	public boolean moveCurrentTetriminoDown() {
		if (currentTetrimino == null)
			return false;

		int nextY = currentTetrimino.getY() + 1;

		// Clear before move to avoid collisions
		clearTetriminoFromBoard();

		// check if can move down
		if (!canMove(currentTetrimino, currentTetrimino.getX(), nextY)) {
			return false;
		}

		// move down the tetrimino
		currentTetrimino.setY(nextY);

		return true;
	}

	public boolean canMove(Tetrimino tetrimino, int newX, int newY) {
		// I get the shape of the tetrimino T L O J S Z I
		int[][] shape = tetrimino.getShape();

		// Both for are for iterating the tetrimino shape
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {
				if (shape[i][j] == 1) {

					// boardXY are the next position X Y of the tetrimino if can move
					int boardX = newX + j;
					int boardY = newY + i;

					// check if collide with the bounds of the board
					if (boardX < MIN_X || boardX > MAX_X || boardY < MIN_Y || boardY > MAX_Y) {
						return false;
					}

					// check if has a colision down with other tetriminos in the board
					if (gameBoard[boardY][boardX] == 1) {
						return false;
					}
				}
			}
		}
		return true; // There is no collision
	}

	public void fixTetriminoToBoard() {
		int[][] shape = currentTetrimino.getShape();

		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {
				if (shape[i][j] == 1) {
					int boardX = currentTetrimino.getX() + j;
					int boardY = currentTetrimino.getY() + i;

					if (boardY >= 0 && boardY < gameBoard.length && boardX >= 0 && boardX < gameBoard[0].length) {
						gameBoard[boardY][boardX] = 1; // Fix the tetrimino in the board
					}
				}
			}
		}
	}

	public void placeTetriminoOnBoard() {
		int[][] shape = currentTetrimino.getShape();

		// Both for are for iterating the tetrimino shape
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {
				
				if (shape[i][j] == 1) { // If the shape belongs to the tetrimino
					
					
					int boardX = currentTetrimino.getX() + j;
					int boardY = currentTetrimino.getY() + i;
					
					if (boardY >= 0 && boardY < gameBoard.length && boardX >= 0 && boardX < gameBoard[0].length) {
						gameBoard[boardY][boardX] = 1; // Fix the tetrimino in the board
					}
				}
			}
		}
	}

	public void clearTetriminoFromBoard() {
		int[][] shape = currentTetrimino.getShape();

		// Both for are for iterating the tetrimino shape
		for (int i = 0; i < shape.length; i++) {
			for (int j = 0; j < shape[i].length; j++) {
				if (shape[i][j] == 1) { // If the shape belongs to the tetrimino
					
					// boardXY are the next position X Y of the tetrimino if can move
					int boardX = currentTetrimino.getX() + j;
					int boardY = currentTetrimino.getY() + i;
					gameBoard[boardY][boardX] = 0; // clear the square
				}
			}
		}
	}

	public void printBoard() {
		StringBuilder sb = new StringBuilder();

		for (int[] row : gameBoard) {
			sb.append("<!"); // Left column
			sb.append(Arrays.stream(row).mapToObj(cell -> cell == 0 ? " ." : "[]").reduce("", (a, b) -> a + b));
			sb.append("!>\n"); // Right column
		}

		// print the base
		String base = "<!" + "*".repeat(gameBoard[0].length * 2) + "!>\n";
		String arrows = "  " + "\\/".repeat(gameBoard[0].length) + "\n\n\n";

		// print in the console
		System.out.println(sb.toString() + base + arrows);
	}

	public void setHorizontal(Tetrimino tetrimino, int x) {
		if (x < MIN_X || x > MAX_X) {
			throw new IllegalArgumentException(
					"X position out of bounds. Valid range is between " + MIN_X + " and " + MAX_X);
		}
		tetrimino.setX(x);
	}

	public void setVertical(Tetrimino tetrimino, int y) {
		if (y < MIN_Y || y > MAX_Y) {
			throw new IllegalArgumentException(
					"Y position out of bounds. Valid range is between " + MIN_Y + " and " + MAX_Y);
		}
		tetrimino.setY(y);
	}
}
