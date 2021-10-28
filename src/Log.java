import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;


public class Log extends Truc_mobile{
    private boolean hostile;
    protected Piste piste;

    public Log(Piste piste, Scene scene, int l_case) {
        super(piste, scene, l_case, piste.taille_obstacle);
        this.piste = piste;
        this.setImageView("log.png");
    }

    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        trans.play();
    }
}
