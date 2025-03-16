package tetris;

/**
 * The TetrisApp class serves as the entry point for the Tetris game. It manages
 * the game loop and board interactions.
 */
public class TetrisApp {

	// *******************************
	// *** ATTRIBUTES ***
	// *******************************

	/** The game board instance. */
	private Board board;

	// *******************************
	// *** APPLICATION ENTRY POINT ***
	// *******************************

	/**
	 * The main method serves as the entry point for the application.
	 * 
	 * @param args Command-line arguments passed to the application. These are not
	 *             used in this implementation.
	 */
	public static void main(String[] args) {
		TetrisApp app = new TetrisApp();
		app.run();
	}

	// *******************************
	// *** MAIN GAME LOGIC LOOP ***
	// *******************************

	/**
	 * Runs the main game loop of the Tetris application. This method initializes
	 * the game, listens for user input, and continuously updates the game state.
	 */
	public void run() {

		board = new Board();
		SoundManager.loop("tetris_theme"); // Loop tetris theme
		board.startListeningForKeyPresses();
		board.spawnTetrimino();

		while (!board.isGameOver()) {

			board.placeTetriminoOnBoard();
			board.printBoard();

			// Move down automatically until movement is blocked
			boolean moved;
			do {
				// Wait 1 second before the tetrimino move down automatically
				try {
					Thread.sleep(board.getFastDropping() ? 250 : 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// clear the previous position
				board.clearTetriminoFromBoard();

				// Try to move down if possible
				moved = board.moveCurrentTetriminoDown();

				// Place the current tetrimino position on the board
				board.placeTetriminoOnBoard();

				// Print the board
				board.printBoard();

			} while (moved);

			// Fix tetrimino to the board, clear full lines and spawn a new tetrimino
			board.fixTetriminoToBoard();
		}
		SoundManager.stop(); // stop the main theme
		board.stopListeningForKeyPresses();
		System.out.println("Game Over!");
		SoundManager.play("game_over");
		try {
			Thread.sleep(1500);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
