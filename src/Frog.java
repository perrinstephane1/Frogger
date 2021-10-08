import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Frog{
    private double X;
    private double Y;
    private Circle c;
    private Scene scene;

    public Frog(double X, double Y, Scene scene) {
        this.X = X;
        this.Y = Y;
        this.c = new Circle(this.X, this.Y, 20);
        this.c.setFill(Color.RED);
        this.scene = scene;
    } // test

    public Circle getC() {
        return c;
    }

    public void up() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans.setByY(-40f);
        if (this.Y-20>0) {
            trans.play();
            this.Y -= 40;
        }
    }

    public void down() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans.setByY(40f);
        if (this.scene.getHeight()>this.Y+20) { //this.scene.getHeight()>this.Y+20
            trans.play();
            this.Y += 40;
        }
    }

    public void left() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans.setByX(-40f);
        if (this.X-20>0) {
            this.X -= 40;
            trans.play();
        }
    }

    public void right() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans.setByX(40f);
        if (this.X+20<this.scene.getWidth()) { //this.X+20>this.scene.getWidth()
            this.X += 40;
            trans.play();
        }
    }


}
