package za.ac.uj.acsse.csc3a.queue;

import za.ac.uj.acsse.csc3a.linkedList.LinkedList;

/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class LinkedQueue<T> {
	/**
	 * 
	 */
	private LinkedList<T> list = new LinkedList<>(); // an empty list
	/**
	 * 
	 */
	public LinkedQueue(){/*System.out.println("Initialize empty queue");*/} // new queue relies on the initially empty list
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
	public void enqueue(T item){/*System.out.println("Add "+ item.toString()+ " to the back of the queue list");*/list.addLast(item);} 
	/**
	 * 
	 * @return
	 */
	public T first(){/*System.out.println("the node " + list.first().getItem() + " is the front node on the queue list");*/return list.first().getItem();} 
	/**
	 * 
	 * @return
	 */
	public T dequeue(){/*if(list.first()!= null)System.out.println("Removing " + list.first().getItem() + " from the queue list");*/return list.remove(list.first());}
}
