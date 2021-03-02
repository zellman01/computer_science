#ifndef QUEUE_H
#define QUEUE_H

#include "Airplane.h"
#include "Node.h"

class Queue
{
	public:
		Queue();
		Queue(int);
		void insertNode(Airplane&, int);
		void deleteNode();
		void sort();
		int getSize();
		bool updateNode(int); // returns if the plane crashed
	private:
		int size, maxSize;
		Node * nodeArray;
};

#endif
