#ifndef BST_H
#define BST_H

#include "Node.h"

class BinarySearchTree {
	public:
		BinarySearchTree();
		~BinarySearchTree();
		void insertNode(Node&,Node&);
		Node * deleteNode(int, Node*); // Look into
		Node * searchNode(int,Node&);
		void display(Node &);
		Node * getRootNode();
	private:
		Node * headNode;
		Node * getPred(Node&);
};

#endif
