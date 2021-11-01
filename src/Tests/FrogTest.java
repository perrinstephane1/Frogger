package Tests;

import frogger.Frog;
import junit.framework.TestCase;


public class FrogTest extends TestCase {
    public void testInit() {
        Frog frog = new Frog(50, 50, 20, 10, "frog8bit.png");
        assertNotNull(frog);
        assertSame("frogger.Frog", frog.getClass().getTypeName());
    }

    public void testGetter() {
        Frog frog = new Frog(50, 50, 20, 10, "frog8bit.png");
        assertSame("javafx.scene.image.ImageView", frog.getImageView().getClass().getTypeName());
    }

    public void testSetter() {
        Frog frog = new Frog(50, 50, 20, 10, "frog8bit.png");
        assertFalse(frog.isOnLog());
        frog.setOnLog(true);
        assertTrue(frog.isOnLog());
        frog.setOnLog(false);
        assertFalse(frog.isOnLog());
    }

    public void testMove() {
        Frog frog = new Frog(50, 50, 20, 4, "frog8bit.png");
        assertEquals(50.0, frog.getX());
        assertEquals(50.0, frog.getY());
        assertEquals(0, frog.getScore());
        assertEquals(0, frog.getEtage());
        frog.up();
        assertEquals(50.0, frog.getX());
        assertEquals(30.0, frog.getY());
        assertEquals(1, frog.getScore());
        assertEquals(1, frog.getEtage());
        frog.up();
        frog.up();
        assertEquals(50.0, frog.getX());
        assertEquals(10.0, frog.getY());
        assertEquals(2, frog.getScore());
        assertEquals(2, frog.getEtage());
        frog.down();
        assertEquals(50.0, frog.getX());
        assertEquals(30.0, frog.getY());
        assertEquals(2, frog.getScore());
        assertEquals(1, frog.getEtage());
        frog.down();
        frog.down();
        frog.down();
        assertEquals(50.0, frog.getX());
        assertEquals(70.0, frog.getY());
        assertEquals(2, frog.getScore());
        assertEquals(0, frog.getEtage());
        frog.left();
        assertEquals(30.0, frog.getX());
        assertEquals(70.0, frog.getY());
        assertEquals(2, frog.getScore());
        assertEquals(0, frog.getEtage());
        frog.left();
        frog.left();
        assertEquals(10.0, frog.getX());
        assertEquals(70.0, frog.getY());
        assertEquals(2, frog.getScore());
        assertEquals(0, frog.getEtage());
        frog.right();
        assertEquals(30.0, frog.getX());
        assertEquals(70.0, frog.getY());
        assertEquals(2, frog.getScore());
        assertEquals(0, frog.getEtage());
        frog.right();
        frog.right();
        frog.right();
        assertEquals(70.0, frog.getX());
        assertEquals(70.0, frog.getY());
        assertEquals(2, frog.getScore());
        assertEquals(0, frog.getEtage());
    }
}
