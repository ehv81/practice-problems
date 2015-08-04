package prime_factorization;

import static org.junit.Assert.*;
import static prime_factorization.PrimeFactorizer.computePrimeFactors;
import static prime_factorization.PrimeFactorizer.computePrimes;

import org.junit.Test;
import org.junit.runner.Computer;

public class PrimeFactorizerTest {

	@Test (expected = IllegalArgumentException.class)
	public void testComputePrimes() {
		computePrimes(1);
	}

	@Test
	public void testComputePrimeFactors() {
		String factorsOf13195 = "[5, 7, 13, 29]";
		System.out.println(factorsOf13195);
		System.out.println(computePrimeFactors(13195));
		assertTrue(factorsOf13195.equals(computePrimeFactors(13195).toString()));
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testComputePrimeFactors2() {
		computePrimeFactors(-3);
	}

}
