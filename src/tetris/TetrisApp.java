package tetris;

public class TetrisApp {

	// *******************************
	// *** ATTRIBUTES ***
	// *******************************

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
	 * Executes the main game loop of the Tetris application. This method handles
	 * the initialization of the game, ....
	 */
	public void run() {

		//
		// *** Game state initialization ***
		board = new Board();

		//
		// *** Starting a new game ***
		do {
			// Generate a random tetrimino
			board.spawnTetrimino();

			// Place the tetrimino in the board
			board.placeTetriminoOnBoard();
			
			// Print the board
			board.printBoard();

			// Move down automatically until it can't
			boolean moved;
			do {				
				// Wait 1 second before move down automatically
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				// Try to move down if possible
				moved = board.moveCurrentTetriminoDown(); // Intenta mover la pieza abajo
				
				// clear the previous position
				board.clearTetriminoFromBoard();

				// Place the current tetrimino position on the board
				board.placeTetriminoOnBoard();

				// Print the board
				board.printBoard();

			} while (moved);

			// When the tetrimino can`t move fix it to the board
			board.fixTetriminoToBoard();

		} while (true);

	}

// ***********************
// *** PRIVATE METHODS ***
// ***********************
}
