/**
 * Node class holds a question object and a reference to the next node
 * @author jiawen
 *
 */
public class Node {
	/**
	 * question object variable
	 */
	private Question question;
	/**
	 * reference to the next node
	 */
	private Node next;

	/**
	 * constructor with only one element
	 */
	public Node (Question q) {
		question = q;
		next = null;
	}

	/**
	 * constructor with one and the next element
	 */
	public Node (Question q, Node n) {
		question = q;
		next = n;
	}

	/**
	 * accessor for the next value
	 * @return the next Node
	 */
	public Node getNext () {
		return next;
	}

	/**
	 * mutator - set the next question object
	 */
	public void setNext (Node n) {
		next = n;
	}

	/**
	 * accessor for the question object
	 * @return the values in question
	 */
	public String getValue () {
		return question.toString();
	}

	/**
	 * mutator - set the value of a question
	 */
	public void setValue (Question q) {
		question = q;
	}

	/**
	 * Get the question object from Node
	 * @return
	 */
	public Question getObject () {
		return question;
	}

	/**
	 * get the difficulty rating from Question object
	 * @return the rating
	 */
	public int getRating () {
		return question.getRating();
	}

	/**
	 * get the string version of Question object from object
	 * @return
	 */
	public String getQ () {
		return question.getQuestion();
	}

	/**
	 * get the string version of answer 1 from object
	 * @return answer1
	 */
	public String getAnsw1 () {
		return question.getAns1();
	}

	/**
	 * get the string version of answer 2 from object
	 * @return answer 2
	 */
	public String getAnsw2 () {
		return question.getAns2();
	}

	/**
	 * get the string version of answer 3 from object
	 * @return answer 3
	 */
	public String getAnsw3 () {
		return question.getAns3();
	}

	/**
	 * get the string version of answer 4 from object
	 * @return answer 4
	 */
	public String getAnsw4 () {
		return question.getAns4();
	}

	/**
	 * get the solution number from object
	 * @return the solution
	 */
	public int getSol () {
		return question.getSolution();
	}

	/**
	 * modify the question from object
	 * @param q a new modified version of the question
	 */
	public void setQ (String q) {
		question.setQuestion(q);
	}

	/**
	 * modify the answer from object
	 * @param an1 new answer1
	 * @param an2 new answer2
	 * @param an3 new answer3
	 * @param an4 new answer4
	 * @param s the new solution
	 */
	public void setAns (String an1, String an2, String an3, String an4, int s) {
		question.setAnswers(an1, an2, an3, an4);
		question.setSolution(s);
	}


}
