import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

// -------------------------------------------------------------------------

/**
 * This class contains static methods that implementing sorting of an array of
 * numbers using different sort algorithms.
 *
 * @author
 * @version HT 2019
 */

class SortComparison {

	/**
	 * Sorts an array of doubles using InsertionSort. This method is static,
	 * thus it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order.
	 *
	 */
	static double[] insertionSort(double a[]) {
		int i = 1;
		int j = 0;
		double x = 0;
		while (i < a.length) {
			x = a[i];
			j = i - 1;
			while (j >= 0 && a[j] > x) {
				a[j + 1] = a[j];
				j = j - 1;
			}
			a[j + 1] = x;
			i = i + 1;
		}
		return a;
	}

	/**
	 * Sorts an array of doubles using Quick Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] quickSort(double a[]) {
		quickSortRecursive(a, 0, a.length - 1);
		return a;

	}

	public static void quickSortRecursive(double a[], int low, int high) {
		if (high <= low)
			return;

		if (a[high] < a[low])
			exch(a, low, high);

		int lt = low + 1, gt = high - 1;
		int i = low + 1;
		while (i <= gt) {
			if (a[i] < a[low])
				exch(a, lt++, i++);
			else if (a[high] < a[i])
				exch(a, i, gt--);
			else
				i++;
		}

		exch(a, low, --lt);
		exch(a, high, ++gt);

		quickSortRecursive(a, low, lt - 1);
		if (a[lt] < a[gt])
			quickSortRecursive(a, lt + 1, gt - 1);
		quickSortRecursive(a, gt + 1, high);

	}

	public static void exch(double a[], int i, int j) {
		double swap = a[i];
		a[i] = a[j];
		a[j] = swap;
	}

	/**
	 * Sorts an array of doubles using Merge Sort. This method is static, thus
	 * it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	/**
	 * Sorts an array of doubles using iterative implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */

	// reference taken from https://www.geeksforgeeks.org/iterative-merge-sort/
	static double[] mergeSortIterative(double a[]) {
		int curr_size;

		int left_start;

		for (curr_size = 1; curr_size <= a.length - 1; curr_size = 2 * curr_size)
			for (left_start = 0; left_start < a.length - 1; left_start += 2 * curr_size) {
				int mid = left_start + curr_size - 1;
				int right_end = Math.min(left_start + 2 * curr_size - 1, a.length - 1);
				merge(a, left_start, mid, right_end);
			}
		return a;
	}

	static void merge(double a[], int left, int mid, int right) {
		int i, j, k;
		int n1 = mid - left + 1;
		int n2 = right - mid;
		
		System.out.println("mid " + mid );
		System.out.println("right " + right);
		System.out.println();
		
		double L[] = new double[n1];
		double R[] = new double[n2];

		for (i = 0; i < n1; i++)
			L[i] = a[left + i];
		
		for (j = 0; j < n2; j++)
			R[j] = a[mid + 1 + j];

		i = 0;
		j = 0;
		k = 1;

		while (i < n1 && j < n2) {
			if (L[i] <= R[j]) {
				a[k] = L[i];
				i++;
			} else {
				a[k] = R[j];
				j++;
			}
			k++;
		}

		while (i < n1) {
			a[k] = L[i];
			i++;
			k++;
		}

		while (j < n2) {
			a[k] = R[j];
			j++;
			k++;
		}
	}

	/**
	 * Sorts an array of doubles using recursive implementation of Merge Sort.
	 * This method is static, thus it can be called as SortComparison.sort(a)
	 *
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return after the method returns, the array must be in ascending sorted
	 *         order.
	 */
	// reference taken from https://www.geeksforgeeks.org/iterative-merge-sort/
	static double[] mergeSortRecursive(double a[]) {
		if (a == null)
			return null;

		if (a.length > 1) {
			int mid = a.length / 2;

			double[] left = new double[mid];
			for (int i = 0; i < mid; i++)
				left[i] = a[i];

			double[] right = new double[a.length - mid];
			for (int i = mid; i < a.length; i++)
				right[i - mid] = a[i];

			mergeSortRecursive(left);
			mergeSortRecursive(right);

			int i = 0;
			int j = 0;
			int k = 0;

			while (i < left.length && j < right.length) {
				if (left[i] < right[j]) {
					a[k] = left[i];
					i++;
				} else {
					a[k] = right[j];
					j++;
				}
				k++;
			}

			while (i < left.length) {
				a[k] = left[i];
				i++;
				k++;
			}
			while (j < right.length) {
				a[k] = right[j];
				j++;
				k++;
			}
		}
		return a;

	}

