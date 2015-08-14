package reverseWords;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * Google Code Jam Problem: Reverse Words
 * https://code.google.com/codejam/contest/351101/dashboard#s=p1
 */

public class SentenceReverser {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader r;
		String line;
		String[] words;
		StringBuilder reverseLine;
		FileWriter fw;
		
		String inputFilename = "input.txt";
		String outputFilename = "output.txt";
		
		int i = 1;

		try {
			r = new BufferedReader(new FileReader(inputFilename));
			fw= new FileWriter(outputFilename);	

			//The first line of the input contains the number of cases
			System.out.printf("Number of entries is %d%n",Integer.parseInt(r.readLine()));

			while((line= r.readLine()) != null){
				//The expected line format are words seperated by whitespaces
				words = line.split(" ");
				reverseLine = new StringBuilder("");
				for (int l = words.length; l > 0; l--) {
					reverseLine.append(words[l-1] + " ");
				}
				fw.write("Case #" + i + ": " + reverseLine.toString() + "\n");
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

}
