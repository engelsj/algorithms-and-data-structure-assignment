
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;
import java.util.Map.Entry;

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

// Reference for my implementation of this algorithm was taken from 
// https://www.baeldung.com/java-dijkstra
public class CompetitionDijkstra {
	Graph searchGraph;
	Node[] nodeList;
	double slowestSpeed;

	/**
	 * @param filename:
	 *            A filename containing the details of the city road network
	 * @param sA,
	 *            sB, sC: speeds for 3 contestants
	 */
	CompetitionDijkstra(String filename, int sA, int sB, int sC) {

		//"/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment2/src/"
		
		// try and load the file
		try 
		{	
			// check if the name isn't null and the speeds are within bounds
			if (filename != null && withInBounds(sA, sB, sC)) 
			{
				
				File file = new File(filename);
				// making file reader
				BufferedReader fileReader = new BufferedReader(new FileReader(file));

				// get the number of nodes and number of intersections from file
				int numberOfNodes = Integer.parseInt(fileReader.readLine());
				int numberOfEdges = Integer.parseInt(fileReader.readLine());

				// string to read currentLine
				String[] currentLine;

				// nodeList of to look at and load them
				nodeList = new Node[numberOfNodes];
				for (int i = 0; i < nodeList.length; i++)
					nodeList[i] = new Node(i);
				
				// trim the line incase there are trailing or leading zeros then load it into the node list
				for (int i = 0; i < numberOfEdges; i++) {
					currentLine = fileReader.readLine().trim().replaceAll("\\s{2,}", " ").split(" ");
					nodeList[Integer.parseInt(currentLine[0])].addDestination(nodeList[Integer.parseInt(currentLine[1])], Double.parseDouble(currentLine[2]));
				}
				
				// create out search graph and populate it with nodes
				searchGraph = new Graph();
				for (int i = 0; i < nodeList.length; i++)
					searchGraph.addNode(nodeList[i]);
				
				// close the file reader and fine the slowest speed
				fileReader.close();
				slowestSpeed = ((double) Math.min(Math.min(sA, sB), sC)) / 1000;
			}
			else
				slowestSpeed = -1;
		} 
		catch (Exception e) 
		{
		}

	}
	
	// returns where or not the speed is within the bounds of the min and max speed
	public boolean withInBounds(int sA, int sB, int sC)
	{
		if(sA >= 50 && sA <= 100)
			if(sB >= 50 && sB <= 100)
				if(sC >= 50 && sC <= 100)
					return true;
		return false;
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants
	 *         can meet
	 */
	public int timeRequiredforCompetition() 
	{
		// if the file or speed was incorrect, return -1;
		if(slowestSpeed == -1)
			return -1;
		
		// longest distance travled
        double longestDistance = 0;
        
        // iterate over each node in the graph
		for(int i = 0; i < nodeList.length; i++)
        {
			// find the distance from each node to the destination node
            searchGraph = calculateShortestPathFromSource(searchGraph, nodeList[i]);
            // go through each node and check their distance to the destination node
            for(Node currentNode : searchGraph.nodes)
            {
            	// if there was no route for that node then the graph is not complete so return -1
            	if(currentNode.distance == Double.MAX_VALUE)
            		return -1;
            	// check if it is the longest distance we have seen
            	else if(longestDistance < currentNode.distance)
                	longestDistance = currentNode.distance;
            	// reset the node to its original value
                currentNode.setDistance(Double.MAX_VALUE);
            }
        }
		// if we didnt find any distance/ the graph has none return -1;
		if(longestDistance == 0)
			return -1;
		// round the shortest distance and return it divided by the speed
		return (int) Math.ceil((longestDistance / slowestSpeed));
	}

	// find the shortest distance to a node and returns a graph object
	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
		// set the origin to 0
		source.setDistance(0);
		
		// hashset of nodes to loop through
		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();
		
		// 
		unsettledNodes.add(source);
		
		// loop from the starting node
		while (unsettledNodes.size() != 0) 
		{
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			// go through each adjacent node within that node
			for (Entry<Node, Double> adjacencyPair : currentNode.getAdgacentNodes().entrySet()) 
			{
				Node adjacentNode = adjacencyPair.getKey();
				double edgeWeight = adjacencyPair.getValue();
				// if the node isnt settled finds it distance and add it
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}
	
	// finds the shortest distance to a node and returns the node 
	private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		// set the lowest distnace to make value
		double lowestDistance = Integer.MAX_VALUE;
		// go through each node
		for (Node currentNode : unsettledNodes) {
			double nodeDistance = currentNode.getDistance();
			// if its distance is smaller set it to min distance
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = currentNode;
			}
		}
		return lowestDistanceNode;
	}
	// sets a nodes distance to the distance from the source node 

	private static void calculateMinimumDistance(Node currentNode, double edgeWeight, Node sourceNode) {
		double sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeight < currentNode.getDistance()) {
			currentNode.setDistance(sourceDistance + edgeWeight);
		}
	}
	
	// a graph object

	public class Graph {
		// hashset of nodes
		private Set<Node> nodes = new HashSet<>();
		// adds a node to the hashset
		public void addNode(Node newNode) {
			nodes.add(newNode);
		}
	}

	public class Node{
		private int index;
		
		// hashmap of adjacent nodes
		private HashMap<Node, Double> adgacentNodes = new HashMap<>();

		// distance of a node to the source node
		private double distance = Integer.MAX_VALUE;
		
		// adds an edge to a node
		public void addDestination(Node destination, double distance) {
			adgacentNodes.put(destination, distance);
		}
		
		// returns index of a node
		public Node(int index) {
			this.index = index;
		}
		
		// sets the distance of a node
		public void setDistance(double distance) {
			this.distance = distance;
		}
		
		// gets the distance of a node
		public double getDistance() {
			return this.distance;
		}

		// returns the hashmap of adjacent nodes
		public HashMap<Node, Double> getAdgacentNodes() {
			return this.adgacentNodes;
		}

	}
}
