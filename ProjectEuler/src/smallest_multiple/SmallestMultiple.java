package smallest_multiple;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/*
 * Project Euler Problem 5: Smallest multiple
 * What ist the smallest positive number that is evenly 
 * divisible by all of the numbers from 1 to 20?
 * 
 * Example: 2520 is the answer for numbers 1 to 10
 */

public class SmallestMultiple {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int largestFactor = 20;
		Map<Integer,Integer> primeFactorsNeeded = getPrimeNumberCounter(largestFactor);
		findNeededPrimeFactors(largestFactor,primeFactorsNeeded);
		System.out.println("Needed prime factors are " + primeFactorsNeeded.toString());
		System.out.println("The product of those factors equals " + computeFactorProduct(primeFactorsNeeded));
	}
	
	/**
	 * @param factorMap that provides multiplicities of factors
	 * @return the product of the factors
	 */
	private static long computeFactorProduct(Map<Integer, Integer> factorMap) {
		long product = 1;
		int i, occurence;
		for (Integer f : factorMap.keySet()) {
			i = 0;
			occurence = factorMap.get(f);
			while (i < occurence) {
				product = product * f;
				i++;
			}
		}
		return product;
	}

	/**
	 * @param upperBound
	 * @return A Map that maps each prime lower than upperBound to 0
	 */
	private static Map<Integer,Integer> getPrimeNumberCounter(int upperBound) {
		List<Integer> primes = computePrimes(upperBound);
		Map<Integer,Integer> primeNumberCounter = new HashMap<>();
		for (Integer p : primes) {
			primeNumberCounter.put(p, 0);
		}
		return primeNumberCounter;
	}
	
	/**
	 * @param upperBound A number greater than 1
	 * @return All prime numbers from 2 to upperBound
	 * @throws IllegalArgumentException if upperBound is less than 2
	 */
	public static List<Integer> computePrimes(int upperBound) {
		if (upperBound < 2) throw new IllegalArgumentException("upperBound is less than 2");
		
		List<Integer> primes = new LinkedList<>();
		Iterator<Integer> primeIterator = primes.iterator();
		boolean isPrime = true;
		
		for (int i = 2; i <= upperBound; i++) {
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
	 * @param upperBound
	 * @param primeFactorsGiven A Map that counts factors, which is updated during the process.
	 * 	At the end it contains all prime factors with multiplicities, such that the resulting product
	 * 	is divisible without remainder by all positive integers up to and including upperBound
	 */
	public static void findNeededPrimeFactors(int upperBound, Map<Integer,Integer> primeFactorsGiven) {
		Map<Integer,Integer>  primeFactors;		
		for (int i = 2; i <= upperBound; i++) {
			primeFactors = getPrimeNumberCounter(i);
			int k = i;
			int f = 2;
			while (f <= k) {
				if (k % f == 0) {
					// factor f is found and then factored out with multiplicity being counted
					do { 
						k /= f; 
						primeFactors.put(f,primeFactors.get(f)+1);
					} while (k % f == 0);
				}
				f++;
			}
//			System.out.println("Factors of " + i + " are " + primeFactors.toString());
			for (Integer p : primeFactors.keySet()) {
				if ( primeFactors.get(p) > primeFactorsGiven.get(p) ) {
					primeFactorsGiven.put(p, primeFactors.get(p));
				}
			}
		}
	}

}
