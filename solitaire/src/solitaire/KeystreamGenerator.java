package solitaire;

/**
 * @author maxbr
 */
public class KeystreamGenerator {
	public static boolean LOG = false; //allows user to troubleshoot by displaying all steps
	public SolitaireDeck deck_; //stores the deck

	/**
	 * constructs a KeystreamGenerator with a deck deckSize
	 * 
	 * @param deckSize
	 */
	public KeystreamGenerator ( int deckSize ) {
		deck_ = new SolitaireDeck(deckSize);
	}
	public KeystreamGenerator (SolitaireDeck deck) {
		deck_ = deck;
	}

	/**
	 * retuns the ASCII dec code for a given char. not really necessary, but an
	 * artifact of an earlier version
	 * 
	 * @param char
	 *          c
	 * @return ASCII dec code for char c
	 */
	public int numberOfLetter ( char c ) {

		return (int) c;
	}

	/**
	 * returns the char corresponding to the ASCII dec code c. not really
	 * necessary, but an artifact of an earlier version
	 * 
	 * @param c
	 * @return char with ASCII dec code c
	 */
	public char letterOfNumber ( int c ) {

		return (char) c;
	}
/**
 * arranges the deck based off of a passphrase.
 * @param pass, the passphrase
 * @return keyed deck
 */
	public void key ( String pass ) {
		char[] passArray = pass.toCharArray();
		int[] intArray = new int[passArray.length];
		for ( int i = 0 ; i < passArray.length ; i++ ) {
			intArray[i] = numberOfLetter(passArray[i]);
		}
		for ( int i = 0 ; i < passArray.length ; i++ ) {
			if ( LOG ) {
				System.out.println(deck_.toString() + " inital Key");
			}
			deck_.swapJokerA();
			if ( LOG ) {
				System.out.println(deck_.toString() + " After A Key");
			}
			deck_.swapJokerB();
			if ( LOG ) {
				System.out.println(deck_.toString() + " After B Key");
			}
			deck_.tripleCut();
			if ( LOG ) {
				System.out.println(deck_.toString() + " After Triple Key");
			}
			int val = deck_.getHead().getPrev().getCard().getValue();
			deck_.countCut(deck_.getHead().getPrev().getCard().getValue());
			if ( LOG ) {
				System.out.println(deck_.toString() + " Key After count 1, value was: "
				    + val);
			}
			deck_.countCut(intArray[i]);
			if ( LOG ) {
				System.out.println(deck_.toString() + " Key After count 2, value was: "
				    + intArray[i] + "\n");
			}
		}
	}
/**
 * generates the next value to be used in encryption
 * @return the next int in keystream
 */
	public int nextKeystreamValue () {
		int result = 0;
		while ( true ) {
			if ( LOG ) {
				System.out.println(deck_.toString() + " inital");
			}
			deck_.swapJokerA();
			if ( LOG ) {
				System.out.println(deck_.toString() + " After A");
			}
			deck_.swapJokerB();
			if ( LOG ) {
				System.out.println(deck_.toString() + " After B");
			}
			deck_.tripleCut();
			if ( LOG ) {
				System.out.println(deck_.toString() + " After Triple");
			}
			int val = deck_.getHead().getPrev().getCard().getValue();
			deck_.countCut(val);
			if ( LOG ) {
				System.out
				    .println(deck_.toString() + " After count 1, value was: " + val);
			}
			SolitaireCard possibleCard =
			    deck_.getNthCard(deck_.getTopCard().getValue());
			if ( !possibleCard.isJoker() ) { //if its a joker, 
				result = possibleCard.getValue();

				while ( result < 32 ) { //ensures the value is within the range of usable ASCII characters (32-126)
					result += 95;
				}
				while ( result > 126 ) {
					result -= 95;
				}

				if ( LOG ) {
					System.out.println("value recieved is: " + possibleCard.getValue()
					    + ", or " + letterOfNumber(result));
				}
				return result;

			} else {
				if ( LOG ) {
					System.out.println("got " + possibleCard.getValue());
				}
			}
		}
	}
}
