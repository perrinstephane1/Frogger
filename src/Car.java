import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;


public class Car extends MovingElements {

    public Car(Lane lane, Scene scene, int l_case) {
        super(lane, scene, l_case, lane.taille_obstacle);

        if(lane.taille_obstacle == 1.0){
            this.setImageView("car11.png");
        } else if (lane.taille_obstacle == 2.0){
            this.setImageView("car22.png");
        } else if (lane.taille_obstacle == 3.0){
            this.setImageView("car33.png");
        }
    }

    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        trans.play();
    }

}
