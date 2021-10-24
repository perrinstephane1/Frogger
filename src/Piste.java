import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Piste {
    protected boolean hostile;
    protected double sens;
    protected double vitesse;
    protected double taille_obstacle;
    protected double longueur_piste;
    protected double longueur_bloc;
    protected int numero_piste;
    protected double densite;
    protected boolean arrivee; // si c'est la dernière ligne c'est FINI
    protected ImageView imageView;
    protected GridPane gridPane = new GridPane();
    protected int type_piste;

    public Piste(int ii, Plateau p, boolean hostile, double sens, double vitesse, double taille_obstacle, int type_piste) {
        this.vitesse = vitesse;
        this.sens = sens;
        this.taille_obstacle = taille_obstacle;
        this.longueur_bloc = p.h_piste;
        this.longueur_piste = p.nb_pistes * p.h_piste;
        this.numero_piste = ii;
        this.densite = Math.random();
        this.type_piste = type_piste;


        if (this.type_piste == 0){ // If it is a road
            try {
                for (int jj = 0; jj < p.nb_pistes; jj++) {
                    Image image = new Image(new FileInputStream("routemieuxJAUNE.jpeg.png"));
                    this.imageView = new ImageView(image);
                    this.imageView.setFitHeight(p.h_piste);
                    this.imageView.setFitWidth(p.h_piste);
                    this.gridPane.add(this.imageView, jj, ii);
                }

            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }
        else if (this.type_piste == 1){  // If it is a river
            try {
                for (int jj = 0; jj < p.nb_pistes; jj++) {
                    Image image = new Image(new FileInputStream("river.png"));
                    this.imageView = new ImageView(image);
                    this.imageView.setFitHeight(p.h_piste);
                    this.imageView.setFitWidth(p.h_piste);
                    this.gridPane.add(this.imageView, jj, ii);
                }

            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }else if (this.type_piste == 2){  // If it is the beginning
            try {
                for (int jj = 0; jj < p.nb_pistes; jj++) {
                    Image image = new Image(new FileInputStream("debut.png"));
                    this.imageView = new ImageView(image);
                    this.imageView.setFitHeight(p.h_piste);
                    this.imageView.setFitWidth(p.h_piste);
                    this.gridPane.add(this.imageView, jj, ii);
                }

            } catch (FileNotFoundException e) {
                System.out.println(e);
            }
        }

    }



    public Piste(int ii, Plateau p) {
        this(ii, p, true, 1, 10, 40, 0);
    }

    public GridPane getImageView() {
        return this.gridPane;
    }
}