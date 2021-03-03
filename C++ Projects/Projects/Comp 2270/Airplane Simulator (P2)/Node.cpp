#include "Node.h"

Node::Node() {
}

Node::Node(Airplane & obj) {
	nodeInfo = obj;
}

Airplane Node::getObject() {
	return nodeInfo;
}

int Node::getNum() { return airplaneNumber; }
