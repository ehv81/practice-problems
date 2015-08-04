package even_fibonacci;

import static org.junit.Assert.*;

import multiples_3_5.MultiplesOfThreeAndFive;

import org.junit.Test;

public class EvenFibonacciNumbersTest {

	@Test
	public void testComputeEvenFibonacciSum() {
		assertEquals(44, EvenFibonacciNumbers.computeEvenFibonacciSum(34));
	}

}
