import javafx.animation.TranslateTransition;
import javafx.scene.Group;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import java.util.ArrayList;


public class Plateau extends ArrayList<Piste> {
    private Group root;
    protected int nb_pistes;
    protected double h_piste;
    protected double l_piste;
    protected GridPane gridPane = new GridPane();
    protected int cnt_decalage = (int)this.h_piste;
    public Chrono chrono = new Chrono();


    public Plateau(Group root, int nb_pistes, double h_piste) {
        this.root = root;
        this.nb_pistes = nb_pistes;
        this.h_piste = h_piste;
        this.l_piste = this.h_piste*this.nb_pistes;

        this.addPiste(0, 2); // Top safe lane

        for (int ii=1; ii<this.nb_pistes/2; ii++) { // River lanes
            this.addPiste(ii, 1);
        }
        for (int ii=this.nb_pistes/2; ii<this.nb_pistes-1; ii++) { // Road lanes
            this.addPiste(ii, 0);
        }
        this.addPiste(this.nb_pistes-1, 2); // Bottom safe lane
        this.addPiste(this.nb_pistes-2, 3);


    }

    public Text getChrono() {
        Text text_chrono = new Text("Time : "+this.chrono.getElapsedSeconds()+" s");
        return text_chrono;
    }

    public void addPiste(int cnt, int type_piste) {
        Piste piste = new Piste(cnt, this, true, cnt%2, 1, Math.random(), type_piste);
        this.add(piste);
        this.gridPane.addColumn(0, piste.getImageView());
    }

    public GridPane getGridPane() {
        return this.gridPane;
    }

    public void decalage() {
        Piste piste = new Piste(0, this, true, 1, 1, Math.random(), 2);

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