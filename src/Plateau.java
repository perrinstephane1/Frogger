

import javafx.scene.Group;

import java.util.ArrayList;

public class Plateau extends ArrayList{
    private Group root;
    protected int nb_pistes;
    protected double h_piste;
    protected double l_piste;

    public Plateau(Group root, int nb_pistes, double h_piste, double l_piste) {
        super(nb_pistes);
        this.root = root;
        for (int ii = 0; ii<nb_pistes; ii++) {
            this.add(new Piste(root, nb_pistes, h_piste, l_piste, true, 1, 40, 80, false));
        }
        this.nb_pistes = nb_pistes;
        this.h_piste = h_piste;
        this.l_piste = l_piste;
    }

    public Plateau(Group root) {
        this(root, 10, 40, 400);
    }
    //test

}


