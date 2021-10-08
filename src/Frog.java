import javafx.animation.TranslateTransition;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;


public class Frog{
    private double X;
    private double Y;
    private Circle c;

    public Frog(double X, double Y) {
        this.X = X;
        this.Y = Y;
        this.c = new Circle(this.X, this.Y, 20);
        this.c.setFill(Color.RED);
    }

    public Circle getC() {
        return c;
    }

    public void up() {
        TranslateTransition trans_up = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans_up.setByY(-10f);
        trans_up.play();
    }

    public void down() {
        TranslateTransition trans_up = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans_up.setByY(10f);
        trans_up.play();
    }

    public void left() {
        TranslateTransition trans_up = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans_up.setByX(-10f);
        trans_up.play();
    }

    public void right() {
        TranslateTransition trans_up = new TranslateTransition(Duration.seconds(0.001), this.getC());
        trans_up.setByX(10f);
        trans_up.play();
    }


}
