/**
 * @author 012750918 Jiawen Zhong
 * @author Shannon Foss
 * CECS 274 
 * This program will run as a game called War between the player and the computer. The game result will be calculated 
 * automatically by the program and the game will run automatically.
 */
import java.util.ArrayList;
import java.util.Scanner;

public class War {
	public static void main(String[] args) {
		int winner = 0;
		int playerWin = 0;
		int totalGame = 0;
		//Create an initial deck and shuffle it
		ArrayList <Integer> initialDeck = new ArrayList<Integer>();
		initializeDeck(initialDeck);
			System.out.println("original " + initialDeck);
		shuffleDeck(initialDeck);
			System.out.println("Shufled " + initialDeck);
			
		//create the player's deck and the computer's deck by calling splitDeck()
		ArrayList <Integer> playerDeck = new ArrayList <Integer>();
		ArrayList <Integer>compDeck = new ArrayList <Integer>();
		playerDeck = splitDeck(initialDeck);
			System.out.println("player " + playerDeck);
		compDeck = initialDeck;
			System.out.println("computer " + initialDeck);
		
		int userChoice = 0;
		boolean done = false;

		System.out.println("Welcome to War!");
		System.out.println("");

		while(!done) {
			displayMenu();
			userChoice = checkInt();
			switch(userChoice){
			case 1: //Battle
				winner = battle(compDeck, playerDeck);
				totalGame ++;//each round will count as one game
				if (winner == 1){
					System.out.println("winner: computer");
					System.out.println("computer " + initialDeck);
					System.out.println("player " + playerDeck);
				}else if (winner == 2){
					System.out.println("winner: player");
					System.out.println("computer " + initialDeck);
					System.out.println("player " + playerDeck);
					playerWin ++;//add 1 pt to the score
				}else if (winner == 3){
					System.out.println("Player is out of cards");
					System.out.println("winner: computer");
					System.out.println("computer " + initialDeck);
					System.out.println("player " + playerDeck);
					done = true;
				}else if (winner == 4){
					System.out.println("Computer is out of cards");
					System.out.println("winner: player");
					System.out.println("computer " + initialDeck);
					System.out.println("player " + playerDeck);
					playerWin ++;//add 1 pt to the score
					done = true;
				}
				break;
			case 2: displayScore(compDeck, playerDeck);//Show number of cards for each player.
				break;
			case 3: printDeck(playerDeck);//Display player's cards.
				break;
			case 4: displayStats(totalGame, playerWin);//Display game stats.
				break;
			case 5: //End game
				System.out.println("Game Over");
				done = true;
				break;
			default:System.out.println("Invalid Input");
			break;
			}
		}
	}

	/**
	 * This method displays the menu
	 */
	public static void displayMenu() {

		System.out.println("1. Play");
		System.out.println("2. Show Score");
		System.out.println("3. Peek at my cards");
		System.out.println("4. Show Percentage");
		System.out.println("5. Exit");
	}

	/**
	 * This method checks if the value the user enters choosing from the option menu is valid
	 * if it is not valid, it will prompt the user to enter again
	 * @return 			the number that the user enters
	 */
	public static int checkInt() {
		Scanner in = new Scanner(System.in);
		int input = 0;
		boolean valid = false;
		while(!valid) {
			if(in.hasNextInt()) {
				input = in.nextInt();
				valid = true;
			} else {
				System.out.println("Invalid Input");
				in.next();
			}
		}
		return input;
	}

	/**
	 * This method initialize a deck of 52 cards from 1 -13
	 * @param deck			the empty arraylist deck that need to be initialize with 52 cards.
	 */
	public static void initializeDeck(ArrayList<Integer> deck) {
		//ArrayList<Integer> shuffledCards = new ArrayList<Integer>();
		//loop through to initialize the deck with four of each of the values 1-13.
		int initialValue = 1;//the card number starts at one
		for(int i = 0; i < 52 && initialValue < 14; i++){//need 52 cards and the number will stop when it reach to 14
			for (int j = 1; j < 5; j++){//four of the same number from 1- 13
				deck.add(initialValue);
			}
			initialValue ++;
		}
	}

