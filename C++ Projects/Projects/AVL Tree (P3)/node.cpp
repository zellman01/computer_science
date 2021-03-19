#include "Node.h"

Node::Node(int key) {
	this.key = key;
	left = nullptr;
	right = nullptr;
}

int Node::getKeyValue() { return key; }

void Node::updateLeft(Node & leftNode) {
	left = leftNode;
}

void Node::updateRight(Node & rightNode) {
	right = rightNode;
}