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
	int currentHandNum;
	int currentPlayerIndex;
	CardSuit leadingSuit;
	HeartsPlayer dealer;
	HeartsPlayer winner;
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
		currentHandNum = 1;
		currentPlayerIndex = findTwoOfClubs();
	}
	public void playGame() {
	//	while(!gameOver) {
			shuffleAndDeal();
			passCards();
			
			currentPlayerIndex = findTwoOfClubs();
			
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
		// prompt each player to pick three cards to pass
		// pass cards in direction depending on round num:
		// 	currentRoundIndex % 4 == 1 pass left
		// 		0 to 1, 1 to 2, 2 to 3, 3 to 0
		// 	cri % 4 == 2, pass right
		// 		0 to 3, 3 to 2, 2 to 1, 1 to 0
		// 	cri % 4 == 3, pass across
		//		0 to 2, 2 to 0, 3 to 1, 1 to 3
	}
	private void playTrick () {
		for(int j = currentPlayerIndex; j < (currentPlayerIndex + 3); j++) {
			
		}
	}
	private boolean checkMoonShot () {
		return false;
	}	
	private int findTwoOfClubs() {
		Card twoOfClubs = new Card(CardRank.TWO, CardSuit.CLUBS);
		int playerIndex = -1;
		for(int i = 0; i < 3; i++) {
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
	
}
