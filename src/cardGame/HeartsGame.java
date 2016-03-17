package cardGame;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class HeartsGame {
	/* visual representation of players
	 * 			0
	 * 		3		1
	 * 			2
	 */
	HeartsPlayer[] players;
	Deck baseDeck;
	int handsFinished;
	int cpi; // current player index
	CardSuit leadingSuit;
	int winner;
	boolean firstTrick;
	boolean heartsBroken;
	
	public static void main(String[] args) {
		HeartsGame game = new HeartsGame();
		game.playGame();
		
		// FOR EACH HAND {
		// deal cards
				
			// pass cards [in HANDS]
				
				// prompt CURRENT PLAYER to pick a card
				// must play two of clubs if first round
				
				// until all PLAYERS are out of cards [HANDS]:
				// 	all four PLAYERS take their turns
				// 	four CARDS in CURRENT TRICK
				// 	CURRENT TRICK goes to PLAYER with the HIGHEST CARD of LEADING SUIT
				//	that PLAYER starts the next HAND
				// 	HEARTS cannot lead until they have been BROKEN
				          
				// score HAND:
				// 	for all CARDS taken by a PLAYER:
				// 		each HEART = 1 POINT
				// 		QUEEN of SPADES = 3 POINTS
				// 		shooting the moon: if one PLAYER ends up with all 13 HEARTS and
				// 			the QUEEN of SPADES, they get 0 POINTS and all OTHERS get 26
				
				// play continues until a PLAYER reaches 100 POINTS
				// PLAYER with the LOWEST SCORE wins
		
	}
	public HeartsGame() {
		baseDeck = new Deck();
		players = new HeartsPlayer[4];
		players[0] = new HeartsPlayer("North");
		players[1] = new HeartsPlayer("East");
		players[2] = new HeartsPlayer("South");
		players[3] = new HeartsPlayer("West");
		handsFinished = 0;
		cpi = findTwoOfClubs();
	}
	public void playGame() {
		while(!gameOver()) {
			if(handsFinished != 0)
				baseDeck.buildDeck();
			shuffleAndDeal();
			printPlayersHands();
			passCards();
			printPlayersHands();
			
			cpi = findTwoOfClubs();
			firstTrick = true;
			heartsBroken = false;
			
			for(int i = 0; i < 13; i++) {
				playTrick();
			}
			
			scoreHand();
			System.out.println("Total scores: ");
			printScores();
			System.out.println();
			
			handsFinished++;
		}
		findWinner();
		System.out.println(players[winner].getName() + " wins!");
	}
	private int findTwoOfClubs() {
		Card twoOfClubs = new Card(CardRank.TWO, CardSuit.CLUBS);
		int playerIndex = -1;
		for(int i = 0; i < 4; i++) {
			if(players[i].handContains(twoOfClubs))
				playerIndex = i;
		}
		return playerIndex;
	}
	private void shuffleAndDeal (){
		// shuffle 3 times
		for(int i = 0; i < 3; i++)
			baseDeck.shuffle();
		
		baseDeck.deal(players);
		
		for(int i = 0; i < 4; i++)
			players[i].sortHand();
	}
	// pass cards in direction depending on round number
	private void passCards () {
		// 	pass left: 0 to 1, 1 to 2, 2 to 3, 3 to 0
		String direction;
		if ((handsFinished % 4) == 0 ) {
			direction = "left";
			// pass to the left
			players[3].pass(players[0], direction);
			for(int i = 0; i < 3; i++) 
				players[i].pass(players[i + 1], direction);
		} 
		// 	pass right: 0 to 3, 3 to 2, 2 to 1, 1 to 0
		else if ((handsFinished % 4) == 1) {
			direction = "right";
			// pass to the right
			players[0].pass(players[3], direction);
			for (int i = 3; i > 0; i--) 
				players[i].pass(players[i - 1], direction);
		}
		//	pass across: 0 to 2, 2 to 0, 3 to 1, 1 to 3
		else if((handsFinished % 4) == 2) {
			direction = "across";
			 // pass across
			for(int i = 0; i < 2; i++)
				players[i].pass(players[i + 2], direction);
			for(int i = 3; i > 1; i--)
				players[i].pass(players[i - 2], direction);
		}
		// fourth round: no passing
	}
	private void playTrick () {
		Card current;
		Card[] trick = new Card[4];
		int highCard = -1;
		leadingSuit = null;
		
		// starts at current player and play moves clockwise
		for(int j = 0; j < 4; j++) {
			System.out.println(players[cpi].getName());
			
			// two of clubs always played on first trick of hand
			if(firstTrick) {
				current = players[cpi].startHand();
				trick[cpi] = current;
				leadingSuit = CardSuit.CLUBS;
				highCard = cpi;
				firstTrick = false;
			}
			else {
				current = players[cpi].playCard(leadingSuit, heartsBroken);
				
				if((heartsBroken == false) && (current.getSuit() == CardSuit.HEARTS))
					heartsBroken = true;
				
				// first card of trick
				if(j == 0) {
					leadingSuit = current.getSuit();
					// first card plays is the high card by default
					highCard = cpi;
					trick[cpi] = current;
				} else {
					// adds each card to the trick array
					trick[cpi] = current;
					if((current.compareTo(trick[highCard]) > 0) && (current.getSuit() == leadingSuit))
							highCard = cpi;
				}
			}
			
			System.out.println("Current trick: ");
			for(int i = 0; i < trick.length; i++) {
				if(trick[i] != null)
					System.out.println(trick[i].toString());
			}
			System.out.println();
			
			// move to next player
			if(cpi == 3)
				cpi = 0;
			else
				cpi++;
		}
		
		// player with high card takes trick and starts next trick
		players[highCard].takeTrick(trick);
		cpi = highCard;
		
		System.out.println();
	}
	private void scoreHand () {
		int[] scores = new int[4];
		boolean moonShot = false;
		int moonShotPlayer = -1;
		
		for(int i = 0; i < 4; i++) {
			scores[i] = players[i].scoreHand();
			if(scores[i] == 26) {
				moonShot = true;
				moonShotPlayer = i;
			}
		}
		if(moonShot == true) {
			System.out.println(players[moonShotPlayer] + " shot the moon!");
			for(int i = 0; i < 4; i++) {
				if(i != moonShotPlayer)
					players[i].addPoints(26);
			}
		}
		else if (moonShot == false) {
			System.out.println("Scores for this round: ");
			for(int i = 0; i < 4; i++) {
				players[i].addPoints(scores[i]);
				System.out.println(players[i].getName() + ": " + scores[i]);
			}
		}
		System.out.println();
	}
	private boolean gameOver() {
		boolean hundredReached = false;
		winner = 0;
		for(int i = 0; (i < 4) && (hundredReached == false); i++) {
			if(players[i].getScore() >= 100)
				hundredReached = true;
		}
		return hundredReached;
	}
	private void findWinner() {
		for(int j = 0; j < 4; j++) {
			if(players[j].getScore() < players[winner].getScore())
				winner = j;
		}
	}
	private void printScores () {
		for(int i = 0; i < 4; i++)
			System.out.println(players[i].getName() + ": " + players[i].getScore());
	}
	private void printPlayersHands () {
		for(int i = 0; i < 4; i++) {
			System.out.println(players[i].getName());
			players[i].printHand();
			System.out.println();
		}
	}
}