	/**
	 * Sorts an array of doubles using Selection Sort. This method is static,
	 * thus it can be called as SortComparison.sort(a)
	 * 
	 * @param a:
	 *            An unsorted array of doubles.
	 * @return array sorted in ascending order
	 *
	 */
	static double[] selectionSort(double a[]) {
		double temp; // temp variable for swapping the value
		for (int i = 0; i < a.length - 1; i++) // iterate over the array
		{
			int minIndex = i; // find the smallest element in the array
			for (int j = i + 1; j < a.length; j++)
				if (a[j] < a[minIndex])
					minIndex = j;
			temp = a[minIndex]; // swap the smallest element with the first
								// element
			a[minIndex] = a[i];
			a[i] = temp;
		}
		return a;
	}

	private static boolean isSorted(double a[]) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(double[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (a[i] < a[i - 1])
				return false;
		return true;
	}

	public static void main(String[] args) {

		double[] numbers10 = new double[10];
		fillArray(numbers10, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers10.txt");
		System.out.println("Testing numbers10 array");
		runSortTests(numbers10);
		
		double[] numbers100 = new double[100];
		fillArray(numbers100, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers100.txt");
		System.out.println("Testing numbers100 array");
		runSortTests(numbers100);

		
		double[] numbers1000 = new double[1000];
		fillArray(numbers1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers1000.txt");
		System.out.println("Testing numbers1000 array");
		runSortTests(numbers1000);

		
		double[] duplicate1000 = new double[1000];
		fillArray(duplicate1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers1000Duplicates.txt");
		System.out.println("Testing duplicate1000 array");
		runSortTests(duplicate1000);

		
		double[] ordered1000 = new double[1000];
		fillArray(ordered1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbersNearlyOrdered1000.txt");
		System.out.println("Testing ordered1000 array");
		runSortTests(ordered1000);
		
		double[] reverse1000 = new double[1000];
		fillArray(reverse1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbersReverse1000.txt");
		System.out.println("Testing reverse1000 array");
		runSortTests(reverse1000);
		
		double[] sorted1000 = new double[1000];
		fillArray(sorted1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbersSorted1000.txt");
		System.out.println("Testing sorted1000 array");
		runSortTests(sorted1000);
		
	}
	
	public static void fillArray(double[] a, String fileName)

	{
		try
		{
			File fileInput = new File(fileName);
			Scanner scanner = new Scanner(fileInput);
			int indexCounter = 0;
			while(scanner.hasNextLine())
			{
				a[indexCounter++] = scanner.nextDouble();
			}
			scanner.close();
		}
		catch(FileNotFoundException e)
		{
			System.out.println(e);
		}
	}
	
	
	public static void runSortTests(double[] a)
	{
		runTest(a, "insertionSort");
		runTest(a, "quickSort");
		runTest(a, "mergeSortRecursive");
		//runTest(numbers10, "mergeSortIterative");
		runTest(a, "selectionSort");
	}
	public static void runTest(double[] a, String sortType)
	{
		double[] testArray = Arrays.copyOf(a, a.length);
		long start = -1;    
		long elapsedTime = -1;
		if(sortType.equals("insertionSort"))
		{
			start = System.nanoTime();  
			insertionSort(testArray);
			elapsedTime = System.nanoTime() - start;
		}
		if(sortType.equals("quickSort"))
		{
			start = System.nanoTime();  
			quickSort(testArray);
			elapsedTime = System.nanoTime() - start;
		}
		if(sortType.equals("mergeSortRecursive"))
		{
			start = System.nanoTime();  
			mergeSortRecursive(testArray);
			elapsedTime = System.nanoTime() - start;
		}
		if(sortType.equals("mergeSortIterative"))
		{
			start = System.nanoTime();  
			mergeSortIterative(testArray);
			elapsedTime = System.nanoTime()- start;
		}
		if(sortType.equals("selectionSort"))
		{
			start = System.nanoTime();  
			selectionSort(testArray);
			elapsedTime = System.nanoTime() - start;
		}
		System.out.println("Elapsedtime for " + sortType + " " + elapsedTime);
	}
}// end class
