import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;


public class Truc_mobile extends Rectangle {
    protected double taille_obstacle;
    protected double vitesse;
    protected double sens;
    protected boolean hostile;
    protected double l_case;
    protected Scene scene;
    protected int in_plateau = 1; //1 : sur le plateau, 0 : out
    protected Piste piste;
    protected ImageView imageView;




    public Truc_mobile(Piste piste, Scene scene, int l_case, double taille_obstacle) {
        super(0, 0, (int) (taille_obstacle*l_case), l_case);
        int position_min = 0;
        int position_max = (int) piste.longueur_piste;
        int position_start = ThreadLocalRandom.current().nextInt(position_min, position_max + 1);

        this.setLocation(position_start,  (piste.numero_piste-1)*(int)piste.longueur_bloc);

        this.in_plateau = in_plateau;
        this.piste = piste;

        this.vitesse = piste.vitesse;
        this.sens = piste.sens;
        this.taille_obstacle = piste.taille_obstacle;
        this.hostile = piste.hostile;
        this.scene = scene;
        this.l_case = l_case; //(this.scene.getHeight()/piste.p.nb_pistes)
    }

    public void setImageView(String file) {
        try {
            Image image = new Image(new FileInputStream(file));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.getX());
            this.imageView.setY(this.getY());
            this.imageView.setFitWidth(this.l_case * piste.taille_obstacle);
            this.imageView.setFitHeight(this.l_case);
            this.imageView.setPreserveRatio(false);
        } catch (FileNotFoundException var7) {
            System.out.println("No picture found.");
            System.out.println(var7);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void move(int speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        if (this.sens == 1) {

            if (this.getX() >= this.scene.getWidth()) {
                trans.setByX(-this.scene.getWidth()-this.l_case);
                this.setLocation((int)this.getX()-(int)this.scene.getWidth()-(int)this.l_case, (int)this.getY());
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(speed);
                this.setLocation((int)this.getX()+speed, (int)this.getY());
            }
            trans.play();
        }
        else{
            if (this.getX() + this.l_case < 0) {
                trans.setByX(this.scene.getWidth()+this.l_case);
                this.setLocation((int)this.scene.getWidth(), (int)this.getY());
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(-speed);
                this.setLocation((int)this.getX()-speed, (int)this.getY());
            }
            trans.play();
        }
    }


    public void auto_down(int speed) {

        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(speed);
        trans.play();

    }

}
