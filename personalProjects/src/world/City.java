package world;

public class City {
	// Supposed to represent a city
	Building[] buildings;
	
	public City() {
		buildings = new Building[5];
		this.start();
	}
	
	private void start() {
		for (int i = 0; i < buildings.length; i++) {
			buildings[i] = new Building(null, i, false);
		}
	}
}
