#include "Node.h"

Node::Node() {
}

Node::Node(Airplane & obj, int num) {
	nodeInfo = obj;
	airplaneNumber = num;
}

Airplane Node::getObject() {
	return nodeInfo;
}
