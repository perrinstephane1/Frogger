//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javafx.scene.Scene;
import javafx.scene.image.ImageView;


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
        this.X = 30; //TODO changer le point d apparition
        this.Y = 30; //TODO en fonction de la piste
        this.vitesse = piste.vitesse;
        this.sens = piste.sens;
        this.taille_obstacle = piste.taille_obstacle;
        this.hostile = piste.hostile;
        this.scene = scene;
        this.height = (this.scene.getHeight()/10)* piste.taille_obstacle;

    }

}
