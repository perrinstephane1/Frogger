package frogger;

import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * This class represents the frog
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Frog extends Rectangle {
    /** True if the frog is on a log and is false otherwise */
    private boolean onLog = false;
    /** ImageView */
    private ImageView imageView;
    /** Length in pixels of a square on the game windows */
    private final double l_case;
    /** Number of lane (river, road, safe lane) composing the game*/
    private final int nb_case;
    /** Score of the player */
    private int score = 0;
    /** Number of the Lane on which the player is */
    private int etage = 0;

    /**
     * This method is the constructor
     * @param X This int corresponds to the first horizontal position of the frog
     * @param Y This int corresponds to the first vertical position of the frog
     * @param l_case This int corresponds to the length in pixels of a square on the game windows
     * @param nb_case This int corresponds to the number of lane (river, road, safe lane) composing the game
     * @param img This String is the name of the image file used to represent the frog in the game
     */
    public Frog(int X, int Y, int l_case, int nb_case, String img) {
        super(X, Y, l_case, l_case);
        this.l_case = l_case;
        this.nb_case = nb_case;
        try {
            Image image = new Image(new FileInputStream(img));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.getX());
            this.imageView.setY(this.getY());
            imageView.setFitHeight(l_case);
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * This method returns an ImageView
     * @return an ImageView
     */
    public ImageView getImageView() {
        return imageView;
    }

    public int getScore() {
        return score;
    }

    public int getEtage() {
        return etage;
    }

    /**
     * This method returns true if the frog is on a log and false otherwise
     * @return true if the frog is on a log and false otherwise
     */
    public boolean isOnLog() {
        return onLog;
    }

    /**
     * This method sets the boolean onLog on true if the frog is on a log and sets it to false otherwise
     * @param onLog This boolean is true if the frog is on a log and is false otherwise
     */
    public void setOnLog(boolean onLog) {
        this.onLog = onLog;
    }

    /**
     * This method moves the frog upwards in the game and update its position
     */
    public void up() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(-this.l_case);
        if (this.getY()-this.l_case>=0) {
            trans.play();
            this.setLocation((int)this.getX(), (int) ((int)this.getY()-this.l_case));
            if (this.score==this.etage) {
                this.score += 1;
            }
            this.etage += 1;
//        } else {
//            this.dead = true;
        }
    }

    /**
     * This method moves the frog downwards in the game and update its position
     */
    public void down() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(this.l_case);
        if (this.l_case*this.nb_case>this.getY()+this.l_case) {
            trans.play();
            this.setLocation((int)this.getX(), (int) ((int)this.getY()+this.l_case));
            if (this.etage>0) {
                this.etage -= 1;
            }
//        } else {
//            this.dead = true;
        }
    }

    /**
     * This method moves the frog to the left in the game and update its position
     */
    public void left() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(-this.l_case);
        if (this.getX()-this.l_case>=0) {
            this.setLocation((int) ((int)this.getX()-this.l_case), (int)this.getY());
            trans.play();
        }
    }

    /**
     * This method moves the frog to the right in the game and update its position
     */
    public void right() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(this.l_case);
        if (this.getX()+this.l_case<this.l_case*this.nb_case) {
            this.setLocation((int) ((int)this.getX()+this.l_case), (int)this.getY());
            trans.play();
        }
    }

    /**
     * This method moves the element downwards at a certain speed with the lanes in infinite mode
     * @param speed This int determmines the speed at which the element will move
     */
    public void auto_down(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        this.setLocation((int)this.getX(), (int)this.getY() + speed);
        trans.play();
    }


}
