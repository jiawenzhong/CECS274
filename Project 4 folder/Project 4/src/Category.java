import java.io.*;
import java.util.*;

/**
 * The class create a category to put all the related questions
 * @author jiawen
 *
 */
public class Category {

	/**
	 * the name of the category
	 */
	private String category;
	/**
	 * the linkedlist that contain all the quesitons
	 */
	private LinkedList allQuestions = new LinkedList();

	/**
	 * The constructor for category class
	 * @param fileName
	 */
	public Category (String fileName) {
		try{
			category = fileName;
			//read from the file
			Scanner read = new Scanner(new File(category));
			//System.out.println("Okay");
			String question, answer1, answer2, answer3,answer4;
			//read the questions into the class
			while(read.hasNext()){
				int difficulty = read.nextInt();
				read.nextLine();
				question = read.nextLine();
				answer1 = read.nextLine();
				answer2 = read.nextLine();
				answer3 = read.nextLine();
				answer4 = read.nextLine();
				//System.out.println("done");
				int solution = read.nextInt();
				if(read.hasNext())
					read.nextLine();

				//add every new Question object into he linked list
				allQuestions.addNode(new Question(difficulty, question, answer1, answer2, answer3, answer4, solution));
			}
			read.close();
		}catch(FileNotFoundException n){
			System.out.println("File not found");
		}
		//sort the list according to the difficulty rating
		sortList(allQuestions);
		//displayQ();

	}

	/**
	 * return the entire question object
	 * @param i the index of the question
	 * @return the question 
	 */
	public Question getQuestion (int i){
		return allQuestions.get(i).getObject();
	}

	/**
	 * display the entire linked list of questions
	 */
	public void displayQ () {
		int number = 1;
		for (int i = 0; i < allQuestions.size(); i ++){
			System.out.println (number + ". " + allQuestions.get(i).getValue());
			number ++;
		}
	}

	/**
	 * set a new question from Category class
	 * @param q the new question
	 * @param i the index location of that question
	 */
	public void setQuestion(String q, int i) {
		allQuestions.get(i).setQ(q);
	}

	/**
	 * set new answers to the question from category class
	 * @param answer
	 * @param i
	 * @param solution
	 */
	public void setAnswer (String answer, int i, int solution) {
		String [] separate = answer.split(" ");
		allQuestions.get(i).setAns(separate [0], separate [1], separate [2], separate [3], solution);
	}
	/**
	 * get the allQestion linked list through category class
	 * @return
	 */
	public LinkedList getList () {
		return allQuestions;
	}

	/**
	 * get the size of the linked list through category class
	 * @return the size of the list
	 */
	public int getSize() {
		return allQuestions.size();
	}

	/**
	 * sort the list with selection sort
	 * @param list the first item in the linkedlist
	 */
	public void sortList (LinkedList list) {
		//sort by artist using linear serach
		for (int i = 0; i < list.size(); i++){
			int first = i;
			for (int j = i + 1; j < list.size(); j++){
				//compare the second element with the first, negative if the second should go before first
				if (list.get(j).getObject().compareTo(list.get(first).getRating()) < 0){
					//set first the the second element
					first = j;
				}
			}
			//set a temp for the swap element
			Question swap = list.get(i).getObject();
			//swap the i element with first
			list.get(i).setValue(list.get(first).getObject());
			//set the first with the i element (the temp variable)
			list.get(first).setValue(swap);
		}
	}

	/**
	 * remove the question from category class
	 * @param i the index of the quesiton
	 * @return the removed question
	 */
	public Node removeQ(int i) {
		return allQuestions.remove(i);
	}

	/**
	 * write the new list to the file
	 * @param fileName the file that need to be rewritten
	 */
	public void writeToFile( String fileName ) {
		try{
			PrintWriter writer = new PrintWriter(fileName);
			for (int i = 0; i < allQuestions.size(); i++){
				//write every line as original text back to the text file
				writer.print(allQuestions.get(i).getRating() + "\n");
				writer.print(allQuestions.get(i).getQ() + "\n");
				writer.print(allQuestions.get(i).getAnsw1() + "\n");
				writer.print(allQuestions.get(i).getAnsw2() + "\n");
				writer.print(allQuestions.get(i).getAnsw3() + "\n");
				writer.print(allQuestions.get(i).getAnsw4() + "\n");
				writer.print(allQuestions.get(i).getSol() + "\n");
			}
			writer.close();
		}catch (FileNotFoundException a){
			System.out.println("File was not found");
		}
	}

	/**
	 * get the string version of the object, list all the questions out
	 * @return the string version of that object
	 */
	public String toString () {
		return allQuestions.toString();
	}

	/**
	 * add a new question in the linked list through category
	 * @param q the new object that need to be added
	 */
	public void add(Question q) {
		//access from the linkedlist class
		Node first = allQuestions.get(0);

		boolean found = false;
		//search through the list to find the correct difficulty rating, and insert the question into the list
		for (int i = 0; i < allQuestions.size() && !found; i ++) {
			if (allQuestions.get(i).getObject().compareTo(q.getRating()) == 0) {
				allQuestions.add(q, i);
				found = true;
			}
		}

	}
}
