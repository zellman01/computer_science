#include "Node.h"

Node::Node() {
	next = nullptr;
}

Node::Node(Airplane & obj, int num) {
	nodeInfo = obj;
	airplaneNumber = num;
	next = nullptr;
}

Node & Node::nextPointer() {
	return *next;
}

void Node::update(Node& nextPointer) {
	next = &nextPointer;
}
