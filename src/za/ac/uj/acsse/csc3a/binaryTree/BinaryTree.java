package za.ac.uj.acsse.csc3a.binaryTree;

import java.util.ArrayList;
import java.util.Iterator;

import za.ac.uj.acsse.csc3a.linkedList.LinkedList;
/**
 * 
 * @author Andile Ndlovu
 *
 * @param <T>
 */
public class BinaryTree<T> {
	/**
	 * 
	 */
	private BTNode<T> rootNode;
	/**
	 * 
	 */
	private int size;
	/**
	 * 
	 */
	public BinaryTree() {
		System.out.println("Initializing empty Binary Tree. null root.");
		this.rootNode = new BTNode<>();
		size = 0;
	}
	/**
	 * 
	 * @param item
	 */
	public BinaryTree(T item) {
		System.out.println("Initialize Binary Tree. Root: "+ item.toString());
		this.rootNode = new BTNode<>(null, null, null, item);
		size = 1;
	}
	/**
	 * 
	 * @param root
	 */
	public BinaryTree(BTNode<T> root) {
		System.out.println("Initialize Binary Tree. Root: "+ root.getItem().toString());
		this.rootNode = root;
		
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public BTNode<T> left(BTNode<T> node){
		System.out.println("Calling the left node of the current node.");
		if(hasLeft(node))
			return node.getLeftNode();
		return null;
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public boolean hasLeft(BTNode<T> node) {
		return (node.getLeftNode() != null);
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public BTNode<T> right(BTNode<T> node){
		System.out.println("Calling the right node of the current node.");
		if(hasRight(node))
			return node.getRightNode();
		return null;
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public boolean hasRight(BTNode<T> node) {	
		return (node.getRightNode() != null);
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public BTNode<T> parent(BTNode<T> node){
		System.out.println("Calling the parent node of the current node.");
		if(node != this.rootNode)
			return node.getParentNode();
		return null;
	}
	/**
	 * 
	 * @param node
	 * @return
	 */
	public Integer nodeDepth(BTNode<T> node){
		if(node == this.rootNode)return 0;
		else return (1+ nodeDepth(parent(node)));
	}
	/**
	 * 
	 * @return
	 */
	public boolean isEmpty() {
		return (size == 0);
	}

	/**
	 * @return the size
	 */
	public int getSize() {
		return size;
	}

	/**
	 * @param size the size to set
	 */
	public void setSize(int size) {
		this.size = size;
	}

	/**
	 * @return the rootNode
	 */
	public BTNode<T> getRootNode() {
		return rootNode;
	}
	/**
	 * 
	 * @param nodePos
	 * @return
	 */
	public int numChildren(BTNode<T> nodePos) {
		int counter = 0;
		if(hasLeft(nodePos))counter++;
		if(hasRight(nodePos))counter++;
		return counter;
	}
	/**
	 * 
	 * @param nodePos
	 * @return
	 */
	public boolean isExternalNodeInTree(BTNode<T> nodePos) {
		return (this.numChildren(nodePos) == 0);
	}
	/**
	 * 
	 * @param nodePos
	 * @return
	 */
	public Iterable<BTNode<T>> children(BTNode<T> nodePos) {
		ArrayList<BTNode<T>> children = new ArrayList<>(2);
		if(hasLeft(nodePos)) children.add(nodePos.getLeftNode());
		if(hasRight(nodePos))children.add(nodePos.getRightNode());
		return children;
	}
	/**
	 * 
	 * @param nodePos
	 * @return
	 */
	public int height(BTNode<T> nodePos) { 
		int counter = 0; // base case if positionOfNode is external
		for (BTNode<T> positionOfChildNode : children(nodePos))
			counter = Math.max(counter, 1 + height(positionOfChildNode));
		return counter;
	}
	/**
	 * 
	 * @param listOfExternals
	 * @param root
	 */
	public void InOrderNodeTraversal(LinkedList<BTNode<T>> listOfExternals, BTNode<T> root) {
		System.out.println("In Order traversal of a tree storing external nodes.");
		System.out.println("Height of node: "+ height(root));
		if (hasLeft(root)) InOrderNodeTraversal(listOfExternals, left(root));
		if(isExternalNodeInTree(root))listOfExternals.addLast(rootNode);
		if (hasRight(root))InOrderNodeTraversal(listOfExternals, right(root));
	}
	/**
	 * 
	 * @return
	 */
	public Iterator<BTNode<T>> InOrderIterator(){
		System.out.println("In order iterator of the list storing external nodes called.");
		LinkedList<BTNode<T>> list = new LinkedList<>();
		InOrderNodeTraversal(list, rootNode);
		System.out.println("Size of list of externals: "+ list.size());
		return list.iterator();
	}
	
}
