package cardGame;

import cardGame.Card.*;
import java.util.ArrayList;
public class  Deck {
    
    List<Card> stdDeck = new List<Card>();
    

   
    public void  buildDeck(){
        int i = 0;
        for(CardSuit newSuit: CardSuit.values()) {
			for(CardRank newRank: CardRank.values()) {
				
					stdDeck.add(new Card(newRank, newSuit));
				System.out.println(stdDeck.getEntry(i));
				i++;
			}
		}
        
    }
    
    
    public void shuffle(){
    	
    	List<Card> newDeck = new List<Card>();
    	for (int i=0;i<10;i++){
    		int randomCard = 0 + (int)(Math.random() * newDeck.getLength()); 
    		Card temp=stdDeck.getEntry(randomCard);
    		newDeck.add(temp);
    		System.out.println(newDeck.getEntry(i).getRank());
    		
    	}
    }
    
    
    
    
   
		
}

