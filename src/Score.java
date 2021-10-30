public class Score implements Comparable<Score> {
    private float time;
    private String name;

    public Score(float time, String pseudo) {
        this.time = time;
        this.name = pseudo;
    }

    public float getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(Score other) {
        if (this.time== other.time) {
            return 0;
        } else if (this.time < other.time) {
            return -1;
        } else {
            return 1;
        }
    }

    public String toString() {
        return this.name+" : "+this.time+"s";
    }
}
