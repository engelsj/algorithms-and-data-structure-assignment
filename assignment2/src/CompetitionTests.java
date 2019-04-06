

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
    
    
    
   // tests null values for inputs
   /*@Test
   public void testDijkstraNull()
   {
	   CompetitionDijkstra d = new CompetitionDijkstra(null, 60, 60, 60);
	   assertEquals("Testing with null file", -1, d.timeRequiredforCompetition());
	   
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
	   
   }
   */
   @Test
   public void testDijkstra()
   {
	   CompetitionDijkstra d = new CompetitionDijkstra("input-B.txt", 60, 80, 50);
	   assertEquals("Testing with test file", 10000, d.timeRequiredforCompetition());
	   
   }
    
}