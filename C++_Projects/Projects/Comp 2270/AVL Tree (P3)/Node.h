#ifndef NODE_H
#define NODE_H

#include <iostream>

class Node {
	public:
		Node();
		Node(int);
		~Node();
		int getKeyValue();
		Node *& getLeftPointer();
		Node *& getRightPointer();
		void updateLeft(Node&);
		void updateRight(Node&);
		void updateLeft(Node*);
		void updateRight(Node*);
		bool hasChildren();
		void updateKey(int);
		int getBF();
		void updateBF(int);
		void replaceNode(Node);
	private:
		int key, bf;
		Node * left, * right;
};

#endif
