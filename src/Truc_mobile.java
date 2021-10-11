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
    protected double height;
    protected Scene scene;
    private ImageView imageView;


    public Truc_mobile(Piste piste, Scene scene) {
        this.X = 0; //TODO changer le point d apparition
        this.Y = 30; //TODO en fonction de la piste
        this.vitesse = piste.vitesse;
        this.sens = piste.sens;
        this.taille_obstacle = piste.taille_obstacle;
        this.hostile = piste.hostile;
        this.scene = scene;
        this.height = (this.scene.getHeight()/10)* piste.taille_obstacle;

    }
    public ImageView getImageView() {
        return this.imageView;
    }

    public void move() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001D), this.getImageView());
        if (this.sens == 1) {
            trans.setByX(this.height);
            trans.play();
            if (this.X + this.height > this.scene.getWidth()) {
                this.X = 0; // if the object goes off the scene, its position is re-init
            }
        }
        else{
            trans.setByX(-this.height);
            trans.play();
            if (this.X - this.height < 0) {
                this.X = this.height; // if the object goes off the scene, its position is re-init
            }
        }
    }
}
