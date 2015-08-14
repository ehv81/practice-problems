package largest_grid_product;

import java.io.BufferedReader;
import java.io.FileReader;

/*
 * Project Euler Problem 11: Largest product in a grid
 * 
 * What is the greatest product of four adjacent numbers in the same direction
 * (up, down, left, right, or diagonally) in the 20×20 grid?
 */

public class LargestGridProduct {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader r;
		String line;
		String[] numbers;
		int[][] grid;
		int m,n;

		String inputFilename = "test.txt";

		try {
			r = new BufferedReader(new FileReader(inputFilename));

			//The first line of the input contains the grid size in format "m x n"
			line= r.readLine();
			System.out.printf("Gridsize is %s%n",line);
			numbers = line.split(" ");
			m = Integer.parseInt(numbers[0]);
			n = Integer.parseInt(numbers[2]);
			grid = new int[m][n];

			for (int i = 0; i < m; i++) {
				line = r.readLine();
				//The expected line format are integers seperated by whitespaces
				numbers = line.split(" ");
				for (int j = 0; j < n; j++) {
					grid[i][j] = Integer.parseInt(numbers[j]);
				}
			}
			r.close();
			
//			for (int i = 0; i < m; i++) {
//				line = "";
//				for (int j = 0; j < n; j++) {
//					line += grid[i][j] + " ";
//				}
//				System.out.println(line);
//			}

			int maxProduct = 0;
			String maxProductPostion = "nowhere";
			int product;
			
			for (int i = 0; i < m; i++) {
				for (int j = 0; j < n; j++) {
					// if horizontal quadruple exists
					if (j < n-3) {
						product = grid[i][j] * grid[i][j+1] * grid[i][j+2] *  grid[i][j+3];
						if ( product > maxProduct) {
							maxProduct = product;
							maxProductPostion = "horizontally from index (" + (i+1) + "," + (j+1) + ")";
							System.out.printf("The maximal product found so far equals %d and is located %s.%n",
									maxProduct,maxProductPostion);
						}
					}
					// if vertical quadruple exists
					if (i < m-3) {
						product = grid[i][j] * grid[i+1][j] * grid[i+2][j] *  grid[i+3][j];
						if ( product > maxProduct) {
							maxProduct = product;
							maxProductPostion = "vertically from index (" + (i+1) + "," + (j+1) + ")";
							System.out.printf("The maximal product found so far equals %d and is located %s.%n",
									maxProduct,maxProductPostion);
						}
					}
					// if diagonal quadruple exists
					if ( (i < m-3) && (j < n-3) ){
						product = grid[i][j] * grid[i+1][j+1] * grid[i+2][j+2] *  grid[i+3][j+3];
						if ( product > maxProduct) {
							maxProduct = product;
							maxProductPostion = "diagonally from index (" + (i+1) + "," + (j+1) + ")";
							System.out.printf("The maximal product found so far equals %d and is located %s.%n",
									maxProduct,maxProductPostion);
						}
					}
				}
			}
			
			System.out.printf("File %s successfully processed.%n" +
					"The maximal product found equals %d and is located %s.%n",
					inputFilename,maxProduct,maxProductPostion);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
