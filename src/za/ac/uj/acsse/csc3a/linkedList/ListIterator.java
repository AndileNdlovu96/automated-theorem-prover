package za.ac.uj.acsse.csc3a.linkedList;

import java.util.Iterator;
/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class ListIterator<T> implements Iterator<T> {
	/**
	 * 
	 */
	LinkedList<T> list;
	/*
	 * 
	 */
	LNode<T> cursor;
	
	/**
	 * @param list
	 * @param cursor
	 */
	public ListIterator(LinkedList<T> list) {
		super();
		this.list = list;
		if (!list.isEmpty()) {
			this.cursor = list.first();
		}
	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return !list.isEmpty() && list.next(cursor) != null;
	}
	/*
	 * (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		T item = cursor.getItem();
		System.out.println("Moving the cursor in the iterator to the node next to it in the underlying list being iterated");
		cursor = list.next(cursor);
		
		System.out.println("iterator's next item: "+ item);
		return item;
	}
}
