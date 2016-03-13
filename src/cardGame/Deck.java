package cardGame;

import cardGame.Card.*;
import java.util.ArrayList;
public class  Deck {
    
    ArrayList<Card> stdDeck = new ArrayList<Card>();
    

   
    public void  buildDeck(){
        int i = 0;
        for(CardSuit newSuit: CardSuit.values()) {
			for(CardRank newRank: CardRank.values()) {
				
					stdDeck.add(new Card(newRank, newSuit));
				System.out.println(stdDeck.get(i));
				i++;
			}
		}
        
    }
    
    
    
    
   
		
}

