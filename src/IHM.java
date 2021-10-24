import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import java.sql.Time;
import java.util.Date;
import java.util.TimerTask;
import java.util.Timer;
import java.util.TimerTask;



public class IHM extends Application {
    private double l_case=70;
    private int nb_case=12;
    private int speed_down = 1;
    private int speed_h = 3;




    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*this.nb_case);
        Plateau plateau = new Plateau(root, this.nb_case, this.l_case);
        Voiture voiture = new Voiture(plateau.get(1),scene, this.l_case);
        scene.setFill(Color.web("#81c483"));

        GridPane deadwindow = new GridPane();
        Text deadText = new Text("You're dead !");
        Scene the_end = new Scene(deadwindow, this.l_case*this.nb_case, this.l_case*this.nb_case);
        deadwindow.setAlignment(Pos.CENTER);
        deadText.setStyle("-fx-font: normal bold "+this.l_case+"px 'serif' ");
        deadwindow.add(deadText, 0, 0);
        the_end.setFill(Color.web("#d13318"));


        Frog frog = new Frog(0, 0, this.l_case, this.nb_case);


        EventHandler<KeyEvent> keyListener = e -> {
            if(e.getCode()== KeyCode.UP){
                System.out.println(frog.getCoord());
                frog.up();
            }
            if(e.getCode()==KeyCode.DOWN){
                System.out.println(frog.getCoord());
                frog.down();

            }
            if(e.getCode()==KeyCode.RIGHT){
                System.out.println(frog.getCoord());
                frog.right();
                voiture.setSize((int)this.l_case, (int)this.l_case);
                frog.setSize((int)this.l_case, (int)this.l_case);
                System.out.println(voiture.getSize());
                System.out.println(voiture.intersects(frog));
            }
            if(e.getCode()==KeyCode.LEFT){
                System.out.println(frog.getCoord());
                frog.left();
            }
            this.check_end(frog, primaryStage, the_end);
        };


        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);

        root.getChildren().add(plateau.getGridPane());
        root.getChildren().add(frog.getImageView());
        root.getChildren().add(voiture.getImageView());

        primaryStage.setScene(scene);
        primaryStage.show();
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                voiture.move(speed_h);

                plateau.auto_down(speed_down);
                voiture.auto_down(speed_down);
                frog.auto_down(speed_down);
            }
        }, 0, 50);


    }


    public void check_end(Frog frog, Stage primaryStage, Scene scene) {
        if (frog.getX()<0 || frog.getX()>this.l_case*this.nb_case || frog.getY()<0 || frog.getY()>this.l_case*this.nb_case) {
            primaryStage.setScene(scene);
        }
//        if (frog.isDead()) {
//            primaryStage.setScene(scene);
//        }
    }


}
