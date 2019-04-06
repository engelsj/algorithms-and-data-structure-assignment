
/*
1. Justify the choice of the data structures used in CompetitionDijkstra and
CompetitionFloydWarshall

For my implementation of CompetitionDijkstra I choose to create a Graph that has an array of

2. Explain theoretical differences in the performance of Dijkstra and Floyd-Warshall algorithms
in the given problem. Also explain how would their relative performance be affected by the
density of the graph. Which would you choose in which set of circumstances and why? 

*/
import static org.junit.Assert.*;

import org.junit.Test;

public class CompetitionTests {

    @Test
    public void testDijkstraConstructor() 
    {

    }

    @Test
    public void testFWConstructor() 
    {
        //TODO
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
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 10, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 100, 49);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   d = new CompetitionFloydWarshall("tinyEWD.txt", 50, 100, 101);
	   assertEquals("Testing with too fast speed", -1, d.timeRequiredforCompetition());
	   
   }
    
}