import java.io.*;
import java.util.*;

/**
 * This class display the functions that the user select from the menu
 * It display the grades of the 100 students in the file
 * @author jiawen Zhong 012750918
 * CECS 274
 *
 */
public class StudentGrades {

	public static void main(String[] args) {
		//arraylist of the grades of two classes
		ArrayList<Integer> allGrades = new ArrayList<Integer>();

		//read in the grades from the file
		try{
			Scanner numbers = new Scanner(new File ("MidtermGrades.txt"));
			do{
				allGrades.add(numbers.nextInt());
			}while (numbers.hasNext());
			numbers.close();
		}catch (FileNotFoundException n){
			System.out.println("File not found.");
		}

		//the sorted arraylist of the grades of two classes
		ArrayList<Integer> sortedGrades = new ArrayList<Integer>(Statistic.sort(allGrades));

		//arraylists of class1 and class 2
		ArrayList <Integer> class1 = new ArrayList <Integer>();
		ArrayList <Integer> class2 = new ArrayList <Integer>();

		//Split the allGrades arraylist into class1 and class2
		class1 = splitClass1(allGrades);
		//System.out.println("Class1: " + class1);
		class2 = splitClass2(allGrades);
		//System.out.println("class2: " + class2);

		//the sorted arraylists of class1 and class 2
		ArrayList <Integer> sortedClass1 = new ArrayList <Integer>(Statistic.sort(class1));
		ArrayList <Integer> sortedClass2 = new ArrayList <Integer>(Statistic.sort(class2));

		//System.out.println("square root: " + Statistic.sqrt(663, 663));

		int menuInput = 0;
		boolean done = false;
		while(!done) {
			displayMenu();
			menuInput = CheckInput.checkInt(1, 8);
			switch(menuInput){
			case 1: //Display the grades
				System.out.println("The grades for both classes:");
				print(allGrades);
				break;
			case 2: //Display the sorted grades
				System.out.println("The sorted grades for both classes:");
				print (sortedClass1, sortedClass2);
				break;
			case 3://Display average
				System.out.println("The average of class 1 is: " + Statistic.avgList(class1));
				System.out.println("The average of class 2 is: " + Statistic.avgList(class2));
				System.out.println("The average of both classes is: " + Statistic.avgList(allGrades));
				break;
			case 4: //Display median
				System.out.println("Median of Class 1: " + Statistic.medianList(sortedClass1));
				System.out.println("Median of Class 2: " + Statistic.medianList(sortedClass2));
				System.out.println("Median of both classes: " + Statistic.medianList(sortedGrades));
				break;
			case 5: //Display mode 
				System.out.println("Mode of Class 1: " + Statistic.mode(class1));
				System.out.println("Mode of Class 2: " + Statistic.mode(class2));
				System.out.println("Mode of both classes: " + Statistic.mode(allGrades));
				break;
			case 6://Display range
				System.out.println("Range for class 1: " + Statistic.range(sortedClass1));
				System.out.println("Range for class 1: " + Statistic.range(sortedClass2));
				System.out.println("Range for class 1: " + Statistic.range(sortedGrades));
				break;
			case 7: //Display standard deviation
				System.out.printf("Standard Deviation for Class 1: %.3f\n" , Statistic.stdDev(class1));
				System.out.printf("Standard Deviation for Class 2: %.3f\n" , Statistic.stdDev(class2));
				System.out.printf("Standard Deviation for both classes: %.3f" , Statistic.stdDev(allGrades));
				System.out.println("");
				break;
			case 8: //Quit the program
				done = true;
				System.out.println("Session end");
				break;
			default:System.out.println("Please enter a number according to the menu.");
			break;
			}
		}
	}

	/**
	 * Displays the menu
	 */
	public static void displayMenu() {
		System.out.println("");
		System.out.println("1. Display the grades");
		System.out.println("2. Display the sorted grades");
		System.out.println("3. Display average");
		System.out.println("4. Display median");
		System.out.println("5. Display mode");
		System.out.println("6. Display range");
		System.out.println("7. Display standard deviation");
		System.out.println("8. Quit");
	}

	/**
	 * Display the grades of both classes separately
	 * @param class1		grades from class one
	 * @param class2		grades from class two
	 * @param i				the initial index of the arraylist
	 */
	private static void displayGrades (ArrayList<Integer> class1, ArrayList<Integer> class2, int i){
		if (i < class1.size()){//print the words in two columns
			System.out.println("Class 1: " + class1.get(i) + " 		" + "Class 2: " + class2.get(i));
			displayGrades (class1, class2, i + 1);
		}
	}

	/**
	 * Display the grades of two classes
	 * @param combineGrades		the grades of 100 students
	 * @param i				the initial index of the arraylist
	 */
	private static void displayGrades (ArrayList<Integer> combineGrades,int i){
		if (i < combineGrades.size() / 2){
			System.out.print("Class1: " + combineGrades.get(i) + "          " + "Class 2: "+ combineGrades.get(i + (combineGrades.size() / 2)) + "\n");
			displayGrades (combineGrades, i + 1);
		}
	}

	/**
	 * facede function for displayGrades with one arraylist
	 * @param grades		the grade arraylist
	 */
	public static void print (ArrayList<Integer> grades){
		displayGrades (grades, 0);
	}

	/**
	 * facade function for displayGrades with two arraylists
	 * @param class1		grades for class 1
	 * @param class2		grades for class 2
	 */
	public static void print (ArrayList<Integer> class1, ArrayList<Integer> class2){
		displayGrades (class1, class2, 0);
	}
	/**
	 * This method split the list into two equal list between the class1 and the class2
	 * @param grades			the combine grades of two classes
	 * @return class2	 		class2's grade list
	 */
	public static ArrayList<Integer> splitClass2 (ArrayList<Integer> grades) {
		ArrayList<Integer> class2 = new ArrayList<Integer>();
		//remove from the end of the deck and place into the new deck.
		for (int i = grades.size() - 1 ; i >= grades.size() / 2; i --){
			class2.add(grades.get(i));
		}
		return class2;
	}
	/**
	 * This method split the list into two equal list between the class1 and the class2
	 * @param grades			the combine grades of two classes
	 * @return class2	 		class2's grade list
	 */
	public static ArrayList<Integer> splitClass1 (ArrayList<Integer> grades) {
		ArrayList<Integer> class1 = new ArrayList<Integer>();
		//remove from the end of the deck and place into the new deck.
		for (int i = 0 ; i < grades.size() / 2; i ++){
			class1.add(grades.get(i));
		}
		return class1;
	}
}
