package solitaire;

/**
 * @author maxbr
 */
public class SolitaireDeck {
	private DoubleListNode head_; // used to access the deck
	private int deckSize_; // size of the deck
	public static boolean LOG = false;
	public static boolean extendedLOG = false;

	/**
	 * allows external access to LOG
	 */
	public SolitaireDeck () {};
	/**
	 * creates a deck from an array, for testing.
	 * 
	 * @param cards
	 * @param deckSize
	 *          - must be greater than 2
	 */
	public SolitaireDeck ( SolitaireCard[] cards, int deckSize ) {
		deckSize_ = deckSize;
		head_ = new DoubleListNode(cards[0]);

		DoubleListNode current = new DoubleListNode(cards[1]);
		head_.setNext(current);
		DoubleListNode prev = head_;
		for ( int i = 2 ; i < deckSize_ + 2 ; i++ ) {
			// System.out.println(i);
			current.setPrev(prev);
			current.setNext(new DoubleListNode(cards[i]));
			prev = current;
			current = current.getNext();

		}

		current.setNext(head_);
		current.setPrev(prev);
		head_.setPrev(current);
	}

	/**
	 * Constructor makes deck with size deckSize, plus 2 jokers
	 * 
	 * @param deckSize
	 * @return an instance variable SolitaireDeck
	 */
	public SolitaireDeck ( int deckSize ) {
		deckSize_ = deckSize;
		head_ = new DoubleListNode(new SolitaireCard(1)); // creates the first card
		DoubleListNode current = new DoubleListNode(new SolitaireCard(2)),
		    prev = head_; // creates the second card
		head_.setNext(current);
		for ( int i = 2 ; i < deckSize_ ; i++ ) { // adds a new node on the end of
		                                          // the deck
			current.setPrev(prev);
			current.setNext(new DoubleListNode(new SolitaireCard(i + 1)));
			prev = current;
			current = current.getNext();
		}
		// adds two jokers
		current
		    .setNext(new DoubleListNode(new SolitaireCard(getDeckSize() + 1,'A')));
		prev = current;
		current = current.getNext();
		current.setPrev(prev);

		current
		    .setNext(new DoubleListNode(new SolitaireCard(getDeckSize() + 1,'B')));
		prev = current;
		current = current.getNext();
		current.setPrev(prev);
		// links head and tail together to complete the double linked list
		current.setNext(head_);
		current.setPrev(prev);
		head_.setPrev(current);
		if (extendedLOG) { System.out.println("contents correct: " + checkContents());}
	}

	/**
	 * returns deck size
	 * 
	 * @return deck size
	 */
	public int getDeckSize () {
		return deckSize_;
	}

	/**
	 * returns top card of deck
	 * 
	 * @return top card of deck
	 */
	public SolitaireCard getTopCard () {
		return head_.getCard();
	}

	/**
	 * returns bottom card of deck
	 * 
	 * @return bottom of deck
	 */
	public SolitaireCard getBottomCard () {
		return head_.getPrev().getCard();
	}

	/**
	 * returns nth card
	 * 
	 * @param n
	 * @return nth card in deck, n=0 returns top card
	 */
	public SolitaireCard getNthCard ( int n ) {
		DoubleListNode runner = head_;
		if ( n <= 0 || n > deckSize_ + 1 ) {
			System.out.println("Error at getNthCard(n): n out of bounds");
			return null;
		}
		for ( int i = 0 ; i < n ; i++, runner = runner.getNext() ) {}
		if (extendedLOG) { System.out.println("contents correct: " + checkContents());}
		return runner.getCard();
	}

	/**
	 * Swaps JokerA with the succeeding card
	 * 
	 * @return deck with swapped jokerA
	 */
	public void swapJokerA () {
		DoubleListNode runner = head_;
		int counter = 0;
		while ( !runner.getCard().isJokerA() ) {
			runner = runner.getNext();
			counter++;
		}
		if ( runner == head_ ) {
			swapConsec(runner,true);
		} else if ( runner.getCard() == getBottomCard() ) {
			DoubleListNode prev = runner.getPrev();
			head_.setNext(runner);
			runner.setPrev(head_);
			prev.setNext(head_);
			head_.setPrev(prev);

		} else {
			swapConsec(runner,false);
		}
		if (extendedLOG) { System.out.println("contents correct: " + checkContents());}

	}

	/**
	 * @param runner
	 *          isHead
	 * @return runner in new location, also swaps the runner with whatever card
	 *         succeeds it. If isHead, also sets the head to be the new card
	 */
	public DoubleListNode swapConsec ( DoubleListNode runner, boolean isHead ) {
		DoubleListNode switchingCard = head_, left = head_, right = head_;
		switchingCard = runner.getNext();
		left = runner.getPrev();
		right = runner.getNext().getNext();
		switchingCard.setNext(runner);
		switchingCard.setPrev(left);
		runner.setNext(right);
		runner.setPrev(switchingCard);
		left.setNext(switchingCard);
		right.setPrev(runner);
		if ( isHead ) {
			head_ = switchingCard;
		}
		if (extendedLOG) { System.out.println("contents correct: " + checkContents());}
		return runner;
	}

