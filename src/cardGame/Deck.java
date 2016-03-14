package cardGame;

import cardGame.Card.*;
import java.util.ArrayList;
import java.util.Random;
public class  Deck {
    
    List<Card> stdDeck = new List<Card>();
    

   
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
    
    
    public void shuffle(){
    	
    	List<Card> newDeck = new List<Card>();
    	int shuffleSize=stdDeck.getLength();
    	for (int i=0;i<shuffleSize;i++){
    		Random rnd = new Random();
    		int randomCard = 0 + (int)(rnd.nextInt(stdDeck.getLength()-1)); 
    		Card temp=stdDeck.getEntry(randomCard);
    		newDeck.add(temp);
    		System.out.println(newDeck.getEntry(i).toString());
    		
    	}
    }
    
    
    
    
   
		
}

