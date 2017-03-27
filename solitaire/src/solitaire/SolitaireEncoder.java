package solitaire;

/**
 * @author maxbr
 */
public class SolitaireEncoder {
	private KeystreamGenerator gen_;
	private int deckSize_;
	
	public SolitaireEncoder (int deckSize) {
		deckSize_ = deckSize;
		gen_ = new KeystreamGenerator(deckSize_);
	}
/**
 * encrypts a message  from a passphrase
 * @param message - message to be encrypted
 * @param pass - passphrase
 * @return encrypted message
 */
	public String encrypt ( String message, String pass) {
		String result = "";
		gen_.key(pass);
		if (gen_.LOG) {System.out.println("keyed: " + gen_.deck_.toString() + "\n\n\n");}
		char[] messArray = message.toCharArray(); //converts message to an array of char
		int[] messInt = new int[messArray.length];
		for ( int i = 0 ; i < messArray.length ; i++ ) {
			int keyVal = gen_.nextKeystreamValue(); //gets a keystream value
			if (gen_.LOG) {System.out.println( "\n      keyVal is " + keyVal);}
			
			messInt[i] = gen_.numberOfLetter(messArray[i]); //fills an int array with the integer representation of the corresponding char
			messInt[i] += keyVal;
		//ensures the value is within the range of usable ASCII characters (32-126)
			if (gen_.LOG) {System.out.println("initial: " +messInt[i]);}
			while ( messInt[i] > 126) {
				messInt[i] -= 95;
			}
			if (gen_.LOG) {System.out.println("then: " +messInt[i]);}
			while ( messInt[i] < 32) {
				messInt[i] += 95;
			}
			if (gen_.LOG) {System.out.println("then: " + messInt[i] + "   me\n\n");}

			result += gen_.letterOfNumber(messInt[i]);
			if (gen_.LOG) {System.out.println(result);}
		}

		return result;
	}
	/**
	 * decrypts a message with the passphrase
	 * @param message - message to be decrypted
	 * @param pass - passphrase to decrypt with
	 * @return
	 */
	public String decrypt (String message, String pass) {
		String result = "";
		gen_.key(pass);
		if (gen_.LOG) {System.out.println("keyed: " + gen_.deck_.toString() + "\n\n\n");}
		char[] messArray = message.toCharArray();
		int[] messInt = new int[messArray.length];
		for ( int i = 0 ; i < messArray.length ; i++ ) {
			int keyVal = gen_.nextKeystreamValue();
			if (gen_.LOG) {System.out.println( "\n      keyVal is" + keyVal);}
			
			messInt[i] = gen_.numberOfLetter(messArray[i]);
			messInt[i] -= keyVal; 
		//ensures the value is within the range of usable ASCII characters (32-126)
			while ( messInt[i] < 32) {
				messInt[i] += 95;
			}
			while ( messInt[i] > 126) {
				messInt[i] -= 95;
			}
			
			result += gen_.letterOfNumber(messInt[i]) ; 
			if (gen_.LOG) {System.out.println(result);}
		}

		return result;
	}

}
