public class Chrono {
    private final long nanoSecondsPerMillisecond = 1000000;

    private long chronoStartTime = 0;
    private long chronoStopTime = 0;
    public boolean isRunning = false;

    public void start() {
        this.chronoStartTime = System.nanoTime();
        this.isRunning = true;
    }

    public void stop() {
        this.chronoStopTime = System.nanoTime();
        this.isRunning = false;
    }

    public long getElapsedMilliseconds() {
        long elapsedTime;
        if (isRunning)
            elapsedTime = (System.nanoTime() - chronoStartTime);
        else
            elapsedTime = (chronoStopTime - chronoStartTime);
        return elapsedTime / nanoSecondsPerMillisecond;
    }
}
