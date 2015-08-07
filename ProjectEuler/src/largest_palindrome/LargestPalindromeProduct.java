package largest_palindrome;

/* Project Euler Problem 4:
 * Find the largest palindrome made from the product of two 3-digit numbers.
 * https://projecteuler.net/problem=4
 */

public class LargestPalindromeProduct {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(findLargesPalindrome(3));
	}
	
	public static int findLargesPalindrome(int factorNumberDigits) {
		if (factorNumberDigits < 1) throw new IllegalArgumentException("number of digits has to be at least 1");
		int largestPalindromeFound = 1;
		int k;
		String stringRepresentation;
		int l;
		boolean isPalindrome;

		for (int i = 1; i < Math.pow(10, factorNumberDigits); i++) {
			for (int j = 1; j < Math.pow(10, factorNumberDigits); j++) {
				isPalindrome = true;
				k = i * j;
				stringRepresentation = Integer.toString(k);
				l = stringRepresentation.length();

				for (int m = 0; m < l/2; m++) {
					if (stringRepresentation.charAt(m) != stringRepresentation.charAt(l-1-m)) {
						isPalindrome = false;
					}
				}
				if (isPalindrome && k > largestPalindromeFound) { largestPalindromeFound = k; }
			}
		}

		return largestPalindromeFound;
	}

}
