public class Chrono {
    private final long nanoSecondsPerSecond = 1000000000;
    private final long nanoSecondPerCentieme = 10000000;

    private long chronoStartTime = 0;
    private long chronoStopTime = 0;
    public boolean isRunning = false;

    public void start() {
        this.chronoStartTime = System.nanoTime();
        this.isRunning = true;
    }

    public void stop() {
        if (this.isRunning) {
            this.chronoStopTime = System.nanoTime();
            this.isRunning = false;
        }
    }

    public long getElapsedSeconds() {
        long elapsedTime;
        if (isRunning) {
            elapsedTime = (System.nanoTime() - chronoStartTime);
        } else {
            elapsedTime = (chronoStopTime - chronoStartTime);
        }
        return elapsedTime / nanoSecondsPerSecond;
    }

    public long getElapsedCenti() {
        long elapsedTime;
        if (isRunning) {
            elapsedTime = (System.nanoTime() - chronoStartTime);
        } else {
            elapsedTime = (chronoStopTime - chronoStartTime);
        }
        return elapsedTime / nanoSecondPerCentieme;
    }
}
