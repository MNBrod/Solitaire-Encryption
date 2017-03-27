package solitaire;

/**
 * @author maxbr
 */
public class KeystreamGeneratorTester {
	private static SolitaireDeck deck_;
	private static SolitaireCard[] cards_ = new SolitaireCard[5];
	private static KeystreamGenerator gen_;
	public KeystreamGeneratorTester () {
		cards_[0] = new SolitaireCard(1);
		cards_[1] = new SolitaireCard(27,'A');
		cards_[2] = new SolitaireCard(5);
		cards_[3] = new SolitaireCard(27,'B');
		cards_[4] = new SolitaireCard(17);
		deck_ = new SolitaireDeck(cards_,3);
		gen_ = new KeystreamGenerator(deck_);
	}
	public static void test () {
		
		
		
		gen_.nextKeystreamValue();
		if (gen_.deck_.toString().replaceAll(" ","").equals("1527A27B17")) {
			System.out.println("Joker A swap passed");
		} else {
			System.out.println("Joker A ");
		}

	}
}
