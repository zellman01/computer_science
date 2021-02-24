#ifndef AIRPLANE_H
#define AIRPLANE_H

class Airplane
{
	public:
		Airplane(int);
		Airplane();
		void update();
	private:
		int fuelLeft;
		bool crashed;
};

#endif
