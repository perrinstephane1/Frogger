package com.company;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;

/**
 * This class represents the human-machine interface
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class Lane {
    protected boolean hostile;
    protected double direction;
    protected double speed;
    protected double taille_obstacle;
    protected double longueur_lane;
    protected double longueur_bloc;
    protected int numero_lane;
    protected double density;
    protected boolean arrivee; // si c'est la dernière ligne c'est FINI
    protected ImageView imageView;
    protected GridPane gridPane = new GridPane();
    protected int type_lane;
    protected Board board;


    /**
     * This method is the constructor
     * @param ii This int is the number given to the com.company.Lane as an ID.
     * @param p This com.company.Board is the one in which the com.company.Lane will be put
     * @param direction This double gives the direction of the com.company.Lane (0 : from right to left. 1 : from left to right)
     * @param speed This double is the speed at which the MovingElement is moving.
     * @param density This double determines the probability of having cars on the com.company.Lane
     * @param type_lane This int characterizes the lane. A type-0 com.company.Lane is a road, a type-1 com.company.Lane is a river and a type-2 com.company.Lane is a safe lane.
     */
    public Lane(int ii, Board p, double direction, double speed, double density, int type_lane) {
        this.board = p;
        this.speed = speed;
        this.direction = direction;
        this.longueur_bloc = p.l_case;
        this.longueur_lane = p.nb_case * p.l_case;
        this.numero_lane = ii;
        this.type_lane = type_lane;

        if (density < 0.33) {
            this.taille_obstacle = 1;
        }
        else if (density < 0.66) {
            this.taille_obstacle = 2;
        }
        else{
            this.taille_obstacle = 3;
        }
        if (this.type_lane == 0){ // If it is a road
            this.setImageView("routemieuxJAUNE.png");
        } else if (this.type_lane == 1){  // If it is a river
            this.setImageView("river.png");
        } else if (this.type_lane == 2){  // If it is a safe lane
            this.setImageView("debut.png");
        } else if (this.type_lane == 3) {
            this.gridPane.add(p.getChrono(), ii, 0);
        }
    }

    /**
     * This method returns sets the image on the game window.
     * @param file This String corresponds to the image file to be showed in the game.
     */
    private void setImageView(String file) {
        try {
            for (int jj = 0; jj < this.board.nb_case; jj++) {
                Image image = new Image(new FileInputStream(file));
                this.imageView = new ImageView(image);
                this.imageView.setFitHeight(this.board.l_case);
                this.imageView.setFitWidth(this.board.l_case);
                this.gridPane.add(this.imageView, jj, this.numero_lane);
            }
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    /**
     * This method returns an array with the Lane parameters
     */
    public List getParametre() {
        return Arrays.asList(this.speed, this.direction, this.taille_obstacle, this.longueur_bloc, this.longueur_lane, this.numero_lane, this.density, this.type_lane);
    }

    /**
     * This method returns an ImageView.
     */
    public GridPane getImageView() {
        return this.gridPane;
    }
}