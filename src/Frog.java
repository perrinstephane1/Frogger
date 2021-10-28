import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Frog extends Rectangle {
    private boolean onNenuphar = false;
    private ImageView imageView;
    protected boolean dead;
    private double l_case;
    private int nb_case;
    private int score = 0;
    private int etage = 0;

    public Frog(int X, int Y, int l_case, int nb_case, String img) {
        super(X, Y, l_case, l_case);
        this.dead = false;
        this.l_case = l_case;
        this.nb_case = nb_case;
        try {
            Image image = new Image(new FileInputStream(img));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.getX());
            this.imageView.setY(this.getY());
            imageView.setFitHeight(l_case);
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public Frog(int X, int Y, int l_case, int nb_case) {
        this(X, Y, l_case, nb_case, "frog8bit.png");
    }

    public ImageView getImageView() {
        return imageView;
    }

    public boolean isOnNenuphar() {
        return onNenuphar;
    }

    public void setOnNenuphar(boolean onNenuphar) {
        this.onNenuphar = onNenuphar;
    }

    public void up() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(-this.l_case);
        if (this.getY()-this.l_case>=0) {
            trans.play();
            this.setLocation((int)this.getX(), (int) ((int)this.getY()-this.l_case));
            if (this.score==this.etage) {
                this.score += 1;
            }
            this.etage += 1;
        } else {
            this.dead = true;
        }
    }

    public void down() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(this.l_case);
        if (this.l_case*this.nb_case>this.getY()+this.l_case) {
            trans.play();
            this.setLocation((int)this.getX(), (int) ((int)this.getY()+this.l_case));
            this.etage -= 1;
        } else {
            this.dead = true;
        }
    }

    public void left() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(-this.l_case);
        if (this.getX()-this.l_case>=0) {
            this.setLocation((int) ((int)this.getX()-this.l_case), (int)this.getY());
            trans.play();
        }
    }

    public void right() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(this.l_case);
        if (this.getX()+this.l_case<this.l_case*this.nb_case) {
            this.setLocation((int) ((int)this.getX()+this.l_case), (int)this.getY());
            trans.play();
        }
    }

//    public void auto_down(int speed) {
//        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
//        trans.setByY(speed);
//        this.Y += speed;
//        trans.play();
//    }


}
