/**
* This program constructs a Card object with three properties and methods that help us to get the properties
* Known Bugs: None
*
* @author Xingchen Ye
* xingchenye@brandeis.edu
* Mar 24, 2021
* COSI 12B PA4
*/

public class Card {
	String suit;
	String color;
	int value;
	
	/**
	 * This constructor receives a int and a string, indicating its value and suit, to construct a card object
	 * It also determines the color of the card from the suit
	 * @param value int the value of the card
	 * @param suit String the suit of the card
	 */
	public Card(int value, String suit) {
		if(1 <= value  && value <= 13) {
			this.value = value;
		} else {
			System.out.println("Please enter a valid value of card.");
		}
		if(suit.equals("Hearts") || suit.equals("Diamonds")) {
			this.color = "red";
			this.suit = suit;
		} else if (suit.equals("Spades") || suit.equals("Clubs")) {
			this.color = "black";
			this.suit = suit;
		} 
	}
	
	/**
	 * This method gets the value of the card
	 * @return int the value of the card
	 */
	public int getValue() {
		return this.value;
	}
	
	/**
	 * This method gets the color of the card
	 * @return String the color of the card
	 */
	public String getColor() {
		return this.color;
	}
	
	/**
	 * This method gets the suit of the card
	 * @return String the suit of the card
	 */
	public String getSuit() {
		return this.suit;
	}
	
	/**
	 * This method returns the string representation of the card
	 * @return result String representation of the card
	 */
	public String toString() {
		String result;
		String value;
		if(this.value == 1) {
			value = "Ace";
			result = value + " of " + this.suit;
		} else if (this.value == 11) {
			value = "Jack";
			result = value + " of " + this.suit;
		} else if (this.value == 12) {
			value = "Queen";
			result = value + " of " + this.suit;
		} else if (this.value == 13) {
			value = "King";
			result = value + " of " + this.suit;
		} else {
			result = this.value + " of " + this.suit;
		}
		return result;
		
	}
}
