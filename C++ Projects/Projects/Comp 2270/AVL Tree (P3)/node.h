#ifndef NODE_H
#define NODE_H

#include <iostream>

class Node {
	public:
		Node(int);
		int getKeyValue();
		Node * getLeftPointer();
		Node * getRightPointer();
		void updateLeft(Node&);
		void updateRight(Node&);
		bool checkForChildren();
		void updateKey(int);
	private:
		int key, bf;
		Node * left, * right;
};

#endif
