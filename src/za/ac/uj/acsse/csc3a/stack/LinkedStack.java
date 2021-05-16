package za.ac.uj.acsse.csc3a.stack;

import za.ac.uj.acsse.csc3a.linkedList.LinkedList;

/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class LinkedStack<T> {
	/**
	 * 
	 */
	private LinkedList<T> list = new LinkedList<>();
	/**
	 * 
	 */
	public LinkedStack(){/*System.out.println("Initialize empty stack");*/}
	/**
	 * 
	 * @return
	 */
	public int size(){return list.size();}
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty(){return list.isEmpty();}
	/**
	 * 
	 * @param item
	 */
	public void push(T item){/*System.out.println("Add "+ item.toString()+ "to the front of the stack list");*/list.addFirst(item);}
	/**
	 * 
	 * @return
	 */
	public T top(){/*System.out.println("the node " + list.first().getItem() + "is the front node on the stack list");*/return list.first().getItem();}
	/**
	 * 
	 * @return
	 */
	public T pop(){/*System.out.println("Removing " + list.first().getItem() + " from the stack list");*/return list.remove(list.first());}
}
