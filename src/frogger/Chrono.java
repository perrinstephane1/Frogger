package frogger;

/**
 * This class represents the chronometer
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Chrono {
    private long chronoStartTime = 0;
    private long chronoStopTime = 0;
    /** Determines if the chronometer is running or not */
    public boolean isRunning = false;
    /**
     * This method starts a chronometer
     */
    public void start() {
        this.chronoStartTime = System.nanoTime();
        this.isRunning = true;
    }
    /**
     * This method stops the chronometer
     */
    public void stop() {
        if (this.isRunning) {
            this.chronoStopTime = System.nanoTime();
            this.isRunning = false;
        }
    }

    /**
     * This method returns the elapsed time in second between the start and the stop of the chronometer
     * @return the elapsed time in second between the start and the stop of the chronometer
     */
    public long getElapsedSeconds() {
        long elapsedTime;
        if (isRunning) {
            elapsedTime = (System.nanoTime() - chronoStartTime);
        } else {
            elapsedTime = (chronoStopTime - chronoStartTime);
        }
        return elapsedTime / 1000000000;
    }
    /**
     * This method returns the elapsed time in second with decimals between the start and the stop of the chronometer
     * @return returns the elapsed time in second with decimals between the start and the stop of the chronometer
     */
    public long getElapsedCenti() {
        long elapsedTime;
        if (isRunning) {
            elapsedTime = (System.nanoTime() - chronoStartTime);
        } else {
            elapsedTime = (chronoStopTime - chronoStartTime);
        }
        return elapsedTime / 10000000;
    }
}
