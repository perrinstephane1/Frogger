import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


public class Plateau extends ArrayList<Piste> {
    private Group root;
    protected int nb_pistes;
    protected double h_piste;
    protected double l_piste;
    protected GridPane gridPane = new GridPane();


    public Plateau(Group root, int nb_pistes, double h_piste) {
        this.root = root;
        this.nb_pistes = nb_pistes;
        this.h_piste = h_piste;
        this.l_piste = this.h_piste*this.nb_pistes;

        for (int ii=0; ii<this.nb_pistes/2; ii++) {
            this.addPiste(ii, 1);
        }
        for (int ii=this.nb_pistes/2; ii<this.nb_pistes; ii++) {
            this.addPiste(ii, 0);
        }
        this.addPiste(this.nb_pistes-1, 2); // Bottom line
        this.addPiste(this.nb_pistes/2 - 1, 2); // Middle line
        this.addPiste(0, 2); // Top line




    }

    public void addPiste(int cnt, int type_piste) {

        int taille_min = 1;
        int taille_max = 3;
        int taille_obstacle = ThreadLocalRandom.current().nextInt(taille_min, taille_max + 1);
        int sens = ThreadLocalRandom.current().nextInt(0, 1 + 1);


        Piste piste = new Piste(cnt, this, true, sens, 1, taille_obstacle, type_piste);
        this.add(piste);
        this.gridPane.add(piste.getImageView(), 0, cnt);
    }


    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getGridPane());
        trans.setByY(speed);
        trans.play();
    }

    public GridPane getGridPane() {
        return gridPane;
    }

}