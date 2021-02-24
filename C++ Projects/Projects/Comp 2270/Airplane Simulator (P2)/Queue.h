#ifndef QUEUE_H
#define QUEUE_H

#include "Airplane.h"
#include "Node.h"

class Queue
{
	public:
		Queue(int);
		void insertNode(Airplane&, int);
		void deleteNode();
		void list();
	private:
		int size, maxSize;
		Node * headNode;
};

#endif
