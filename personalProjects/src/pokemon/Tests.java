package pokemon;

public class Tests {
	private static int success = 0;
	private static int totalTests = 0;
	@SuppressWarnings("unused")
	private static void typeTests() {
		totalTests++;
		Types type = new Types();
		type.setType1("Normal");
		System.out.println("Type: Normal, NS");
		System.out.println(type.obtainType1() + ", " + type.obtainType2());
		if (type.obtainType1().equals("Normal") && type.obtainType2().equals("NS")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type2 = new Types();
		type2.setType2("Normal");
		System.out.println("Type: false");
		System.out.println(type2.validateType(type2.obtainType1()));
		if (!type2.validateType(type2.obtainType1())) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type3 = new Types();
		type3.setType1("Electric");
		type3.setType2("Fire");
		System.out.println("Type: Electric, Fire");
		System.out.println(type3.obtainType1() + ", " + type3.obtainType2());
		if (type3.obtainType1().equals("Electric") && type3.obtainType2().equals("Fire")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type4 = new Types();
		type4.setType1("Normal");
		System.out.println("Type: true");
		System.out.println(type4.validateType(type4.obtainType1()));
		if (type4.validateType(type4.obtainType1())) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type5 = new Types();
		type5.setType1("Norml");
		System.out.println("Type: NS, NS");
		System.out.println(type5.obtainType1() + ", " + type5.obtainType2());
		if (type5.obtainType1().equals("NS") && type5.obtainType2().equals("NS")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type6 = new Types();
		type6.setType2("Fre");
		System.out.println("Type: NS, NS");
		System.out.println(type6.obtainType1() + ", " + type6.obtainType2());
		if (type6.obtainType1().equals("NS") && type6.obtainType2().equals("NS")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type7 = new Types();
		type7.setType1("Normal");
		type7.setType2("Fre");
		System.out.println("Type: Normal, NS");
		System.out.println(type7.obtainType1() + ", " + type7.obtainType2());
		if (type7.obtainType1().equals("Normal") && type7.obtainType2().equals("NS")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type8 = new Types();
		type8.setType2("Normal");
		type8.setType1("Fre");
		System.out.println("Type: NS, Normal");
		System.out.println(type8.obtainType1() + ", " + type8.obtainType2());
		if (type8.obtainType1().equals("NS") && type8.obtainType2().equals("Normal")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type9 = new Types("Fire", "Water");
		System.out.println("Type: Fire, Water");
		System.out.println(type9.obtainType1() + ", " + type9.obtainType2());
		if (type9.obtainType1().equals("Fire") && type9.obtainType2().equals("Water")) {
			success++;
		}
		System.out.println();
	}
	@SuppressWarnings("unused")
	private static void nameTests() {
		totalTests++;
		Name name1 = new Name();
		name1.setName("Test");
		System.out.println("Name: Test");
		System.out.println(name1.obtainName());
		if (name1.obtainName().equals("Test")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Name name2 = new Name();
		name2.setName("Testing");
		System.out.println("Name: Testing");
		System.out.println(name2.obtainName());
		name2.setName("Hoi");
		System.out.println("Name: Testing->Hoi");
		System.out.println(name2.obtainName());
		if (name2.obtainName().equals("Hoi")) {
			success++;
		}
		System.out.println();
	}
	@SuppressWarnings("unused")
	private static void resistTests() {
		totalTests++;
		Types type1 = new Types("Fire", "NA");
		Resistance resistance1 = new Resistance(type1.obtainType1(), type1.obtainType2());
		double rest1test = resistance1.effective("Water");
		System.out.println("Resistance: 2.0");
		System.out.println(rest1test);
		if (rest1test == 2.0) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type2 = new Types("Fire", "NA");
		Resistance resistance2 = new Resistance(type2.obtainType1(), type2.obtainType2());
		double rest2test = resistance2.effective("Fire");
		System.out.println("Resistance: 0.5");
		System.out.println(rest2test);
		if (rest2test == 0.5) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type3 = new Types("Flying", "Dragon");
		Resistance resistance3 = new Resistance(type3.obtainType1(), type3.obtainType2());
		double rest3test = resistance3.effective("Ice");
		System.out.println("Resistance: 4.0");
		System.out.println(rest3test);
		if (rest3test == 4.0) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type4 = new Types("Fire");
		Resistance resistance4 = new Resistance(type4.obtainType1(), type4.obtainType2());
		double rest4test = resistance4.effective("ibe");
		System.out.println("Resistance: -1.0");
		System.out.println(rest4test);
		if (rest4test == -1.0) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type5 = new Types("Water");
		Resistance resistance5 = new Resistance(type5.obtainType1(), type5.obtainType2());
		double rest5test = resistance5.effective("Fire");
		System.out.println("Resistance: 0.5");
		System.out.println(rest5test);
		if (rest5test == 0.5) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type6 = new Types("Electric");
		Resistance resistance6 = new Resistance(type6.obtainType1(), type6.obtainType2());
		double rest6test = resistance6.effective("Normal");
		System.out.println("Resistance: 1.0");
		System.out.println(rest6test);
		if (rest6test == 1.0) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type7 = new Types("Ice");
		Resistance resistance7 = new Resistance(type7.obtainType1(), type7.obtainType2());
		double rest7test = resistance7.effective("Steel");
		System.out.println("Resistance: 2.0");
		System.out.println(rest7test);
		if (rest7test == 2.0) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Types type8 = new Types("Fighting");
		Resistance resistance8 = new Resistance(type8.obtainType1(), type8.obtainType2());
		double rest8test = resistance8.effective("Bug");
		System.out.println("Resistance: 0.5");
		System.out.println(rest8test);
		if (rest8test == 0.5) {
			success++;
		}
		System.out.println();
	}
	@SuppressWarnings("unused")
	private static void natureTests() {
		
	}
	@SuppressWarnings("unused")
	private static void fullTests() {
		totalTests++;
		Pokemon Test1 = new Pokemon("something", "Fire");
		Test1.buildPokemon();
		System.out.println("Full Build: Something");
		System.out.println(Test1.obtainName());
		if (Test1.obtainName().equals("something")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		System.out.println("Full Build: Fire");
		System.out.println(Test1.obtainType1());
		if (Test1.obtainType1().equals("Fire")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		System.out.println("Full Build: NS");
		System.out.println(Test1.obtainType2());
		if (Test1.obtainType2().equals("NS")) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		System.out.println("Full Build: 2.0");
		System.out.println(Test1.effective("Water"));
		if (Test1.effective("Water") == 2.0) {
			success++;
		}
		System.out.println();
		
		totalTests++;
		Test1.setType2("Ice");
		System.out.println("Full Build: Ice");
		System.out.println(Test1.obtainType2());
		if (Test1.obtainType2().equals("Ice")) {
			success++;
		}
		System.out.println();
	}
	public static void main(String [] args) {
		typeTests();
		//nameTests();
		resistTests();
		//fullTests();
		System.out.println(success + "/" + totalTests + " tests complete.");
		System.out.println((double) success/totalTests);
	}
}
