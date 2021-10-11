import javafx.scene.Group;

import java.util.ArrayList;


public class Plateau extends ArrayList<Piste> {
    private Group root;
    protected int nb_pistes;
    protected double h_piste;
    protected double l_piste;


    public Plateau(Group root, int nb_pistes, double h_piste, double l_piste) {
        this.root = root;
        this.nb_pistes = nb_pistes;
        this.h_piste = h_piste;
        this.l_piste = l_piste;

        for (int ii=0; ii<this.nb_pistes; ii++) {
            this.add(new Piste());
        }
    }

    public void display() {
        for (int ii=0; ii<this.nb_pistes; ii++) {
            this.root.getChildren().add(this.get(ii).getImageView());
        }
    }

}


