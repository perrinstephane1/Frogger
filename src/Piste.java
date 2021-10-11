import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Piste extends Plateau {
    private boolean hostile;
    private double sens;
    private double vitesse;
    private double taille_obstacle;
    private boolean arrivee; // si c'est la dernière ligne c'est FINI
    private ImageView imageView;

    public Piste(Group root, int nb_pistes, double h_piste, double l_piste, boolean hostile, double sens, double vitesse, double taille_obstacle, boolean arrivee) {
        super(root, nb_pistes, h_piste, l_piste);
        this.hostile = hostile;
        this.sens = sens;
        this.vitesse = vitesse;
        this.taille_obstacle = taille_obstacle;
        this.arrivee = arrivee;
        try {
            Image image = new Image(new FileInputStream("D:\\SOIA_2A\\java\\Frogger\\herbe.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(0);
            this.imageView.setY(0);
            imageView.setFitHeight(this.h_piste);
            imageView.setFitWidth(this.l_piste);
        } catch (FileNotFoundException e) {
            System.out.println("f***");
            System.out.println(e);
        };
    }



    public ImageView getImageView() {
        return imageView;
    }
}
