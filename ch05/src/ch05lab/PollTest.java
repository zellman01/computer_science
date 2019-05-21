package ch05lab;

/**
 * @author Zach Wellman
 */

public class PollTest extends Poll {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static void main(String[] args) {
		PollDisplayPanel votingMachine =
				new PollDisplayPanel("Tami", "Brian", "Liz");
		votingMachine.vote1();
		votingMachine.vote2();
		votingMachine.vote2();
		System.out.println(votingMachine);
	}
}
