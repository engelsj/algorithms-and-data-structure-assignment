import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

/*
 * A Contest to Meet (ACM) is a reality TV contest that sets three contestants at three random
 * city intersections. In order to win, the three contestants need all to meet at any intersection
 * of the city as fast as possible.
 * It should be clear that the contestants may arrive at the intersections at different times, in
 * which case, the first to arrive can wait until the others arrive.
 * From an estimated walking speed for each one of the three contestants, ACM wants to determine the
 * minimum time that a live TV broadcast should last to cover their journey regardless of the contestantsâ€™
 * initial positions and the intersection they finally meet. You are hired to help ACM answer this question.
 * You may assume the following:
 *    ï‚· Each contestant walks at a given estimated speed.
 *    ï‚· The city is a collection of intersections in which some pairs are connected by one-way
 * streets that the contestants can use to traverse the city.
 *
 * This class implements the competition using Floyd-Warshall algorithm
 */

//Reference for my implementation of this algorithm was taken from 
//https://www.geeksforgeeks.org/floyd-warshall-algorithm-dp-16/
public class CompetitionFloydWarshall {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
     */
	double slowestSpeed;
	double[][] graph;
	double[][] solutionGraph;
    CompetitionFloydWarshall (String filename, int sA, int sB, int sC)
    {
    	try 
		{
			if (filename != null && withInBounds(sA, sB, sC)) 
			{
				
				File file = new File("/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment2/src/" + filename);
				// making file reader
				BufferedReader fileReader = new BufferedReader(new FileReader(file));

				// get the number of nodes and number of intersections from file
				int numberOfNodes = Integer.parseInt(fileReader.readLine());
				int numberOfEdges = Integer.parseInt(fileReader.readLine());

				// string to read currentLine
				String[] currentLine;
				
				// create graph and load in 0's and max values
				graph = new double[numberOfNodes][numberOfNodes];
				solutionGraph = new double[numberOfNodes][numberOfNodes];
				
				for(int i = 0; i < graph.length; i++)
					for(int j = 0; j < graph[i].length; j++)
					{
						if(i == j)
							graph[i][j] = 0;
						else
							graph[i][j] = Double.MAX_VALUE;
					}
				
				for (int i = 0; i < numberOfEdges; i++) {
					currentLine = fileReader.readLine().trim().replaceAll("\\s{2,}", " ").split(" ");
					graph[Integer.parseInt(currentLine[0])][Integer.parseInt(currentLine[1])] = Double.parseDouble(currentLine[2]); 
				}
					
				fileReader.close();
				slowestSpeed = ((double) Math.min(Math.min(sA, sB), sC)) / 1000;
			}
			else
				slowestSpeed = -1;
		} 
		catch (Exception e) 
		{
			System.out.println(e);
		}
    }
    
    public boolean withInBounds(int sA, int sB, int sC)
	{
		if(sA >= 50 && sA <= 100)
			if(sB >= 50 && sB <= 100)
				if(sC >= 50 && sC <= 100)
					return true;
		return false;
	}


    /**
     * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition()
    {
    	if(graph == null || graph.length == 0)
    		return -1;
       floydWarshall(graph);
       return (int) Math.ceil(getDistance(solutionGraph, slowestSpeed));
    }
    
    public void floydWarshall(double graph[][]) 
    { 
  
        int i, j, k; 

        for (i = 0; i < solutionGraph.length; i++) 
            for (j = 0; j < solutionGraph.length; j++) 
            	solutionGraph[i][j] = graph[i][j];
        
        for (k = 0; k < solutionGraph.length; k++)          
            for (i = 0; i < solutionGraph.length; i++) 
                for (j = 0; j < solutionGraph.length; j++)                   
                    if (solutionGraph[i][k] + solutionGraph[k][j] < solutionGraph[i][j]) 
                    	solutionGraph[i][j] = solutionGraph[i][k] + solutionGraph[k][j]; 
  
    } 
    
    public double getDistance(double graph[][], double speed)
    {
    	double largestValue = Double.MIN_VALUE;
    	   for (int i = 0; i < graph.length; i++) { 
    	        for (int j = 0; j < graph[i].length; j++) { 
    	            if (graph[i][j] > largestValue) { 
    	            	largestValue = graph[i][j]; 
    	            } 
    	        } 
    	    }
    	 if((largestValue == Double.MAX_VALUE || largestValue <= 0) || speed <= 0)
    		 return -1;
    	 else
    		 return largestValue / speed;
    }
    
   

}