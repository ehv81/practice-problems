package pseudominion;

public class Card {
	
	int id; //unique card number
	int drawAmount; //#cards drawn if this card is played
	int score; //#points scored if this card is played
	int turnsGained; //#turns gained if this card is played
	
	public Card(int id, int c, int s, int t) {
		this.id = id;
		this.drawAmount = c;
		this.score = s;
		this.turnsGained = t;
	}

	@Override
	public String toString() {
		return "Card [id=" + id + ", drawAmount=" + drawAmount + ", score=" + score + ", turnsGained=" + turnsGained + "]";
	}
	
}
