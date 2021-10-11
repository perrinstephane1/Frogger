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

    public Voiture(Piste piste, Scene scene, double l_case) {
        super(piste,scene, l_case);

        try {
            Image image = new Image(new FileInputStream("moto.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.X);
            this.imageView.setY(this.Y);
            this.imageView.setFitHeight(this.l_case);
            this.imageView.setPreserveRatio(true);
        } catch (FileNotFoundException var7) {
            System.out.println("f***voiture");
            System.out.println(var7);
        }
    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void move() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001D), this.getImageView());
        if (this.sens == 1) {

            if (this.X + this.l_case > this.scene.getWidth()) {
                trans.setByX(-this.scene.getWidth());
                this.X = 0;
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(this.l_case);
                this.X += this.l_case;
            }
            trans.play();
        }
        else{
            if (this.X - this.l_case < 0) {
                trans.setByX(this.scene.getWidth());
                this.X = this.scene.getWidth();
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(-this.l_case);
                this.X -= this.l_case;
            }
            trans.play();
        }
    }
}
