package name_scores;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/*
 * Project Euler Problem 22: Name scores
 * 
 * The file names.txt is a 46K text file containing over five-thousand first names.
 * Begin by sorting it into alphabetical order. Then working out the alphabetical
 * value for each name, multiply this value by its alphabetical position in the list
 * to obtain a name score. 
 *  
 * For example, when the list is sorted into alphabetical order, COLIN,
 * which is worth 3 + 15 + 12 + 9 + 14 = 53, is the 938th name in the list.
 * So, COLIN would obtain a score of 938 × 53 = 49714.
 * 
 * What is the total of all the name scores in the file?
 */

public class NameScoresCalculator {


	public static void main(String[] args) {
		BufferedReader r;
		StringBuilder name = new StringBuilder();
		List<String> names = new LinkedList<>();

		String inputFilename = "names.txt";

		boolean nameReadingActive = false;

		try {
			r = new BufferedReader(new FileReader(inputFilename));
			//the below is from stackoverflow and also works. is it better?
//			r = new BufferedReader(new InputStreamReader(
//					new FileInputStream(inputFilename),
//					Charset.forName("UTF-8")));
			int c_int;
			char c;

			while((c_int = r.read()) != -1) {
				c = (char) c_int;
				if ( (c == '"') && !nameReadingActive) {
					name = new StringBuilder("");
					nameReadingActive = true;
				}
				else if ( (c == '"') && nameReadingActive) {
					//					System.out.println(name);
					names.add(name.toString());
					nameReadingActive = false;
				}
				if ( (c != '"') && nameReadingActive) {
					name.append(c);
				}
			}
			r.close();

			Collections.sort(names);

			int score = 0;
			int totalScore = 0;
			String n;

			for (int i = 0; i < names.size(); i++) {
				score = 0;
				n = names.get(i);
				for (int j = 0; j < n.length(); j++) {
					//add the position of the char in the alphabet to score
					score += n.codePointAt(j)-64;
				}
				//multiply by the position in the list
				score *= (i+1);
				//System.out.printf("Score of %s is %d%n",n,score);
				totalScore += score;
			}

			System.out.printf("File %s successfully processed.%n" +
					"The total score of all names is %d.%n",
					inputFilename,totalScore);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

}
