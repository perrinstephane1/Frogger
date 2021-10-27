import javafx.animation.TranslateTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;

import java.awt.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;


public class Frog extends Rectangle {
//    private double X;
//    private double Y;
    private ImageView imageView;
    protected boolean dead;
    private int l_case;
    private int nb_case;
    private int score = 0;
    private int etage = 0;

    public Frog(int X, int Y, int l_case, int nb_case) {
        super(X, Y, l_case, l_case);
        this.dead = false;
//        this.X = X;
//        this.Y = Y;
        this.l_case = l_case;
        this.nb_case = nb_case;
        try {
            Image image = new Image(new FileInputStream("froggergreen.png"));
            this.imageView = new ImageView(image);
            this.imageView.setX(this.getX());
            this.imageView.setY(this.getY());
            imageView.setFitHeight(l_case);
            imageView.setPreserveRatio(true);
        } catch (FileNotFoundException e) {
            System.out.println(e);
        }
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void up() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByY(-this.l_case);
        if (this.getY()-this.l_case>=0) {
            trans.play();
            this.setLocation((int)this.getX(), (int)this.getY()-this.l_case);
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
            this.setLocation((int)this.getX(), (int)this.getY()+this.l_case);
            this.etage -= 1;
        } else {
            this.dead = true;
        }
    }

    public void left() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(-this.l_case);
        if (this.getX()-this.l_case>=0) {
            this.setLocation((int)this.getX()-this.l_case, (int)this.getY());
            trans.play();
        } else {
            this.dead = true;
        }
    }

    public void right() {
        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
        trans.setByX(this.l_case);
        if (this.getX()+this.l_case<this.l_case*this.nb_case) {
            this.setLocation((int)this.getX()+this.l_case, (int)this.getY());
            trans.play();
        } else {
            this.dead = true;
        }
    }

    public int getScore() {
        return score;
    }
    //    public String getCoord(){
//        return this.X+", "+this.Y+", "+this.dead;
//    }

//    public boolean isDead() {
//        return dead;
//    }
//
//    public void setDead(boolean dead) {
//        this.dead = dead;
//    }
//
//    public void auto_down(int speed) {
//        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), this.getImageView());
//        trans.setByY(speed);
//        this.Y += speed;
//        trans.play();
//    }
//
//    public double getX() {
//        return X;
//    }
//
//    public double getY() {
//        return Y;
//    }
}
