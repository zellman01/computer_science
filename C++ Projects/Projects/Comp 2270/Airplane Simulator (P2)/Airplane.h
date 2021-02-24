#ifndef AIRPLANE_H
#define AIRPLANE_H

class Airplane
{
	public:
		Airplane(int);
		Airplane();
		void update();
		int fuel();
		bool isCrashed();
	private:
		int fuelLeft;
		bool crashed;
};

#endif
