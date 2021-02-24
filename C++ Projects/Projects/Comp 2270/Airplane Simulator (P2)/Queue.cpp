#include "Queue.h"
#include <iostream>

using namespace std;

Queue::Queue(int totalSize) {
	size = 0;
	maxSize = totalSize;
	nodeArray = new Node[totalSize];
}

int Queue::getSize() { return size; }

void Queue::insertNode(Airplane & air, int airplaneNum) {
	if (size == maxSize) return;
	Node insert(air, airplaneNum);
	nodeArray[size] = insert;
	size++;
}

void Queue::sort() {
	int minCount, minValue;
	
	for (int seek = 0; seek < (size-1); seek++) {
		minCount = seek;
		minValue = nodeArray[seek].getObject().fuel();
		
		for (int index = seek+1; index < size; index++) {
			if (nodeArray[index].getObject().fuel() > minValue) {
				minValue = nodeArray[index].getObject().fuel();
				minCount = index;
			}
		}
		Node temp = nodeArray[seek];
		nodeArray[minCount] = nodeArray[seek];
		nodeArray[seek] = temp;
	}
}

void Queue::deleteNode() {
	if (size == 0) return;
	for (int i = 0; i < size; i++) {
		nodeArray[i] = nodeArray[i+1]; // Work on this
	}
	size--;
}
