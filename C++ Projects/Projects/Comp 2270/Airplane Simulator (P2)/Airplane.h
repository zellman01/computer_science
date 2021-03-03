#ifndef AIRPLANE_H
#define AIRPLANE_H

class Airplane
{
	public:
		Airplane(int, int);
		Airplane();
		void update();
		int fuel();
		bool isCrashed();
	private:
		int fuelLeft, timeWaiting, airplaneNumber;
		bool crashed;
};

#endif
