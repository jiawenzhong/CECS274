import java.util.*;

/**
 * The program allows users to pick a category and begin a quiz. The user
 * can select problems by difficulty rating.
 *  @author jiawen zhong
 *  CECS 274
 *  Project 4
 */
public class TriviaGame {

	public static void main(String[] args) {
		ArrayList <Category> categoryList = new ArrayList <Category> ();
		categoryList.add(new Category("StarWars.txt"));
		categoryList.add(new Category("HarryPotter.txt"));
		categoryList.add(new Category("AdventureTime.txt"));

		int menuInput = 0;
		boolean done = false;
		while(!done) {
			displayMenu();
			menuInput = CheckInput.checkInt(1, 2);
			switch(menuInput){
			case 1: //choose category
				categoryMenu();
				//select menu for category
				boolean finish = false;
				while(!finish) {
					menuInput = CheckInput.checkInt(1, 4);
					switch(menuInput){
					case 1: //star wars category
						optionMenu("StarWars.txt", categoryList.get(0));
						finish = true;
						done = true;
						break;

					case 2: //adventure time category
						optionMenu("HarryPotter.txt", categoryList.get(1));
						finish = true;
						done = true;
						break;

					case 3: //harry potter category
						optionMenu("AdventureTime.txt", categoryList.get(2));
						finish = true;
						done = true;
						break;

					case 4: //Quit the program
						finish = true;//inside while
						done = true;//outside while
						System.out.println("Game end");
						break;
					default:System.out.println("Please enter a number according to the menu.");
					break;
					}
				}
				break;
			case 2: //Quit the program
				done = true;
				System.out.println("Game end");
				break;
			default:System.out.println("Please enter a number according to the menu.");
			break;
			}
		}
	}

