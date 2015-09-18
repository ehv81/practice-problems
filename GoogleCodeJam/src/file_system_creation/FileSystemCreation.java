package file_system_creation;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;

/*
 * Google Code Jam Problem: File Fix-It
 * https://code.google.com/codejam/contest/635101/dashboard#s=p0
 */

public class FileSystemCreation {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader r;
		FileWriter fw;
		String[] buffer;
		
		FileSystemNode root;
		FileSystemNode actNode;

		String inputFilename = "input.txt";
		String outputFilename = "output.txt";
		
		int numberOfExistingDirectories;
		int numberOfTargetDirectories;
		int numberOfMkdirInvoked;

		int numberOfCases;
		int caseNumber = 1;
		
		try {
			r = new BufferedReader(new FileReader(inputFilename));
			fw = new FileWriter(outputFilename);	

			//The first line of the input contains the number of cases
			numberOfCases = Integer.parseInt(r.readLine());
			System.out.printf("Number of cases is %d%n",numberOfCases);

			while( caseNumber <= numberOfCases ){
				System.out.println("\n" + "-------Case #" + caseNumber + "-------");
				buffer = r.readLine().split(" ");
				numberOfExistingDirectories = Integer.parseInt(buffer[0]);
				numberOfTargetDirectories = Integer.parseInt(buffer[1]);
				root = new FileSystemNode("/");
				
				for (int k = 0; k < numberOfExistingDirectories; k++) {
					//leading dash results in empty string for buffer[0]
					buffer = r.readLine().split("/");
					actNode = root;
					for (int l = 1; l < buffer.length; l++) {
						actNode = actNode.decend(buffer[l]);
					}
				}
				
				numberOfMkdirInvoked = 0;
				String tmp;
				
				for (int k = 0; k < numberOfTargetDirectories; k++) {
					tmp = r.readLine();
					System.out.println("\n" + tmp);
					//leading dash results in empty string for buffer[0]
					buffer = tmp.split("/");
					actNode = root;
					for (int l = 1; l < buffer.length; l++) {
						if (actNode.mkdirIsNecessary(buffer[l])) {
							numberOfMkdirInvoked++;
							System.out.println(buffer[l]);
						}
						actNode = actNode.decend(buffer[l]);
					}
				}

				fw.write("Case #" + caseNumber + ": " + numberOfMkdirInvoked + "\n");

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
	
}
