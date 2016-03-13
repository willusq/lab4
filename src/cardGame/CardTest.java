package cardGame;

import java.util.ArrayList;
import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class CardTest {
	public static void main(String[] args) {
		
		Deck myDeck= new Deck();
		myDeck.buildDeck();
		myDeck.shuffle();
		
	}
	
}