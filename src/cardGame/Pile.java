package cardGame;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;
import java.util.ArrayList;
import java.util.Random;

public class  Pile{
    
    protected List<Card> stdDeck = new List<Card>();    
    
    public Pile() {
    	stdDeck = new List<Card>();
    }
    public void addToBottom(Card c) {
    	stdDeck.add(c);
    }
    public Card getTopCard() {
    	return stdDeck.getEntry(0);
    }
    public void addToTop(Card c) {
    	stdDeck.add(0, c);
    }
    public int getNumCards() {
    	return stdDeck.getLength();
    }
    public Card getCard(int pos) {
    	return stdDeck.getEntry(pos);
    }
    public Card removeCardAt(int pos) {
    	return stdDeck.remove(pos);
    }
    public boolean containsCard(Card c) {
    	return stdDeck.contains(c);
    }
    public boolean containsSuit(CardSuit s) {
    	Card current;
    	for(int i = 0; i < stdDeck.getLength(); i++) {
    		current = stdDeck.getEntry(i);
    		if(current.getSuit() == s)
    			return true;
    	}
    	return false;
    }
    public void print() {
    	if(stdDeck.getLength() > 0)
    		stdDeck.display();
    }
    public void clear () {
    	stdDeck.clear();
    }
    public void shuffle(){
    	
    	List<Card> newDeck = new List<Card>();
    	int shuffleSize=stdDeck.getLength();
    	for (int i=0;i<shuffleSize;i++){
    		Random rnd = new Random();
    		int randomCard = 0 + (int)(rnd.nextInt(stdDeck.getLength())); 
    		Card temp=stdDeck.getEntry(randomCard);
    		stdDeck.remove(randomCard);
    		newDeck.add(temp);
    		//System.out.println(i + newDeck.getEntry(i).toString());
    		
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
    	//System.out.println(stdDeck.getLength());

		 List<Card> currSuit=new List<Card>();
    	 for(CardSuit newSuit: CardSuit.values()) {
    		 
    		 currSuit.clear();
    		
    		 for(int i=0;i<stdDeck.getLength();++i){
    			 Card tmp1=stdDeck.getEntry(i);
    		//	 System.out.println(tmp1.toString());
    			 if(newSuit.equals(tmp1.getSuit())){
    				 currSuit.add(tmp1);
    			 }
    			 
    		 }
    		 	if(!currSuit.isEmpty()) {
					for(CardRank newRank: CardRank.values()) {
						for(int i=0;i<currSuit.getLength();++i){
			    			 Card tmp1=currSuit.getEntry(i);
			    			 if(newRank.equals(tmp1.getRank())){
			    				 out.add(tmp1);
			    			 }
			    		 }
					}
    		 	}
    	 }
    	 stdDeck=out;
    //	 for(int i=0;i<stdDeck.getLength();i++) System.out.println(stdDeck.getEntry(i).toString());
	}
    
}

