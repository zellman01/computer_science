#include "Node.h"
#include <iostream>
using namespace std;

Node::Node() {
	bf = -5;
	left = nullptr;
	right = nullptr;
}

Node::Node(int keya) {
	key = keya;
	bf = 0; // If this ever becomes 2 or -2, balance must happen
	left = nullptr;
	right = nullptr;
}

Node::~Node() {
	delete left, right;
}

int Node::getKeyValue() { return key; }

void Node::updateLeft(Node & leftNode) {
	left = &leftNode;
}

void Node::updateRight(Node & rightNode) {
	right = &rightNode;
}

void Node::updateLeft(Node * leftNode) {
	left = leftNode;
}

void Node::updateRight(Node * rightNode) {
	right = rightNode;
}

Node *& Node::getLeftPointer() {
	return left;
}

Node *& Node::getRightPointer() {
	return right;
}

bool Node::hasChildren() {
	return !((left == nullptr) && (right == nullptr));
}

void Node::updateKey(int key) {
	this->key = key;
}

int Node::getBF() {
	return bf;
}

void Node::updateBF(int newBF) {
	bf = newBF;
}

void Node::replaceNode(Node nodea) {
	cout << "Test";
	key = nodea.getKeyValue();
	cout << "aaaaaaa";
	left = nodea.getLeftPointer();
	cout << "Test 2";
	right = nodea.getRightPointer();
	cout << "Test 3";
	bf = nodea.getBF();
	cout << "Test 4";
}
