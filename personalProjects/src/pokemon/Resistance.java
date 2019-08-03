package pokemon;

public class Resistance {
	private String type1, type2;
	private final String[] typeName = {"Normal", "Fire", "Water", "Grass", "Electric", "Ground", "Rock", "Flying", "Ice", "Poison", "Steel", "Dragon", "Fairy", "Psychic", "Dark", "Ghost", "Fighting", "Bug"};
	// Constructor should not be hard-set. Should be set by using obtainType<type#>
	// Constructor does internally check if the type name is correct.
	public Resistance(String type1a, String type2a) {
		type1 = null;
		type2 = null;
		for (String test : typeName) {
			if (test.equals(type1a)) {
				type1 = type1a;
			}
		}
		if (!type2a.equalsIgnoreCase("ns")) {
			for (String test : typeName) {
				if (test.equals(type2a)) {
					type2 = type2a;
				}
			}
		}
	}

	public double effective(String moveType) {
		for (String test : typeName) {
			if (test.equals(moveType)) {
				if (type2 == null) {
					return checkEffOne(moveType);
				}
				return checkEffTwo(moveType);
			}
		}
		return -1.0;
	}
	// TODO: Set up resistance/weakness chart
	// TODO: checkEffOne needs fully tested
	private double checkEffOne(String moveType) {
		switch (type1) {
		case "Normal":
			switch (moveType) {
			case "Fighting":
				return 2.0;
			case "Ghost":
				return 0.0;
			}
		case "Fire":
			switch (moveType) {
			case "Water":
			case "Ground":
			case "Rock":
				return 2;
			case "Fire":
			case "Grass":
			case "Ice":
			case "Bug":
			case "Steel":
			case "Fairy":
				return 0.5;
			}
		case "Water":
			switch (moveType) {
			case "Fire":
			case "Water":
			case "Ice":
			case "Steel":
				return 0.5;
			case "Electric":
			case "Grass":
				return 2;
			}
		case "Electric":
			switch (moveType) {
			case "Electric":
			case "Flying":
			case "Steel":
				return 0.5;
			case "Ground":
				return 2;
			}
		case "Grass":
			switch (moveType) {
			case "Water":
			case "Electric":
			case "Grass":
			case "Ground":
				return 0.5;
			case "Fire":
			case "Ice":
			case "Poison":
			case "Flying":
			case "Bug":
				return 2;
			}
		case "Ice":
			switch (moveType) {
			case "Ice":
				return 0.5;
			case "Fire":
			case "Fighting":
			case "Rock":
			case "Steel":
				return 2;
			}
		case "Fighting":
			switch (moveType) {
			case "Bug":
			case "Rock":
			case "Dark":
				return 0.5;
			case "Flying":
			case "Psychic":
			case "Fairy":
				return 2;
			}
		case "Poison":
			switch (moveType) {
			case "Grass":
			case "Fighting":
			case "Poison":
			case "Bug":
			case "Fairy":
				return 0.5;
			case "Ground":
			case "Psychic":
				return 2;
			}
		case "Ground":
			switch (moveType) {
			case "Electric":
				return 0;
			case "Poison":
			case "Rock":
				return 0.5;
			case "Water":
			case "Grass":
			case "Ice":
				return 2;
			}
		case "Flying":
			switch (moveType) {
			case "Ground":
				return 0;
			case "Grass":
			case "Fighting":
			case "Bug":
				return 0.5;
			case "Electric":
			case "Ice":
			case "Rock":
				return 2;
			}
		case "Psychic":
			switch (moveType) {
			case "Fighting":
			case "Psychic":
				return 0.5;
			case "Bug":
			case "Ghost":
			case "Dark":
				return 2;
			}
		case "Bug":
			switch (moveType) {
			case "Grass":
			case "Fighting":
			case "Ground":
				return 0.5;
			case "Fire":
			case "Flying":
			case "Rock":
				return 2;
			}
		case "Rock":
			switch (moveType) {
			case "Normal":
			case "Fire":
			case "Poison":
			case "Flying":
				return 0.5;
			case "Water":
			case "Grass":
			case "Fighting":
			case "Ground":
			case "Steel":
				return 2;
			}
		case "Ghost":
			switch (moveType) {
			case "Normal":
			case "Fighting":
				return 0;
			case "Poison":
			case "Bug":
				return 0.5;
			case "Ghost":
			case "Dark":
				return 2;
			}
		case "Dragon":
			switch (moveType) {
			case "Fire":
			case "Water":
			case "Electric":
			case "Grass":
				return 0.5;
			case "Ice":
			case "Dragon":
			case "Fairy":
				return 2;
			}
		case "Dark":
			switch (moveType) {
			case "Psychic":
				return 0;
			case "Ghost":
			case "Dark":
				return 0.5;
			case "Fighting":
			case "Bug":
			case "Fairy":
				return 2;
			}
		case "Steel":
			switch (moveType) {
			case "Poison":
				return 0;
			case "Normal":
			case "Grass":
			case "Ice":
			case "Flying":
			case "Psychic":
			case "Bug":
			case "Rock":
			case "Dragon":
			case "Steel":
			case "Fairy":
				return 0.5;
			case "Fire":
			case "Fighting":
			case "Ground":
				return 2;
			}
		case "Fairy":
			switch (moveType) {
			case "Dragon":
				return 0;
			case "Fighting":
			case "Bug":
			case "Dark":
				return 0.5;
			case "Poison":
			case "Steel":
				return 2;
			}
		}
		return 1;
	}

	private double checkEffTwo(String moveType) {
		return -2;
	}
}
