//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Truc_mobile {
    protected double taille_obstacle;
    protected double vitesse;
    protected double sens;
    protected boolean hostile;
    protected double X;
    protected double Y;
    protected double l_case;
    protected Scene scene;
    private ImageView imageView;



    public Truc_mobile(Piste piste, Scene scene, double l_case) {
        this.X = 0; //TODO changer le point d apparition
        this.Y = 0; //TODO en fonction de la piste



        this.vitesse = piste.vitesse;
        this.sens = piste.sens;
        this.taille_obstacle = piste.taille_obstacle;
        this.hostile = piste.hostile;
        this.scene = scene;
        this.l_case = l_case* piste.taille_obstacle; //(this.scene.getHeight()/piste.p.nb_pistes)

    }

}
