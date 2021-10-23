import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.TimerTask;


public class Plateau extends ArrayList<Piste> {
    private Group root;
    protected int nb_pistes;
    protected double h_piste;
    protected double l_piste;
    protected GridPane gridPane = new GridPane();


    public Plateau(Group root, int nb_pistes, double h_piste, double l_piste) {
        this.root = root;
        this.nb_pistes = nb_pistes;
        this.h_piste = h_piste;
        this.l_piste = this.h_piste*this.nb_pistes;

        for (int ii=0; ii<this.nb_pistes; ii++) {
            this.addPiste(ii);
        }
    }

    public void addPiste(int cnt) {
        Piste piste = new Piste(cnt, this, true, 1, 1, 1);
        this.add(piste);
        this.gridPane.add(piste.getImageView(), 0, cnt);
    }




    public GridPane getGridPane() {
        return gridPane;
    }

}


