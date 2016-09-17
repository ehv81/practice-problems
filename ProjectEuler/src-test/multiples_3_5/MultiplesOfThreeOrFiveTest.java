package multiples_3_5;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MultiplesOfThreeOrFiveTest {

	@Test
		public void testComputeMultiplesOfThreeOrFive() {
			assertEquals(23, MultiplesOfThreeOrFive.computeMultiplesOfThreeOrFive(0, 10));
		}

}
