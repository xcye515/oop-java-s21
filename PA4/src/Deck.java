/**
* This program constructs a Deck object with two piles of cards and methods that shuffles and gets the cards from the deck.
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Mar 24, 2021
* COSI 12B PA4
*/

import java.util.Random;

public class Deck {
	Card[] deck;
	Card[] discard;
	int count = 0;
	
	/**
	 * This constructor initializes the deck card array to a pile with 52 different cards and shuffles it. 
	 * It also initializes the discard pile to a empty card array with length 52.
	 */
	public Deck() {
		this.deck = new Card[52];
		this.discard = new Card[52];
		for(int i = 0; i < 13; i++) {
			Card c = new Card(i+1,"Hearts");
			deck[i] = c;
		}
		for(int i = 0; i < 13; i++) {
			Card c = new Card(i+1,"Diamonds");
			deck[13+i] = c;
		}
		for(int i = 0; i < 13; i++) {
			Card c = new Card(i+1,"Spades");
			deck[26+i] = c;
		}
		for(int i = 0; i < 13; i++) {
			Card c = new Card(i+1,"Clubs");
			deck[39+i] = c;
		}
		shuffle();
	}
	
	/**
	 * This method shuffles the cards in the deck pile using The Modern Algorithm, which is described in pseudocode on Wikipedia
	 */
	public void shuffle() {
		Card temp;
		int j;
		Random rand = new Random();
		for(int i = 51; i > 0; i--) {
			j = rand.nextInt(i+1);
			temp = deck[i];
			deck[i] = deck[j];
			deck[j] = temp;
		}
	}
	
	/**
	 * This method returns to the card at the index count in the deck array, in which the index count is augmented by calling this method
	 * @return next Card the card should be drawn in the deck array
	 */
	public Card drawNextCard() {
		if(count < 52) {
			Card next = deck[count];
			deck[count] = null;
			discard(next);
			count++;
			return next;
		} else {
			deck = discard.clone();
			discard = new Card[52];
			shuffle();
			count = 0;
			Card next = deck[count];
			deck[count] = null;
			discard(next);
			count++;
			return next;
		}
	}
	
	/**
	 * This method receives a card object and add the card in the discard array
	 * @param c Card that we want to discard
	 */
	public void discard(Card c) {
		discard[count] = c;
	}
	
}
