package amicable_numbers;

/* Project Euler Problem 21: Amicable numbers
 * Find the largest prime factor of the number 600851475143
 * 
 * Let d(n) be defined as the sum of proper divisors of n
 * (numbers less than n which divide evenly into n).
 * If d(a) = b and d(b) = a, where a ≠ b, then a and b are
 * an amicable pair and each of a and b are called amicable numbers.
 * 
 * For example, the proper divisors of 220 are 1, 2, 4, 5, 10,
 * 11, 20, 22, 44, 55 and 110; therefore d(220) = 284.
 * The proper divisors of 284 are 1, 2, 4, 71 and 142; so d(284) = 220.
 * 
 * Evaluate the sum of all the amicable numbers under 10000.
 */

public class AmicableNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		int upperBound = 10000;
		int amicableSum = 0;
		int a;
		
		for (int i = 2; i < upperBound; i++) {
			a = sumOfProperDivisors(i);
			if ( (a != 1) && (a != i) && (sumOfProperDivisors(a) == i) ) {
//				System.out.println(i);
				amicableSum += i;
			}
		}
		
		System.out.printf("The sum of all amicable numbers below %d is %d%n",
				upperBound, amicableSum);
	}

	public static int sumOfProperDivisors(int n) {
		if (n < 2) throw new IllegalArgumentException("value to factor is less than 2");

		int sum = 0;
		for (int f = 1; f <= n/2; f++) {
			if (n % f == 0) {
				// factor f is found and added to the sum of factors
				sum += f;
			}
		}
		return sum;
	}
}
