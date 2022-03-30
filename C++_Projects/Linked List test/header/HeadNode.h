#include "Node.h"

#ifndef HEADNODE_H
#define HEADNODE_H

class HeadNode : public Node {
	public:
		signed int nodeAmount;
		void increaseAmount() { nodeAmount++;}
		void decreseAmount() { nodeAmount--;}
	protected:
};

#endif
