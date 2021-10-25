import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Frog{
    private double X;
    private double Y;
    private ImageView imageView;
    private boolean dead;
    private double l_case;
    private int nb_case;

    public Frog(double X, double Y, double l_case, int nb_case) {
        this.dead = false;
        this.X = X;
        this.Y = Y;
        this.l_case = l_case;
        this.nb_case = nb_case;
        try {
            Image image = new Image(new FileInputStream("froggergreen.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.X);
            this.imageView.setY(this.Y);
            imageView.setFitHeight(l_case);
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void up() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(-this.l_case);
        if (this.Y-this.l_case>=0) {
            trans.play();
            this.Y -= this.l_case;
        } else {
            this.dead = true;
        }
    }

    public void down() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(this.l_case);
        if (this.l_case*this.nb_case>this.Y+this.l_case) {
            trans.play();
            this.Y += this.l_case;
        } else {
            this.dead = true;
        }
    }

    public void left() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(-this.l_case);
        if (this.X-this.l_case>=0) {
            this.X -= this.l_case;
            trans.play();
        } else {
            this.dead = true;
        }
    }

    public void right() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(this.l_case);
        if (this.X+this.l_case<this.l_case*this.nb_case) {
            this.X += this.l_case;
            trans.play();
        } else {
            this.dead = true;
        }
    }

    public String getCoord(){
        return this.X+", "+this.Y+", "+this.dead;
    }

    public boolean isDead() {
        return dead;
    }

    public void setDead(boolean dead) {
        this.dead = dead;
    }

    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        this.Y += speed;
        trans.play();
    }

    public double getX() {
        return X;
    }

    public double getY() {
        return Y;
    }
}
