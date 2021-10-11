import javafx.scene.Group;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;


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
            Piste piste = new Piste(ii, this, true, 1, 1, 1);
            this.add(piste);
            this.gridPane.add(piste.getImageView(), 0, ii);
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

//    public void display() {
//        for (int ii=0; ii<this.nb_pistes; ii++) {
//            this.root.getChildren().add(this.get(ii).getImageView());
//        }
//    }

}


