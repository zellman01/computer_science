#ifndef NODE_H
#define NODE_H

#include "Airplane.h"

class Node
{
	public:
		Node();
		Node(Airplane&, int);
		Airplane getObject();
	private:
		Airplane nodeInfo;
		int airplaneNumber;
};

#endif
