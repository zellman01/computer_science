package primary;

import java.util.concurrent.ThreadLocalRandom;

public class World {
	private String[] biomeNames;
	private String[][] enemyNames;
	public Biome[] biomes;
	private int screens;
	
	public World(String[] biome, int screen, String[][] enemies) {
		biomeNames = new String[biome.length];
		enemyNames = new String[biome.length][];
		enemyNames = enemies;
		biomeNames = biome;
		screens = screen;
		biomes = new Biome[screens];
		worldGen();
		
	}
	
	private void worldGen() {
		for (int i = 0; i < biomes.length; i++) {
			int biomeSel = ThreadLocalRandom.current().nextInt(0, biomeNames.length-1);
			if (i >= 2) {
				while(biomeNames[biomeSel].equalsIgnoreCase(biomes[i-1].name) || biomeNames[biomeSel].equalsIgnoreCase(biomes[i-2].name)) {
					biomeSel = ThreadLocalRandom.current().nextInt(0, biomeNames.length-1);
				}
			}
			biomes[i] = new Biome(biomeNames[biomeSel], i+1, (int)((i+1)*1.75), enemyNames[biomeSel]);
		}
	}
	
	public String toString() {
		String str = "Biomes: ";
		for (int i = 0; i < biomeNames.length; i++) {
			str += biomeNames[i];
			if (i != biomeNames.length-1) {
				str += ",";
			}
		}
		str += "\n";
		for (int i = 0; i < biomes.length; i++) {
			str += biomes[i];
			str += "\n";
		}
		return str;
	}
	
	
	public static void main(String[] args) {
		String[] test = {"Forest", "Volcano", "Jungle", "Desert", "Mesa", "Tundra"};
		String[][] test2 = new String[6][3];
		
		test2[0][0] = "Forest1"; test2[0][1] = "Forest2"; test2[0][2] = "Forest3";
		test2[1][0] = "Volcano1"; test2[1][1] = "Volcano2"; test2[1][2] = "Volcano3";
		test2[2][0] = "Jungle1"; test2[2][1] = "Jungle2"; test2[2][2] = "Jungle3";
		test2[3][0] = "Desert1"; test2[3][1] = "Desert2"; test2[3][2] = "Desert3";
		test2[4][0] = "Mesa1"; test2[4][1] = "Mesa2"; test2[4][2] = "Mesa3";
		test2[5][0] = "Tundra1"; test2[5][1] = "Tundra2"; test2[5][2] = "Tundra3";
		
		System.out.println(new World(test, 13, test2));
	}
}
