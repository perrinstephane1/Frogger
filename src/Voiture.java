import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;


public class Voiture extends Truc_mobile {

    public Voiture(Piste piste, Scene scene, int l_case) {
        super(piste, scene, l_case, piste.taille_obstacle);

        if(piste.taille_obstacle == 1.0){
            this.setImageView("car1red.png");
        } else if (piste.taille_obstacle == 2.0){
            this.setImageView("car2red.png");
        } else if (piste.taille_obstacle == 3.0){
            this.setImageView("car3red.png");
        }
    }

    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        trans.play();
    }

}
