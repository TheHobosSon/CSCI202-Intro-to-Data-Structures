package edu.unca.csci202;


import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

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
		LinkedList<T> list = new LinkedList<T>();
		traverseLevelOrder(root, list);
		if(list.contains(targetElement)) {
			return true;
		}
		return false;
	}

	@Override
	public T find(T targetElement) throws ElementNotFoundException {
		LinkedList<T> list = new LinkedList<T>();
		traverseLevelOrder(root, list);
		if(list.contains(targetElement)) {
			return targetElement;
		}
		return null;
	}

	@Override
	public Iterator<T> iterator() {
		LinkedList<T> list = new LinkedList<T>();
		traverseLevelOrder(root, list);
		return list.iterator();
	}

	@Override
	public Iterator<T> iteratorInOrder() {
		LinkedList<T> list = new LinkedList<T>();
		traverseInOrder(root, list);
		return list.iterator();
	}

	@Override
	public Iterator<T> iteratorPostOrder() {
		LinkedList<T> list = new LinkedList<T>();
		traversePostOrder(root, list);
		return list.iterator();
	}

	@Override
	public Iterator<T> iteratorLevelOrder() {
		LinkedList<T> list = new LinkedList<T>();
		traverseLevelOrder(root, list);
		return list.iterator();
	}
		
	@Override
	public Iterator<T> iteratorPreOrder() {
		LinkedList<T> list = new LinkedList<T>();
		traversePreOrder( root, list);
		return list.iterator();
	}

	/**
	 * Recursive helper method for iteratorPreOrder
	 * 
	 * @param node the root of the subtree
	 * @param list list that is built in preorder
	 */
	private void traversePreOrder(BinaryTreeNode<T> node, LinkedList<T> list) {
		if(node != null) {
			// visit node
			list.add( node.getData() );
			//recurse left
			traversePreOrder( node.getLeft(), list);
			//recurse right
			traversePreOrder( node.getRight(), list);
		}
	}
	
	/**
	 * Recursive helper method for iteratorInOrder
	 * 
	 * @param node is the root of the subtree
	 * @param list list that is built in inorder
	 */
	
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
	
	/**
	 * Recursive helper method for iteratorPostOrder
	 * 
	 * @param node is the root of the subtree
	 * @param list list that is built in postorder
	 */
	
	private void traversePostOrder(BinaryTreeNode<T> node, LinkedList<T> list) {
		if(node != null) {
			//recurse left
			traversePostOrder(node.getLeft(), list);
			//recurse right
			traversePostOrder(node.getRight(), list);
			//visit node
			list.add(node.getData());
		}
	}
	
	/**
	 * Iterative helper method for iteratorLevelOrder
	 * 
	 * @param node is the root of the tree
	 * @param list list that is built in levelorder
	 */
	
	private void traverseLevelOrder(BinaryTreeNode<T> node, LinkedList<T> list) {
		Queue<BinaryTreeNode<T>> queue = new LinkedList<BinaryTreeNode<T>>();
		queue.add(node);
		while(!queue.isEmpty()) {
			BinaryTreeNode<T> temp = queue.poll();
			list.add(temp.getData());
			if(temp.getLeft() != null) {
				queue.add(temp.getLeft());
			}
			if(temp.getRight() != null) {
				queue.add(temp.getRight());
			}
		}
		
	}

}
