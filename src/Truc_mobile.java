//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import javafx.scene.Scene;
import javafx.scene.image.ImageView;

import java.util.concurrent.ThreadLocalRandom;


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


        int position_min = 0;
        int position_max = (int) piste.longueur_piste;
        int position_start = ThreadLocalRandom.current().nextInt(position_min, position_max + 1);

        this.X = position_start; //TODO changer le point d apparition
        this.Y = (piste.numero_piste-1)*piste.longueur_bloc; //TODO en fonction de la piste



        this.vitesse = piste.vitesse;
        this.sens = piste.sens;
        this.taille_obstacle = piste.taille_obstacle;
        this.hostile = piste.hostile;
        this.scene = scene;
        this.l_case = l_case* piste.taille_obstacle; //(this.scene.getHeight()/piste.p.nb_pistes)

    }

}
