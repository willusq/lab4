package cardGame;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class Deck extends Pile{
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
}
