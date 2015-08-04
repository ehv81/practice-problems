package multiples_3_5;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.Computer;

public class MultiplesOfThreeAndFiveTest {

	@Test
	public void testComputeMultiplesOfThreeAndFive() {
		assertEquals(23, MultiplesOfThreeAndFive.computeMultiplesOfThreeAndFive(0, 10));
	}

}
