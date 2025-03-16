package tetris;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogManager {
	public static final Logger LOGGER = Logger.getLogger("GlobalLogger");

	static {
		try {
			FileHandler fh = new FileHandler("./logs/tetris.log", true); // Store logs in a file for debugging purposes
			fh.setFormatter(new SimpleFormatter());
			LOGGER.addHandler(fh);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

//LogManager.LOGGER.log(Level.INFO, "Tetrimino setted:", currentTetrimino);