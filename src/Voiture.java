import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;



public class Voiture extends Truc_mobile {
    private ImageView imageView;

    public Voiture(Piste piste, Scene scene) {
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
