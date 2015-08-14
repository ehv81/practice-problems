package rope_intranet;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/*
 * Google Code Jam Problem: Rope Intranet
 * https://code.google.com/codejam/contest/619102/dashboard#s=p0
 */

public class RopeIntersectionCounter {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader r;
		FileWriter fw;

		String inputFilename = "input.txt";
		String outputFilename = "output.txt";

		int numberOfRopes;
		int[] x;
		int[] y;
		String[] connectedWindows;

		int numberOfCases;
		int caseNumber = 1;
		
		try {
			r = new BufferedReader(new FileReader(inputFilename));
			fw = new FileWriter(outputFilename);	

			//The first line of the input contains the number of cases
			numberOfCases = Integer.parseInt(r.readLine());
			System.out.printf("Number of cases is %d%n",numberOfCases);

			while( caseNumber <= numberOfCases ){

				numberOfRopes = Integer.parseInt(r.readLine());
				x = new int[numberOfRopes];
				y = new int[numberOfRopes];

				for (int k = 0; k < numberOfRopes; k++) {
					connectedWindows = r.readLine().split(" ");
					x[k] = Integer.parseInt(connectedWindows[0]);
					y[k] = Integer.parseInt(connectedWindows[1]);
				}

//				System.out.println(Arrays.toString(x));
//				System.out.println(Arrays.toString(y));

				fw.write("Case #" + caseNumber + ": " + computeIntersections(x, y) + "\n");

				caseNumber++;
			}
			r.close();
			fw.close();
			System.out.printf("File %s successfully processed and results written to %s%n",
					inputFilename, outputFilename);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	private static int computeIntersections(int[] x, int[] y) {
		int intersections = 0;

		for (int i = 0; i < x.length - 1; i++) {
			for (int j = i+1; j < y.length; j++) {
				if (x[i] < x[j]) {
					if (y[i] > y[j])
						intersections++;
				} else { //I know, that the members of x are pairwise distinct, so x_i > x_j
					if (y[i] < y[j])
						intersections++;
				}
			}
		}
		
		return intersections;
	}
}
