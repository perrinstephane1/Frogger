import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;


public class Log extends Truc_mobile{
    protected Piste piste;

    public Log(Piste piste, Scene scene, int l_case) {
        super(piste, scene, l_case, piste.taille_obstacle);
        this.piste = piste;
        if(piste.taille_obstacle == 1.0){
            this.setImageView("nenuphar.png");
        } else if (piste.taille_obstacle == 2.0){
            this.setImageView("log22.png");
        } else if (piste.taille_obstacle == 3.0){
            this.setImageView("log.png");
        }
    }

    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        trans.play();
    }
}
