package smallest_scalar_product;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

/*
 * Google Code Jam Problem: Smallest Scalar Product
 * https://code.google.com/codejam/contest/32016/dashboard#s=p0
 */

public class SmallestScalarProductFinder {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader r;
		FileWriter fw;
		
		String inputFilename = "input.txt";
		String outputFilename = "output.txt";

		int dimension;
		int[] v1;
		int[] v2;
		String[] coordinates;
		
		int numberOfCases;
		int caseNumber = 1;
		
		try {
			r = new BufferedReader(new FileReader(inputFilename));
			fw = new FileWriter(outputFilename);	

			//The first line of the input contains the number of cases
			numberOfCases = Integer.parseInt(r.readLine());
			System.out.printf("Number of entries is %d%n",numberOfCases);

			while( caseNumber <= numberOfCases ){
			
				dimension = Integer.parseInt(r.readLine());
				v1 = new int[dimension];
				v2 = new int[dimension];
				
				coordinates = r.readLine().split(" ");
				for (int i = 0; i < dimension; i++)
					v1[i] = Integer.parseInt(coordinates[i]);
				coordinates = r.readLine().split(" ");
				for (int i = 0; i < dimension; i++)
					v2[i] = Integer.parseInt(coordinates[i]);
				
				//I conjecture, that the minimum value is found when the max of v1 is multiplied with the min of v2
				//and so on -> "anti product" on sorted arrays
				Arrays.sort(v1);
				Arrays.sort(v2);

//				System.out.println(Arrays.toString(v1));
//				System.out.println(Arrays.toString(v2));
								
				fw.write("Case #" + caseNumber + ": " + computeAntiProduct(v1, v2) + "\n");

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

	private static int computeAntiProduct(int[] v1, int[] v2) {
		int product = 0;
		
		for (int i = 0; i < v1.length; i++) {
			product += v1[i] * v2[v2.length - 1 - i];
		}
		
		return product;
	}
}
