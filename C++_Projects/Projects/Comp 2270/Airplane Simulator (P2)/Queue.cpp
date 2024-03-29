#include "Queue.h"
#include <iostream>

using namespace std;

Queue::Queue() {
	
}

Queue::Queue(int totalSize) {
	size = 0;
	maxSize = totalSize;
	nodeArray = new Airplane[totalSize];
}

int Queue::getSize() { return size; }

bool Queue::insertNode(Airplane & air) {
	if (size >= maxSize-1) return false;
	nodeArray[size] = air;
	size++;
	return true;
}

Airplane Queue::getNode(int nodeNum) {
	if (nodeNum > size) return Airplane(-1, -1);
	return nodeArray[nodeNum];
}

bool Queue::updateNode(int nodeNum, bool landing) {
	if (nodeNum > size) return false;
	nodeArray[nodeNum].update(landing);
	bool crashed = false;
	if (nodeArray[nodeNum].isCrashed()) {
		for (int i = nodeNum; i < size; i++) {
			nodeArray[i] = nodeArray[i+1];
		}
		crashed = true;
		size--;
	}
	return crashed;
}

void Queue::sort() {
	int minCount, minValue;
	
	for (int seek = 0; seek < (size-1); seek++) {
		minCount = seek;
		minValue = nodeArray[seek].fuel();
		
		for (int index = seek+1; index < size; index++) {
			if (nodeArray[index].fuel() < minValue) {
				minValue = nodeArray[index].fuel();
				minCount = index;
			}
		}
		Airplane temp = nodeArray[seek];
		nodeArray[seek] = nodeArray[minCount];
		nodeArray[minCount] = temp;
	}
}

bool Queue::deleteNode(Airplane & temp) {
	if (size == 0) return false;
	temp = nodeArray[0];
	for (int i = 0; i < size; i++) {
		nodeArray[i] = nodeArray[i+1]; // Work on this
	}
	size--;
	return true;
}
