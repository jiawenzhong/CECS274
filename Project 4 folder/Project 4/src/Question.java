/**
 * Stores data about a question
 * @author jiawen
 *
 */
public class Question {

	/**
	 * the question's difficulty rating
	 */
	private int diffRating;
	/**
	 * the question
	 */
	private String question;
	/**
	 * possible answer 1
	 */
	private String ans1;
	/**
	 * possible answer 2
	 */
	private String ans2;
	/**
	 * possible answer 3
	 */
	private String ans3;
	/**
	 * possible answer 4
	 */
	private String ans4;
	/**
	 * the solution to the question
	 */
	private int solution;

	/**
	 * constructor of question class
	 * @param difRate the difficulty rating of the question
	 * @param q the question
	 * @param an1 answer 1
	 * @param an2 answer 2
	 * @param an3 answer 3
	 * @param an4 answer 4
	 * @param solu the solution of the problem
	 */
	public Question (int difRate, String q, String an1, String an2, String an3, String an4, int solu) {
		diffRating = difRate;
		question = q;
		ans1 = an1;
		ans2 = an2;
		ans3 = an3;
		ans4 = an4;
		solution = solu;
	}

	/**
	 * display the quesiton in string form
	 */
	public String toString () {
		String s = question + "\n" + "1. " + ans1 + "\n" + "2. " + ans2 
				+ "\n" + "3. " + ans3 + "\n" + "4. " + ans4 + "\n";
		return s;
	}

	/**
	 * get the difficult rating from Question
	 * @return the a rating between 1 - 4
	 */
	public int getRating () {
		return diffRating;
	}

	/**
	 * get the solution # in Question
	 * @return the solution #
	 */
	public int getSolution () {
		return solution;
	}

	/**
	 * get the question in string form
	 * @return the question with all answers
	 */
	public String getQuestion () {
		return question;
	}

	/**
	 * get answer 1
	 * @return answer 1
	 */
	public String getAns1 () {
		return ans1;
	}

	/**
	 * get answer 2
	 * @return answer2
	 */
	public String getAns2 () {
		return ans2;
	}

	/**
	 * get answer 3
	 * @return answer 3
	 */
	public String getAns3 () {
		return ans3;
	}

	/**
	 * get answer 4
	 * @return answer4
	 */
	public String getAns4 () {
		return ans4;
	}

	/**
	 * user defined compareTo function in Question class 
	 * compare the difficulty rating between two Question Object
	 * @param m the explicit parameter of second QUesiton object
	 * @return 0 if equal, positive if implicit is greater, negative if implicit is smaller
	 */
	public int compareTo (int m) {
		if (this.diffRating > m){
			return 1;
		}else if (this.diffRating < m) {
			return -1;
		}else {
			return 0;
		}
	}

	/**
	 * set the question
	 * @param q a new string that need to be set
	 */
	public void setQuestion (String q) {
		question = q;
	}

	/**
	 * set all the answers and the solution
	 * @param an1 new answer 1
	 * @param an2 new answer 2
	 * @param an3 new answer 3
	 * @param an4 new answer 4
	 * @param s   new solution to the new answer set
	 */
	public void setAnswers (String an1, String an2, String an3, String an4) {
		ans1 = an1;
		ans2 = an2;
		ans3 = an3;
		ans4 = an4;
		//solution = s;
	}

	/**
	 * set the solution to the quesiton
	 * @param s the new solution
	 */
	public void setSolution (int s) {
		solution = s;
	}
}
