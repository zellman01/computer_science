#include "Queue.h"
#include <iostream>

using namespace std;

Queue::Queue(int totalSize) {
	size = 0;
	maxSize = totalSize;
	headNode = new Node();
}

void Queue::insertNode(Airplane & air, int airplaneNum) {
	if (size == maxSize) return;
	Node * insert = new Node(air, airplaneNum);
	if (&headNode->nextPointer() == nullptr) { // First check if the headnode is empty. Create the headnode if it is.
		headNode->update(*insert);
		size++;
		return;
	}
	
	Node * test = headNode;
	while (&test->nextPointer() != nullptr) {
		test = &test->nextPointer();
	}
	
	test->update(*insert);
	size++;
}

void Queue::deleteNode() {
	if (size == 0) return;
	Node * test = headNode;
	headNode = &headNode->nextPointer();
	delete test;
}

void Queue::list() {
	cout << &headNode->nextPointer();
}
