package multiples_3_5;

import java.util.Stack;

/* Project Euler Problem 1:
 * Find the sum of all the multiples of 3 or 5 below 1000.
 */

public class MultiplesOfThreeOrFive {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int from = 0;
		int to = 1000;
		
		System.out.printf("The sum of all integers divisible by 3 or 5" +
				" between %d and %d equals %d.%n",
				from,to,computeMultiplesOfThreeOrFive(from, to));
	}


	public static int computeMultiplesOfThreeOrFive(int from, int to) {
		int sum = 0;
		Stack<Integer> divisibleIntegers = new Stack<Integer>();

		for (int i = from; i < to; i++) {
			if (i % 3 == 0) { divisibleIntegers.push(i); }
			else { if (i % 5 == 0) { divisibleIntegers.push(i); } }
		}

		while (!divisibleIntegers.empty()) {
			sum += divisibleIntegers.pop();
		}

		return sum;
	}
}
