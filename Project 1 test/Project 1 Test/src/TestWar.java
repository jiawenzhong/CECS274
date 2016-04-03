import java.util.ArrayList;
import java.util.Collections;

/**
 * Test program for class War.
 * 
 * Instructions: 
 * 		1. Place this file in the same project folder as War.java.
 * 		2. Do not modify this file.
 * 		3. Write your functions in War.java. 
 * 		4. When you finish a function, run this program to check if it works.
 * 		5. If a test returns false, it should report in what way your function is 
 * 			incorrect.  Go back and fix your function and then try again.
 *  	6. This program is merely a cursory check for common mistakes, do not rely 
 *  		on it to guarantee that your program is running perfectly. Run your program 
 *  		several times to check that it works correctly. Double check values for errors.
 *  
 * @author Shannon Foss
 */

public class TestWar {
	public static void main(String[] args) {
		System.out.println("Initialize Deck Passed: " + testInitDeck());
		System.out.println("Shuffle Deck Passed: " + testShuffleDeck());
		System.out.println("Split Deck Passed: " + testSplitDeck());
		System.out.println("Sort Deck Passed: " + testSortDeck());
		System.out.println("Battle Test Passed: " + testBattle());
		System.out.println("War Test Passed: " + testWar());
		
	}
	
	/**
	 * Test function for Initializing Deck
	 * @return false if the deck is not initialized properly.
	 */
	public static boolean testInitDeck() {
		int[] correctFullDeck = {1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,
								 8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13};
		ArrayList<Integer> testDeck = new ArrayList<Integer>();
		War.initializeDeck(testDeck);
		
		if(testDeck.size() != 52) {
			System.out.println("Initialize Deck - Incorrect Size: " + testDeck.size());
			return false;
		}
		for(int i = 0; i < correctFullDeck.length; i++) {
			if(testDeck.get(i) != correctFullDeck[i]) {
				System.out.println("Initialize Deck - Incorrect Values: " + testDeck);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Test function for Shuffling Deck
	 * @return false if the deck is not shuffled properly.
	 */
	public static boolean testShuffleDeck() {
		ArrayList<Integer> testDeck1 = new ArrayList<Integer>();
		ArrayList<Integer> testDeck2 = new ArrayList<Integer>();
		War.initializeDeck(testDeck1);
		War.initializeDeck(testDeck2);
		War.shuffleDeck(testDeck1);
		War.shuffleDeck(testDeck2);
		if(testDeck1.size() != 52 || testDeck2.size() != 52) {
			System.out.println("Shuffle Deck - Incorrect Size");
			return false;
		}
		boolean same = true;
		for(int i = 0; i < testDeck1.size(); i++) {
			if(testDeck1.get(i) != testDeck2.get(i)) {
				same = false;
			}
		}
		if(same) {
			System.out.println("Shuffle Deck - Not a Unique Shuffle");
			return false;
		}
		return true;
	}
	/**
	 * Test function for Splitting a Deck
	 * @return false if the deck is not split properly.
	 */
	public static boolean testSplitDeck() {
		int[] correctSplitDeckA = {1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7};
		int[] correctSplitDeckB = {13,13,13,13,12,12,12,12,11,11,11,11,10,10,10,10,9,9,9,9,8,8,8,8,7,7};
		ArrayList<Integer> testDeck = new ArrayList<Integer>();
		War.initializeDeck(testDeck);
		ArrayList<Integer> splitDeck = War.splitDeck(testDeck);
		if(testDeck.size() != 26 || splitDeck.size() != 26) {
			System.out.println("Split Deck - Incorrect Size");
			return false;
		}
		for(int i = 0; i < testDeck.size(); i++) {
			if(testDeck.get(i) != correctSplitDeckA[i]) {
				System.out.println("Split Deck - Incorrect Split: " + testDeck);
				return false;
			}
			if(splitDeck.get(i) != correctSplitDeckB[i]) {
				System.out.println("Split Deck - Incorrect Split: " + splitDeck);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Test function for Sorting a Deck
	 * @return false if the deck is not sorted properly.
	 */
	public static boolean testSortDeck(){
		int[] correctDeck = {1,1,1,1,2,2,2,2,3,3,3,3,4,4,4,4,5,5,5,5,6,6,6,6,7,7,7,7,8,8,
							 8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,12,13,13,13,13};
		ArrayList<Integer> testDeck = new ArrayList<Integer>();
		War.initializeDeck(testDeck);
		War.shuffleDeck(testDeck);
		testDeck = War.sortDeck(testDeck);
		
		if(testDeck.size() != 52) {
			System.out.println("Sort Deck - Incorrect Size");
			return false;
		}
		for(int i = 0; i < correctDeck.length; i++) {
			if(testDeck.get(i) != correctDeck[i]) {
				System.out.println("Sort Deck - Incorrect Sort: " + testDeck);
				System.out.println(testDeck);
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Test function for a battle
	 * @return false if the battle is not performed properly.
	 */
	public static boolean testBattle() {
		ArrayList<Integer> compDeck = new ArrayList<Integer>();
		ArrayList<Integer> playerDeck = new ArrayList<Integer>();
		compDeck.add(13);
		playerDeck.add(1);
		int winner = War.battle(compDeck, playerDeck);
		if(winner != 1) {
			System.out.println("Test Battle - Did not return correct winner.");
			return false;
		}
		if(compDeck.size() != 2) {
			System.out.println("Test Battle - Did not add card to winner's deck.");
			return false;
		}
		if(playerDeck.size() != 0) {
			System.out.println("Test Battle - Did not remove card from loser's deck.");
			return false;
		}
		compDeck.clear();
		playerDeck.clear();
		compDeck.add(1);
		playerDeck.add(13);
		winner = War.battle(compDeck, playerDeck);
		if(winner != 2) {
			System.out.println("Test Battle - Did not return correct winner.");
			return false;
		}
		if(playerDeck.size() != 2) {
			System.out.println("Test Battle - Did not add card to winner's deck.");
			return false;
		}
		if(compDeck.size() != 0) {
			System.out.println("Test Battle - Did not remove card from loser's deck.");
			return false;
		}
		return true;
	}
	
	/**
	 * Test function for a war
	 * @return false if the war is not performed properly.
	 */
	public static boolean testWar() {
		ArrayList<Integer> compDeck = new ArrayList<Integer>();
		ArrayList<Integer> playerDeck = new ArrayList<Integer>();
		Integer [] warSet1Comp = {7,1,3,5,1};
		Integer [] warSet1Player = {7,2,4,6,13};
		Collections.addAll(compDeck, warSet1Comp);
		Collections.addAll(playerDeck, warSet1Player);
		int winner = War.war(compDeck, playerDeck);
		if(winner != 2) {
			System.out.println("Test War 2 - Did not return correct winner." + winner);
			return false;
		}
		if(playerDeck.size() != 10) {
			System.out.println("Test War - Did not add correct number of cards to winner's deck.");
			return false;
		}
		if(compDeck.size() != 0) {
			System.out.println("Test War - Did not remove correct number of cards from loser's deck.");
			return false;
		}
		compDeck.clear();
		playerDeck.clear();

		Integer [] warSet2Comp =   {7,1,3,5,9,1,3,5,11,1,3,5,13};
		Integer [] warSet2Player = {7,2,4,6,9,2,4,6,11,1,3,5,1};
		Collections.addAll(compDeck, warSet2Comp);
		Collections.addAll(playerDeck, warSet2Player);
		winner = War.war(compDeck, playerDeck);
		if(winner != 1) {
			System.out.println("Test War winner 1 - Did not return correct winner."+ winner);
			System.out.println("computer "+compDeck);
			System.out.println("player " + playerDeck);
			return false;
		}
		if(compDeck.size() != 26) {
			System.out.println("Test War - Did not add correct number of cards to winner's deck.");
			return false;
		}
		if(playerDeck.size() != 0) {
			System.out.println("Test War - Did not remove correct number of cards from loser's deck.");
			return false;
		}
		compDeck.clear();
		playerDeck.clear();
		
		Integer [] warSet3Comp =   {7,1,3,5,9,1,3,5,11,1,3,5};
		Integer [] warSet3Player = {7,2,4,6,9,2,4,6,11,1,3,5,1};
		Collections.addAll(compDeck, warSet3Comp);
		Collections.addAll(playerDeck, warSet3Player);
		winner = War.war(compDeck, playerDeck);
		if(winner != 4) {
			System.out.println("Test War - Did not return correct winner.  Loser ran out of cards.");
			return false;
		}
		compDeck.clear();
		playerDeck.clear();

		Integer [] warSet4Comp = {7,1,3,5,1};
		Integer [] warSet4Player = {7,2};
		Collections.addAll(compDeck, warSet4Comp);
		Collections.addAll(playerDeck, warSet4Player);
		winner = War.war(compDeck, playerDeck);
		if(winner != 3){
			System.out.println("Test War - Did not return correct winner.  Loser ran out of cards.");
			return false;
		}
		
		return true;
	}
}