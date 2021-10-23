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
    private int nb_case=15;
    Group root = new Group();
    Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*this.nb_case);
    Plateau plateau = new Plateau(root, this.nb_case, this.l_case, this.l_case*this.nb_case);
    Voiture voiture = new Voiture(plateau.get(1),scene, this.l_case);

    public static void main(String[] args) {
        launch(args);
    }




    @Override
    public void start(Stage primaryStage) {


        scene.setFill(Color.web("#81c483"));

        GridPane deadwindow = new GridPane();
        deadwindow.setAlignment(Pos.CENTER);
        Text deadText = new Text("You're dead !");
        deadText.setStyle("-fx-font: normal bold "+this.l_case+"px 'serif' ");
        deadwindow.add(deadText, 0, 0);
        Scene the_end = new Scene(deadwindow, this.l_case*this.nb_case, this.l_case*this.nb_case);
        the_end.setFill(Color.web("#d13318"));


        Frog frog = new Frog(0, 0, this.l_case, this.nb_case);


        Timer timer = new Timer(1000, down);
        timer.start();

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

                voiture.move();
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
                voiture.move();
            }
        }, 0, 500);


    }

    ActionListener down = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            TranslateTransition transPlateau = new TranslateTransition(Duration.seconds(0.001), plateau.getGridPane());
            TranslateTransition transVoiture = new TranslateTransition(Duration.seconds(0.001), voiture.getImageView());
            transPlateau.setByY(40);
            transPlateau.play();
            transVoiture.setByY(40);
            transVoiture.play();


        }
    };


    public void check_end(Frog frog, Stage primaryStage, Scene scene) {
        if (frog.isDead()) {
            primaryStage.setScene(scene);
        }
    }


}
