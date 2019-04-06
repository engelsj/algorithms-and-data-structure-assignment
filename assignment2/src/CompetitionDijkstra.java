
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
		
		try 
		{
			if (filename != null && withInBounds(sA, sB, sC)) 
			{
				
				File file = new File("/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment2/src/" + filename);
				// making file reader
				BufferedReader fileReader = new BufferedReader(new FileReader(file));

				// get the number of nodes and number of intersections from file
				int numberOfNodes = Integer.parseInt(fileReader.readLine());
				int numberOfIntersections = Integer.parseInt(fileReader.readLine());

				// string to read currentLine
				String[] currentLine;

				// nodeList of to look at and load them
				nodeList = new Node[numberOfNodes];
				for (int i = 0; i < nodeList.length; i++)
					nodeList[i] = new Node(i);

				for (int i = 0; i < numberOfIntersections; i++) {
					currentLine = fileReader.readLine().trim().replaceAll("\\s{2,}", " ").split(" ");
					nodeList[Integer.parseInt(currentLine[0])].addDestination(nodeList[Integer.parseInt(currentLine[1])], Double.parseDouble(currentLine[2]));
				}

				searchGraph = new Graph();
				for (int i = 0; i < nodeList.length; i++)
					searchGraph.addNode(nodeList[i]);
				
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
	 * @return int: minimum minutes that will pass before the three contestants
	 *         can meet
	 */
	public int timeRequiredforCompetition() 
	{
		if(slowestSpeed == -1)
			return -1;
	
        double longestDistance = 0;
		
		for(int i = 0; i < nodeList.length; i++)
        {
            searchGraph = calculateShortestPathFromSource(searchGraph, nodeList[i]);
            for(Node currentNode : searchGraph.nodes)
            {
                if(longestDistance < currentNode.distance)
                	longestDistance = currentNode.distance;
                currentNode.setDistance(Integer.MAX_VALUE);
                currentNode.getShortestPath().clear();
            }
        }
		return (int) (longestDistance / slowestSpeed);
	}

	public static Graph calculateShortestPathFromSource(Graph graph, Node source) {
		source.setDistance(0);

		Set<Node> settledNodes = new HashSet<>();
		Set<Node> unsettledNodes = new HashSet<>();

		unsettledNodes.add(source);

		while (unsettledNodes.size() != 0) 
		{
			Node currentNode = getLowestDistanceNode(unsettledNodes);
			unsettledNodes.remove(currentNode);
			for (Entry<Node, Double> adjacencyPair : currentNode.getAdgacentNodes().entrySet()) 
			{
				Node adjacentNode = adjacencyPair.getKey();
				double edgeWeight = adjacencyPair.getValue();
				if (!settledNodes.contains(adjacentNode)) {
					calculateMinimumDistance(adjacentNode, edgeWeight, currentNode);
					unsettledNodes.add(adjacentNode);
				}
			}
			settledNodes.add(currentNode);
		}
		return graph;
	}

	private static Node getLowestDistanceNode(Set<Node> unsettledNodes) {
		Node lowestDistanceNode = null;
		double lowestDistance = Integer.MAX_VALUE;
		for (Node currentNode : unsettledNodes) {
			double nodeDistance = currentNode.getDistance();
			if (nodeDistance < lowestDistance) {
				lowestDistance = nodeDistance;
				lowestDistanceNode = currentNode;
			}
		}
		return lowestDistanceNode;
	}

	private static void calculateMinimumDistance(Node currentNode, double edgeWeight, Node sourceNode) {
		double sourceDistance = sourceNode.getDistance();
		if (sourceDistance + edgeWeight < currentNode.getDistance()) {
			currentNode.setDistance(sourceDistance + edgeWeight);
			LinkedList<Node> shortestPath = new LinkedList<>(sourceNode.getShortestPath());
			shortestPath.add(sourceNode);
			currentNode.setShortestPath(shortestPath);
		}
	}

	public class Graph {
		private ArrayList<Node> nodes;

		public Graph() {
			nodes = new ArrayList<>();
		}

		public void addNode(Node newNode) {
			nodes.add(newNode);
		}
	}

	public class Node implements Comparable<Node> {
		private int index;

		private LinkedList<Node> shortestPath = new LinkedList<>();

		private HashMap<Node, Double> adgacentNodes = new HashMap<>();

		private double distance = Integer.MAX_VALUE;

		public void addDestination(Node destination, double distance) {
			adgacentNodes.put(destination, distance);
		}

		public Node(int index) {
			this.index = index;
		}

		public void setDistance(double distance) {
			this.distance = distance;
		}

		public double getDistance() {
			return this.distance;
		}

		public LinkedList<Node> getShortestPath() {
			return this.shortestPath;
		}

		public void setShortestPath(LinkedList<Node> shortestPath) {
			this.shortestPath = shortestPath;
		}

		public HashMap<Node, Double> getAdgacentNodes() {
			return this.adgacentNodes;
		}

		@Override
		public int compareTo(Node o) {
			if (this.distance > o.distance)
				return 1;
			else if (this.distance < o.distance)
				return -1;
			return 0;
		}

	}
}