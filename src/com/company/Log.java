package com.company;

import javafx.scene.Scene;

/**
 * This class represents the logs on which the frog can be
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Log extends MovingElements {
    protected Lane lane;

    /**
     * This method is the constructor
     * @param l_case This int corresponds to the length in pixels of a square on the game windows.
     * @param lane This com.company.Lane corresponds to a lane in the game (river, road or safe lane).
     * @param scene This Scene will be used in the class com.company.MovingElements to get its height and width for the game parameters
     * */
    public Log(Lane lane, Scene scene, int l_case) {
        super(lane, scene, l_case, lane.taille_obstacle);
        this.lane = lane;
        if(lane.taille_obstacle == 1.0){
            this.setImageView("nenuphar.png");
        } else if (lane.taille_obstacle == 2.0){
            this.setImageView("log22.png");
        } else if (lane.taille_obstacle == 3.0){
            this.setImageView("log.png");
        }
    }
}
