#ifndef NODE_H
#define NODE_H

#include "Airplane.h"

class Node
{
	public:
		Node();
		Node(Airplane&, int);
		Node& nextPointer();
		void update(Node&);
	private:
		Node * next;
		Airplane nodeInfo;
		int airplaneNumber;
};

#endif
