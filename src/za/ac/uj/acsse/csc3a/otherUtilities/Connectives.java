package za.ac.uj.acsse.csc3a.otherUtilities;

/**
 * 
 * @author Andile Ndlovu
 *
 */
public enum Connectives {
	/**
	 * 
	 */
	AND('A'),
	OR('V'),
	NOT('!'),
	IMPLIES(':'),
	BI_IMPLIES('=');
	/**
	 * 
	 */
	private char symbol;
	/**
	 * 
	 * @param symbol
	 */
	private Connectives(char symbol) {
		this.symbol = symbol;
	}
	/**
	 * 
	 * @return
	 */
	public char getSymbol() {
		return this.symbol;
	}
}
