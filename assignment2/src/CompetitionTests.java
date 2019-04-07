
/*
1. Justify the choice of the data structures used in CompetitionDijkstra and
CompetitionFloydWarshall

For my implementation of CompetitionDijkstra I choose to create a Graph that has a hashset of nodes. 
I choose to use a hashset instead of a linked list or an arraylist because each node is unique and
there can not be repeated nodes of the same index, making it an easier data structure to use because I 
just need to add values to it without in the order of the nodes. The hashset is made up of node objects
which I created. A node object has a hashmap that contains the list of adjacent nodes to that node. I choose to
use a hashmap because I wanted to easily access each node and clearly see their connection. While I could have used
a 2d array to store this info, I prefer a hashmap because I can look up value close to constant time O(1). The final datastrucute
that I used was a simple array to hold all of the nodes that I wanted to iterate over for the algorithm.

For my implementation of CompetitionFloydWarshall the only data structures that i used were 2 2d arrays to hold the graph.
The first 2d array stored the original values of the graph and the second stored the final result graph. Because the algorithm only needs
a simple 2d array to function, that is the only data structure that I needed.  

2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms
in the given problem. Also explain how would their relative performance be affected by the
density of the graph. Which would you choose in which set of circumstances and why? 

The major difference between Dijkstra and FW is that Dijkstra calculates the shortest path to a single node, while FW calculates the 
shortest path for ever node in the graph. Even though it was encountered in the problem, FW works for negative edges, but not negative cycles,
while Dijkstra does not work for negative edges. In the given problem, Dijkstra had to be run each time for every node in the graph, while FW only
had to be run once for the whole graph. This made FW much easier to implement as only a 2D array was needed and then the algorithm was run once on the 
array. For Dijkstra however, I needed to create a graph object and then run each node through the algorithm for each node in the graph. For Dijkstra the 
time complexity is  O(E log V) where e is the number of edges and V the number of vertexes. For FW the time complexity is  O(V^3) where V is the number of 
vertexes. As the number of vertexes increases, FW becomes a lot slower as a ^3 run time does not scale well. For smaller graphs that can be easily fit into
a relatively small 2d array, FW is a much better solution to this problem, but as the graph get larger, Dijkstra is a much better solution as it has a
much faster runtime. 


*/
import static org.junit.Assert.*;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() 
    {
    	 CompetitionDijkstra d = new CompetitionDijkstra("tinyEWD.txt", 50, 60, 70);
    }

    @Test
    public void testFWConstructor() 
    {
    	CompetitionFloydWarshall d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 60, 70);
    }
    
    
   
   @Test
   public void testDijkstraOutOfBoundSpeeds()
   {
	   CompetitionDijkstra d = new CompetitionDijkstra("tinyEWD.txt", 0, 0, 0);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", -1, -1, -1);
	   assertEquals("Testing with negative speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", 101, 101, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", 49, 10, 100);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", 101, 10, 100);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", 50, 49, 100);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", 50, 10, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", 50, 100, 49);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("tinyEWD.txt", 50, 100, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   
   }

   @Test
   public void testDijkstra()
   {
	   CompetitionDijkstra d = new CompetitionDijkstra("input-K.txt", 51, 70, 88);
	   assertEquals("Testing with test file", 314, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("input-I.txt", 72, 70, 65);
	   assertEquals("Testing with test file", 185, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("input-A.txt", 60,50,75);
	   assertEquals("Testing with test file", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("input-B.txt", 60,80,50);
	   assertEquals("Testing with test file", 10000, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra("input-J.txt", 60,75,61);
	   assertEquals("Testing with test file", -1, d.timeRequiredforCompetition());
	   
   }
   
   @Test
   public void testFW()
   {
	   CompetitionFloydWarshall d = new CompetitionFloydWarshall("input-K.txt", 51, 70, 88);
	   assertEquals("Testing with test file", 314, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("input-I.txt", 72, 70, 65);
	   assertEquals("Testing with test file", 185, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("input-A.txt", 60,50,75);
	   assertEquals("Testing with test file", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("input-B.txt", 60,80,50);
	   assertEquals("Testing with test file", 10000, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("input-J.txt", 60,75,61);
	   assertEquals("Testing with test file", -1, d.timeRequiredforCompetition());
   }
   
   @Test
   public void testFWOutOfBoundSpeeds()
   {
	   CompetitionFloydWarshall d = new CompetitionFloydWarshall("tinyEWD.txt", 0, 0, 0);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", -1, -1, -1);
	   assertEquals("Testing with negative speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 101, 101, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 49, 10, 100);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 101, 10, 100);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 49, 100);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 101, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 100, 49);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 100, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   
   }
   @Test
   public void testFWNull()
   {
	   CompetitionFloydWarshall d = new CompetitionFloydWarshall(null, 50, 50, 50);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall(null, 1, 1, 1);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall(null, 101, 101, 101);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
   }
   
   public void testDijkstraNull()
   {
	   CompetitionDijkstra d = new CompetitionDijkstra(null, 50, 50, 50);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra(null, 1, 1, 1);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionDijkstra(null, 101, 101, 101);
	   assertEquals("Testing with 0 speed", -1, d.timeRequiredforCompetition());
   }
   
   
    
}