package assignment2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
	 * @param filename:
	 *            A filename containing the details of the city road network
	 * @param sA,
	 *            sB, sC: speeds for 3 contestants
	 */
	CompetitionDijkstra(String filename, int sA, int sB, int sC) {
		File file = new File(
				"/Users/jackengels1/Documents/GitHub/algorithms-and-data-structure-assignment/assignment2/src/"
						+ filename);
		try {
			BufferedReader fileReader = new BufferedReader(new FileReader(file));
			int numberOfNodes = Integer.parseInt(fileReader.readLine());
			int numberOfIntersections = Integer.parseInt(fileReader.readLine());
			String[] currentLine;
			Node[] nodeList = new Node[numberOfNodes];
			for (int i = 0; i < nodeList.length; i++)
				nodeList[i] = new Node(i);
			for (int i = 0; i < numberOfIntersections; i++) 
			{
				currentLine = fileReader.readLine().split(" ");
				nodeList[Integer.parseInt(currentLine[0])].addDestination(nodeList[Integer.parseInt(currentLine[1])],
						Double.parseDouble(currentLine[2]));
			}
			Graph graph = new Graph();
			for (int i = 0; i < nodeList.length; i++) {
				graph.addNode(nodeList[i]);
			}
			fileReader.close();


		} 
		catch (Exception e) {
			System.out.println(e);
		}
	}

	/**
	 * @return int: minimum minutes that will pass before the three contestants
	 *         can meet
	 */
	public int timeRequiredforCompetition() {

		// TO DO
		return -1;
	}

	public class Graph {
		private Set<Node> nodes = new HashSet<>();

		public void addNode(Node newNode) {
			nodes.add(newNode);
		}
	}

	public class Node {
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

	}
}
