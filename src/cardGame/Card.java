package cardGame;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

/* Card class which has aces high by default. 
 * */ 

public class Card implements Comparable<Card> {
	
	// should be imported into other classes? rather than using strings
	public enum CardRank { TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, TEN, JACK, QUEEN, KING, ACE };
	public enum CardSuit { CLUBS, DIAMONDS, HEARTS, SPADES };
	
	private CardRank rank;
	private CardSuit suit;
	
	/* point to other cards to impose order */
	private Card prev;
	private Card next;
	
	public Card (CardRank r, CardSuit s) {
		setRank(r);
		setSuit(s);
		prev = null;
		next = null;
	}
	
	public Card (CardRank r, CardSuit s, Card p, Card n) {
		setRank(r);
		setSuit(s);
		setPrev(p);
		setNext(n);
	}
	
	/* returns negative value if less than c, 0 if equal, and positive if greater than c
	 * only evaluates face value and treats aces as high by default */
	public int compareTo(Card c) {
		return this.rank.ordinal() - c.rank.ordinal();
	}
	
	/* returns whether cards are identical in suit and rank */
	public boolean equals(Card c) {
		return((this.rank.equals(c.rank)) && (this.suit.equals(c.suit)));
	}

	public String toString() {
		return (rank.toString() + " of " + suit.toString());
	}
	
	// get rank as a string
	public String getRankString () {
		return rank.toString();
	}
	
	// get rank as enum type
	public CardRank getRank () {
		return rank;
	}
	
	public void setRank (CardRank r) {
		rank = r;
	}
	
	public String getSuitString () {
		return suit.toString();
	}
	
	public CardSuit getSuit () {
		return suit;
	}
	
	public void setSuit (CardSuit s) {
		suit = s;
	}
	
	public Card getPrev () {
		return prev;
	}
	
	public void setPrev (Card p) {
		// method will still run with a null p but won't do anything
		if(p != null) {
			// shifts links if there is a card stored in prev
			if(prev != null) {
				prev.next = p;
				p.prev = prev;
			}
		
			p.next = this;
			prev = p;
		}
	}
	
	public Card getNext () {
		return next;
	}
	
	public void setNext (Card n) {
		if(n != null) {
			if(next!= null) {
				// shifts links if there is a card stored in next
				next.prev = n;
				n.next = next;
			}
		
			n.prev = this;
			next = n;
		}
	}
}