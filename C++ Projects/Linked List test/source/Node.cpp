#include "../header/Node.h"

Node::Node() {
	next = nullptr;
	previous = nullptr;
	key = -1;
}

void Node::nextNode(Node * nextNode) {
	next = nextNode;
}
