package edu.unca.csci202;


import java.util.Iterator;

/**
 * class to demo LinkedBinaryTree
 * 
 * @author Kevin Sanft
 *
 */
public class Main {

	public static void testBinaryTreeNode() {
		BinaryTreeNode<String> leaf1 = new BinaryTreeNode<String>("leaf1");
		BinaryTreeNode<String> leaf2 = new BinaryTreeNode<String>("leaf2");
		BinaryTreeNode<String> internal1 = new BinaryTreeNode<String>("internal1",leaf1,leaf2);

		BinaryTreeNode<String> leaf3 = new BinaryTreeNode<String>("leaf3");
		BinaryTreeNode<String> internal2 = new BinaryTreeNode<String>("internal2",leaf3,null);
				
		BinaryTreeNode<String> root = new BinaryTreeNode<String>("root",internal1,internal2);

		System.out.println("access leaf2..."+root.getLeft().getRight().getData());
		
		System.out.println("root.numChildren()="+root.countDescendants()); //5
	}
	
	public static void main(String[] args) {
		//testBinaryTreeNode();
		
		// Make the leaves
		LinkedBinaryTree<String> D = new LinkedBinaryTree<String>("D");
		LinkedBinaryTree<String> E = new LinkedBinaryTree<String>("E");
		LinkedBinaryTree<String> C = new LinkedBinaryTree<String>("C");

		// make sub-tree
		LinkedBinaryTree<String> B = new LinkedBinaryTree<String>("B", D, E);

		// make the root tree
		LinkedBinaryTree<String> A = new LinkedBinaryTree<String>("A", B, C);
		
		System.out.println(A);

		try {
			System.out.print("PreOrder: ");
			Iterator<String> itr = A.iteratorPreOrder();
			while(itr.hasNext()) {
				System.out.print(itr.next() + " ");
			}
			System.out.println();
		}catch(Exception e) {
			System.out.println("Need to implement preorder iterator.");
		}

		try {
			System.out.print("InOrder: ");
			Iterator<String> itr = A.iteratorInOrder();
			while(itr.hasNext()) {
				System.out.print(itr.next() + " ");
			}
			System.out.println();
		}catch(Exception e) {
			System.out.println("Need to implement inorder iterator.");
		}

		try {
			System.out.print("PostOrder: ");
			Iterator<String> itr = A.iteratorPostOrder();
			while(itr.hasNext()) {
				System.out.print(itr.next() + " ");
			}
			System.out.println();
		}catch(Exception e) {
			System.out.println("Need to implement postorder iterator.");
		}
		try {
			System.out.print("Level-Order: ");
			Iterator<String> itr = A.iteratorLevelOrder();
			while(itr.hasNext()) {
				System.out.print(itr.next() + " ");
			}
			System.out.println();
		}catch(Exception e) {
			System.out.println("Need to implement levelorder iterator.");
		}
		
		if( ! A.contains( A.getRootElement() ) ) {
			System.out.println("Need to implement contains.");
		} else {
			//tree A should also contain all other nodes
			if (!A.contains(C.getRootElement())) {
				System.out.println("Error in contains().");
			} else {
				System.out.println("contains() seems to work correctly!");
			}
		}
		
		if( A.find( E.getRootElement() ) != E.getRootElement() ) {
			System.out.println("Need to implement find.");
		} else {
			System.out.println("find() seems to work correctly!");
		}

	}

}
