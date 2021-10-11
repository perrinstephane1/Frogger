import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Nenuphar extends Truc_mobile{
    private boolean hostile;
    private ImageView imageView;

    public Nenuphar(Piste piste, Scene scene) {
        super(piste,scene);

        try {
            Image image = new Image(new FileInputStream("C:\\Users\\Williams HOARAU\\Desktop\\Travail\\Année 2\\POO\\images pour le projet\\nenuphar.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.X);
            this.imageView.setY(this.Y);
            this.imageView.setFitHeight(this.height);
            this.imageView.setPreserveRatio(true);
        } catch (FileNotFoundException var7) {
            System.out.println("f***nenuphar");
            System.out.println(var7);
        }
    }
}
