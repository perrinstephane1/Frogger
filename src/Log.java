import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 * This class represents the logs on which the frog can be
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Log extends Truc_mobile{
    protected Piste piste;

    /**
     * This method is the constructor
     * @param l_case This int corresponds to the length in pixels of a square on the game windows.
     * @param piste This Piste corresponds to a lane in the game (river, road or safe lane).
     * @param scene This Scene will be used in the class Truc_mobile to get its height and width for the game parameters
     * */ // TODO changer le nom de la classe Truc_mobile
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
}
