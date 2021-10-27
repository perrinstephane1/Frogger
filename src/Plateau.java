import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;


public class Plateau extends ArrayList<Piste> {
    private Group root;
    protected int nb_pistes;
    protected double h_piste;
    protected double l_piste;
    protected GridPane gridPane = new GridPane();
    protected int cnt_decalage = (int)this.h_piste;


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
        this.addPiste(0, 1); // Top line
        this.addPiste(0, 0); // Top line

    }

    public void addPiste(int cnt, int type_piste) {

        int taille_min = 1;
        int taille_max = 3;
        int taille_obstacle = ThreadLocalRandom.current().nextInt(taille_min, taille_max + 1);

        //int sens = ThreadLocalRandom.current().nextInt(0, 1 + 1);


        Piste piste = new Piste(cnt, this, true, cnt%2, 1, taille_obstacle, type_piste);
        this.add(piste);
        this.gridPane.addColumn(0, piste.getImageView());

//        int n = this.gridPane.getChildren().size();
//        System.out.println("n : "+n);
//        GridPane temp = new GridPane();
//        for (int ii=n-1; ii>=0; ii--) {
//
//            temp.addColumn(0, this.gridPane.getChildren().get(ii));
//        }
//        this.gridPane = temp;

    }

    public void setGridPane(GridPane gridPane) {
        this.gridPane = gridPane;
    }

    public void decalage() {
//        System.out.println(Thread.currentThread());
        Piste piste = new Piste(0, this, true, 1, 1, 1, 2);

        System.out.println("inversion");
        this.invert();
        this.add(piste);
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
        System.out.println("nbr pistes : "+this.size());
        if (cnt_decalage>=0) {
            cnt_decalage = cnt_decalage-(int)this.h_piste+speed;
            trans.setByY(-this.h_piste+speed);
            trans.play();
            this.decalage();
        } else {
            cnt_decalage += speed;
            trans.setByY(speed);
            trans.play();

        }
    }

    public GridPane getGridPane() {
//        int n = this.gridPane.getChildren().size();
//        System.out.println("n : "+n);
//        GridPane temp = new GridPane();
//        for (int ii=n-1; ii>=0; ii--) {
//
//            temp.addColumn(0, this.gridPane.getChildren().get(ii));
//        }
//        this.gridPane = temp;
        return this.gridPane;
    }

    public void invert() {
        ArrayList<Piste> temp = new ArrayList<Piste>();
        int n  = this.size();
        for (int ii=n-1; ii>=0; ii--) {
            temp.add(this.get(ii));
        }
        for (int ii=n-1; ii>=0; ii--) {
            this.set(ii, temp.get(ii));
        }
    }

}