package freecellStatistics;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * Google Code Jam Problem: Freecell Statistics
 * https://code.google.com/codejam/contest/1145485/dashboard
 */

public class FreecellStatisticChecker {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader r;
		String line;
		String[] data;
		String result;
		FileWriter fw;
		
		String inputFilename = "input.txt";
		String outputFilename = "output.txt";
		
		long n;
		int pd,pg;
		int i = 1;

		try {
			r = new BufferedReader(new FileReader(inputFilename));
			fw= new FileWriter(outputFilename);	
			
			//The first line of the input contains the number of cases
			System.out.printf("Number of entries is %d%n",Integer.parseInt(r.readLine()));

			while((line= r.readLine()) != null){
				//The expected line format is "n pd pg"
				data = line.split(" ");
				n = Long.parseLong(data[0]);
				pd = Integer.parseInt(data[1]);
				pg = Integer.parseInt(data[2]);
				if (isValidCombination(n,pd,pg))
					result = "Case #" + i + ": Possible\n";
				else result = "Case #" + i + ": Broken\n";
//				System.out.print(result);
//				System.out.printf("%d %d %d%n",n,pd,pg);
				fw.write(result);
				i++;
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
		
	
	/**
	 * @param n upper bound on games today
	 * @param pd percentage won today
	 * @param pg percentage won all-time
	 * @return true if there exist a number d <= n of games played today such
	 * that the percentages pd and pg are exact
	 */
	public static boolean isValidCombination(long n, int pd, int pg) {
		//check validity of arguments
		if (n < 1 || pd < 0 || pd > 100 || pg < 0 || pg > 100)
			throw new IllegalArgumentException();
		//if I never won a game, I didn't win a game today and percentages are exact
		if (pg == 0) {
			if (pd == 0)
				return true;
			else return false;
		}
		//if I won all games, I won all games today and percentages are exact
		if (pg == 100) {
			if (pd == 100)
				return true;
			else return false;
		}
		//the global number of games is irrelevant: it can be made as big as necessary so that pg is exact
		//it remains to check, whether there exists 0 < d <= n such that pd is exact, that is
		//d * pd % 100 == 0. This is trivially satisfied if n > 99.
		if (n > 99)
			return true;
		//brute force applied
		for (int d = 1; d <= n; d++) {
			if (d * pd % 100 == 0)
				return true;
		}	
		return false;
	}

}
