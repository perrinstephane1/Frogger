import javafx.animation.TranslateTransition;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.concurrent.ThreadLocalRandom;


public class MovingElements extends Rectangle {
    protected double taille_obstacle;
    protected double vitesse;
    protected double sens;
    protected boolean hostile;
    protected double l_case;
    protected Scene scene;
    protected int in_plateau = 1; //1 : sur le board, 0 : out
    protected Lane lane;
    protected ImageView imageView;




    public MovingElements(Lane lane, Scene scene, int l_case, double taille_obstacle) {
        super(0, 0, (int) (taille_obstacle*l_case), l_case);
        int position_min = 0;
        int position_max = (int) lane.longueur_lane;
        int position_start = ThreadLocalRandom.current().nextInt(position_min, position_max + 1);

        this.setLocation(position_start,  (lane.numero_lane-1)*(int) lane.longueur_bloc);

        this.in_plateau = in_plateau;
        this.lane = lane;
        this.sens = lane.sens;
        this.taille_obstacle = lane.taille_obstacle;
        this.scene = scene;
        this.l_case = l_case; //(this.scene.getHeight()/lane.p.nb_case)
    }

    public void setImageView(String file) {
        try {
            String adresse = file;
            if (this.lane.sens == 0) {
                adresse = file.replace(".png", "_r.png");
//                System.out.println(adresse);
            }
            Image image = new Image(new FileInputStream(adresse));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.getX());
            this.imageView.setY(this.getY());
            this.imageView.setFitWidth(this.l_case * lane.taille_obstacle + 10);
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

    public void move(double speed) {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        if (this.sens == 1) {

            if (this.getX() >= this.scene.getWidth()) {
                trans.setByX(-this.scene.getWidth()-this.taille_obstacle*this.l_case);
                this.setLocation((int) (this.getX()-this.scene.getWidth()-this.taille_obstacle*this.l_case), (int)this.getY());
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(speed);
                this.setLocation((int) (this.getX()+speed), (int)this.getY());
            }
            trans.play();
        }
        else{
            if (this.getX() + this.taille_obstacle*this.l_case < 0) {
                trans.setByX(this.scene.getWidth()+this.taille_obstacle*this.l_case);
                this.setLocation((int)this.scene.getWidth(), (int)this.getY());
                // if the object goes off the scene, its position is re-init
            }
            else{
                trans.setByX(-speed);
                this.setLocation((int) (this.getX()-speed), (int)this.getY());
            }
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
        trans.play();

    }

}
