#ifndef QUEUE_H
#define QUEUE_H

#include "Airplane.h"

class Queue
{
	public:
		Queue();
		Queue(int);
		bool insertNode(Airplane&);
		void deleteNode();
		void sort();
		int getSize();
		bool updateNode(int); // returns if the plane crashed
		Airplane getNode(int);
	private:
		int size, maxSize;
		Airplane * nodeArray;
};

#endif
