#include "bst.h"

BinarySearchTree:BinarySearchTree() {
	headNode = nullptr;
}

BinarySearchTree:~BinarySearchTree() {
	delete headNode;
}