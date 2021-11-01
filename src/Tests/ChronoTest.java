package Tests;

import frogger.Chrono;
import junit.framework.TestCase;


public class ChronoTest extends TestCase {
    public void testInit() {
        Chrono chrono = new Chrono();
        assertNotNull(chrono);
        assertSame("frogger.Chrono", chrono.getClass().getTypeName());
    }

    public void testStartStop() {
        Chrono chrono = new Chrono();
        assertFalse(chrono.isRunning);
        chrono.stop();
        assertFalse(chrono.isRunning);
        chrono.start();
        assertTrue(chrono.isRunning);
        chrono.start();
        assertTrue(chrono.isRunning);
        chrono.stop();
        assertFalse(chrono.isRunning);
    }

    public void testMeasure() throws InterruptedException {
        Chrono chrono = new Chrono();
        chrono.start();
        Thread.sleep(1001);
        chrono.stop();
        assertEquals(1, chrono.getElapsedSeconds());
        assertTrue( Math.abs(100-chrono.getElapsedCenti())<3);

        chrono.start();
        Thread.sleep(500);
        assertTrue( Math.abs(50-chrono.getElapsedCenti())<3);
        Thread.sleep(500);
        chrono.stop();
        assertEquals(1, chrono.getElapsedSeconds());
        assertTrue( Math.abs(100-chrono.getElapsedCenti())<3);
    }
}
