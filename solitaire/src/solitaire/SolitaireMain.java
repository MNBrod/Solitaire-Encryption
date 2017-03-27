package solitaire;

import java.util.Scanner;

/**
 * @author maxbr
 */
public class SolitaireMain {
	public static void main ( String args[] ) {

		KeystreamGenerator gen = new KeystreamGenerator(5); // used only to check if
		SolitaireDeck deck = new SolitaireDeck(); // LOG is true or false

		System.out.println("\n  \\__/ \n  /\\/\\    Max Brodheim\n / /\\ \\\n");
		System.out.println("Solitaire Encryption\nDisplay Solitaire Algorithm: "
		    + gen.LOG
		    + " (to change, enter \"diagnostics on\" after entering passphrase)\nEncrypts ASCII characters 32-126\n");
		Boolean running = true;
		while ( running ) { // keeps the program running
			Scanner input = new Scanner(System.in);
			System.out.println("enter passphrase: "); // gets passphrase
			String solPass = input.nextLine();
			System.out.println("encrypt, decrypt, or quit? [e/d/q]");
			String next = input.nextLine();
			if ( next.equals("q") ) { // if quit
				System.out.println("quitting...");
				running = false;
				break;
			} else if ( next.equals("e") ) {
				System.out.println("enter message: "); // if encrypt
				String message = input.nextLine();
				SolitaireEncoder encoder = new SolitaireEncoder(95);
				String result = encoder.encrypt(message,solPass);
				System.out.println("encrypted: \"" + result + "\"\n\n");

			} else if ( next.equals("d") ) { // if decrypt
				System.out.println("enter message: ");
				String message = input.nextLine();
				SolitaireEncoder decode = new SolitaireEncoder(95);
				String result = decode.decrypt(message,solPass);
				System.out.println("decrypted: \"" + result + "\"\n\n");
			} else if ( next.equals("give me a challenge") ) {
				System.out.println("*J.G\\#p0By:Hg'=^J\"*!S&@<x$pc}5ve*w'");
			} else if ( next.equals("diagnostics on") ) {
				gen.LOG = true;
				deck.LOG = true;
				SolitaireDeckTester tester = new SolitaireDeckTester();
				tester.test();
				System.out
				    .println("diagnostics on. to turn off, enter \"diagnostics off\" after entering passphrase \nfor check contents, enter \"extended diagnostics\" after entering passphrase");
			} else if ( next.equals("diagnostics off") ) {
				gen.LOG = false;
				deck.extendedLOG = false;
			}else if (next.equals("extended diagnostics")) {
				gen.LOG = true;
				deck.LOG = true;
				deck.extendedLOG = true;
			} else { // if invalid input
				System.out.println("Please enter valid input. Returning to start...    "
				    + next);
			}

		}

	}
}
