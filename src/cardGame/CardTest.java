package cardGame;

import java.util.ArrayList;
import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class CardTest {
	public static void main(String[] args) {
		
		Deck myDeck= new Deck();
		myDeck.buildDeck();
		for(int i=0;i<1;++i)myDeck.shuffle();
		
	}
	
}