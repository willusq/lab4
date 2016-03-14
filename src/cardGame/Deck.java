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
    
    public List<Card> split(){
    	List<Card> deck1 = new List<Card>();
    	List<Card> deck2 = new List<Card>();
    	int i =0;
    	while(i<stdDeck.getLength()/2){
    		deck1.add(stdDeck.getEntry(i));
    		i++;
    	}
    	while(i<52){
    		deck2.add(stdDeck.getEntry(i));
    		i++;
    	}
    	
    	for(int j=0;j<deck1.getLength();j++){
    		deck2.add(deck1.getEntry(j));
    	}
    	
    	
    	for (int b=0;b<deck2.getLength();b++){
    		System.out.println(deck2.getEntry(b).toString());
    	}
    	
    	return deck2;
    	
    	
    }
    
    
    
    
   
		
}

