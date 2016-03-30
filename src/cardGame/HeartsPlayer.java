package cardGame;

import java.util.Scanner;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class HeartsPlayer {
	String name;
	Hand hand;
	Pile cardsTaken;
	int score;
	
	public HeartsPlayer() {
		name = "No Name";
		hand = new Hand();
		cardsTaken = new Pile();
		score = 0;
	}
	public HeartsPlayer(String n) {
		name = n;
		hand = new Hand();
		cardsTaken = new Pile();
		score = 0;
	}
	public HeartsPlayer(String n, Hand h) {
		name = n;
		hand = h;
		cardsTaken = new Pile();
		score = 0;
	}
	public void setHand(Hand h) {
		hand = h;
	}
	public boolean handContains (Card c) {
		return hand.containsCard(c);
	}
	public int getScore() {
		return score;
	}
	public void addPoints(int points) {
		score+=points;
	}
	public void addToHand(Card c) {
		hand.addToBottom(c);                                 
	}
	public String getName() {
		return name;
	}
	public void printHand() {
		hand.print();
	}
	public void sortHand () {
		hand.sort();
	}
	private boolean checkSuit(Card card, CardSuit s, boolean hearts) {
		
		// is new leading suit?
		if(s == null) {
			// trying to lead hearts?
			if(card.getSuit() != CardSuit.HEARTS)
				return true;
			else if(card.getSuit() == CardSuit.HEARTS) {
				// hearts broken?
				if(hearts)
					return true;
				else if(!hearts) {
					// have other suits?
					if(((hand.containsSuit(CardSuit.CLUBS)) || 
							(hand.containsSuit(CardSuit.DIAMONDS)) || 
							(hand.containsSuit(CardSuit.SPADES))))
						System.out.println("Hearts have not yet been broken. ");
					return(!(((hand.containsSuit(CardSuit.CLUBS)) || 
							(hand.containsSuit(CardSuit.DIAMONDS)) || 
							(hand.containsSuit(CardSuit.SPADES)))));
				}
			}
		}
		else if (s != null) {
			// playing leading suit?
			if(card.getSuit() == s)
				return true;
			else {
				// have leading suit?
				if(hand.containsSuit(s))
						System.out.println("You must play " + s.toString().toLowerCase());
				return(!(hand.containsSuit(s)));
			}
		}
		return false;
	}
	public void pass(HeartsPlayer player, String direction) {
		Card[] cards = new Card[3];
		
		/* 
		 * one concern may be displaying hands before/after passing
		 * current it displays all hands before doing any passing
		 * could print individually but must distinguish between original hand
		 * and hand after being passed cards
		 */
		
		System.out.println(name);
		System.out.println("Choose 3 cards to pass " + direction + " to " + player.getName());
		
		for(int i = 0; i < 3; i++) {
			cards[i] = pickCard();
			
			// add to other player's hand
			player.addToHand(cards[i]);
			
			// remove from hand
			moveToTop(cards[i]);
			hand.removeCardAt(0);
			
			System.out.println("You passed the " + cards[i].toString());
		}

		System.out.println();
	}
	// used for user input to confirm card is in player's hand
	protected Card verifyCard(Card c) {
		Scanner input = new Scanner(System.in);
		Card outCard = c;
		String inRank;
		String inSuit;
		
		while(!(hand.containsCard(outCard))) {
			System.out.println("The " + outCard.toString().toLowerCase() + " is not in your hand, try again. Enter card in the form [rank suit]");
			inRank = input.next();
			inSuit = input.next();
			outCard = Card.getFromString(inRank, inSuit);
		}
		
		return outCard;
		
	}
	public void moveToTop(Card c) {
		for(int i = 0; i < hand.getNumCards(); i++) {
			if(c.equals(hand.getCard(i)))
				hand.removeCardAt(i);
		}
		hand.addToTop(c);
	}
	public Card startHand() {
		hand.sort();
		hand.print();
		Card twoClubs = new Card(CardRank.TWO, CardSuit.CLUBS);
		System.out.print("Press enter to play the two of clubs and begin the hand. ");
		new Scanner(System.in).nextLine();
		hand.removeCardAt(0);
		return twoClubs;
	}
	public Card playCard(CardSuit leadSuit, boolean hearts) {
		hand.sort();
		System.out.println("your hand");
		hand.print();
		boolean run = true;
		Card card = null;
		
		while(run) {
			card = pickCard();
			run = !checkSuit(card, leadSuit, hearts);
		}
		
		moveToTop(card);
		hand.removeCardAt(0);
		
		System.out.println();
		
		return card;
	}
	public void takeTrick(Card[] trick) {
		System.out.println(name + " takes the trick");
		for(int i = 0; i < trick.length; i++)
			cardsTaken.addToBottom(trick[i]);
	}
	public int scoreHand() {
		int handScore = 0;
		System.out.println(name);
		printCardsTaken();
		System.out.println();
		int numCards = cardsTaken.getNumCards();
		
		for (int i = 0; i < numCards; i++) {
			if (cardsTaken.getCard(i).getSuit() == CardSuit.HEARTS)
				handScore++;
			else if(cardsTaken.getCard(i).equals(new Card(CardRank.QUEEN, CardSuit.SPADES)))
				handScore += 13;
		}
		
		cardsTaken.clear();
		
		return handScore;
	}
	public void printCardsTaken() {
		cardsTaken.print();
	}
	private Card pickCard() {
		Scanner input = new Scanner(System.in);
		boolean valid = false;
		String inRank;
		String inSuit;
		Card card = new Card();
		
		while(valid == false) {
			System.out.print("Enter card in th`e form [rank suit]: ");
			inRank = input.next();
			inSuit = input.next();
			card = Card.getFromString(inRank, inSuit);
			
			if((card.getRank() != null) && (card.getSuit() != null))
				valid = true;
		}
		
		// make sure card is in this player's hand
		card = verifyCard(card);
		
		return card;
	}
	public void clearHand() {
		hand.clear();
		
	}
}
