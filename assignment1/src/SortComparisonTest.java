import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

//-------------------------------------------------------------------------
/**
 *  Test class for SortComparison.java
 *
 *  @author
 *  @version HT 2019
 */
@RunWith(JUnit4.class)
public class SortComparisonTest
{
    //~ Constructor ........................................................
    @Test
    public void testConstructor()
    {
        new SortComparison();
    }

    //~ Public Methods ........................................................

    // ----------------------------------------------------------
    /**
     * Check that the methods work for empty arrays
     */
    @Test
    public void testEmpty()
    {
    	// testing each method with a null array
    	assertEquals("Testing Empty Array insertionSort", null, SortComparison.insertionSort(null));
    	assertEquals("Testing Empty Array quickSort", null, SortComparison.quickSort(null));
    	assertEquals("Testing Empty Array mergeSortIterative", null, SortComparison.mergeSortIterative(null));
    	assertEquals("Testing Empty Array mergeSortRecursive", null, SortComparison.mergeSortRecursive(null));
    	assertEquals("Testing Empty Array selectionSort", null, SortComparison.selectionSort(null));
    }
    
    @Test
    public void insertionSort()
    {
    	// testing with single element array
    	assertEquals("Testing single element Array insertionSort", true, SortComparison.isSorted(SortComparison.insertionSort(new double[] {1.0})));
    	// testing with sorted array 
    	assertEquals("Testing sorted element Array insertionSort", true, SortComparison.isSorted(SortComparison.insertionSort(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0})));
    	// testing with reverse array 
    	assertEquals("Testing reverse element Array insertionSort", true, SortComparison.isSorted(SortComparison.insertionSort(new double[] {6.0, 5.0, 4.0, 3.0, 2.0, 1.0})));
    	// testing with random array 
    	assertEquals("Testing random element Array insertionSort", true, SortComparison.isSorted(SortComparison.insertionSort(new double[] {6.0, 1.0, 3.0, 4.0, 5.0, 2.0})));
    	// testing with repeated array 
    	assertEquals("Testing repeated element Array insertionSort", true, SortComparison.isSorted(SortComparison.insertionSort(new double[] {6.0, 6.0, 6.0, 6.0, 6.0, 6.0})));
    
    }
    
    @Test
    public void quickSort()
    {
    	// testing with single element array
    	assertEquals("Testing single element Array quickSort", true, SortComparison.isSorted(SortComparison.quickSort(new double[] {1.0})));
    	// testing with sorted array 
    	assertEquals("Testing sorted element Array quickSort", true, SortComparison.isSorted(SortComparison.quickSort(new double[] {1.0, 2.0, 3.0, 4.0, 5.0, 6.0})));
    	// testing with reverse array 
    	assertEquals("Testing reverse element Array quickSort", true, SortComparison.isSorted(SortComparison.quickSort(new double[] {6.0, 5.0, 4.0, 3.0, 2.0, 1.0})));
    	// testing with random array 
    	assertEquals("Testing random element Array quickSort", true, SortComparison.isSorted(SortComparison.quickSort(new double[] {6.0, 1.0, 3.0, 4.0, 5.0, 2.0})));
    	// testing with repeated array 
    	assertEquals("Testing repeated element Array quickSort", true, SortComparison.isSorted(SortComparison.quickSort(new double[] {6.0, 6.0, 6.0, 6.0, 6.0, 6.0})));
    
    }
    
    


    // TODO: add more tests here. Each line of code and each decision in Collinear.java should
    // be executed at least once from at least one test.

    // ----------------------------------------------------------
    /**
     *  Main Method.
     *  Use this main method to create the experiments needed to answer the experimental performance questions of this assignment.
     *
     */
    public static void main(String[] args)
    {
    	double[] numbers10 = new double[10];
    	SortComparison.fillArray(numbers10, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers10.txt");
		System.out.println("Testing numbers10 array");
		SortComparison.runSortTests(numbers10);
		
		double[] numbers100 = new double[100];
		SortComparison.fillArray(numbers100, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers100.txt");
		System.out.println("Testing numbers100 array");
		SortComparison.runSortTests(numbers100);

		
		double[] numbers1000 = new double[1000];
		SortComparison.fillArray(numbers1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers1000.txt");
		System.out.println("Testing numbers1000 array");
		SortComparison.runSortTests(numbers1000);

		
		double[] duplicate1000 = new double[1000];
		SortComparison.fillArray(duplicate1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbers1000Duplicates.txt");
		System.out.println("Testing duplicate1000 array");
		SortComparison.runSortTests(duplicate1000);

		
		double[] ordered1000 = new double[1000];
		SortComparison.fillArray(ordered1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbersNearlyOrdered1000.txt");
		System.out.println("Testing ordered1000 array");
		SortComparison.runSortTests(ordered1000);
		
		double[] reverse1000 = new double[1000];
		SortComparison.fillArray(reverse1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbersReverse1000.txt");
		System.out.println("Testing reverse1000 array");
		SortComparison.runSortTests(reverse1000);
		
		double[] sorted1000 = new double[1000];
		SortComparison.fillArray(sorted1000, "/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment1/bin/numbersSorted1000.txt");
		System.out.println("Testing sorted1000 array");
		SortComparison.runSortTests(sorted1000);    }

}

