package pseudominion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

/*
 * Google Code Jam Problem: Pseudominion	
 * https://code.google.com/codejam/contest/1145485/dashboard#s=p2
 */

public class PseudominionApplication {

	private static List<Card> cardCache;
	private static Hand hand;
	private static Deck deck;
	private static int maxCardNumber; //maximal number of total cards;
	private static int n; //initial #cards in hand
	private static int m; //initial #cards in deck
	private static int score;
	private static int turns;

	public static void main(String[] args) {

		String inputFilename = "input.txt";
		String outputFilename = "output.txt";
		maxCardNumber = 80;

		try {
			BufferedReader r = new BufferedReader(new FileReader(inputFilename));
			FileWriter fw = new FileWriter(outputFilename);	
			String[] buffer;

			//The first line of the input contains the number of cases
			int numberOfCases = Integer.parseInt(r.readLine());
			System.out.printf("Number of cases is %d%n",numberOfCases);

			int caseNumber = 1;

			while( caseNumber <= numberOfCases ){

				cardCache = new ArrayList<>(maxCardNumber);

				n = Integer.parseInt(r.readLine()); //initial #cards in hand
				for (int i = 0; i < n; i++) {
					buffer = r.readLine().split(" ");
					Card c = new Card(i+1,Integer.parseInt(buffer[0]),
							Integer.parseInt(buffer[1]),Integer.parseInt(buffer[2]));
					cardCache.add(c);
				}

				m = Integer.parseInt(r.readLine()); //initial #cards in deck
				for (int i = 0; i < m; i++) {
					buffer = r.readLine().split(" ");
					Card c = new Card(n+i+1,Integer.parseInt(buffer[0]),
							Integer.parseInt(buffer[1]),Integer.parseInt(buffer[2]));
					cardCache.add(c);
				}

				init();
				printCache();
				printStats();
				play(cardCache.get(0));
				printStats();

				//				fw.write(caseNumber < numberOfCases ? 
				//						"Case #" + caseNumber + ": " + maxScore + "\n" :
				//							"Case #" + caseNumber + ": " + maxScore);
				System.out.print("Case #" + caseNumber + ": " + "\n");

				caseNumber++;
			}
			r.close();
			fw.close();
			System.out.printf("File %score successfully processed and results written to %score%n",
					inputFilename, outputFilename);
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}

	private static void init() {
		hand = new Hand(maxCardNumber);
		deck = new Deck(m);

		for (int i = 0; i < n; i++)
			hand.add(cardCache.get(i));

		for (int i = n; i < n+m; i++)
			deck.add(cardCache.get(i));

		score = 0;
		turns = 1;
	}

	private static void printHand() {
		System.out.println("Hand:");
		hand.print();
	}
	
	private static void printDeck() {
		System.out.println("Deck:");
		deck.print();
	}
	
	private static void printCache() {
		System.out.println("Cache:");
		for (Card c : cardCache)
			System.out.println(c.toString());
	}
	
	private static void printStats() {
		System.out.printf("Score: %d, turns: %d, #cards in hand: %d, #cards in deck: %d%n",
				score,turns,hand.size(),deck.size());
	}

	private static void play(Card c) {
		turns--;
		
		score += c.score;
		turns += c.turnsGained;

		int cardsToDraw = c.drawAmount;
		while (cardsToDraw > 0 && deck.size() > 0) {
			hand.add(deck.poll());
			cardsToDraw--;
		}
		
		hand.remove(c);	
	}

}
