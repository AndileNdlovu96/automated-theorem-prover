package za.ac.uj.acsse.csc3a.linkedList;

import java.util.Iterator;
/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class LinkedList<T> implements Iterable<T>{
	/**
	 * 
	 */
	private LNode<T> header;
	/**
	 * 
	 */
	private LNode<T> trailer;
	/**
	 * 
	 */
	private int size;
	
	/**
	 * 
	 */
	public LinkedList() {
		trailer = new LNode<T>(null, null, null);
		header = new LNode<T>(null, trailer, null);
		trailer.setPrevNode(header);
		size = 0;
//		System.out.println("initializing an empty list");
	}
	/**
	 * 
	 * @return
	 */
	public int size(){ return size; } 
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() { return (size == 0); }
	/**
	 * 
	 * @param item
	 * @return
	 */
	public LNode<T> addFirst(T item) {
//		System.out.println("Adding "+ item.toString()+ " after the header.");
		LNode<T> node = new LNode<T>(header, header.getNextNode(), item);
		header.getNextNode().setPrevNode(node);
		header.setNextNode(node);
		size++;
		return node;
	}
	/**
	 * 
	 * @param item
	 * @return
	 */
	public LNode<T> addLast(T item) {
//		System.out.println("Adding "+ item.toString()+ " before the trailer.");
		LNode<T> node = new LNode<T>(trailer.getPrevNode(), trailer, item);
		trailer.getPrevNode().setNextNode(node);
		trailer.setPrevNode(node);
		size++;
		return node;
	}
	/**
	 * 
	 * @param node
	 * @param item
	 * @return
	 */
	LNode<T> addAfter(LNode<T> node, T item){
//		System.out.println("Adding "+ item.toString()+ " after "+ node.getItem().toString()+ " and before "+ node.getNextNode().getItem().toString());
		return addBetween(node, node.getNextNode(), item);
	}
	/**
	 * 
	 * @param node
	 * @param item
	 * @return
	 */
	LNode<T> addBefore(LNode<T> node, T item){
//		System.out.println("Adding "+ item.toString()+ " before "+ node.getItem().toString()+ " and after "+ node.getNextNode().getItem().toString());
		return addBetween(node.getPrevNode(), node, item);
	}
	/**
	 * 
	 * @param previous
	 * @param next
	 * @param item
	 * @return
	 */
	private LNode<T> addBetween(LNode<T> previous, LNode<T> next, T item) {
//		System.out.println("Adding "+ item.toString()+ " between "+ previous.getItem().toString() + " & " + next.getItem().toString());
		LNode<T> newestNode = new LNode<>(previous, next, item); // create and link a new node
		previous.setNextNode(newestNode);
		next.setPrevNode(newestNode);
		this.size++;
		return newestNode;
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public T remove(LNode<T> node) {
		if(node == null || isEmpty())return null;
//		System.out.println("Removing "+ node.getItem().toString());
		T element = node.getItem();

		LNode<T> predecessor = node.getPrevNode();
		LNode<T> successor = node.getNextNode();
		predecessor.setNextNode(successor);
		successor.setPrevNode(predecessor);
		node.setNextNode(null);
		node.setPrevNode(null);
		node.setItem(null);
		size--;
//		System.out.println(element.toString()+" is removed");
		return element;
	}
	/**
	 * 
	 * @return
	 */
	public LNode<T> first(){
//		if(header.getNextNode() != trailer)System.out.println("The first node: "+header.getNextNode().getItem().toString());
		if(isEmpty())return null;	
		return header.getNextNode();
	}
	/**
	 * 
	 * @return
	 */
	public LNode<T> last(){
//		if(trailer.getPrevNode() != header)System.out.println("The last node: "+trailer.getPrevNode().getItem().toString());
		if(isEmpty())return null;
		return trailer.getPrevNode();
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public LNode<T> next(LNode<T> node){
//		if(node.getNextNode() != null)System.out.println("The next node of "+node.getItem().toString()+" is "+ node.getNextNode().getItem());
		return node.getNextNode();
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public LNode<T> prev(LNode<T> node){
//		if(node.getPrevNode() != null)System.out.println("The prev node of "+node.getItem().toString()+" is "+ node.getPrevNode().getItem());
		return node.getPrevNode();
	}
	/**
	 * 
	 * @param item
	 * @return
	 */
	public LNode<T> search(T item) {
//		System.out.println("Searching for "+ item+ " in list");
		LNode<T> currentNode = header.getNextNode();
		
		while (currentNode != trailer) {
			if (currentNode.getItem().equals(item)) {
				return currentNode;
			}
			currentNode = currentNode.getNextNode();
		}
		return null;
	}
	/**
	 * 
	 */
	public Iterator<T> iterator() {
//		System.out.println("Instantiate an iterator over this list "+ this.toString());
		return new ListIterator<T>(this);
	}
	
}
