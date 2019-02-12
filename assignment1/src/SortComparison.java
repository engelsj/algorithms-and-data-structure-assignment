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
		if (a == null)
			return null;
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
		if (a == null)
			return null;
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
	static double[] mergeSortIterative(double a[]) 
	{
		int low = 0;
        int high = a.length - 1;
 
        double[] temp = Arrays.copyOf(a, a.length);
 
        for (int m = 1; m <= high - low; m = 2*m)
        {

            for (int i = low; i < high; i += 2*m)
            {
                int from = i;
                int mid = i + m - 1;
                int to = Integer.min(i + 2 * m - 1, high);
 
                merge(a, temp, from, mid, to);
            }
        }
		return a;
	}

	static void merge(double a[], double[] temp, int from, int mid, int to) 
	{
		int k = from, i = from, j = mid + 1;
		 
        // loop till there are elements in the left and right runs
        while (i <= mid && j <= to) {
            if (a[i] < a[j]) {
                temp[k++] = a[i++];
            } else {
                temp[k++] = a[j++];
            }
        }
 
        // Copy remaining elements
        while (i <= mid && i < a.length) {
            temp[k++] = a[i++];
        }
 
        // Don't need to copy second half
 
        // copy back to the original array to reflect sorted order
        for (i = from; i <= to; i++) {
            a[i] = temp[i];
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
		if (a == null)
			return null;
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

	public static boolean isSorted(double a[]) {
		return isSorted(a, 0, a.length - 1);
	}

	private static boolean isSorted(double[] a, int lo, int hi) {
		for (int i = lo + 1; i <= hi; i++)
			if (a[i] < a[i - 1])
				return false;
		return true;
	}

	public static void main(String[] args) {

	}

}
