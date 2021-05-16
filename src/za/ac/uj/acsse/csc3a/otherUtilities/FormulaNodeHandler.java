package za.ac.uj.acsse.csc3a.otherUtilities;
/**
 * 
 * @author Andile Ndlovu
 *
 */
public class FormulaNodeHandler{
	/**
	 * 
	 */
	private String formula;
	/**
	 * 
	 */
	private int index;
	/**
	 * 
	 */
	private boolean sign;
	/**
	 * 
	 */
	private boolean status;
	/**
	 * 
	 */
	public FormulaNodeHandler() {
		this(null, 0, false, false);
	}
	/**
	 * @param formulaTree
	 * @param index
	 * @param sign
	 * @param status
	 */
	public FormulaNodeHandler(String formula, int index, boolean sign, boolean status) {
		super();
		this.formula = formula;
		this.index = index;
		this.sign = sign;
		this.status = status;
//		System.out.println("initializing Formula node Handler with Formula: " + formula+ ", Sign: "+ sign + ", Status: "+ status);
	}
	/**
	 * @return the formula
	 */
	public String getFormula() {
		return formula;
	}
	/**
	 * @param formula the formula to set
	 */
	public void setFormula(String formula) {
		this.formula = formula;
	}
	/**
	 * @return the index
	 */
	public int getIndex() {
		return index;
	}
	/**
	 * @param index the index to set
	 */
	public void setIndex(int index) {
		this.index = index;
	}
	/**
	 * @return the sign
	 */
	public boolean isSign() {
		return sign;
	}
	/**
	 * @param sign the sign to set
	 */
	public void setSign(boolean sign) {
		this.sign = sign;
	}
	/**
	 * @return the status
	 */
	public boolean isStatus() {
		return status;
	}
	/**
	 * @param status the status to set
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}
	
}
