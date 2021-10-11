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
            Image image = new Image(new FileInputStream("D:\\SOIA_2A\\java\\Frogger\\frog.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(0.0D);
            this.imageView.setY(0.0D);
            this.imageView.setFitHeight(this.height);
            this.imageView.setPreserveRatio(true);
        } catch (FileNotFoundException var7) {
            System.out.println("f***");
            System.out.println(var7);
        }
    }
}
