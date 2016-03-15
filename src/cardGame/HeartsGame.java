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
	HeartsPlayer dealer;
	HeartsPlayer winner;
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
	//	while(!gameOver) {
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
	//	}
	}
	private void shuffleAndDeal (){
		// shuffle 3 times
		for(int i = 0; i < 3; i++)
			baseDeck.shuffle();
		
		baseDeck.deal(players);
	}
	private void passCards () {
		// pass cards in direction depending on round num:
		// 	roundsFinished % 4 == 0 pass left
		// 		0 to 1, 1 to 2, 2 to 3, 3 to 0
		// 	roundsfinished % 4 == 1, pass right
		// 		0 to 3, 3 to 2, 2 to 1, 1 to 0
		// 	roundsFinished % 4 == 2, pass across
		//		0 to 2, 2 to 0, 3 to 1, 1 to 3
		String direction;
		if ((handsFinished % 4) == 0 ) {
			direction = "left";
			// pass to the left
			players[3].pass(players[0], direction);
			for(int i = 0; i < 3; i++) 
				players[i].pass(players[i + 1], direction);
		} 
		else if ((handsFinished % 4) == 1) {
			direction = "right";
			// pass to the right
			players[0].pass(players[3], direction);
			for (int i = 3; i > 0; i--) 
				players[i].pass(players[i - 1], direction);
		}
		else if((handsFinished % 4) == 2) {
			direction = "across";
			 // pass across
			for(int i = 0; i < 2; i++)
				players[i].pass(players[i + 2], direction);
			for(int i = 3; i > 1; i--)
				players[i].pass(players[i - 2], direction);
		}
	}
	private void playTrick () {
		Card current;
		Card[] trick = new Card[4];
		int highCard = -1;
		leadingSuit = null;
		System.out.println(cpi);
		// starts at current player and goes to each
		for(int j = 0; j < 4; j++) {
			System.out.println(players[cpi].getName());
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
				
				if(j == 0) {
					leadingSuit = current.getSuit();
					highCard = cpi;
					trick[cpi] = current;
				} else {
					trick[cpi] = current;
					if((current.compareTo(trick[highCard]) > 0) && (current.getSuit() == leadingSuit))
							highCard = cpi;
				}
			}
			
			System.out.println("trick");
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
		
		players[highCard].takeTrick(trick);
		cpi = highCard;
		
		System.out.println();
	}
	private boolean checkMoonShot () {
		return false;
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
	private boolean gameOver() {
		boolean hundredReached = false;
		for(int i = 0; (i < 3) && (hundredReached == false); i++) {
			if(players[i].getScore() >= 100) {
				winner = players[i];
				hundredReached = true;
			}
		}
		return hundredReached;
	}
	private void printPlayersHands () {
		for(int i = 0; i < 4; i++) {
			System.out.println(players[i].getName());
			players[i].sortHand();
			players[i].printHand();
			System.out.println();
		}
	}
}
