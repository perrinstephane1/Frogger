//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.awt.*;


public class Truc_mobile extends Rectangle {
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
