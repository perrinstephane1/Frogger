import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.TimerTask;
import java.util.Timer;
import java.util.concurrent.ThreadLocalRandom;


public class IHM extends Application {
    private int l_case=50;
    private int nb_case=10  ;
    private int speed_down = 1;
    private int speed_h = 3;
    protected int compteur_voiture = 100;
    protected int compteur_nenuphar = 0;
    private Chrono chrono = new Chrono();

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
            } else if (e.getCode()==KeyCode.SPACE) {
                plateau.auto_down(speed_down);
//                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
//                    this.voitures[i - plateau.nb_pistes / 2].auto_down(speed_down);
//                }
            }
            this.check_end(frog, primaryStage, the_end);
        };


        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);


        root.getChildren().add(plateau.getGridPane());
        initLog();
//        initLog();
//        initLog();
        initCar();
        initCar();
        initCar();

        root.getChildren().add(frog.getImageView());


        primaryStage.setScene(scene);
        primaryStage.show();

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
            voitures[i-plateau.nb_pistes/2] = new Voiture(plateau.get(i),scene, this.l_case);
            root.getChildren().add(voitures[i-plateau.nb_pistes/2].getImageView());

        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
                    voitures[i-plateau.nb_pistes/2].move(speed_h);
                    if (voitures[i-plateau.nb_pistes/2].intersects(frog)) {
                        frog.dead = true;
                        System.out.println("colision");
                        chrono.stop();
//                        System.out.println(chrono.getElapsedMilliseconds());

                    }
                }
            }
        }, 0, 50);
    }
    public void initLog() {
        Nenuphar[] nenuphars = new Nenuphar[plateau.nb_pistes*10];
        for (int i = 2; i < plateau.nb_pistes/2; i++) { // Start at number 2 because Number 1 is a safe lane
            System.out.println("nenu init");
            nenuphars[i] = new Nenuphar(plateau.get(i),scene, this.l_case);
            root.getChildren().add(nenuphars[i].getImageView());
        }
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                for (int i = 2; i < plateau.nb_pistes/2; i++) {
                    nenuphars[i].move(speed_h);
                    if (nenuphars[i].intersects(frog)) {
                        TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), frog.getImageView());
                        if (nenuphars[i].piste.sens == 0){
                            trans.setByX(-speed_h);
                            if (frog.getX()-l_case>=-50) {
                                frog.setLocation((int)(frog.getX()-speed_h), (int)frog.getY());
                                trans.play();
                            } else {
                                frog.dead = true;
//                                System.out.println("dans le else du nenuphar");
//                                System.out.println(frog.getX());
                            }
                        }
                        else{
                            trans.setByX(speed_h);
                            if (frog.getX() <= scene.getWidth()) {
                                frog.setLocation((int)(frog.getX() + speed_h), (int)frog.getY());
                                trans.play();
                            } else {
                                frog.dead = true;
//                                System.out.println("dans le else du nenuphar");
//                                System.out.println(frog.getX());
                            }
                        }
//                        chrono.stop();
//                        System.out.println(chrono.getElapsedMilliseconds());

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
        System.out.println("fin de moveelement");

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
