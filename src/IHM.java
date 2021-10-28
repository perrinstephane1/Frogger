import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.control.*;
//import java.awt.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;


public class IHM extends Application {
    private int l_case=50;
    private int nb_case=12;
    private int speed_down = 1;
    private int speed_h = 6;
    protected int compteur_voiture = 0;
    protected int compteur_log = 0;
    private Chrono chrono = new Chrono();
    private Voiture[] voitures = new Voiture[10000];
    private Log[] logs = new Log[10000];
    private int difficulte = 1;



    Frog frog = new Frog((this.nb_case) * this.l_case /2 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case);
    Group root = new Group();
    Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*this.nb_case);
    Plateau plateau = new Plateau(root, this.nb_case, this.l_case);


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
                frog.up();
            } else if (e.getCode()==KeyCode.DOWN){
                frog.down();
            } else if (e.getCode()==KeyCode.RIGHT){
                frog.right();
            } else if (e.getCode()==KeyCode.LEFT){
                frog.left();
            } else if (e.getCode()==KeyCode.SPACE) {
                plateau.auto_down(speed_down);
                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
                    this.voitures[i - plateau.nb_pistes / 2].auto_down(speed_down);
                }
            }
        };


        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().add(plateau.getGridPane());

        update_state(primaryStage,frog);
        difficulte = 1;
        if (difficulte ==3){ // expert
            speed_h = 6;
            initLog(logs, 1);
            initCar(voitures, 3);
        }else if (difficulte ==2){ // intermediaire
            speed_h = 4;
            initLog(logs, 2);
            initCar(voitures, 2);
        }else{ // debutant
            speed_h = 3;
            initLog(logs, 2);
            initCar(voitures, 1);
        }

        root.getChildren().add(frog.getImageView());
        primaryStage.setScene(scene);
        primaryStage.show();

    }

