package pseudominion;

import java.util.ArrayDeque;
import java.util.Queue;

public class Deck {
	
	private Queue<Card> cards;
	
	public Deck(int size) {
		cards = new ArrayDeque<>(size);
	}
	
	public void add(Card c) {
		cards.add(c);
	}
	
	public Card poll() {
		return cards.poll();
	}
	
	public int size() {
		return cards.size();
	}
	
	public void print() {
		for (Card c : cards)
			System.out.println(c.toString());
	}

}
