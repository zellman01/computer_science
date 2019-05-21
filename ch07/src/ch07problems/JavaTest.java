package ch07problems;

public class JavaTest {
	private static double requiredScore = 95, startingKnowledge, totalPoints = 100, totalPoints2;
	private static int months, years;
	
	public static void main(String[] args) {
		do {
			totalPoints2 = totalPoints * 0.1;
			totalPoints -= totalPoints2;
			startingKnowledge += totalPoints2;
			months += 1;
		} while (startingKnowledge <= requiredScore);
		years = months / 12;
		while (months > 12) {
			months -= 12;
		}
		if (years < 0) {
			years = 0;
		}
		System.out.println("It will take Kevin " + years + " years and " + months + " months to get ready for the exam.");
	}
}
