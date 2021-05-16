package za.ac.uj.acsse.csc3a.otherUtilities;

/**
 * 
 * @author Andile Ndlovu
 *
 */
public class NonBranchingFormulaHandler {
	/**
	 * 
	 */
	private FormulaNodeHandler subFormulaA, subFormulaB;
	/**
	 * 
	 */
	private boolean isNonBranching;
	/**
	 * @param subFormulaA
	 * @param subFormulaB
	 * @param isNonBranching
	 */
	public NonBranchingFormulaHandler(FormulaNodeHandler subFormulaA, FormulaNodeHandler subFormulaB,
			boolean isNonBranching) {
		super();
		this.subFormulaA = subFormulaA;
		this.subFormulaB = subFormulaB;
		this.isNonBranching = isNonBranching;
//		System.out.println("Initializing non branching formula handlers");
	}
	/**
	 * @return the subFormulaA
	 */
	public FormulaNodeHandler getSubFormulaA() {
		return subFormulaA;
	}
	/**
	 * @return the subFormulaB
	 */
	public FormulaNodeHandler getSubFormulaB() {
		return subFormulaB;
	}
	/**
	 * @return the isNonBranching
	 */
	public boolean isNonBranching() {
		return isNonBranching;
	}
}
