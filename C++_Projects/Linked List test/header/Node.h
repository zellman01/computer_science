#ifndef NODE_H
#define NODE_H

class Node {
	public:
		Node();
		int key;
		Node * next, * previous;
		void nextNode(Node*);
	protected:
};

#endif
