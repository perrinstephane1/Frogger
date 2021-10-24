import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;


public class Voiture extends Truc_mobile {
    private ImageView imageView;

    public Voiture(Piste piste, Scene scene, double l_case) {
        super(piste, scene, l_case);

        if(piste.taille_obstacle == 1.0){
            System.out.println(piste.taille_obstacle);
            try {
                Image image = new Image(new FileInputStream("car1red.png"));
                this.imageView = new ImageView(image);
                this.imageView.setX(this.X);
                this.imageView.setY(this.Y);
                this.imageView.setFitWidth(piste.taille_obstacle*this.l_case);
                this.imageView.setFitHeight(this.l_case);
                this.imageView.setPreserveRatio(false);
            } catch (FileNotFoundException var7) {
                System.out.println("No picture found.");
                System.out.println(var7);
            }
        }

        if(piste.taille_obstacle == 2.0){
            System.out.println(piste.taille_obstacle);
            try {
                Image image = new Image(new FileInputStream("car2red.png"));
                this.imageView = new ImageView(image);
                this.imageView.setX(this.X);
                this.imageView.setY(this.Y);
                this.imageView.setFitWidth(this.l_case);
                this.imageView.setFitHeight(this.l_case/piste.taille_obstacle);
                this.imageView.setPreserveRatio(false);
            } catch (FileNotFoundException var7) {
                System.out.println("No picture found.");
                System.out.println(var7);
            }
        }

        if(piste.taille_obstacle == 3.0){
            System.out.println(piste.taille_obstacle);
            try {
                Image image = new Image(new FileInputStream("car3red.png"));
                this.imageView = new ImageView(image);
                this.imageView.setX(this.X);
                this.imageView.setY(this.Y);
                this.imageView.setFitWidth(piste.taille_obstacle*this.l_case);
                this.imageView.setFitHeight(this.l_case);
                this.imageView.setPreserveRatio(false);
            } catch (FileNotFoundException var7) {
                System.out.println("No picture found.");
                System.out.println(var7);
            }
        }





    }

    public ImageView getImageView() {
        return this.imageView;
    }

    public void move(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001D), this.getImageView());
        if (this.sens == 1) {

            if (this.X + this.l_case > this.scene.getWidth()) {
                trans.setByX(-this.scene.getWidth());
                this.X = 0;
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(speed);
                this.X += speed;
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
                trans.setByX(-speed);
                this.X -= speed;
            }
            trans.play();
        }
    }

    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        trans.play();
    }
}
