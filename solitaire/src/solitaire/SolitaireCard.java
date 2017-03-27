package solitaire;

/**
 * A card in the Solitaire deck.
 * 
 * @author Stina Bridgeman
 */
public class SolitaireCard {

	private int value_; // the card's value
	private char joker_; // which joker ('A' or 'B') or '-' if not a joker

	/**
	 * Create a new joker with the specified values.
	 * 
	 * @param value
	 *          card value (>= 1)
	 * @param joker
	 *          which joker ('A' or 'B')
	 */
	public SolitaireCard ( int value, char joker ) {
		if ( value < 1 ) {
			throw new IllegalArgumentException("card value must be >= 1; got "
			    + value);
		}
		if ( joker != 'A' && joker != 'B' ) {
			throw new IllegalArgumentException("joker value must be 'A' or 'B'; got "
			    + joker);
		}

		value_ = value;
		joker_ = joker;
	}

	/**
	 * Create a new (non-joker) card with the specified value.
	 * 
	 * @param value
	 *          card value (>= 1)
	 */
	public SolitaireCard ( int value ) {
		if ( value < 1 ) {
			throw new IllegalArgumentException("card value must be >= 1; got "
			    + value);
		}

		value_ = value;
		joker_ = '-';
	}

	/**
	 * Get the card's value.
	 * 
	 * @return the card's value
	 */
	public int getValue () {
		return value_;
	}

	/**
	 * Determine if the card is joker A.
	 * 
	 * @return true if the card is joker A, false otherwise
	 */
	public boolean isJokerA () {
		return joker_ == 'A';
	}

	/**
	 * Determine if the card is joker B.
	 * 
	 * @return true if the card is joker B, false otherwise
	 */
	public boolean isJokerB () {
		return joker_ == 'B';
	}

	/**
	 * Determine if the card is a joker (either A or B).
	 * 
	 * @return true if the card is a joker, false otherwise
	 */
	public boolean isJoker () {
		return isJokerA() || isJokerB();
	}

	/**
	 * Get a string representation of the card. This will be just the card's value
	 * (for a non-joker) or the value followed by A or B (for a joker).
	 * 
	 * @return string representation of the card
	 */
	public String toString () {
		return "" + value_ + (isJoker() ? "" + joker_ : "");
	}
}
