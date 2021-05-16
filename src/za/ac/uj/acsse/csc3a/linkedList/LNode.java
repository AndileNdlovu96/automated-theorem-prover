package za.ac.uj.acsse.csc3a.linkedList;

/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class LNode<T> {
	/**
	 * 
	 */
	private LNode<T> nextNode;
	/**
	 * 
	 */
	private LNode<T> prevNode;
	/**
	 * 
	 */
	private T item;
	/**
	 * @param nextNode
	 * @param prevNode
	 * @param item
	 */
	public LNode(LNode<T> prevNode, LNode<T> nextNode, T item) {
		super();
		this.nextNode = nextNode;
		this.prevNode = prevNode;
		this.item = item;
	}
	/**
	 * 
	 */
	public LNode() {
		this(null, null, null);
	}
	/**
	 * @return the nextNode
	 */
	public LNode<T> getNextNode() {
		return nextNode;
	}
	/**
	 * @param nextNode the nextNode to set
	 */
	public void setNextNode(LNode<T> nextNode) {
		this.nextNode = nextNode;
	}
	
	/**
	 * @return the prevtNode
	 */
	public LNode<T> getPrevNode() {
		return prevNode;
	}
	/**
	 * @param prevNode the prevNode to set
	 */
	public void setPrevNode(LNode<T> prevNode) {
		this.prevNode = prevNode;
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