	/**
	 * This method shuffle the deck of cards, so the numbers will be in different location in the
	 * arraylist
	 * @param deck		the initialized deck of card that is being passed from the method initializeDeck
	 */
	public static void shuffleDeck(ArrayList<Integer> deck) {
		//swap values at random locations many times to shuffle deck.
		int tempCard = 0;
		for (int i = 0; i < deck.size(); i++){
			int randomIndex1 = (int) (Math.random()*deck.size());
			int randomIndex2 = (int) (Math.random()*deck.size());
			tempCard = deck.get(randomIndex1);
			deck.set(randomIndex1, deck.get(randomIndex2));
			deck.set(randomIndex2, tempCard);

		}

	}
	/**
	 * This method split the deck into two equal deck between the player and the computer
	 * @param deck			 the shuffled deck of card that need to be processed
	 * @return playerDeck	 the second half of the original shuffled deck that belong to the player
	 */
	public static ArrayList<Integer> splitDeck(ArrayList<Integer> deck) {
		ArrayList<Integer> playerDeck = new ArrayList<Integer>();
		
		//remove from the end of the deck and place into the new deck.
		for (int i = deck.size() - 1 ; i >= deck.size() / 2; i --){
			playerDeck.add(deck.get(i));
		}
		deck.subList(deck.size()/2, deck.size()).clear();//clear the second half of the deck arraylist
		
		return playerDeck;
	}

	/**
	 * This method sort the shuffled deck of card back into order, from low to high
	 * @param deck		the shuffled deck of cards
	 * @return			the sorted copy of the shuffled cards
	 */
	public static ArrayList<Integer> sortDeck(ArrayList <Integer> deck) {
		ArrayList<Integer> copy = new ArrayList<Integer>(deck);
		//copy over the contents of the deck and return the new sorted list (copy)  
		//(do not sort the original (deck))
		for (int i = 0; i < copy.size(); i++){
			int lowest = i;
			for (int j = i + 1; j < copy.size(); j++){
				if (copy.get(j) < copy.get(lowest)){
					lowest = j;
				}
			}
			int swap = copy.get(i);
			copy.set(i, copy.get(lowest));
			copy.set(lowest, swap);
		}
		return copy;
	}

	/**
	 * This method print the sorted deck of card from the method sortDeck
	 * @param deck		the arraylist that is passed down from the shuffledDeck method
	 */
	public static void printDeck(ArrayList<Integer> deck) {
		//print the contents of the sorted deck
		System.out.println("My Cards: " + sortDeck(deck));
	}

	public static void distributeCards(ArrayList<Integer> winner, ArrayList<Integer> loser) {
		//loser loses a card to the winner
		int loserCard = loser.remove(0);
		int winCard = winner.remove(0);
		//winner places their played card at the bottom of their deck
		winner.add(loserCard);
		winner.add(winCard);

//		for (int i = 0; i < numberOfCards; i++){
//			//loser loses a card to the winner
//			int loserCard = loser.remove(0);
//			//winner places their played card at the bottom of their deck
//			winner.add(loserCard);
//		}
	}
	
	/**
	 * This method is for the battle of just a single card between the computer and the player
	 * @param compDeck			the cards that belong to the computer for battle
	 * @param playerDeck		the cards that belong to the player in the battle
	 * @return					the winner in the battle
	 */
	public static int battle(ArrayList<Integer> compDeck, ArrayList<Integer> playerDeck) {
		int winner = 0;
		//player and computer still have cards
		boolean playerHasCards = playerDeck.size() > 0;
		boolean compHasCards = compDeck.size() > 0;		

		//1 = comp won battle
		int firstCard = 0;
		if (compDeck.get(firstCard) > playerDeck.get(firstCard) && playerHasCards && compHasCards){
			System.out.println("Comp plays a " + compDeck.get(firstCard));
			System.out.println("player plays a " + playerDeck.get(firstCard));
			distributeCards(compDeck, playerDeck);
			winner = 1;

			//2 = player won battle
		}else if (playerDeck.get(firstCard) > compDeck.get(firstCard) && playerHasCards && compHasCards){
			System.out.println("Comp plays a " + compDeck.get(firstCard));
			System.out.println("player plays a " + playerDeck.get(firstCard));
			distributeCards(playerDeck, compDeck);
			winner = 2;

			//3 = comp won game due to player running out of cards
		}else if (playerDeck.size() == 0){
			System.out.println("Player is out of cards");
			winner = 3;

			//4 = player won game due to comp running out of cards
		}else if (compDeck.size() == 0){
			System.out.println("Computer is out of cards");
			winner = 4;

			//display and compare the cards at the top of the deck, determine the winner
			//if the values are the same, a war is declared
		}else if (compDeck.get(firstCard) == playerDeck.get(firstCard)){
			System.out.println("Comp plays a " + compDeck.get(firstCard));
			System.out.println("player plays a " + playerDeck.get(firstCard));
			System.out.println("War!");
			winner = war(compDeck, playerDeck);
		}
		
		return winner;
	}
	
