//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import com.sun.xml.internal.bind.v2.model.core.NonElement;
import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.util.concurrent.ThreadLocalRandom;


public class Truc_mobile extends Rectangle {
    protected double taille_obstacle;
    protected double vitesse;
    protected double sens;
    protected boolean hostile;
//    protected double X;
//    protected double Y;
    protected double l_case;
    protected Scene scene;
    protected ImageView imageView;



    public Truc_mobile(Piste piste, Scene scene, int l_case) {
        super(0, 0, l_case, l_case);
//        this.imageView = null;
        int position_min = 0;
        int position_max = (int) piste.longueur_piste;
        int position_start = ThreadLocalRandom.current().nextInt(position_min, position_max + 1);

        this.setLocation(position_start,  (piste.numero_piste-1)*(int)piste.longueur_bloc);
//        this.X = position_start;
//        this.Y = (piste.numero_piste-1)*piste.longueur_bloc;



        this.vitesse = piste.vitesse;
        this.sens = piste.sens;
        this.taille_obstacle = piste.taille_obstacle;
        this.hostile = piste.hostile;
        this.scene = scene;
        this.l_case = l_case* piste.taille_obstacle; //(this.scene.getHeight()/piste.p.nb_pistes)

    }

    public ImageView getImageView() {
        return imageView;
    }

    public void move(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001D), this.getImageView());
        if (this.sens == 1) {

            if (this.getX() >= this.scene.getWidth()) {
                trans.setByX(-this.scene.getWidth()-this.l_case);
                this.setLocation((int)this.getX()-(int)this.l_case, (int)this.getY());
//                this.X = -this.l_case;
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(speed);
                this.setLocation((int)this.getX()+speed, (int)this.getY());
//                this.X += speed;
            }
            trans.play();
        }
        else{
            if (this.getX() + this.l_case < 0) {
                trans.setByX(this.scene.getWidth()+this.l_case);
                this.setLocation((int)this.scene.getWidth(), (int)this.getY());
//                this.X = ;
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(-speed);
                this.setLocation((int)this.getX()-speed, (int)this.getY());

//                this.X -= speed;
            }
            trans.play();
        }
    }


}
