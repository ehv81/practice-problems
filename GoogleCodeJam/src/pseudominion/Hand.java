package pseudominion;

import java.util.HashSet;
import java.util.Set;

public class Hand {
	
	private Set<Card> cards;
	
	public Hand(int size) {
		cards = new HashSet<>(size);
	}
	
	public void add(Card c) {
		cards.add(c);
	}
	
	public boolean remove(Card c) {
		return cards.remove(c);
	}
	
	public int size() {
		return cards.size();
	}
	
	public void print() {
		for (Card c : cards)
			System.out.println(c.toString());
	}
}
