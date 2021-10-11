import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Piste {
    protected boolean hostile;
    protected double sens;
    protected double vitesse;
    protected double taille_obstacle;
    protected boolean arrivee; // si c'est la dernière ligne c'est FINI
    protected ImageView imageView;
    protected GridPane gridPane = new GridPane();

    public Piste(int ii, Plateau p, boolean hostile, double sens, double vitesse, double taille_obstacle) {
        this.vitesse = vitesse;
        this.sens = sens;
        this.taille_obstacle = taille_obstacle;
        try {
            for (int jj=0; jj<12; jj++) {
                Image image = new Image(new FileInputStream("D:\\SOIA_2A\\java\\Frogger\\herbe.png"));
                this.imageView = new ImageView(image);
//                this.imageView.setX(0);
//                this.imageView.setY(0+ii*p.h_piste);
                this.imageView.setFitHeight(p.h_piste);
                this.imageView.setFitWidth(p.h_piste);
                this.gridPane.add(this.imageView, jj, ii);
            }

        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public Piste(int ii, Plateau p) {
        this(ii, p, true, 1, 10, 40);
    }

    public GridPane getImageView() {
        return this.gridPane;
    }
}
