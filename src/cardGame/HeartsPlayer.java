package cardGame;

import cardGame.Card.CardSuit;

public class HeartsPlayer {
	String name;
	Hand hand;
	Pile cardsTaken;
	int score;
	
	public HeartsPlayer() {
		
	}
	public HeartsPlayer(String n) {
		name = n;
		hand = new Hand();
	}
	public HeartsPlayer(String n, Hand h) {
		name = n;
		hand = h;
	}
	public void setHand(Hand h) {
		hand = h;
	}
	public boolean handContains (Card c) {
		return false;
	}
	public Card chooseCards (int num) {
		return null;
	}
	public int getScore() {
		return score;
	}
	public void addPoints(int points) {
		
	}
	public void addToHand(Card c) {
		hand.add(c);                                 
	}
	public String getName() {
		return name;
	}
	private boolean checkSuit(CardSuit s) {
		return true;
	}
}
