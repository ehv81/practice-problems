package even_fibonacci;

import java.util.Stack;

/* Project Euler Problem 2:
 * Find the sum of the even valued Fibonacci numbers less or equal 4.000.000
 */

public class EvenFibonacciNumbers {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int upperBound = 4000000;
		
		System.out.printf("The sum of even Fibonacci numbers less or equal to %d is %d.%n",
				upperBound,computeEvenFibonacciSum(upperBound));

	}


	public static int computeEvenFibonacciSum(int upperBound) {
		int f1 = 0;
		int f2 = 1;
		int tmp;
		Stack<Integer> evenFibonacciNumbers = new Stack<Integer>();

		while (f2 <= upperBound) {
			if (f2 % 2 == 0) { evenFibonacciNumbers.push(f2); };
			tmp = f1 + f2;
			f1 = f2;
			f2 = tmp;
		}
		
		int sum = 0;
		while (!evenFibonacciNumbers.empty()) {
			sum += evenFibonacciNumbers.pop();
		}

		return sum;
	}
}


