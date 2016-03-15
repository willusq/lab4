package cardGame;

import java.util.ArrayList;
import java.util.Scanner;

import cardGame.Card.CardRank;
import cardGame.Card.CardSuit;

public class CardTest {
	public static void main(String[] args) {
		Pile myPile = new Pile();
		Card card = new Card(CardRank.ACE, CardSuit.HEARTS);
		myPile.addToBottom(card);
		System.out.println(myPile.getNumCards());
		myPile.print();
	}
	
}