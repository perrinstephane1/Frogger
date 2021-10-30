import javafx.animation.TranslateTransition;
import javafx.scene.Group;
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
public class Board extends ArrayList<Lane> {
    private Group root;
    protected int nb_case;
    protected double l_case;
    protected double l_lane;
    protected GridPane gridPane = new GridPane();
    protected int cnt_decalage = (int)this.l_case;
    public Chrono chrono = new Chrono();

    /**
     * This method is the constructor
     * @param root
     * @param nb_case
     * @param l_case
     */
    public Board(Group root, int nb_case, double l_case) {
        this.root = root;
        this.nb_case = nb_case;
        this.l_case = l_case;
        this.l_lane = this.l_case*this.nb_case;

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

    public Text getChrono() {
        Long time = this.chrono.getElapsedCenti();
        Text text_chrono = new Text("Time : "+time/100+","+time%100+" s");
        return text_chrono;
    }

    public float getChronoToFloat() {
        Long time = this.chrono.getElapsedCenti();
        return time/100+time%100;
    }

    public void addLane(int cnt, int type_lane) {
        Lane lane = new Lane(cnt, this, true, cnt%2, 1, Math.random(), type_lane);
        this.add(lane);
        this.gridPane.addColumn(0, lane.getImageView());
    }

    public GridPane getGridPane() {
        return this.gridPane;
    }

    public void decalage() {
        Lane lane = new Lane(0, this, true, 1, 1, Math.random(), 2);

        this.invert();
        this.add(lane);
        this.invert();

        int n = this.size();
        this.gridPane.getChildren().clear();
        for (int ii=0; ii<n; ii++) {
            this.gridPane.addColumn(0, this.get(ii).getImageView());
        }
    }

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

    public void invert() {
        ArrayList<Lane> temp = new ArrayList<Lane>();
        int n  = this.size();
        for (int ii=n-1; ii>=0; ii--) {
            temp.add(this.get(ii));
        }
        for (int ii=n-1; ii>=0; ii--) {
            this.set(ii, temp.get(ii));
        }
    }

}