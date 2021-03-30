#ifndef AVLTREE_H
#define AVLTREE_H

#include "Node.h"

class AVLTree
{
	public:
		AVLTree();
		void rotateLeft(Node&);
		void rotateRight(Node &);
		void rightBalance(Node&, bool &,Node&);
		void leftBalance(Node&, bool &,Node&);
		void insertAVL(Node*, Node*, bool&, Node&); // Needs to be worked on
		void nodeDelete(int, Node*);
		Node * searchNode(int, Node&);
		void clearTree();
		Node * getHeadNode();
		void display(Node&,int=0);
	private:
		Node * headNode;
		void _clearTree(Node*); // Work on later
};

#endif
