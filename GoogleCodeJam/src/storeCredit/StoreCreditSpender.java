package storeCredit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * Google Code Jam Problem: Store Credid
 * https://code.google.com/codejam/contest/351101/dashboard#s=p0
 */

public class StoreCreditSpender {
	

	/**
	 * @param args
	 */
	@SuppressWarnings("resource") //necessary, since the program does not know that the while loop below will terminate
	public static void main(String[] args) {
		BufferedReader r;
		FileWriter fw;
		
		String inputFilename = "input.txt";
		String outputFilename = "output.txt";

		int credit;
		int numberOfItems;
		int[] prices;
		String[] pricesStrings;
		
		int numberOfCases;
		int caseNumber = 1;
		
		try {
			r = new BufferedReader(new FileReader(inputFilename));
			fw = new FileWriter(outputFilename);	

			//The first line of the input contains the number of cases
			numberOfCases = Integer.parseInt(r.readLine());
			System.out.printf("Number of entries is %d%n",numberOfCases);

			while( caseNumber <= numberOfCases ){
			
				credit = Integer.parseInt(r.readLine());
				numberOfItems = Integer.parseInt(r.readLine());
				pricesStrings = r.readLine().split(" ");
				
				if (pricesStrings.length != numberOfItems)
					throw new IllegalArgumentException("Number of prices does not match number of items");
				
				prices = new int[pricesStrings.length];
				for (int i = 0; i < prices.length; i++)
					prices[i] = Integer.parseInt(pricesStrings[i]);
								
				fw.write("Case #" + caseNumber + ": " + findMatch(prices, credit) + "\n");
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
	
	public static String findMatch(int[] prices, int credit) {
        for (int i = 0; i < prices.length ; i++) {
            for (int j = i+1; j < prices.length; j++) {
                if (prices[i] + prices[j] == credit) {
                    return (i+1) + " " + (j+1);
                }
            }
        }
        return "no match";
    }

}
