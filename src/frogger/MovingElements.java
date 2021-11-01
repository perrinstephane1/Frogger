package frogger;

import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;

/**
 * This class represents a moving element on the board
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class MovingElements extends Rectangle {
    /** Size of obstacles on a Lane*/
    protected final double obstacle_size;
    /** Direction of obstacles on a Lane*/
    protected final double direction;
    /** Length in pixels of a square on the game windows */
    protected final double l_case;
    /** Scene */
    protected final Scene scene;
    /** Lane in the game (river, road or safe lane) */
    protected final Lane lane;
    /** ImageView */
    protected ImageView imageView;


    /**
     * This method is the constructor
     * @param l_case This int corresponds to the length in pixels of a square on the game windows.
     * @param lane This Lane corresponds to a lane in the game (river, road or safe lane).
     * @param scene This Scene will be used in the class MovingElements to get its height and width for the game parameters
     * @param obstacle_size This double is the length of a MovingElement (1, 2 or 3).
     */
    public MovingElements(Lane lane, Scene scene, int l_case, double obstacle_size) {
        super(0, 0, (int) (obstacle_size*l_case), l_case);
        int position_min = 0;
        int position_max = (int) lane.longueur_lane;
        int position_start = ThreadLocalRandom.current().nextInt(position_min, position_max + 1);

        this.setLocation(position_start,  (lane.numero_lane-1)*(int) lane.l_case);

        this.lane = lane;
        this.direction = lane.direction;
        this.obstacle_size = lane.obstacle_size;
        this.scene = scene;
        this.l_case = l_case; //(this.scene.getHeight()/lane.p.nb_case)
    }

    /**
     * This method returns sets the image on the game window.
     * @param file This String corresponds to the image file to be showed in the game.
     */
    public void setImageView(String file) {
        try {
            String adresse = file;
            if (this.lane.direction == 0) {
                adresse = file.replace(".png", "_r.png");
            }
            Image image = new Image(new FileInputStream(adresse));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.getX());
            this.imageView.setY(this.getY());
            this.imageView.setFitWidth(this.l_case * lane.obstacle_size + 10);
            this.imageView.setFitHeight(this.l_case);
            this.imageView.setPreserveRatio(false);
        } catch (FileNotFoundException var7) {
            System.out.println("No picture found.");
            System.out.println(var7);
        }
    }

    /**
     * This method returns an ImageView.
     * @return an ImageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    /**
     * This method moves the element on the side regarding the direction of the Lane. If the element goes out of the window, it goes back to the beginning.
      * @param speed This double is the speed at which the MovingElement is moving.
     */
    public void move(double speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        if (this.direction == 1) {

            if (this.getX() >= this.scene.getWidth()) {
                // if the object goes off the scene, its position is re-init
                trans.setByX(-this.scene.getWidth()-this.obstacle_size*this.l_case);
                this.setLocation((int) (this.getX()-this.scene.getWidth()-this.obstacle_size*this.l_case), (int)this.getY());
            }
            else{// if the object is still on the window, it can continue to move
                trans.setByX(speed);
                this.setLocation((int) (this.getX()+speed), (int)this.getY());
            }
        }
        else{
            if (this.getX() + this.obstacle_size*this.l_case < 0) {
                // if the object goes off the scene, its position is re-init
                trans.setByX(this.scene.getWidth()+this.obstacle_size*this.l_case);
                this.setLocation((int)this.scene.getWidth(), (int)this.getY());
            }
            else{ // if the object is still on the window, it can continue to move
                trans.setByX(-speed);
                this.setLocation((int) (this.getX()-speed), (int)this.getY());
            }
        }
        trans.play();
    }

    /**
     * This method moves the element downwards at a certain speed with the lanes in infinite mode.
     * @param speed This int determmines the speed at which the element will move.
     */
    public void auto_down(int speed) {

        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        trans.play();

    }

}
