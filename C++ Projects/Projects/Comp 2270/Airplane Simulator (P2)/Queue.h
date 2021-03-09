#ifndef QUEUE_H
#define QUEUE_H

#include "Airplane.h"

class Queue
{
	public:
		Queue();
		Queue(int);
		bool insertNode(Airplane&);
		bool deleteNode(Airplane&);
		bool deleteNode(int);
		void sort();
		int getSize();
		bool updateNode(int, bool); // returns if the plane crashed
		Airplane getNode(int);
	private:
		int size, maxSize;
		Airplane * nodeArray;
};

#endif
