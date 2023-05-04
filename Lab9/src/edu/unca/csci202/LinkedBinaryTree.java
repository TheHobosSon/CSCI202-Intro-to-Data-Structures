package edu.unca.csci202;


import java.util.Iterator;
import java.util.LinkedList;

/**
 * Binary Tree data structure
 *
 * @param <T>
 */
public class LinkedBinaryTree<T> implements BinaryTreeADT<T>, Iterable<T> {	
	// instance variables
	protected BinaryTreeNode<T> root;
	protected int modCount; //
	
	// Constructors
	public LinkedBinaryTree() {
		this.root = null;
	}
	public LinkedBinaryTree(T data) {
		this.root =  new BinaryTreeNode<T>(data);
	}
	public LinkedBinaryTree(T data, LinkedBinaryTree<T> left, LinkedBinaryTree<T> right) {
		this.root = new BinaryTreeNode<T>(data);
		if (left!=null) {
			this.root.setLeft( left.getRootNode()  );
		}
		if (right!=null) {
			this.root.setRight( right.getRootNode() );
		}
	}
	
	
	/**
	 * Print the tree
	 */
	public String toString() {
		return print(root, 0);
	}
	
	/** 
	 * Print a subtree
	 */
	String print(BinaryTreeNode<T> node, int level) {
		String ret = "";
		if(node != null) {
			for(int i=0;i<level;i++) {
				ret += "\t";
			}
			ret += node.getData();
			ret += "\n";
			// recurse left
			ret += print(node.getLeft(), level+1);
			// recurse right
			ret += print(node.getRight(), level+1);
		}
		return ret;
	}
	
	
	/** return the root node of this subtree
	 * 
	 * @return root node of sub tree
	 */
	public BinaryTreeNode<T> getRootNode(){
		return this.root;
	}
	

	@Override
	public T getRootElement() {
		if(root == null) {
			return null;
		}
		return this.root.getData();
	}

	@Override
	public boolean isEmpty() {
		if(root == null) {
			return true;
		}
		return false;
	}

	@Override
	public int size() {
		if(isEmpty()) {
			return 0;
		}
		return root.countDescendants() + 1;
	}

	@Override
	public boolean contains(T targetElement) {
		// solved in Lab8, override in LinkedBinarySearchTree
		return false;
	}

	@Override
	public T find(T targetElement) throws ElementNotFoundException {
		// solved in Lab8, override in LinkedBinarySearchTree
		return null;
	}

	@Override
	public Iterator<T> iteratorInOrder() {
		LinkedList<T> list = new LinkedList<T>();
		traverseInOrder(root, list);
		return list.iterator();
	}
	
	// You do not need to implement these (implemented in Lab8)
	@Override
	public Iterator<T> iterator() {
		// implemented in Lab8
		return null;
	}
	@Override
	public Iterator<T> iteratorPreOrder() {
		// implemented in Lab8
		return null;
	}
	@Override
	public Iterator<T> iteratorPostOrder() {
		// implemented in Lab8
		return null;
	}
	@Override
	public Iterator<T> iteratorLevelOrder() {
		// implemented in Lab8
		return null;
	}
	
	private void traverseInOrder(BinaryTreeNode<T> node, LinkedList<T> list) {
		if(node != null) {
			//recurse left
			traverseInOrder(node.getLeft(), list);
			//visit node
			list.add(node.getData());
			//recurse right
			traverseInOrder(node.getRight(), list);
		}
	}
	
}
