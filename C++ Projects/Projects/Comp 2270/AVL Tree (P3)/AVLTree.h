#ifndef AVLTREE_H
#define AVLTREE_H

#include "bst.h"

class AVLTree
{
	public:
		AVLTree();
		void rotateLeft(Node&);
		void rotateRight(Node &);
		void rightBalance(Node&, bool &,Node&);
		void leftBalance(Node&, bool &,Node&);
		void insertAVL(Node*, Node*, bool&, Node&);
		void nodeDelete(int, Node*);
		Node * searchNode(int, Node&);
		void display(Node&);
		Node * getHeadNode();
	private:
		Node * headNode;
};

#endif
