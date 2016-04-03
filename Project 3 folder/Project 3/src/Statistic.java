/**
 * the statistic class calculate the sum, the mode, the std dev, range, average, and 
 * sort the grade arraylist
 * @author jiawen
 *
 */
import java.util.*;
public class Statistic {

	/**
	 * The sum method calculate the sum of all the grades 
	 * @param grades		the arraylist that contains all the grades
	 * @param i				the initial index of the arraylist
	 * @return				the sum of all the grades
	 */
	private static int sumList (ArrayList<Integer> grades, int i){
		if (i < grades.size()){
			return grades.get(i) + sumList (grades, i + 1);
		}
		return 0;
	}

	/**
	 * Finds the average of the sum (facade function)
	 * @param grades		the arraylist that contains all the grades
	 * @return				the average of the grades
	 */
	public static double avgList (ArrayList<Integer> grades){
		double avg = sumList(grades, 0);
		return avg / grades.size();
	}

	/**
	 * Find the median of the list
	 * @param grades		the grade arraylist
	 * @return				the median of the list
	 */
	public static double medianList (ArrayList<Integer> grades){
		//System.out.println(grades);
		int middle = grades.size()/2;
		if (grades.size()%2 == 1) {
			return grades.get(middle);
		} else {
			return (grades.get(middle - 1) + grades.get(middle)) / 2.0;
		}
	}

	/**
	 * Find the modes in the grade arraylist
	 * @param mode			all the repeated grades
	 * @param counts		the array that contains the occurrences of all the grade	
	 * @param maxCount		the maximum in the arrry counts
	 * @param index			the initial index
	 * @return				the arraylist with modes
	 */
	private static ArrayList<Integer> modeList (ArrayList<Integer> mode, int [] counts, int maxCount, int index){
		//put the mode into an arraylist
		if (index < counts.length){
			if (counts[index] == maxCount) {
				mode.add(index);
			}
			return modeList (mode, counts, maxCount, index + 1);
		}	
		return mode;
	}

	/**
	 * count the number of times a grade shows up [mode(1)]
	 * @param grades		the grade arraylist
	 * @param counts		occurrence of each grade	
	 * @param index			the index that the method start with
	 */
	private static void setCount (ArrayList<Integer> grades, int [] counts, int index){
		//System.out.println("counts array before: " + Arrays.toString(counts));
		if (index < grades.size()){
			int value = grades.get(index);
			counts[value] += 1;
			//call itself
			setCount (grades, counts, index + 1);
		}
	}

	/**
	 * find the max value in counts array [mode(2)]
	 * @param counts		the array that contains the occurrence of the grades
	 * @param index			the index the method start with
	 * @param maxCount		the maximum in the array counts
	 * @return				the maxCount
	 */
	private static int findMaxCount (int [] counts, int index, int maxCount){
		//find the max value
		if (index < counts.length){
			//System.out.println("index before = " + index);
			if (counts[index] > maxCount){
				maxCount = counts[index];
				//System.out.println("index after = " + index);
			}
			return findMaxCount (counts, index + 1, maxCount);	
		}
		return maxCount;
	}

	/**
	 * Facade funtion of modeList, find the mode
	 * @param grades		grade arraylist
	 * @return				the list with all the modes
	 */
	public static ArrayList<Integer> mode (ArrayList<Integer> grades){
		int[] counts = new int[101];
		ArrayList<Integer> mode = new ArrayList<Integer>();
		//get the count array
		setCount (grades, counts, 0);
		//get the maximum in the array counts
		return modeList (mode, counts, Statistic.findMaxCount(counts, 0, counts[0]), 0);
	}

	/**
	 * Find the range of the list
	 * @param grades		the grade list
	 * @return				the range of the list
	 */
	public static int range (ArrayList<Integer> grades){
		int low = grades.get(0);
		int high = grades.get(grades.size() - 1);
		return high - low;
	}

	/**
	 * Calculate the square root of a number with Newton's method
	 * @param variance			the number that need to find the square root of
	 * @param approx			the approximate number of the square root
	 * @return					the square root of the number (the standard deviation)
	 */
	private static double sqrt (double variance, double approx){
		double c = variance;
		double y = approx; // the estimate value
		double epsilon = 1e-5;    // relative error tolerance
		if (Math.abs(y * y - c) <= epsilon) {//if the approx is less than the error, return the approx
			return y;
		} else {
			y = (y * y + c) / (2 * y);
			return sqrt(c, y);//call it self
		}
	}

	/**
	 * Find the standard deviation of the list
	 * @param grades		the grade arraylist
	 * @return				the variance that need to be passed into sqrt method
	 */
	private static double stdDevList (ArrayList<Integer> grades, int i){
		double variance = 0;
		if (i < grades.size()){
			//call itself
			variance = ((double)Math.pow(grades.get(i) - avgList(grades), 2) 
					+ stdDevList(grades, i + 1));
		}
		return variance; 
	}

	/**
	 * Facade function for standard deviation, find the standard deviation
	 * @param grades		the grade arraylist
	 * @return				the standard devation
	 */
	public static double stdDev (ArrayList<Integer> grades){
		int result =  (int)stdDevList(grades, 0);
		result = (int)stdDevList(grades, 0) / grades.size();
		return sqrt (result, result);
	}

	/**
	 * Partition method of quick sort
	 * @param grades	the list of grades
	 * @param left		the first index	of the arraylsit
	 * @param right		the last index of the arraylist
	 * @return
	 */
	private static int partition (ArrayList<Integer> grades, int left, int right){
		int pivot = grades.get(left);
		while (left <= right){
			while (grades.get(left) < pivot)
				left ++;
			while (grades.get(right) > pivot)
				right --;
			if (left <= right){
				int temp = grades.get(right);
				grades.set(right, grades.get(left));
				grades.set(left, temp);
				left ++;
				right --;
			}
		}
		return left;
	}

	/**
	 * Sort the grades from low to high using quicksort
	 * @param grades	the grade arraylist
	 * @param start		where to begin the sort
	 * @param end		where to end the sort
	 * @return			the sorted grade list
	 */
	private static void quickSort (ArrayList<Integer> grades, int start, int end){
		if (start < end){
			int pivot = partition(grades, start, end);
			if (start < pivot - 1){
				quickSort (grades, start, pivot - 1);
			}
			if (end > pivot){
				//call itself
				quickSort (grades, pivot, end);
			}
		}
	}

	/**
	 * facade funtion for quicksort, sort the list
	 * @param grades	the grade arraylist
	 */
	public static ArrayList<Integer> sort (ArrayList<Integer> grades){
		ArrayList<Integer> copy  = new ArrayList<Integer>(grades);
		quickSort (copy, 0, grades.size()-1);
		return copy;
	}
}
