package prime_factorization;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/* Project Euler Problem 3:
 * What is the largest prime factor of the number 600851475143
 */

public class PrimeFactorizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println(computePrimes(6008514));
//		System.out.println(computePrimeFactors(600851475143L));
//		System.out.println(10 % 7);

	}

	/**
	 * @param upperBound A number greater than 1
	 * @return All prime numbers from 2 to upperBound
	 * @throws IllegalArgumentException if upperBound is less than 2
	 */
	public static List<Long> computePrimes(long upperBound) {
		if (upperBound < 2) throw new IllegalArgumentException("upperBound is less than 2");
		
		List<Long> primes = new LinkedList<Long>();
		Iterator<Long> primeIterator = primes.iterator();
		boolean isPrime = true;
		
		for (long i = 2; i <= upperBound; i++) {
			primeIterator = primes.iterator();
			isPrime = true;
			
			while (primeIterator.hasNext() && isPrime == true) {
				if (i % primeIterator.next() == 0) { isPrime = false; }
			}
			
			if (isPrime) { 
				primes.add(i); 
//				System.out.println(i);
			} 
		}
		
		return primes;
	}
	
//	public static List<Long> getPrimesBetween(long lowerBound, long upperBound) {
//		List<Long> primesBetween = new LinkedList<Long>();
//		List<Long> primes = computePrimeFactors(upperBound);
//		return null;
//	}
	
	/**
	 * @param n The integer to factor
	 * @return The prime factors of n
	 * @throws IllegalArgumentException if n is less than 2
	 */
	public static List<Long> computePrimeFactors(long n) {
		if (n < 2) throw new IllegalArgumentException("n is less than 2");

		List<Long> primes = computePrimes(n);
		List<Long> primeFactors = new LinkedList<Long>();
		for (long p : primes) {
			if (n % p == 0) { primeFactors.add(p); }
		}

		return primeFactors;
	}
}
