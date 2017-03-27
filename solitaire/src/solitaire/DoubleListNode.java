package solitaire;
/**
 * A doubly-linked list node storing a SolitaireCard.
 * 
 * @author Stina Bridgeman
 */
public class DoubleListNode {

	private SolitaireCard card_; // the card
	private DoubleListNode next_, prev_; // next and previous nodes

	/**
	 * Create a new node storing the specified card. Next and previous pointers
	 * are null.
	 * 
	 * @param card
	 *          the element
	 */
	public DoubleListNode ( SolitaireCard card ) {
		card_ = card;
		next_ = null;
		prev_ = null;
	}

	/**
	 * Create a new node storing the specified card. Next and previous pointers
	 * are as specified.
	 * 
	 * @param card
	 *          the element
	 * @param prev
	 *          the prev node
	 * @param next
	 *          the next node
	 */
	public DoubleListNode ( SolitaireCard card, DoubleListNode prev,
	                        DoubleListNode next ) {
		card_ = card;
		prev_ = prev;
		next_ = next;
	}

	/**
	 * Get the next node.
	 * 
	 * @return the next node
	 */
	public DoubleListNode getNext () {
		return next_;
	}

	/**
	 * Set the next node.
	 * 
	 * @param next
	 *          the next node
	 */
	public void setNext ( DoubleListNode next ) {
		next_ = next;
	}

	/**
	 * Get the previous node.
	 * 
	 * @return the previous node
	 */
	public DoubleListNode getPrev () {
		return prev_;
	}

	/**
	 * Set the previous node.
	 * 
	 * @param prev
	 *          the previous node
	 */
	public void setPrev ( DoubleListNode prev ) {
		prev_ = prev;
	}

	/**
	 * Get the element.
	 * 
	 * @return this node's card
	 */
	public SolitaireCard getCard () {
		return card_;
	}

}
