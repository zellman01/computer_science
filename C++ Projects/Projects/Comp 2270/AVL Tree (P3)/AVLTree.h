#ifndef AVLTREE_H
#define AVLTREE_H

#include "Node.h"

class AVLTree
{
	public:
		AVLTree();
		void rotateLeft(Node&);
		void rotateRight(Node &);
		void rightBalance(Node&, bool &,Node&); // Needs to be worked on
		void leftBalance(Node&, bool &,Node&); // Needs to be worked on
		// Set both balances (and rotations) to return a Node* and then have it set the left or right pointer accordingly
		void insertAVL(Node*, Node*, bool&, Node&);
		Node *  nodeDelete(int, Node*); // Needs to be worked on
		Node * searchNode(int, Node&);
		void clearTree();
		Node * getHeadNode();
		void display(Node&,int=0);
	private:
		Node * headNode;
		Node * immedeatePred(Node*);
		bool _clearTree(Node*);
};

#endif
