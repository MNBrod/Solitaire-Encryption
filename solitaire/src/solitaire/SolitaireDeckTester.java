package solitaire;

/**
 * @author mb5053
 */
public class SolitaireDeckTester {
	private static SolitaireDeck deck_;
	private static SolitaireCard[] cards_ = new SolitaireCard[5];
	private static SolitaireEncoder encode = new SolitaireEncoder(5);

	public static void test () {
		cards_[0] = new SolitaireCard(26);
		cards_[1] = new SolitaireCard(27,'A');
		cards_[2] = new SolitaireCard(5);
		cards_[3] = new SolitaireCard(27,'B');
		cards_[4] = new SolitaireCard(17);

		deck_ = new SolitaireDeck(cards_,3);
		/**
		 * Test if toString() method works 
		 * @input: 26, 27A, 5, 27B, 17 
		 * @expected: "26	 27A 5 27B 17 "
		 */
		if ( !deck_.toString().equals("26  27A 5   27B 17  ") ) {
			System.out.println("toString() failed: got " + deck_.toString());
		} else {
			System.out.println("to string: passed");
		}
		/**
		 * JokerA swap
		 * 
		 * @input: 26, 27A, 5, 27B, 17
		 * @expected: "26  5   27A 27B 17  "
		 */
		deck_ = new SolitaireDeck(cards_,3);
		deck_.swapJokerA();
		if ( deck_.toString().equals("26  5   27A 27B 17  ") ) {
			System.out.println("JokerA swap: passed");
		} else {
			System.out.println("JokerA swap failed: got " + deck_.toString());
		}

		/**
		 * jokerB swap
		 * @input: 26, 27A, 5, 27B, 17
		 * @expected: "26  27B 27A 5   17  "
		 */
		deck_ = new SolitaireDeck(cards_,3);
		deck_.swapJokerB();
		if (deck_.toString().equals("26  27B 27A 5   17  ")) {
			System.out.println("JokerB swap passed");
		} else {
			System.out.println("JokerB swap failed: got " + deck_.toString());
		}
		
		/**
		 * triple cut
		 * @input: 26, 27A, 5, 27B, 17
		 * @expected: " 17  27A 5   27B 26  "
		 */
		deck_ = new SolitaireDeck(cards_,3);
		deck_.tripleCut();
		if (deck_.toString().replaceAll(" ", "").equals("1727A527B26")) {
			System.out.println("triple cut passed");
		} else {
			System.out.println("Triple Cut swap failed: got " + deck_.toString());
		}
		
		deck_ = new SolitaireDeck(cards_,3);
		deck_.countCut(2);
		if (deck_.toString().replaceAll(" ", "").equals("527B2627A17")) {
			System.out.println("count cut passed");
		}
		
		//System.out.println(deck_.toString());
	}
}
