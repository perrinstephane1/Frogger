package frogger;

/**
 * This class represents the score
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Score implements Comparable<Score> {
    /** Time of the player */
    private float time;
    /** Name of the player */
    private String name;

    /**
     * This method is the constructor
     * @param time Time of the player
     * @param pseudo Name of the player
     */
    public Score(float time, String pseudo) {
        this.time = time;
        this.name = pseudo;
    }

    /**
     * This method returns the time
     * @return the time
     */
    public float getTime() {
        return time;
    }

    /**
     * This method sets the time
     * @param time This long is the time of the player
     */
    public void setTime(long time) {
        this.time = time;
    }

    /**
     * This method returns the name
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * This method sets the name
     * @param name This String is the name of the player
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * This method compares the different times
     * @param other Score to compare with
     * @return -1 if inferior, 1 if superior or 0 if equal
     */
    @Override
    public int compareTo(Score other) {
        return Float.compare(this.time, other.time);
    }

    /**
     * This method returns the name and the time as a String
     */
    public String toString() {
        return this.name+": "+this.time+"s";
    }
}
