package cardGame;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

/* Card class which has aces high by default. 
 * */ 

public class Card implements Comparable<Card> {
	
	// should be imported into other classes? rather than using strings
	public enum CardRank { 
		TWO("2"), THREE("3"), FOUR("4"), FIVE("5"), SIX("6"), SEVEN("7"), EIGHT("8"),
		NINE("9"), TEN("10"), JACK("J"), QUEEN("Q"), KING("K"), ACE("A");
		private String symbol;
		CardRank(String s){ 
			symbol = s;
			}
	};
	public enum CardSuit { 
		CLUBS('C'), DIAMONDS('D'), HEARTS('H'), SPADES('S');
		private char symbol;
		CardSuit(char s) {
			symbol = s;
		}
	};
	
	private CardRank rank;
	private CardSuit suit;
	
	public Card (CardRank r, CardSuit s) {
		rank = r;
		suit = s;
	}
	public Card () {
		rank = null;
		suit = null;
	}
	/* Takes in card and rank as a suit (accepts a few different forms) and returns a Card object */
	public static Card getFromString(String sRank, String sSuit) {
		Card outCard = new Card();
		switch(sRank.toLowerCase()) {
			case "2": case "two":
				outCard.rank = CardRank.TWO;
				break;
			case "3": case "three":
				outCard.rank = CardRank.THREE;
				break;
			case "4": case "four":
				outCard.rank = CardRank.FOUR;
				break;
			case "5": case "five":
				outCard.rank = CardRank.FIVE;
				break;
			case "6": case "six":
				outCard.rank = CardRank.SIX;
				break;
			case "7": case "seven":
				outCard.rank = CardRank.SEVEN;
				break;
			case "8": case "eight":
				outCard.rank = CardRank.EIGHT;
				break;
			case "9": case "nine": 
				outCard.rank = CardRank.NINE;
				break;
			case "10": case "ten":
				outCard.rank = CardRank.TEN;
				break;
			case "j": case "jack":
				outCard.rank = CardRank.JACK;
				break;
			case "q": case "queen":
				outCard.rank = CardRank.QUEEN;
				break;
			case "k": case "king":
				outCard.rank = CardRank.KING;
				break;
			case "a": case "ace":
				outCard.rank = CardRank.ACE;
				break;
			default:
				System.out.println("Invalid Rank. ");
				outCard.rank = null;
				break;
		}
		switch(sSuit.toLowerCase()) {
			case "c": case "clubs":
				outCard.suit = CardSuit.CLUBS;
				break;
			case "d": case "diamonds":
				outCard.suit = CardSuit.DIAMONDS;
				break;
			case "h": case "hearts":
				outCard.suit = CardSuit.HEARTS;
				break;
			case "s": case "spades":
				outCard.suit = CardSuit.SPADES;
				break;
			default:
				System.out.println("Invalid Suit. ");
				outCard.suit = null;
				break;
		}
		return outCard;
	}
	/* returns negative value if less than c, 0 if equal, and positive if greater than c
	 * only evaluates face value and treats aces as high by default */
	public int compareTo(Card c) {
		return this.rank.ordinal() - c.rank.ordinal();
	}	
	/* returns whether cards are identical in suit and rank */
	@Override
	public boolean equals(Object o) {
		Card c = (Card) o;
		return((this.rank.equals(c.getRank())) && (this.suit.equals(c.getSuit())));
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
	public String getSuitString () {
		return suit.toString();
	}
	public CardSuit getSuit () {
		return suit;
	}
	
}