	/**
	 * options after selecting a category
	 * @param fileName	the file under the category that the user select
	 */
	public static void optionMenu (String fileName, Category category) {
		Scanner read = new Scanner (System.in);
		int menuInput = 0;
		int score = 0;
		boolean done = false;
		while(!done) {
			displaySubMenu();
			menuInput = CheckInput.checkInt(1, 6);
			switch(menuInput){
			case 1: //take quiz
				//create a copy list so when the Node is removed, it wont affect the original 
				Category copy1 = new Category (fileName);
				System.out.println("Please choose the correct answer.");

				//print out the question one at a time
				for (int i = 0; i < 10; i ++){
					//random question picking from the linked list
					int randomQ = (int)(Math.random() * copy1.getSize());
					//print out the question
					Node remove = copy1.removeQ(randomQ);
					System.out.println(i + 1 + ". " + remove.getObject());
					int solution = CheckInput.checkInt(1, 4);
					//check if the answer selected by the user is correct
					if (solution == remove.getSol()){
						//if it is correct, score plus 1
						score += 1;
					}
				}

				System.out.println("Your score: " + score + "\n");
				System.out.println("Do you want to take another quiz? \n" + "If not press 2 to quit.");
				System.out.println("Press 1 to continue");
				//System.out.println(category.getList());
				int again = CheckInput.checkInt(1, 2);
				if (again == 2){
					done = true;
					category.writeToFile(fileName);
					System.out.println("Game End");
				} 
				break;

			case 2: //take difficulty quiz
				System.out.println("Please enter a difficulty rating. (1-5)");
				int rating = CheckInput.checkInt(1, 5);
				//create a copy list so when the Node is removed, it wont affect the original 
				//copy2 contains all the questions that match the rating that the user enters
				LinkedList copy2 = new LinkedList ();

				for (int i = 0; i < category.getSize(); i ++) {
					if (rating == category.getList().get(i).getRating() ) {
						//add a question object into the list
						copy2.addNode(category.getQuestion(i));
					}
				}
				//System.out.println("the copy2 list: " + copy2);
				if (copy2.isEmpty()) {
					System.out.println("No question with difficulty rating " + rating);
					System.out.println("Please enter any character to go back to the menu.");
					//purpose of goBack is to let user to press a key before goin back to the menu
					String goBack = read.next();
				} else {
					//print out 10 of the random questions from the copy2 list
					score = 0;
					for (int i = 0; i <= copy2.size() && i < 10; i ++){
						//random question picking from the linked list
						int randomQ = (int)(Math.random() * copy2.size());
						//print out the question
						Node remove = copy2.remove(randomQ);
						System.out.println(i + 1 + ". " + remove.getObject());
						int solution = CheckInput.checkInt(1, 4);
						//check if the answer selected by the user is correct
						if (solution == remove.getSol()){
							//if it is correct, score plus 1
							score += 1;
						}
					}

					//show scores
					System.out.println("Your score: " + score + "\n");
					System.out.println("Do you want to take another quiz? \n" + "If not press 2 to quit.");
					System.out.println("Press 1 to continue");
					//System.out.println(category.getList());
					int again2 = CheckInput.checkInt(1, 2);
					if (again2 == 2){
						//found = true;
						done = true;
						category.writeToFile(fileName);
						System.out.println("Game End");
					} 
				}

				break;

			case 3: //add new question
				System.out.println("Please enter a question.");
				String question = read.nextLine();
				System.out.println("Please enter the first answer.");
				String ans1 = read.nextLine();
				System.out.println("Please enter the second answer.");
				String ans2 = read.nextLine();
				System.out.println("Please enter the third answer.");
				String ans3 = read.nextLine();
				System.out.println("Please enter the fourth answer.");
				String ans4 = read.nextLine();
				System.out.println("Please enter the difficulty of the question (1-4).");
				//check for valid input
				int newRating = CheckInput.checkInt(1, 4);
				System.out.println("Please give the correct answer (1-4).");
				int solution = CheckInput.checkInt(1, 4);
				category.add(new Question(newRating, question, ans1, ans2, ans3, ans4, solution));
				category.displayQ();
				break;

			case 4: //remove question
				category.displayQ();
				System.out.println("Select a question that you want to remove.");
				int remove = CheckInput.checkInt(1, category.getSize());
				System.out.println("The question you have removed: \n" + category.removeQ(remove - 1).getValue());
				break;

			case 5: //modify question: question or problem
				category.displayQ();
				System.out.println("Select a question that you want to modify.");
				int number = CheckInput.checkInt(1, category.getSize());
				System.out.println("Which part do you want to modify?");
				System.out.println("1. question");
				System.out.println("2. answers");
				int part = CheckInput.checkInt(1, 2);
				//if the user input 1, modify the question, otherwise, modify the answers
				if (part == 1) {
					System.out.println("Please enter a modified version of the question.");
					String modify = read.nextLine();
					category.setQuestion(modify, number - 1);
				} else {
					try {
						System.out.println("Please enter a new set of answers with a space after each input.");
						String newAnswers = read.nextLine();
						//split the entire string into pieces
						//String [] separate = newAnswers.split(" ");
						System.out.println("Which one is the correct answer to the question (1 - 4) ?");
						int correctAns = CheckInput.checkInt(1, 4);
						category.setAnswer(newAnswers, number, correctAns);
						//category.getList().get(number - 1).setAns(separate [0], separate [1], separate [2], separate [3], correctAns);
					} catch (ArrayIndexOutOfBoundsException n) {
						System.out.println("Please enter the correct set of answers.");
					}
				}
				//category.displayQ();
				break;

			case 6: //Quit the program
				category.writeToFile(fileName);
				System.out.println("Game end");
				done = true;
				break;
			default:System.out.println("Please enter a number according to the menu.");
			break;
			}
		}
	}

	/**
	 * Displays the sub menu
	 */
	public static void displaySubMenu() {
		System.out.println("");
		System.out.println("1. Take a quiz.");
		System.out.println("2. Take a diffcult one.");
		System.out.println("3. Add a new question.");
		System.out.println("4. Remove a question");
		System.out.println("5. Modify a question.");
		System.out.println("6. Quit");
	}

	/**
	 * display category menu
	 */
	public static void categoryMenu () {
		System.out.println("1. Star Wars");
		System.out.println("2. Harry Potter");
		System.out.println("3. Adventure Time");
		System.out.println("4. Quit");
	}

	/**
	 * display the main menu
	 */
	public static void displayMenu () {
		System.out.println("1. Choose a category");
		System.out.println("2. Quit");
	}
}
