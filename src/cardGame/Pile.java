package cardGame;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;
import java.util.ArrayList;
import java.util.Random;

public class  Pile{
    
    List<Card> stdDeck = new List<Card>();

    
    public void shuffle(){
    	
    	List<Card> newDeck = new List<Card>();
    	int shuffleSize=stdDeck.getLength();
    	for (int i=0;i<shuffleSize;i++){
    		Random rnd = new Random();
    		int randomCard = 0 + (int)(rnd.nextInt(stdDeck.getLength())); 
    		Card temp=stdDeck.getEntry(randomCard);
    		stdDeck.remove(randomCard);
    		newDeck.add(temp);
    		System.out.println(newDeck.getEntry(i).toString());
    		
    	}
    	stdDeck=newDeck;
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
    public void sort(){
    	List<Card> out=new List<Card>();
    	//for(int i=0;i<stdDeck.getLength();i++) System.out.println(stdDeck.getEntry(i).toString());
    	//System.out.println("==============");
    	System.out.println(stdDeck.getLength());
    	 for(CardSuit newSuit: CardSuit.values()) {
    		 
    		 List<Card> currSuit=new List<Card>();
    		 currSuit.clear();System.out.println(currSuit.toString());
    		 for(int i=0;i<stdDeck.getLength();++i){
    			 Card tmp1=stdDeck.getEntry(i);
    			 System.out.println(tmp1.toString());
    			 if(newSuit.equals(tmp1.getSuit())){
    				 currSuit.add(tmp1);
    			 }
    			 
    		 }
				for(CardRank newRank: CardRank.values()) {
					for(int i=0;i<currSuit.getLength();++i){
		    			 Card tmp1=currSuit.getEntry(i);
		    			 if(newRank.equals(tmp1.getRank())){
		    				 out.add(tmp1);
		    			 }
		    			 
		    		 }
					 
					 
				}
    	 }
    	 stdDeck=out;
    	 for(int i=0;i<stdDeck.getLength();i++) System.out.println(stdDeck.getEntry(i).toString());
	}
    
    
    
    
   
		
}

