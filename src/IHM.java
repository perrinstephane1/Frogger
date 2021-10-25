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

import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;


public class IHM extends Application {
    private int l_case=50;
    private int nb_case=10;
    private int speed_down = 1;
    private int speed_h = 3;
    private Chrono chrono = new Chrono();

    Frog frog = new Frog((this.nb_case) * this.l_case /2 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case);

    Group root = new Group();
    Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*this.nb_case);
    Plateau plateau = new Plateau(root, this.nb_case, this.l_case);
//    Voiture voiture = new Voiture(plateau.get(4),scene, this.l_case);
//    Voiture voiture2 = new Voiture(plateau.get(nb_case),scene, this.l_case);
//    Nenuphar nenuphar = new Nenuphar(plateau.get(3),scene, this.l_case);


    GridPane deadwindow = new GridPane();
    Text deadText = new Text("You're dead !");
    Scene the_end = new Scene(deadwindow, this.l_case*this.nb_case, this.l_case*this.nb_case);

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {

        scene.setFill(Color.web("#81c483"));


        deadwindow.setAlignment(Pos.CENTER);
        deadText.setStyle("-fx-font: normal bold "+this.l_case+"px 'serif' ");
        deadwindow.add(deadText, 0, 0);
        the_end.setFill(Color.web("#d13318"));


        EventHandler<KeyEvent> keyListener = e -> {
            if (!chrono.isRunning) {
                chrono.start();
            }
            if(e.getCode()== KeyCode.UP){
//                System.out.println(frog.getCoord());
                frog.up();
            }
            if(e.getCode()==KeyCode.DOWN){
//                System.out.println(frog.getCoord());
                frog.down();

            }
            if(e.getCode()==KeyCode.RIGHT){
//                System.out.println(frog.getCoord());
                frog.right();

            }
            if(e.getCode()==KeyCode.LEFT){
//                System.out.println(frog.getCoord());
                frog.left();
            }
            this.check_end(frog, primaryStage, the_end);
            System.out.println("Score : "+frog.getScore());
        };


        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);


        root.getChildren().add(plateau.getGridPane());
        root.getChildren().add(frog.getImageView());
//        root.getChildren().add(nenuphar.getImageView());
//        root.getChildren().add(voiture.getImageView());
//        root.getChildren().add(voiture2.getImageView());
        addCar();
//        addLog();

        primaryStage.setScene(scene);
        primaryStage.show();
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//
//                nenuphar.move(speed_h);
//                voiture.move(speed_h);
//                voiture.auto_down(speed_down);
//                voiture2.move(speed_h);
//                voiture2.auto_down(speed_down);
//                plateau.auto_down(speed_down);
//                frog.auto_down(speed_down);
//            }
//        }, 0, 50);
    }


    public void check_end(Frog frog, Stage primaryStage, Scene scene) {
        if (frog.getX()<0 || frog.getX()>this.l_case*this.nb_case || frog.getY()<0 || frog.getY()>this.l_case*this.nb_case) {
            primaryStage.setScene(scene);
        }
//        if (frog.isDead()) {
//            primaryStage.setScene(scene);
//        }
    }

    public void addCar() {

        Voiture[] voitures = new Voiture[plateau.nb_pistes/2];
        for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
//            System.out.println("effkfkfkfkfkfkfkfkfkfkfk");
            voitures[i-plateau.nb_pistes/2] = new Voiture(plateau.get(i),scene, this.l_case);
            root.getChildren().add(voitures[i-plateau.nb_pistes/2].getImageView());

        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
                    voitures[i-plateau.nb_pistes/2].move(speed_h);
//                    System.out.println(frog.getLocation());
                    if (voitures[i-plateau.nb_pistes/2].intersects(frog)) {
                        System.out.println("colision");
                        chrono.stop();
                        System.out.println(chrono.getElapsedMilliseconds());

                    }
                }
            }
        }, 0, 50);
    }

    public void addLog() {

        Nenuphar[] nenuphars = new Nenuphar[plateau.nb_pistes/2];
        for (int i = 2; i < plateau.nb_pistes/2; i++) { // Start at number 2 because Number 1 is a safe lane
            System.out.println("nenu nenu");
            nenuphars[i] = new Nenuphar(plateau.get(i),scene, this.l_case);
//            root.getChildren().add(nenuphars[i].getImageView());

        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 2; i < plateau.nb_pistes/2; i++) {
                    nenuphars[i].move(speed_h);
                }
            }
        }, 0, 50);
    }

}
