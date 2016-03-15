package cardGame;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class Deck extends Pile{
	public Deck (){
		super();
		buildDeck();
	}
	
	public void  buildDeck(){
        int i = 0;
        for(CardSuit newSuit: CardSuit.values()) {
        	for(CardRank newRank: CardRank.values()) {		
				stdDeck.add(new Card(newRank, newSuit));
				//System.out.println(stdDeck.getEntry(i));
				i++;
			}
		}
	        
	 }
	public void deal(HeartsPlayer[] players) {
		// runs 52 times
		for(int i = 0; i < 13; i++) {
			for(int j = 0; j < 4; j++) {
				// put top card in players hand, remove from deck
				players[j].addToHand(getTopCard());
				removeCardAt(0);
			}
		}
	}
}
