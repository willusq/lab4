package cardGame;

import java.util.Random;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class AutoHeartsPlayer extends HeartsPlayer {

	public static void main(String[] args) {
		
	}
	public AutoHeartsPlayer () {
		super();
	}
	public AutoHeartsPlayer(String n) {
		super(n);
	}
	public AutoHeartsPlayer(String n, Hand h) {
		super(n, h);
	}
	public void pass(HeartsPlayer player, String direction) {
		Card card = new Card();
		for(int i = 0; i < 3; i++) {
			if(handContains(new Card(CardRank.QUEEN, CardSuit.SPADES)))
				card = new Card(CardRank.QUEEN, CardSuit.SPADES);
			else if(handContains(new Card(CardRank.ACE, CardSuit.SPADES)))
				card = new Card(CardRank.ACE, CardSuit.SPADES);
			else if(handContains(new Card(CardRank.KING, CardSuit.SPADES)))
				card = new Card(CardRank.KING, CardSuit.SPADES);
			else if(handContains(new Card(CardRank.ACE, CardSuit.HEARTS)))
				card = new Card(CardRank.ACE, CardSuit.HEARTS);
			else if(handContains(new Card(CardRank.KING, CardSuit.HEARTS)))
				card = new Card(CardRank.KING, CardSuit.HEARTS);
			else if(handContains(new Card(CardRank.QUEEN, CardSuit.HEARTS)))
				card = new Card(CardRank.QUEEN, CardSuit.HEARTS);
			else
				card = getHighestCard();
			
			player.addToHand(card);
			moveToTop(card);
			hand.removeCardAt(0);
		}
	}
	private Card getHighestCard() {
		Card highCard = hand.getCard(0);
		for(int i = 1; i < hand.getNumCards(); i++) {
			if(hand.getCard(i).compareTo(highCard) > 0)
				highCard = hand.getCard(i);
		}
		return highCard;
	}
	public Card pickAnyCard(CardSuit leadSuit, boolean hearts) {
		Random rnd = new Random();
		Card randomCard = new Card();
		int randomCardInt;
		
		if(leadSuit == null) {
			if(hearts == false) {
				randomCardInt = 0 + (int)(rnd.nextInt(hand.getNumCards()));
				randomCard = hand.getCard(randomCardInt);
				
				if((hand.containsSuit(CardSuit.CLUBS)) || 
						(hand.containsSuit(CardSuit.DIAMONDS)) ||
						(hand.containsSuit(CardSuit.SPADES))) {
					while(randomCard.getSuit() == CardSuit.HEARTS) {
						randomCardInt = 0 + (int)(rnd.nextInt(hand.getNumCards()));
						randomCard = hand.getCard(randomCardInt);
					}
				}
			}
			else {
				randomCardInt = 0 + (int)(rnd.nextInt(hand.getNumCards()));
				randomCard = hand.getCard(randomCardInt);
			}
		}
		else if(leadSuit != null) {
			if(hand.containsSuit(leadSuit)) {
				while(randomCard.getSuit() != leadSuit) {
					randomCardInt = 0 + (int)(rnd.nextInt(hand.getNumCards()));
					randomCard = hand.getCard(randomCardInt);
				}
			}
			else {
				randomCardInt = 0 + (int)(rnd.nextInt(hand.getNumCards()));
				randomCard = hand.getCard(randomCardInt);
			}
		}
		
		return randomCard;
	}
	public Card playCard(CardSuit leadSuit, boolean hearts) {
		Card card = null;
		card = pickAnyCard(leadSuit, hearts);
		moveToTop(card);
		hand.removeCardAt(0);
		return card;
	}
	public Card startHand() {
		return new Card(CardRank.TWO, CardSuit.CLUBS);
	}
}
