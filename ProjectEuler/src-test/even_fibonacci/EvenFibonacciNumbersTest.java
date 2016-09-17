package even_fibonacci;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class EvenFibonacciNumbersTest {

	@Test
	public void testComputeEvenFibonacciSum() {
		assertEquals(44, EvenFibonacciNumbers.computeEvenFibonacciSum(34));
	}

}
