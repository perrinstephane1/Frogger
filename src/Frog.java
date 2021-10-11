import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Frog{
    private double X;
    private double Y;
    private Scene scene;
    private ImageView imageView;
    private double height;

    public Frog(double X, double Y, Scene scene) {
        this.X = X;
        this.Y = Y;
        this.scene = scene;
        this.height = this.scene.getHeight()/10;
        try {
            Image image = new Image(new FileInputStream("C:\\Users\\Williams HOARAU\\Desktop\\Travail\\Année 2\\POO\\images pour le projet\\arnaud.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(0);
            this.imageView.setY(0);
            imageView.setFitHeight(this.height);
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            System.out.println("f***");
            System.out.println(e);
        };
//
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void up() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(-this.height);
        if (this.Y-this.height>0) {
            trans.play();
            this.Y -= this.height;
        }
    }

    public void down() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(this.height);
        if (this.scene.getHeight()>this.Y+this.height) { //this.scene.getHeight()>this.Y+20
            trans.play();
            this.Y += this.height;
        }
    }

    public void left() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(-this.height);
        if (this.X-this.height>0) {
            this.X -= this.height;
            trans.play();
        }
    }

    public void right() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(this.height);
        if (this.X+this.height<this.scene.getWidth()) { //this.X+20>this.scene.getWidth()
            this.X += this.height;
            trans.play();
        }
    }


}
