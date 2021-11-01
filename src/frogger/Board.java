package frogger;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;

/**
 * This class represents the ArrayList in which is store all the Lanes of the game
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Board extends ArrayList<frogger.Lane> {
    /** Number of Lanes */
    protected final int nb_case;
    /** Length of a single square */
    protected double l_case;
    /** GridPane */
    public final GridPane gridPane = new GridPane();
    /** Not in use yet */
    protected int cnt_decalage = (int) this.l_case;
    /** Chronometer */
    public final frogger.Chrono chrono = new frogger.Chrono();

    /**
     * This method is the constructor
     * @param nb_case This int corresponds to the number of lane (river, road, safe lane) composing the game
     * @param l_case This int corresponds to the length in pixels of a square on the game windows
     */
    public Board(int nb_case, double l_case) {
        this.nb_case = nb_case;
        this.l_case = l_case;

        this.addLane(0, 2); // Top safe lane

        for (int ii=1; ii<this.nb_case/2; ii++) { // River lanes
            this.addLane(ii, 1);
        }
        for (int ii=this.nb_case/2; ii<this.nb_case-1; ii++) { // Road lanes
            this.addLane(ii, 0);
        }
        this.addLane(this.nb_case-1, 2); // Bottom safe lane
        this.addLane(this.nb_case-2, 3);

    }

    /**
     * This method returns the time for which the player has been playing
     * @return the time for which the player has been playing
     */
    public Text getChrono() {
        long time = this.chrono.getElapsedCenti();
        return new Text("Time : "+time/100+","+time%100+" s");
    }

    /**
     * This method returns the time as a float
     * @return the time as a float
     */
    public float getChronoToFloat() {
        float time = this.chrono.getElapsedCenti();
        return time/100;
    }

    /**
     * This method adds a new Lane in the Board
     * @param cnt This int is the number given to the Lane as an ID.
     * @param type_lane This int characterizes the lane. A type-0 Lane is a road, a type-1 Lane is a river and a type-2 Lane is a safe lane.
     */
    public void addLane(int cnt, int type_lane) {
        Lane lane = new Lane(cnt, this,  cnt%2, 1, Math.random(), type_lane);
        this.add(lane);
        this.gridPane.addColumn(0, lane.getImageView());
    }

    /**
     * This method returns a GridPane
     * @return a GridPane
     */
    public GridPane getGridPane() {
        return this.gridPane;
    }

    /**
     * This method inverts the order of the Lanes before adding a new one and eventually put it back in order
     */
    public void decalage() {
        Lane lane = new Lane(0, this, 1, 1, Math.random(), 2);

        this.invert();
        this.add(lane);
        this.invert();

        this.gridPane.getChildren().clear();
        for (Lane value : this) {
            this.gridPane.addColumn(0, value.getImageView());
        }
    }
    /**
     * This method inverts the order of the Lanes before adding a new one and eventually put it back in order
     * @param speed This double is the speed at which the MovingElement is moving.
     */
    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getGridPane());
        assert (this.size()==this.gridPane.getChildren().size());
        System.out.println("nbr lanes : "+this.size());
        if (cnt_decalage>=0) {
            cnt_decalage = cnt_decalage-(int)this.l_case+speed;
            trans.setByY(-this.l_case+speed);
            trans.play();
            this.decalage();
        } else {
            cnt_decalage += speed;
            trans.setByY(speed);
            trans.play();
        }
    }
    /**
     * This method inverts the order of the Lanes in the Board
     */
    public void invert() {
        ArrayList<Lane> temp = new ArrayList<>();
        int n  = this.size();
        for (int ii=n-1; ii>=0; ii--) {
            temp.add(this.get(ii));
        }
        for (int ii=n-1; ii>=0; ii--) {
            this.set(ii, temp.get(ii));
        }
    }
}