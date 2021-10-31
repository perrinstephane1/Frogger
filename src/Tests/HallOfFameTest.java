package Tests;

import frogger.HallOfFame;
import junit.framework.TestCase;

import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class HallOfFameTest extends TestCase {
    public void testInit() {
        HallOfFame hof = new HallOfFame();
        assertNotNull(hof);
        assertEquals(0, hof.size());
    }

    public void testAddScore() {
        HallOfFame hof = new HallOfFame();
        assertEquals(0, hof.size());
        hof.addScore("Louis", 20.5f);
        assertEquals(1, hof.size());
        hof.addScore("Theo", 10.5f);
        assertEquals(2, hof.size());
        assertNotSame(hof.get(0), hof.get(1));
        assertTrue(hof.get(0).compareTo(hof.get(1))<0);
        hof.addScore("Nicolas", 30.5f);
        assertEquals(3, hof.size());
        assertTrue(hof.get(0).compareTo(hof.get(1))<0);
        assertTrue(hof.get(1).compareTo(hof.get(2))<0);
        assertSame("Nicolas", hof.get(2).getName());
        hof.addScore("Nikita", 150.4f);
        hof.addScore("Maxime", 10f);
        assertEquals(5, hof.size());
        assertSame("Nikita", hof.get(4).getName());
        hof.addScore("Leon", 200f);
        assertEquals(5, hof.size());
        assertSame("Nikita", hof.get(4).getName());
        hof.addScore("Thomas", 26.7f);
        assertEquals(5, hof.size());
        assertSame("Nicolas", hof.get(4).getName());
        assertSame("Theo", hof.get(0).getName());
    }

    public void testSaveLoad() {
        HallOfFame hof1 = new HallOfFame();
        HallOfFame hof2 = new HallOfFame();
        assertEquals(hof1, hof2);
        assertNotSame(hof1, hof2);
        hof1.addScore("Louis", 20.5f);
        hof1.addScore("Thomas", 26.7f);
        hof1.addScore("Nikita", 150.4f);
        hof1.addScore("Maxime", 10f);
        hof1.addScore("Theo", 10.5f);
        assertNotEquals(hof1, hof2);
        hof1.save("Scores.txt");
        hof2.load("Scores.txt");
        assertEquals(5, hof2.size());
    }
}