	/**
	 * This method begins the war between the computer and the player. Every time there is 
	 * war, each side will put down 4 cards, 3 covered and 1 revealed.
	 * @param compDeck			the cards that the computer use for the war
	 * @param playerDeck		the deck that the player use for the war
	 * @return
	 */
	public static int war(ArrayList<Integer> compDeck, ArrayList<Integer> playerDeck) {
		int winner = 0;
		//number of cards that will be lost by the loser
		int numCardLost = 5;

		//the card that is being pick to be reveal during each war, and it is starting from 1 - 5, 5 is exclusive
		int startLocation = 1;
		int cardLocation = 5;

		//player and computer still have cards
		boolean playerHasCards = playerDeck.size() > 5;
		boolean compHasCards = compDeck.size() > 5;
		
		//random card that will reveal in the four cards
		int randomCompCard = compDeck.get((int)Math.random()*cardLocation + startLocation );
		int randomPlayerCard = playerDeck.get((int)Math.random()*cardLocation + startLocation );
//		System.out.println("Comp plays war " + randomCompCard);
//		System.out.println("player plays war " + randomPlayerCard);
		//when there are no tie
		boolean tie = true;

		//if there was a tie in a battle, a war is played. A war consists of both players laying 
		//down three additional cards face down and then one more face up. Compare these cards
		//in the same way as a battle.
		while(tie){
	
			if ( randomCompCard == randomPlayerCard && playerHasCards && compHasCards){
				//draw four more cards
				numCardLost += 4;
				//when there is a tie, the location of the card move down by 4
				cardLocation += 4;
				startLocation += 4;
				randomCompCard = compDeck.get((int)Math.random()*cardLocation + startLocation );
				randomPlayerCard = playerDeck.get((int)Math.random()*cardLocation + startLocation );
				System.out.println("Comp plays tie " + randomCompCard);
				System.out.println("player plays tie " + randomPlayerCard);
			
			//1 = comp won war
			}else if (randomCompCard > randomPlayerCard && playerHasCards && compHasCards){
				//Dispute the cards from player's deck to computer's deck
				for (int i = 0; i < numCardLost; i++){
					distributeCards(compDeck, playerDeck);
				}
				System.out.println("Comp plays a " + randomCompCard);
				System.out.println("player plays a " + randomPlayerCard);
				tie = false;
				return winner = 1;

				//2 = player won war
			}else if (randomPlayerCard > randomCompCard && playerHasCards && compHasCards){
				//Dispute the cards from computer's deck to player's deck
				for (int i = 0; i < numCardLost; i++){
					distributeCards(playerDeck, compDeck);
				}
				System.out.println("Comp plays a " + randomCompCard);
				System.out.println("player plays a " + randomPlayerCard);
				tie = false;
				return winner = 2;

				//3 = comp won game due to player running out of cards
			}else if (playerDeck.size() == 0){
				tie = false;
				return winner = 3;

				//4 = player won game due to comp running out of cards
			}else if(compDeck.size() == 0){
				tie = false;
				return winner = 4;
			}
		}
		return winner;
		
	}
	
	/**
	 * This method display the total game played, and the numbers of wins from the 
	 * computer and the player.
	 * @param totalGames		total game played by the computer and the player
	 * @param playerWins		the total number of wins from the player.
	 */
	public static void displayStats(int totalGames, int playerWins) {
		//display the number of computer wins, player wins, total games
		//calculate and display the player's win percentage.
		//System.out.println("Total Game: " + totalGames + " " + playerWins);
		float winPercentage = ((float)playerWins / totalGames) * 100;
		//System.out.println(winPercentage);
		System.out.printf("Win Percentage: %.2f%%\n" , winPercentage);
	}
	
	/**
	 * The method displays the remaining cards the computer and the player have, it
	 * is the score of both players
	 * @param comp			cards left for computer
	 * @param player		cards left for the player
	 */
	public static void displayScore(ArrayList<Integer> comp, ArrayList<Integer> player) {
		//display the number of cards remaining in both players' decks.
		System.out.println("Cards left for Computer: " +comp.size());
		System.out.println("Cards left for player: " +player.size());
	
	}
}
