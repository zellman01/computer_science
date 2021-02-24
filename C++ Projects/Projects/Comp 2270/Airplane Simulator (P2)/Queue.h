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
		void sort();
		int getSize();
	private:
		int size, maxSize;
		Node * nodeArray;
};

#endif
