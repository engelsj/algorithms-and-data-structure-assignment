import java.util.Random;

// -------------------------------------------------------------------------

/**
 *  This class contains static methods that implementing sorting of an array of numbers
 *  using different sort algorithms.
 *
 *  @author
 *  @version HT 2019
 */

 class SortComparison {

    /**
     * Sorts an array of doubles using InsertionSort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order.
     *
     */
    static double [] insertionSort (double a[])
    {
    	int i = 1;
    	int j = 0;
    	double x = 0;
    	while(i < a.length)
    	{
    		x = a[i];
    		j = i - 1;
    		while(j >= 0 && a[j] > x)
    		{
    			a[j + 1] = a[j];
    			j = j - 1;
    		}
    		a[j + 1] = x;
    		i = i + 1;
    	}
    	return a;
    }

    /**
     * Sorts an array of doubles using Quick Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] quickSort (double a[])
    {
    

    }
    

    /**
     * Sorts an array of doubles using Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    /**
     * Sorts an array of doubles using iterative implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */

    static double[] mergeSortIterative (double a[]) 
    {
    	
	
    }
    
    
    
    /**
     * Sorts an array of doubles using recursive implementation of Merge Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     *
     * @param a: An unsorted array of doubles.
     * @return after the method returns, the array must be in ascending sorted order.
     */
    static double[] mergeSortRecursive (double a[]) 
    {
    	if(a == null)
			return null;
		
		if(a.length > 1)
		{
			int mid = a.length / 2;
			
			double[] left = new double[mid];
			for(int i = 0; i < mid; i++)
				left[i] = a[i];
			
			double[] right = new double[mid];
			for(int i = mid; i < a.length; i++)
				right[ - mid] = a[i];
			
			mergeSortRecursive(left);
			mergeSortRecursive(right);
			
			int i = 0;
			int j = 0;
			int k = 0;
			
			while(i < left.length && j < right.length)
			{
				if(left[i] < right[j])
				{
					a[k] = left[i];
					i++;
				}
				else
				{
					a[k] = right[j];
					j++;
				}
			}
			
			while(i < left.length)
			{
				a[k] = left[i];
				i++;
				k++;
			}
			while(j < right.length)
			{
				a[k] = right[j];
				j++;
				k++;
			}
		}
		return a;

    	
	}
    	
    
    /**
     * Sorts an array of doubles using Selection Sort.
     * This method is static, thus it can be called as SortComparison.sort(a)
     * @param a: An unsorted array of doubles.
     * @return array sorted in ascending order
     *
     */
    static double [] selectionSort (double a[])
    {
    	double temp; // temp variable for swapping the value
    	for(int i = 0; i < a.length - 1; i++) // iterate over the array
    	{
    		int minIndex= i; // find the smallest element in the array
    		for(int j = i + 1; j < a.length; j++)
    			if(a[j] < a[minIndex])
    				minIndex = j;
    		temp = a[minIndex]; // swap the smallest element with the first element 
    		a[minIndex] = a[i];
    		a[i] = temp;
    	}
        return a;
    }

   


    public static void main(String[] args) {

        //todo: do experiments as per assignment instructions
    }

 }//end class

