/* Project Euler Problem 7:
 * Find the 10001st prime.
 */

package specific_prime;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class SpecificPrime {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int k = 10001;
		System.out.printf("Prime #%d is %d.%n",k,computeKthPrime(k));
	}

	public static long computeKthPrime(int k) {
		if (k < 1) throw new IllegalArgumentException("parameter has to be a positive int");
		
		//2 and 3 are the only consecutive primes. This check in the beginning allows
		//to skip even numbers later on, which should result in a slight performance increase.
		if (k == 1) {return 2;}
		if (k == 2) {return 3;}

		List<Long> primes = new LinkedList<Long>();
		primes.add(2L);
		primes.add(3L);
		long lastPrime = 3L;
		
		Iterator<Long> primeIterator = primes.iterator();
		long n = 5L;
		boolean nIsPrime;
		
		while (primes.size() < k) {
			nIsPrime = true;
			primeIterator = primes.iterator();

			while (primeIterator.hasNext() && nIsPrime == true) {
				if (n % primeIterator.next() == 0) {
					nIsPrime = false;
				}
			}

			if (nIsPrime) {
				lastPrime = n;
				primes.add(lastPrime); 
			}
			//only consider odd numbers
			n += 2;
		}
//		System.out.println(primes.toString());
		return lastPrime;
	}

}
