#ifndef BST_H
#define BST_H

#include "Node.h"

class BinarySearchTree {
	public:
		BinarySearchTree();
		~BinarySearchTree();
		void insertNode(Node&);
		void InsertNode(int); // Override above to be able to create the node within this, and then send it over to the other insert node function
		void deleteNode(int);
		void searchNode(int);
		void display();
	private:
		Node * headNode;
}