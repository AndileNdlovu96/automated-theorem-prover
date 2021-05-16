package za.ac.uj.acsse.csc3a.binaryTree;

/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class BTNode<T>{
	/**
	 * 
	 */
	private BTNode<T> parentNode, rightNode, leftNode;
	/**
	 * 
	 */
	private T item;
	
	/**
	 * @param parentNode
	 * @param rightNode
	 * @param leftNode
	 * @param item
	 */
	public BTNode(BTNode<T> parentNode, BTNode<T> rightNode, BTNode<T> leftNode,
			T item) {
		super();
		this.parentNode = parentNode;
		this.rightNode = rightNode;
		this.leftNode = leftNode;
		this.item = item;
//		System.out.println("Instantiating a node...");
	}
	/**
	 * 
	 */
	public BTNode() {
		this(null, null, null, null);
	}

	/**
	 * @return the parentNode
	 */
	public BTNode<T> getParentNode() {
		return parentNode;
	}

	/**
	 * @param parentNode the parentNode to set
	 */
	public void setParentNode(BTNode<T> parentNode) {
		this.parentNode = parentNode;
	}

	/**
	 * @return the rightNode
	 */
	public BTNode<T> getRightNode() {
		return rightNode;
	}

	/**
	 * @param rightNode the rightNode to set
	 */
	public void setRightNode(BTNode<T> rightNode) {
		this.rightNode = rightNode;
	}

	/**
	 * @return the leftNode
	 */
	public BTNode<T> getLeftNode() {
		return leftNode;
	}

	/**
	 * @param leftNode the leftNode to set
	 */
	public void setLeftNode(BTNode<T> leftNode) {
		this.leftNode = leftNode;
	}

	/**
	 * @return the item
	 */
	public T getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(T item) {
		this.item = item;
	}
}
