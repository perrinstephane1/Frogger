import javafx.scene.Group;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Piste {
    private Plateau p;
    private boolean hostile;
    private double sens;
    private double vitesse;
    private double taille_obstacle;
    private boolean arrivee; // si c'est la dernière ligne c'est FINI
    private ImageView imageView;

    public Piste(int ii, Plateau p) {
        try {
            Image image = new Image(new FileInputStream("D:\\SOIA_2A\\java\\Frogger\\herbe.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(0);
            this.imageView.setY(0+ii*p.h_piste);
            this.imageView.setFitHeight(p.h_piste);
            this.imageView.setFitWidth(p.l_piste);
        } catch (FileNotFoundException e) {
            System.out.println("f***");
            System.out.println(e);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }
}