//    public void check_end(Frog frog, Stage primaryStage, Scene scene) {
//        if (frog.getX()<0 || frog.getX()>this.l_case*this.nb_case || frog.getY()<0 || frog.getY()>this.l_case*this.nb_case) {
//            primaryStage.setScene(scene);
//        }
//        if (frog.dead) {
//            primaryStage.setScene(scene);
//        }
//    }
//    public void check_end_bien(Stage primaryStage){
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                Platform.runLater(new Runnable() {
//                    @Override
//                    public void run() {
//                        if (frog.getX() < - l_case|| frog.getX() > l_case * nb_case || frog.getY() < 0 || frog.getY() > l_case * nb_case) {
//                            primaryStage.setScene(the_end);
//                        }
//                        if (frog.dead) {
//                            primaryStage.setScene(the_end);
//                        }
//                    }
//                });
//            }
//        }, 0, 100);
//    }
    public void update_state(Stage primaryStage,Frog frog){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int numero_piste = (int) (frog.getY()/l_case);
//                        System.out.println(numero_piste);
                        if (numero_piste ==0){
                            chrono.stop();
                            System.out.println(chrono.getElapsedMilliseconds());
                        }
                        if (plateau.get(numero_piste).type_piste == 1 && !(frog.isOnNenuphar())){ // if the frog is on the river and not on a nenuphar
                            frog.dead = true;
                        }
                        if (frog.getX() < - l_case|| frog.getX() > l_case * nb_case || frog.getY() < 0 || frog.getY() > l_case * nb_case) { //if the frig is out of map
                            primaryStage.setScene(the_end);
                        }
                        if (frog.dead) {
                            primaryStage.setScene(the_end);
                        }
                    }
                });
            }
        }, 0, 100);
    }

    public void initCar(Voiture[] voitures, int nombre_voiture) {
        for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
            for (int j = 0; j<nombre_voiture; j++){
                compteur_voiture += 1;
                voitures[compteur_voiture] = new Voiture(plateau.get(i),scene, this.l_case);
                boolean collisions_car = true;
                while(collisions_car){
                    if (compteur_voiture ==2 && voitures[compteur_voiture-1].intersects(voitures[compteur_voiture])){// checks for collisions
                        voitures[compteur_voiture] = new Voiture(plateau.get(i), scene, this.l_case);
                    }
                    if (compteur_voiture >=3 && (voitures[compteur_voiture-2].intersects(voitures[compteur_voiture]) || voitures[compteur_voiture-1].intersects(voitures[compteur_voiture]) )    ){// checks for collisions
                        voitures[compteur_voiture] = new Voiture(plateau.get(i), scene, this.l_case);
                    }
                    else{
                        collisions_car = false;
                    }
                }
                root.getChildren().add(voitures[compteur_voiture].getImageView());
            }
        }
        new Timer().scheduleAtFixedRate(new TimerTask() { //Refresh actual position
            @Override
            public void run() {
                for (int i = 1; i < compteur_voiture+1; i++) {
                    if (voitures[i].intersects(frog)) {
                        frog.dead = true;
                    }
                }
            }
        }, 0, 100);
        new Timer().scheduleAtFixedRate(new TimerTask() { // Refresh visual position
            @Override
            public void run() {
                for (int i = 1; i < compteur_voiture+1; i++) {
                    voitures[i].move(speed_h);
                }
            }
        }, 0, 50);
    }
    public void initLog(Log[] logs,  int nombre_nenuphar) {
        for (int i = 2; i < plateau.nb_pistes/2 + 1; i++) { // Start at number 2 because Number 1 is a safe lane
            for (int j = 0; j<nombre_nenuphar; j++) {
                compteur_log += 1;
                logs[compteur_log] = new Log(plateau.get(i), scene, this.l_case);
                boolean collisions_nenuphar = true;
                while(collisions_nenuphar){
                    if (compteur_log >=2 && logs[compteur_log-1].intersects(logs[compteur_log])){// checks for collisions
                        logs[compteur_log] = new Log(plateau.get(i), scene, this.l_case);
                    }
                    if (compteur_log >=3 && (logs[compteur_log-2].intersects(logs[compteur_log]) || logs[compteur_log-1].intersects(logs[compteur_log]) )    ){// checks for collisions
                        logs[compteur_log] = new Log(plateau.get(i), scene, this.l_case);
                    }
                    else{
                        collisions_nenuphar = false;
                    }
                }
                root.getChildren().add(logs[compteur_log].getImageView());
            }
        }
        new Timer().scheduleAtFixedRate(new TimerTask() { //Refresh actual position (quicker)
            @Override
            public void run() {
                for (int i = 1; i < compteur_log + 1; i++) {
                    if (logs[i].intersects(frog)) {
                        frog.setOnNenuphar(true);
                    }
                }
            }
        }, 0, 10);
        new Timer().scheduleAtFixedRate(new TimerTask() { // Refresh visual position
            @Override
            public void run() {
                for (int i = 1; i < compteur_log + 1; i++) {
                    logs[i].move(speed_h);
                    int numero_piste = (int) (frog.getY()/l_case);
                    if (logs[i].intersects(frog)) {
                        frog.setOnNenuphar(true);
                        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), frog.getImageView());
                        if (logs[i].piste.sens == 0){
                            trans.setByX(-speed_h);
                            if (frog.getX()>=-50) {
                                frog.setLocation((int)(frog.getX()-speed_h), (int)frog.getY());
                                trans.play();
                            }
                        }
                        else{
                            trans.setByX(speed_h);
                            if (frog.getX() <= scene.getWidth()) {
                                frog.setLocation((int)(frog.getX() + speed_h), (int)frog.getY());
                                trans.play();
                            }
                        }
                    }
                    else if (plateau.get(numero_piste).type_piste == 1){
                        frog.setOnNenuphar(true);
                    }
                }
            }
        }, 0, 50);
    }
    public void addElement(){
//        try {
//            ;
//        } catch (Exception exception) {
//
//            System.out.println("exception in add element");
//            System.out.println(exception);
//        }
        Truc_mobile[] truc_mobiles = new Truc_mobile[100];

        System.out.println("debut addelement");

        int ii = ThreadLocalRandom.current().nextInt(1, 2); //plateau.nb_pistes
        System.out.println("numero voix");
        System.out.println(ii);

        compteur_voiture += 1;
        System.out.println("compteur voigture");
        System.out.println(compteur_voiture);

        truc_mobiles[compteur_voiture] = new Voiture(plateau.get(ii),scene, l_case);
        System.out.println("fin de boucle 5000");

        root.getChildren().add(truc_mobiles[compteur_voiture].getImageView());
        System.out.println("fin de showelement");

        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                truc_mobiles[compteur_voiture].move(speed_h);
            }
//            public void run() {
//                for (int i = 1; i < truc_mobiles.length; i++) {
//                    truc_mobiles[i].move(speed_h);
//                    if (truc_mobiles[i].intersects(frog)) {
//                        System.out.println("colision");
//                        chrono.stop();
//                        System.out.println(chrono.getElapsedMilliseconds());
//                    }
//                }
//            }
        }, 0, 50);
        System.out.println("fin de move_element");
    }
//    public void showElement(){
//        root.getChildren().add(truc_mobiles[1000].getImageView());
//        System.out.println("fin de showelement");
//
//    }
//    public void moveElement(){
//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                truc_mobiles[compteur_voiture].move(speed_h);
//            }
//        }, 0, 50);
//        System.out.println("fin de moveelement");
//
//    }
}
