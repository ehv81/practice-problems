package digit_cancelling_fractions;

import java.util.Arrays;

/* 
 * Project Euler Problem 33:
 * 
 * The fraction 49/98 is a curious fraction, as an inexperienced
 * mathematician in attempting to simplify it may incorrectly
 * believe that 49/98 = 4/8, which is correct, is obtained by
 * cancelling the 9s.
 * 
 * We shall consider fractions like, 30/50 = 3/5, to be trivial examples.
 * 
 * There are exactly four non-trivial examples of this type of fraction,
 * less than one in value, and containing two digits in the numerator
 * and denominator.
 * 
 * If the product of these four fractions is given in its lowest
 * common terms, find the value of the denominator.
 */

public class DigitCancellingFractions {

/**
 * @param args
 */
public static void main(String[] args) {
	int productP = 1;
	int productQ = 1;
	String nominator, denominator; //we have to cancel out chars, i.e., parts of strings
	char p0,p1,q0,q1; //digits of nominator and denominator
	// we have to consider fractions p/q with exactly two digits in p and q, no zero digits, and p < q
	for (int q = 12; q < 99; q++) {
		denominator = String.valueOf(q);
		q0 = denominator.charAt(0);
		q1 = denominator.charAt(1);
		for (int p = 11; p < q; p++) {
			nominator = String.valueOf(p);
			p0 = nominator.charAt(0);
			p1 = nominator.charAt(1);
			if (isCuriousFraction(p,q,p0,p1,q0,q1)) {
				System.out.printf("%d / %d is a curious fraction%n",p,q);
				productP *= p;
				productQ *= q;
			}
		}
	}
	//now we have to cancel the resulting product of courious fractions properly
	
	int[] reducedFraction = reduceFraction(productP,productQ);
	System.out.printf("The product of the curious fractions is %d / %d " +
			"and the corresponding fully reduced fraction is %d / %d.%n",
			productP,productQ,reducedFraction[0],reducedFraction[1]);
}


/**
 * @param p
 * @param q
 * @return the fully reduced representative of the fraction p / q
 */
private static int[] reduceFraction(int p, int q) {
	int min = Math.min(p, q);
	int f = 2;
	while (f <= min) {
		if (p % f == 0 && q % f == 0) {
			// factor f is found and factored out completely
			do {
				p /= f;
				q /= f;
				min /= f;
				}
			while (min % f == 0);
		}
		f++;
	}
	return new int[] {p,q};
	
}


/**
 * @param p
 * @param q
 * @param cp0
 * @param cp1
 * @param cq0
 * @param cq1
 * @return true if p / q is a curious fraction
 */
private static boolean isCuriousFraction(double p, double q, char cp0, char cp1, char cq0, char cq1) {
	//zeros must neither be cancelled out nor remain. we now that p0 and q0 != 0.
	if ( cp1 == '0' || cq1 == '0' )
		return false;

	double value = p/q;
	double p0 = Double.parseDouble(""+cp0);
	double p1 = Double.parseDouble(""+cp1);
	double q0 = Double.parseDouble(""+cq0);
	double q1 = Double.parseDouble(""+cq1);

	if (p0 == q0) {
		if ( value == p1 / q1 )
			return true;
	}
	if (p0 == q1) {
		if ( value == p1 / q0 )
			return true;
	}
	if (p1 == q0) {
		if ( value == p0 / q1 )
			return true;
	}
	if (p1 == q1) {
		if ( value == p0 / q0 )
			return true;
	}
	return false;
}

}
