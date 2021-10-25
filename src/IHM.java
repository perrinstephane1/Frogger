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
import java.util.concurrent.ThreadLocalRandom;


public class IHM extends Application {
    private double l_case=50;
    private int nb_case=10;
    private int speed_down = 1;
    private int speed_h = 3;
    private Voiture[] voituresIHM = new Voiture[1000000];
    private int compteur_voiture = 0;
    private int compteur_nenuphar = 0;

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

        Frog frog = new Frog((this.nb_case) * this.l_case /2 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case);

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

            }
            if(e.getCode()==KeyCode.LEFT){
                System.out.println(frog.getCoord());
                frog.left();
            }
            this.check_end(frog, primaryStage, the_end);
        };
        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);

        root.getChildren().add(plateau.getGridPane());
        initLog();
        initLog();
        initCar();
        initCar();

        root.getChildren().add(frog.getImageView());

//        new Timer().scheduleAtFixedRate(new TimerTask() {
//            @Override
//            public void run() {
//                addElement();
//                moveElement();
//                showElement();
//            }
//        }, 0, 10000);



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
    public void initCar() {
        Voiture[] voitures = new Voiture[plateau.nb_pistes*10];
        for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
            System.out.println("car init");
            voitures[i-plateau.nb_pistes/2] = new Voiture(plateau.get(i),scene, this.l_case, 1);
            root.getChildren().add(voitures[i-plateau.nb_pistes/2].getImageView());
        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
                    voitures[i-plateau.nb_pistes/2].move(speed_h);
                }
            }
        }, 0, 50);
    }
    public void initLog() {
        Nenuphar[] nenuphars = new Nenuphar[plateau.nb_pistes*10];
        for (int i = 2; i < plateau.nb_pistes/2; i++) { // Start at number 2 because Number 1 is a safe lane
            System.out.println("nenu init");
            nenuphars[i] = new Nenuphar(plateau.get(i),scene, this.l_case, 1);
            root.getChildren().add(nenuphars[i].getImageView());
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
    public void addElement(){

        System.out.println("debut addelement");
        int ii = ThreadLocalRandom.current().nextInt(1, plateau.nb_pistes);
        System.out.println(ii);

        compteur_voiture += 1;

        System.out.println("compteur voigture");
        System.out.println(compteur_voiture);

        voituresIHM[compteur_voiture] = new Voiture(plateau.get(ii),scene, l_case, 1);
        System.out.println("fin de boucle 5000");
    }
    public void showElement(){
        root.getChildren().add(voituresIHM[1].getImageView());
    }
    public void moveElement(){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                voituresIHM[compteur_voiture].move(speed_h);
            }
        }, 0, 50);
    }
}
