package killer_word;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/*
 * Google Code Jam Problem: Killer Word	
 * https://code.google.com/codejam/contest/1145485/dashboard#s=p1
 */

public class KillerWord {

	public static void main(String[] args) {

		String inputFilename = "input.txt";
		String outputFilename = "output.txt";

		try {
			BufferedReader r = new BufferedReader(new FileReader(inputFilename));
			FileWriter fw = new FileWriter(outputFilename);	

			//The first line of the input contains the number of cases
			int numberOfCases = Integer.parseInt(r.readLine());
			System.out.printf("Number of cases is %d%n",numberOfCases);

			int caseNumber = 1;

			while( caseNumber <= numberOfCases ){
				//For each case, the first line consists of two blank seperated numbers
				String[] numbers = r.readLine().split(" ");
				int d = Integer.parseInt(numbers[0]); //dictionary size in active case
				int l = Integer.parseInt(numbers[1]); //number of letter lists in active case

				//The following #d lines contain all words, which we organize in different dictionaries,
				//each containing all words of equal length (1 to 10 characters)
				List<List<String>> dictionariesWithConstantWordLengths = new ArrayList<List<String>>();
				for (int i = 0; i < 10; i++) {
					dictionariesWithConstantWordLengths.add(new ArrayList<String>());
				}
				for (int i = 0; i < d; i++) {
					String word = r.readLine();
					dictionariesWithConstantWordLengths.get(word.length()-1).add(word);
				}

				//				for (int i = 0; i < 10; i++) {
				//					List<String> dictionary = wordsOfSpecificLength.get(i);
				//					if (actSet.size() > 0)
				//						System.out.printf("words of length %d: %s%n",i+1,dictionary.toString());
				//				}

				//for each strategy one obtains one optimal killer word, which will be listed in a compound string
				StringBuilder killerWords = new StringBuilder();

				//iterate over all guessing strategies
				for (int i = 0; i < l; i++) {
					char[] letterList = r.readLine().toCharArray();
					String optimalKillerWord = "";
					int maxPenaltyPoints = -1;

					for (List<String> dictionary : dictionariesWithConstantWordLengths) {
						for (int j = 0; j < dictionary.size(); j++) {
							int penaltyPoints = computePenaltyPoints(dictionary, j, letterList);
//							System.out.println("penalty points " + penaltyPoints);
							if ( penaltyPoints > maxPenaltyPoints ) {
								optimalKillerWord = dictionary.get(j);
								maxPenaltyPoints = penaltyPoints;
							}
						}
					}

					killerWords.append(i < l-1 ? optimalKillerWord + " " : optimalKillerWord);
				}

				fw.write(caseNumber < numberOfCases ? 
						"Case #" + caseNumber + ": " + killerWords + "\n" :
						"Case #" + caseNumber + ": " + killerWords);
//				System.out.print("\nCase #" + caseNumber + ": " + killerWords + "\n");

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

	/**
	 * @param dictionary a dictionary of words of equal length
	 * @param killerWordIndex the index in dictionary of the chosen killer word
	 * @param letterList the guessing strategy: chars are guessed in that sequence. chars which cannot match
	 * (because no potential word with that char is left) are skipped.
	 * @return number of wrong guesses
	 */
	private static int computePenaltyPoints(List<String> dictionary, int killerWordIndex, char[] letterList) {
		String killerWord = dictionary.get(killerWordIndex);
//		System.out.println("\nkillerword = " + killerWord);

		Set<Integer> remainingCompetingWordIndices = new HashSet<>();
		for (int i = 0; i < dictionary.size(); i++)
			remainingCompetingWordIndices.add(i);
		remainingCompetingWordIndices.remove(killerWordIndex);
		Set<Integer> knownCharPositions = new HashSet<>(); //uncovered letter positions in the killer word

		int penaltyPoints = 0;

		//go over all chars according to the strategy
		for (int i = 0; i < letterList.length; i++) {
//			System.out.println("known char positions " + knownCharPositions.size());
//			System.out.println("remaining competing words " + remainingCompetingWordIndices.size());
			//if there are no competing words left, Sean will finish without failure
			if (remainingCompetingWordIndices.size() == 0)
				return penaltyPoints;

			//otherwise, take the next char
			char c = letterList[i];
//			String action = "char " + c;

			//if the killerWord contains c, Sean will ask
			if (killerWord.contains(String.valueOf(c))) {
				for (int j = 0; j < killerWord.length(); j++) {
					if (killerWord.charAt(j) == c)
						knownCharPositions.add(j);
				}
				if (knownCharPositions.size() == killerWord.length()) //Sean has found the word
					return penaltyPoints;
				//update the remaining words by removing the now incompatible ones
				for (Iterator<Integer> it = remainingCompetingWordIndices.iterator(); it.hasNext(); ) {
					if (!filteredMatch(killerWord, dictionary.get(it.next()), knownCharPositions)) {
						it.remove();
					}
				}
//				System.out.println(action + " successfully chosen");
			}
			//if c is not contained in killerWord, check whether there is another competing
			//word that contains c
			else {
				boolean cIsPotentialChar = false;

				for (int j : remainingCompetingWordIndices) {
					if (dictionary.get(j).contains(String.valueOf(c))) {
						cIsPotentialChar = true;
						break;
					}
				}

				//if c is potential, Sean will ask and be punished
				if (cIsPotentialChar) {
					penaltyPoints++;
					//remove all words from the competing list that contain c
					for (Iterator<Integer> it = remainingCompetingWordIndices.iterator(); it.hasNext(); ) {
						if (dictionary.get(it.next()).contains(String.valueOf(c))) {
							it.remove();
						}
					}
//					System.out.println(action + " wrongfully chosen");
				}
//				else {
//					System.out.println(action + " skipped");
//				}
			}

		}

		return -1; //something went wrong
	}

	private static boolean filteredMatch(String killerWord, String otherWord, Set<Integer> filterIndices) {
		if (killerWord.length() != otherWord.length())
			return false;
		for (int i : filterIndices) {
			if (killerWord.charAt(i) != otherWord.charAt(i))
				return false;
		}
		return true;
	}

}
