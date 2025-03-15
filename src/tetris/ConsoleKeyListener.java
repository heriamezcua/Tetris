package tetris;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class ConsoleKeyListener implements KeyListener {
	private Board board;
	private JFrame frame;

	public ConsoleKeyListener(Board board) {
		this.board = board;

		// I need to create a JFrame in order to capture the keys
		frame = new JFrame("Tetris Key Listener");
		frame.setSize(100, 100);
		frame.setUndecorated(true);
		frame.setLocationRelativeTo(null);
		frame.addKeyListener(this);
		frame.setFocusable(true); // We need to focus to receive key strokes
		frame.setFocusTraversalKeysEnabled(false); // Needed to capture all keys
		frame.setAlwaysOnTop(true); // To maintain the focus
		frame.setVisible(true);
	}

	public void start() {
		frame.requestFocus();
	}

	public void stop() {
		frame.dispose();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		System.out.println("Tecla presionada: " + KeyEvent.getKeyText(e.getKeyCode()));

		// Key X (Rotation)
		if (e.getKeyCode() == KeyEvent.VK_X) {
			board.rotateCurrentTetrimino();
			board.printBoard();
		}

		// Key ESC (Pause the game)
//        if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
//            System.out.println("PAUSE");
//            stop();
//        }

		// For later implementation of the tetriminoes movement
		switch (e.getKeyCode()) {
		case KeyEvent.VK_LEFT:
			// board.moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			// board.moveRight();
			break;
		case KeyEvent.VK_DOWN:
			// board.moveDown();
			break;
		case KeyEvent.VK_SPACE:
			// board.drop();
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
}