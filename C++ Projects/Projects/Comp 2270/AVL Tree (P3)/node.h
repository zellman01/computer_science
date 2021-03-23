#ifndef NODE_H
#define NODE_H

#include <iostream>

class Node {
	public:
		Node(int);
		int getKeyValue();
		void updateLeft(Node&);
		void updateRight(Node&);
	private:
		int key;
		Node * left, * right;
}