	/**
	 * Swaps joker B with the card in the 3rd place ahead
	 * 
	 * @return deck with swapped jokerB
	 */
	public void swapJokerB () {
		DoubleListNode runner = head_;
		int counter = 0;
		while ( !runner.getCard().isJokerB() ) {
			runner = runner.getNext();
			counter++;
		}
		if ( counter == 0 ) {
			runner = swapConsec(runner,true);
			runner = swapConsec(runner,false);

		} else if ( counter == 1 ) {
			DoubleListNode save = head_;
			runner = swapConsec(runner,true);
			runner = swapConsec(runner,false);
			head_ = save;
		} else {
			runner = swapConsec(runner,false);
			swapConsec(runner,false);
		}
		if (extendedLOG) { System.out.println("contents correct: " + checkContents());}
	}

	/**
	 * performs a count cut: counts n cards from the top, inserts those cards
	 * before the last card in the deck
	 * 
	 * @param n
	 * @return deck with counted cut
	 */
	public void countCut ( int n ) {
		DoubleListNode nth = head_, start = head_, end = head_,
		    secondFromEnd = head_, nPlusOne = head_;
		// for loops set the nodes to their corresponding locations
		for ( int i = 1 ; i < n ; i++, nth = nth.getNext() ) {}
		for ( int i = 1 ; i < getDeckSize() + 2 ; i++, end = end.getNext() ) {}
		for ( int i = 1 ; i < getDeckSize() + 1 ; i++, secondFromEnd =
		    secondFromEnd.getNext() ) {}
		DoubleListNode newH = nth.getNext();
		// links appropriate nodes
		head_.setPrev(secondFromEnd);
		secondFromEnd.setNext(head_);

		nth.setNext(end);
		end.setPrev(nth);

		head_ = newH;
		head_.setPrev(end);
		end.setNext(head_);
		if (extendedLOG) { System.out.println("contents correct: " + checkContents());}
	}

	/**
	 * returns the deck with all the cards before the first joker swapped with all
	 * the cards after the second
	 * 
	 * @returns triple cut deck
	 */
	public void tripleCut () {
		DoubleListNode firstJoker = head_, secondJoker = head_, start, prevFirst,
		    afterSecond, end;
		if ( getTopCard().isJoker() && getBottomCard().isJoker() ) {
			return; // in this case, 0 cards are swapped with 0 cards
		}
		// while loops identify first and second jokers
		while ( !firstJoker.getCard().isJoker() ) {
			firstJoker = firstJoker.getNext();
		}
		secondJoker = firstJoker.getNext();
		while ( !secondJoker.getCard().isJoker() ) {
			secondJoker = secondJoker.getNext();
		}

		// these cases only require shifting the head over by one
		if ( getBottomCard().isJoker() ) {

			head_ = firstJoker;

		} else if ( getTopCard().isJoker() ) {

			head_ = secondJoker.getNext();

		} else {

			start = head_;
			prevFirst = firstJoker.getPrev();
			afterSecond = secondJoker.getNext();
			end = head_.getPrev();

			start.setPrev(secondJoker);
			secondJoker.setNext(start);
			afterSecond.setPrev(prevFirst);
			prevFirst.setNext(afterSecond);
			firstJoker.setPrev(end);
			end.setNext(firstJoker);
			head_ = afterSecond;
		}
		if (extendedLOG) { System.out.println("contents correct: " + checkContents());}
	}

	/**
	 * returns the deck as a string
	 * 
	 * @return string representation of the deck
	 */
	public String toString () {
		String result = "";
		DoubleListNode current = head_;

		for ( int i = 0 ; i < deckSize_ + 2 ; i++, current = current.getNext() ) {

			String s = current.getCard().toString();
			int spaces = 3 - current.getCard().toString().length();
			while ( spaces > 0 ) { // makes the printed tables of numbers always line
			                       // up, for easy analysis
				s += " ";
				spaces--;
			}
			result += s + " ";
		}
		return result;
	}

	public DoubleListNode getHead () {
		return head_;
	}

	public boolean checkContents () {
		int i = 0;
		for ( DoubleListNode runner = head_.getNext() ; runner != head_ ; runner = runner.getNext(), i++) {}
		System.out.println(i + "    deck: " + deckSize_);
		if ( i-1 == deckSize_ ) {
			return true;
		} else {
			return false;
		}
	}
	public boolean checkStructure () {
		String deck = "";
		String deckBack = "";
		for ( DoubleListNode runner = head_.getNext() ; runner != head_ ; runner = runner.getNext(), deck+=runner.getCard()) {}
		for ( DoubleListNode runner = head_.getPrev() ; runner != head_ ; runner = runner.getPrev(), deck+=runner.getCard()) {}
		for (int i = 0; i < deck.length(); i++) {
			if (deck.charAt(i) != (deckBack.charAt(deckBack.length()-i))) {
				return false;
			}
		}
		return true;
		
	}

}
