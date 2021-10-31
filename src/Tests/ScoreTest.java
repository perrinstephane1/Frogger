package Tests;

import frogger.Score;
import junit.framework.TestCase;

public class ScoreTest extends TestCase {
    public void testInit() {
        Score score = new Score(23.01f, "Louis");
        assertNotNull(score);
        assertEquals(23.01f, score.getTime());
        assertEquals("Louis", score.getName());
    }

    public void testCompareTo() {
        Score score_1 = new Score(23.01f, "Louis");
        Score score_2 = new Score(13.05f, "Theo");
        Score score_3 = new Score(105f, "Theo");
        Score score_4 = new Score(13.05f, "Theo");
        assertTrue(score_1.compareTo(score_2)>0);
        assertTrue(score_1.compareTo(score_3)<0);
        assertEquals(0, score_2.compareTo(score_4));
    }
}
