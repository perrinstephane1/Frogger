import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.util.Duration;

/**
 * This class represents a car on the board
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Car extends MovingElements {

    /**
     * This method is the constructor
     * @param l_case This int corresponds to the length in pixels of a square on the game windows.
     * @param lane This Lane corresponds to a lane in the game (river, road or safe lane).
     * @param scene This Scene will be used in the class MovingElements to get its height and width for the game parameters
     * */
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
}