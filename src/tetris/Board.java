package tetris;

import java.util.Arrays;

import tetrimino.Tetrimino;
import tetrimino.TetriminoFactory;

/**
 * Represents the game board for a Tetris game. Manages tetrimino movement,
 * collision detection, line clearing, and game state.
 */
public class Board {

	private int[][] gameBoard;
	private Tetrimino currentTetrimino;

	// Board boundaries
	private static final int MIN_X = 0;
	private static final int MAX_X = 9; // Maximum horizontal boundary (assuming board width is 10)
	private static final int MIN_Y = 0;
	private static final int MAX_Y = 19; // Maximum vertical boundary (assuming board height is 20)

	private boolean isFastDropping = false;

	private ConsoleKeyListener keyListener;

	/**
	 * Constructs a new Board for the Tetris game with the specified size.
	 * Initializes a two-dimensional array representing the game board.
	 */
	public Board() {
		this.gameBoard = new int[20][10];
	}

	/**
	 * Starts listening for keyboard inputs.
	 */
	public void startListeningForKeyPresses() {
		keyListener = new ConsoleKeyListener(this);
		keyListener.start();
	}

	/**
	 * Stops listening for keyboard inputs.
	 */
	public void stopListeningForKeyPresses() {
		if (keyListener != null) {
			keyListener.stop();
		}
	}

	/**
	 * Enables or disables fast dropping.
	 * 
	 * @param isFast true to enable fast dropping, false to disable.
	 */
	public void setFastDropping(boolean isFast) {
		this.isFastDropping = isFast;
	}

	public boolean getFastDropping() {
		return isFastDropping;
	}

	/**
	 * Instantly drops the tetrimino to the lowest possible position.
	 */
	public void hardDropTetrimino() {
		if (currentTetrimino == null)
			return;

		// Move down until it can't
		while (moveCurrentTetriminoDown())
			;

		SoundManager.play("hard_drop"); // hard drop sound

		fixTetriminoToBoard();
	}

	/**
	 * Moves the current tetrimino left.
	 * 
	 * @return true if moved successfully, false if blocked.
	 */
	public boolean moveCurrentTetriminoLeft() {
		return moveCurrentTetrimino(-1);
	}

	/**
	 * Moves the current tetrimino right.
	 * 
	 * @return true if moved successfully, false if blocked.
	 */
	public boolean moveCurrentTetriminoRight() {
		return moveCurrentTetrimino(1);
	}

	/**
	 * Moves the tetrimino in the specified horizontal direction.
	 * 
	 * @param direction -1 for left, 1 for right.
	 * @return true if the move was successful, false if blocked.
	 */
	private boolean moveCurrentTetrimino(int direction) {
		if (currentTetrimino == null)
			return false;

		int nextX = currentTetrimino.getX() + direction;

		clearTetriminoFromBoard();

		if (!canMove(currentTetrimino, nextX, currentTetrimino.getY())) {
			placeTetriminoOnBoard(); // Restore the position if can move <- or ->
			return false;
		}

		currentTetrimino.setX(nextX); // Move the tetrimino
		placeTetriminoOnBoard();
		SoundManager.play("move"); // move sound
		return true;
	}

	/**
	 * Spawns a new random tetrimino at the top center of the board.
	 */
	public void spawnTetrimino() {
		currentTetrimino = TetriminoFactory.genRandomTetrimino();

		// Set the tetrimino in the top center
		setHorizontal(currentTetrimino, 4);
		setVertical(currentTetrimino, MIN_Y);
	}

	/**
	 * Moves the current tetrimino down.
	 * 
	 * @return true if moved, false if it reached the bottom.
	 */
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

	/**
	 * Checks if the tetrimino can move to a new position.
	 */
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

	/**
	 * Clears all full lines from the board and shifts the remaining lines down.
	 */
	public void clearFullLines() {
		for (int row = MAX_Y; row >= MIN_Y; row--) {
			if (isFullLine(row)) {
				removeLine(row);
				SoundManager.play("line_clear");
				row++; // Check the same row again after go all down
			}
		}
	}

	/**
	 * Delete the full row and lower everything that is above
	 */
	private void removeLine(int row) {
		// Move all the upper rows down
		for (int i = row; i > MIN_Y; i--) {
			gameBoard[i] = Arrays.copyOf(gameBoard[i - 1], gameBoard[i - 1].length);
		}

		// Empty the upper row
		Arrays.fill(gameBoard[MIN_Y], 0);
	}

	/**
	 * Verify if a row is full
	 */
	private boolean isFullLine(int row) {
		for (int col = MIN_X; col <= MAX_X; col++) {
			if (gameBoard[row][col] == 0) {
				return false; // If some space in the row is not an empty row
			}
		}
		return true;
	}

	/**
	 * Rotates the current tetrimino if possible. If rotation causes a collision, it
	 * is reverted to its original state.
	 */
	public void rotateCurrentTetrimino() {
		if (currentTetrimino == null)
			return;

		clearTetriminoFromBoard(); // Delete the actual tetrimino shape

		int[][] originalShape = currentTetrimino.getShape(); // Save the actual shape
		currentTetrimino.rotate(); // Try to rotate

		// If the rotation provokes a collision with a board bound or other tetrimino,
		// revert the rotation
		if (!canMove(currentTetrimino, currentTetrimino.getX(), currentTetrimino.getY())) {
			currentTetrimino.setShape(originalShape); // Back to the original shape
		}

		SoundManager.play("rotate"); // rotation sound

		// Set the tetrimino rotated
		placeTetriminoOnBoard();
	}

	/**
	 * Fixes the current tetrimino to the board and checks for full lines.
	 */
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

		// Delete all the full lines after fix the current tetrimino
		clearFullLines();

		spawnTetrimino();
	}

	/**
	 * Checks if the game is over.
	 */
	public boolean isGameOver() {
		for (int col = MIN_X; col <= MAX_X; col++) {
			if (gameBoard[MIN_Y][col] == 1) {
				return true; // If there is some tetrimino in the superior row the game ends
			}
		}
		return false;
	}

	/**
	 * Places the current tetrimino on the board by marking its occupied spaces.
	 */
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

	/**
	 * Removes the current tetrimino from the board by clearing its occupied spaces.
	 */
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

	/**
	 * Prints the game board to the console.
	 */
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

	/**
	 * Sets the horizontal position of the given Tetrimino.
	 *
	 * @param tetrimino The Tetrimino whose horizontal position is to be set.
	 * @param x         The new x-coordinate for the Tetrimino.
	 * @throws IllegalArgumentException If the x-coordinate is out of the valid
	 *                                  range (MIN_X to MAX_X).
	 */
	public void setHorizontal(Tetrimino tetrimino, int x) {
		if (x < MIN_X || x > MAX_X) {
			throw new IllegalArgumentException(
					"X position out of bounds. Valid range is between " + MIN_X + " and " + MAX_X);
		}
		tetrimino.setX(x);
	}

	/**
	 * Sets the vertical position of the given Tetrimino.
	 *
	 * @param tetrimino The Tetrimino whose vertical position is to be set.
	 * @param y         The new y-coordinate for the Tetrimino.
	 * @throws IllegalArgumentException If the y-coordinate is out of the valid
	 *                                  range (MIN_Y to MAX_Y).
	 */
	public void setVertical(Tetrimino tetrimino, int y) {
		if (y < MIN_Y || y > MAX_Y) {
			throw new IllegalArgumentException(
					"Y position out of bounds. Valid range is between " + MIN_Y + " and " + MAX_Y);
		}
		tetrimino.setY(y);
	}
}
