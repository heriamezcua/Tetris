package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

/**
 * ConsoleKeyListener handles keyboard input for the Tetris game. It listens for
 * key presses to move, rotate, and drop the Tetrimino.
 */
public class ConsoleKeyListener implements KeyListener {
	private Board board;
	private JFrame frame;
	private boolean isFastDropping = false;

	/**
	 * Constructs a ConsoleKeyListener and initializes a hidden JFrame to capture
	 * key events.
	 *
	 * @param board The game board to control
	 */
	public ConsoleKeyListener(Board board) {
		this.board = board;

		// Create a JFrame to capture key input
		frame = new JFrame("Tetris Key Listener");
		frame.setSize(100, 100);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(this);
		frame.setFocusable(true); // We need to focus to receive key strokes
		frame.setFocusTraversalKeysEnabled(false); // Needed to capture all keys
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.setAlwaysOnTop(true); // To maintain the focus
		frame.setVisible(true);
	}

	/**
	 * Requests focus for the frame to ensure key events are captured.
	 */
	public void start() {
		frame.requestFocus();
	}

	/**
	 * Closes the frame and stops capturing key events.
	 */
	public void stop() {
		frame.dispose();
	}

	/**
	 * Handles key press events to control the Tetrimino.
	 *
	 * @param e KeyEvent representing the key pressed
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		switch (e.getKeyCode()) {
		case KeyEvent.VK_X: // Rotate
			board.rotateCurrentTetrimino();
			board.printBoard();
			break;
		case KeyEvent.VK_LEFT: // Move left
			board.moveCurrentTetriminoLeft();
			board.printBoard();
			break;
		case KeyEvent.VK_RIGHT: // Move right
			board.moveCurrentTetriminoRight();
			board.printBoard();
			break;
		case KeyEvent.VK_DOWN: // Soft drop
			if (!isFastDropping) {
				board.setFastDropping(true);
			}
			break;
		case KeyEvent.VK_SPACE: // Hard drop
			board.hardDropTetrimino();
			board.printBoard();
			break;
		}
	}

	/**
	 * Handles key release events to reset fast dropping.
	 *
	 * @param e KeyEvent representing the key released
	 */
	@Override
	public void keyReleased(KeyEvent e) {
		// Release Key DOWNARR (down faster)
		if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			board.setFastDropping(false); // Normal velocity
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// not used but i need to implement it
	}

}