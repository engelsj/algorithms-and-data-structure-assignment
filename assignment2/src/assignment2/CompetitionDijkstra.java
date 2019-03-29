package assignment2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

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
 * This class implements the competition using Dijkstra's algorithm
 */

public class CompetitionDijkstra {

    /**
     * @param filename: A filename containing the details of the city road network
     * @param sA, sB, sC: speeds for 3 contestants
    */
    CompetitionDijkstra (String filename, int sA, int sB, int sC)
    {
    	
    }


    /**
    * @return int: minimum minutes that will pass before the three contestants can meet
     */
    public int timeRequiredforCompetition(){

        //TO DO
        return -1;
    }
    
    public class Graph
    {
    	private Set<Node> nodes = new HashSet<>();
    	
    	public void addNode(Node newNode)
    	{
    		nodes.add(newNode);
    	}
    }
    
    public class Node
    {
    	private String name;
    	
    	private LinkedList<Node> shortestPath = new LinkedList<>();
    	
    	private HashMap<Node, Integer> adgacentNodes = new HashMap<>();
    	
    	private Integer distance = Integer.MAX_VALUE;
    	
    	public void addDestination(Node destination, int distance)
    	{
    		adgacentNodes.put(destination, distance);
    	}
    	
    	public Node(String name)
    	{
    		this.name = name;
    	}
    	
    }
}

