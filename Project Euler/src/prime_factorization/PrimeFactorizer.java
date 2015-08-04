package prime_factorization;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

/* Project Euler Problem 3:
 * Find the largest prime factor of the number 600851475143
 */

public class PrimeFactorizer {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println(computePrimeFactorsSmart(600851475143L));

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
	
	/**
	 * @param n The integer to factor
	 * @return The prime factors of n
	 * @throws IllegalArgumentException if n is less than 2
	 */
	public static SortedSet<Long> computePrimeFactors(long n) {
		if (n < 2) throw new IllegalArgumentException("n is less than 2");

		List<Long> primes = computePrimes(n);
		SortedSet<Long> primeFactors = new TreeSet<Long>();
		for (long p : primes) {
			if (n % p == 0) { primeFactors.add(p); }
		}

		return primeFactors;
	}
	
	/**
	 * @param n The integer to factor
	 * @return The prime factors of n
	 * @throws IllegalArgumentException if n is less than 2
	 */
	public static SortedSet<Long> computePrimeFactorsSmart(long n) {
		if (n < 2) throw new IllegalArgumentException("value to factor is less than 2");

		SortedSet<Long> primeFactors = new TreeSet<Long>();
		long k = n;
		long f = 2;
		while (f <= k) {
			if (k % f == 0) {
				// factor f is found and added to stack of factors
				primeFactors.add(f);
				// f is factored out completely
				do { k /= f; } while (k % f == 0);
			}
			f++;
		}
		// works because of the fundamental theorem of arithmetic (unique factorization thm)
		return primeFactors;
	}
}
