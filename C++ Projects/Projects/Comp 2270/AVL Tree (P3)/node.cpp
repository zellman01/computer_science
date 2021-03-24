#include "Node.h"

Node::Node(int key) {
	this->key = key;
	bf = 0; // If this ever becomes 2 or -2, balance must happen
	left = nullptr;
	right = nullptr;
}

int Node::getKeyValue() { return key; }

void Node::updateLeft(Node & leftNode) {
	left = &leftNode;
}

void Node::updateRight(Node & rightNode) {
	right = &rightNode;
}

Node * Node::getLeftPointer() {
	return left;
}

Node * Node::getRightPointer() {
	return right;
}

bool Node::checkForChildren() {
	return (left == nullptr) && (right == nullptr);
}

void Node::updateKey(int key) {
	this->key = key;
}
