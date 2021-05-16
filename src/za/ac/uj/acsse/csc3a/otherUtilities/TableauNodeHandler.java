package za.ac.uj.acsse.csc3a.otherUtilities;

import za.ac.uj.acsse.csc3a.linkedList.LinkedList;

/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class TableauNodeHandler<T> {
	/**
	 * 
	 */
	private LinkedList<T> formulaList;
	/**
	 * 
	 */
	boolean closed;
	/**
	 * @param formulaList
	 * @param closed
	 */
	public TableauNodeHandler(LinkedList<T> formulaList, boolean closed) {
		super();
		this.formulaList = formulaList;
		this.closed = closed;
		System.out.println("Initializing tableau node handler with Formula List: "+ formulaList.toString() + ", Closed: "+ closed);
	}
	/**
	 * @return the formulaList
	 */
	public LinkedList<T> getFormulaList() {
		return formulaList;
	}
	/**
	 * @param formulaList the formulaList to set
	 */
	public void setFormulaList(LinkedList<T> formulaList) {
		this.formulaList = formulaList;
	}
	/**
	 * @return the closed
	 */
	public boolean isClosed() {
		return closed;
	}
	/**
	 * @param closed the closed to set
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
}
