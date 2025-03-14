package tetris;

/**
 * The {@code Color} enum represents a set of ANSI color codes used to format
 * text output in the console. These colors can be applied to customize text
 * display in the Pente application.
 */
public enum Color {

	RED("\u001B[31m"), YELLOW("\033[33m"), RESET("\u001B[0m");

	private final String code;

	/**
	 * Constructs a {@code Color} enum instance with the specified ANSI escape code.
	 *
	 * @param code The ANSI escape code associated with the color.
	 */
	Color(String code) {
		this.code = code;
	}

	/**
	 * Retrieves the ANSI escape code for this color.
	 *
	 * @return The ANSI escape code as a {@code String}.
	 */
	public String getCode() {
		return this.code;
	}

}
