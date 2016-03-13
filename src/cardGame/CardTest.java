package cardGame;

import java.util.ArrayList;
import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class CardTest {
	public static void main(String[] args) {
		ArrayList<Card> stdDeck = new ArrayList<Card>();
		
		// something like this can be used to build a full deck in order
		int i = 0;
		for(CardSuit newSuit: CardSuit.values()) {
			for(CardRank newRank: CardRank.values()) {
				if(i == 0)
					stdDeck.add(new Card(newRank, newSuit));
				else if(i == 51)
					stdDeck.add(new Card(newRank, newSuit, stdDeck.get(i - 1), null));
				else
					stdDeck.add(new Card(newRank, newSuit, stdDeck.get(i - 1), null));
				i++;
			}
		}
		
		for(int j = 0; j < stdDeck.size(); j++) {
			Card current = stdDeck.get(j);
			System.out.println("Current: " + current.toString());
			System.out.println("Previous: " + current.getPrev());
			System.out.println("Next: " + current.getNext());
			System.out.println();
		}
		
		Card card1 = new Card(CardRank.JACK, CardSuit.CLUBS);
		Card card2 = new Card(CardRank.QUEEN, CardSuit.SPADES);
		Card card3 = new Card(CardRank.FIVE, CardSuit.HEARTS);
		
		int num = card2.compareTo(card3);
		String msg = "";
		
		if(num < 0)
			msg = " less than ";
		else if (num == 0)
			msg = " equal to ";
		else if (num > 0) 
			msg = " greater than ";
		
		System.out.println(card2.toString() + " is" + msg + card3.toString());
		
	}
	
}