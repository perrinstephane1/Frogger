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
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ThreadLocalRandom;



public class IHM extends Application {
    private int l_case=50;
    private int nb_case=10;
    private int speed_down = 1;
    private int speed_h = 3;
    protected int compteur_voiture = 100;
    protected int compteur_nenuphar = 0;
    private Chrono chrono = new Chrono();
    private Voiture[] voitures;

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

    private void generate_menu() {
        MenuItem pseudo1 = new MenuItem("Pseudo 1");
        MenuItem pseudo2= new MenuItem("Pseudo 2");
        MenuItem nbre_routes = new MenuItem("Nombre de routes");
        MenuItem nbre_riviere=new MenuItem("Largeur de la rivière");
        MenuItem deb = new MenuItem("Débutant");
        MenuItem inter = new MenuItem("Intermédiaire");
        MenuItem exp = new MenuItem("Expert");
        MenuItem fini = new MenuItem("Fini");
        MenuItem infini = new MenuItem("Infini");
        MenuItem un_joueur = new MenuItem("1 joueur");
        MenuItem deux_joueurs = new MenuItem("2 joueurs");
        MenuItem bs = new MenuItem("Meilleurs scores");
        MenuItem pause = new MenuItem("Pause");
        SeparatorMenuItem separator= new SeparatorMenuItem();
        SeparatorMenuItem separator2= new SeparatorMenuItem();
        MenuItem quitter = new MenuItem("Quitter");
        Menu menu1 = new Menu("Paramètres");
        Menu menu2 = new Menu("Difficulté");
        Menu menu3 = new Menu("Mode de jeu");
        Menu menu4 = new Menu("Panthéon");
        Menu menu5 = new Menu("Jeu");
        menu1.getItems().addAll(pseudo1,pseudo2, nbre_routes,nbre_riviere);
        menu2.getItems().addAll(deb,inter,exp);
        menu3.getItems().addAll(un_joueur,deux_joueurs,separator2,fini,infini);
        menu4.getItems().addAll(bs);
        menu5.getItems().addAll(pause, separator,quitter);
        MenuBar menuBar= new MenuBar(menu1,menu2,menu5,menu3,menu4);
        this.plateau.getGridPane().getChildren().add(menuBar);

        pause.setAccelerator(KeyCombination.keyCombination(("P")));
        pause.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO faire un système de pause
                System.out.print("on doit faire pause");
            }
        });
        // action pour quitter
        quitter.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        quitter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.exit(0);
            }
        });
        // action des meilleurs scores
        bs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO afficher les meilleurs scores dans les scores existants
                GridPane meilleurs_scores = new GridPane();
                meilleurs_scores.setPadding(new javafx.geometry.Insets(10,10,10,10));
                meilleurs_scores.setHgap(6);
                meilleurs_scores.setVgap(2);
                Label r1 = new Label("Pi air qui roule");
                Label r2 = new Label("Harry");
                Label r3 = new Label("Wiwi");
                Label r4 = new Label("n'amasse pas mousse");
                Label s1 = new Label("1000");
                Label s2 = new Label("950");
                Label s3 = new Label("456");
                Label s4 = new Label("0");
                Label joueur = new Label("Joueur");
                Label score = new Label("Score");
                meilleurs_scores.add(joueur,0,0);
                meilleurs_scores.add(score,1,0);
                meilleurs_scores.add(r1,0,1);
                meilleurs_scores.add(r2,0,2);
                meilleurs_scores.add(r3,0,3);
                meilleurs_scores.add(r4,0,4);
                meilleurs_scores.add(s1,1,1);
                meilleurs_scores.add(s2,1,2);
                meilleurs_scores.add(s3,1,3);
                meilleurs_scores.add(s4,1,4);
                Stage stage = new Stage();
                stage.setTitle("Best scores");
                stage.setScene(new Scene(meilleurs_scores, 450, 450));
                stage.show();
            }
        });
        // action pour changer le nom des joueurs
        pseudo1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label nameLabel = new Label("Nom du joueur 1");
                TextField pseudo = new TextField("Entrez votre pseudo");
                Button OKbutton= new Button("OK");
                GridPane gridPane = new GridPane();
                // setting the gridPane
                gridPane.setMinSize(100,200);
                gridPane.setPadding(new javafx.geometry.Insets(3,3,3,3));
                gridPane.setHgap(2);
                gridPane.setVgap(10);
                //gridPane.setAlignment(Pos.CENTER);
                gridPane.add(nameLabel,0,3);
                gridPane.add(pseudo,1,3);
                gridPane.add(OKbutton,2,3);
                Stage stage = new Stage();
                stage.setTitle("Changement de nom");
                stage.setScene(new Scene(gridPane, 300, 150));
                stage.show();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("On a chnagé le pseudo du 1er joueur");
                        //TODO changer le nom
                        stage.close();
                    }
                });
            }
        });
        pseudo2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                // TODO regarder si on est en mode 2 joueurs ou pas
                Label nameLabel = new Label("Nom du joueur 2");
                TextField pseudo = new TextField("Entrez votre pseudo");
                Button OKbutton= new Button("OK");
                GridPane gridPane = new GridPane();
                // setting the gridPane
                gridPane.setMinSize(100,200);
                gridPane.setPadding(new javafx.geometry.Insets(3,3,3,3));
                gridPane.setHgap(2);
                gridPane.setVgap(10);
                //gridPane.setAlignment(Pos.CENTER);
                gridPane.add(nameLabel,0,3);
                gridPane.add(pseudo,1,3);
                gridPane.add(OKbutton,2,3);
                Stage stage = new Stage();
                stage.setTitle("Changement de nom");
                stage.setScene(new Scene(gridPane, 300, 150));
                stage.show();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("On a changé le pseudo 2");
                        stage.close();
                        //TODO changer le nom
                    }
                });
            }
        });
        // action pour changer le nombre de routes/voies d'eau
        nbre_routes.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label nameLabel = new Label("Nombre de routes");
                TextField n = new TextField("4");
                Button OKbutton= new Button("OK");
                GridPane gridPane = new GridPane();
                // setting the gridPane
                gridPane.setMinSize(100,200);
                gridPane.setPadding(new javafx.geometry.Insets(3,3,3,3));
                gridPane.setHgap(2);
                gridPane.setVgap(10);
                //gridPane.setAlignment(Pos.CENTER);
                gridPane.add(nameLabel,0,3);
                gridPane.add(n,1,3);
                gridPane.add(OKbutton,2,3);
                Stage stage = new Stage();
                stage.setTitle("Nombre de routes");
                stage.setScene(new Scene(gridPane, 300, 150));
                stage.show();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("On a changé le nombre de routes");
                        stage.close();
                        //TODO changer le nombre de routes
                    }
                });
            }
        });
        nbre_riviere.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label nameLabel = new Label("Larguer de la rivière");
                TextField n = new TextField("4");
                Button OKbutton= new Button("OK");
                GridPane gridPane = new GridPane();
                // setting the gridPane
                gridPane.setMinSize(100,200);
                gridPane.setPadding(new javafx.geometry.Insets(3,3,3,3));
                gridPane.setHgap(2);
                gridPane.setVgap(10);
                //gridPane.setAlignment(Pos.CENTER);
                gridPane.add(nameLabel,0,3);
                gridPane.add(n,1,3);
                gridPane.add(OKbutton,2,3);
                Stage stage = new Stage();
                stage.setTitle("Largeur de la rivière");
                stage.setScene(new Scene(gridPane, 300, 150));
                stage.show();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        System.out.println("On a changé la largeur de la rivière");
                        stage.close();
                        //TODO changer le nombre de routes
                    }
                });
            }
        });
        // actions pour changer la difficulté
        deb.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("on est passé en mode débutant");
                // TODO passer la speed en mode lent
            }
        });
        inter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("on est passé en mode intermédiaire");
                // TODO passer la speed en mode normal
            }
        });
        exp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                System.out.println("on est passé en mode expert");
                // TODO passer la speed en mode lent
            }
        });
        // actions pour changer le mode de jeu
        fini.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO passer en mode fini
                System.out.println("on est passé en mode de jeu fini");
            }
        });
        infini.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO passer en mode fini
                System.out.println("on est passé en mode de jeu infini");
            }
        });
        un_joueur.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO passer en mode un joueur
                System.out.println("on est passé en mode 1 joueur");
            }
        });
        deux_joueurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                //TODO passer en mode deux joueurs
                System.out.println("on est passé en mode 2 joueurs");
            }
        });
    }

    @Override
    public void start(Stage primaryStage) {
//        this.generate_menu();

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
                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
                    this.voitures[i - plateau.nb_pistes / 2].auto_down(speed_down);
                }
            }
//            this.check_end(frog, primaryStage, the_end);
        };


        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().add(plateau.getGridPane());

        check_end_bien(primaryStage);
        check_nenuphar(frog);
        initLog();
//        initLog();
//        initLog();
//        initCar();
//        initCar();
        initCar();
        root.getChildren().add(frog.getImageView());

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void check_end(Frog frog, Stage primaryStage, Scene scene) {
        if (frog.getX()<0 || frog.getX()>this.l_case*this.nb_case || frog.getY()<0 || frog.getY()>this.l_case*this.nb_case) {
            primaryStage.setScene(scene);
        }
        if (frog.dead) {
            primaryStage.setScene(scene);
        }
    }
    public void check_end_bien(Stage primaryStage){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        if (frog.getX() < - l_case|| frog.getX() > l_case * nb_case || frog.getY() < 0 || frog.getY() > l_case * nb_case) {
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
    public void check_nenuphar(Frog frog){
        new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        int numero_piste = (int) (frog.getY()/l_case);
                        if (plateau.get(numero_piste).type_piste == 1 && !(frog.isOnNenuphar())){ // is the frog is on the river and not on a nenuphar
                            frog.dead = true;
                            System.out.println("check_nenu_death");
                            System.out.println(frog.isOnNenuphar());
                        }
                    }
                });
            }
        }, 0, 200);

    }
    public void initCar() {
        Voiture[] voitures = new Voiture[plateau.nb_pistes*10];
        for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
            voitures[i-plateau.nb_pistes/2] = new Voiture(plateau.get(i),scene, this.l_case);
            root.getChildren().add(voitures[i-plateau.nb_pistes/2].getImageView());

        }
        new Timer().scheduleAtFixedRate(new TimerTask() { //Refresh actual position (quicker)
            @Override
            public void run() {
                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
                    if (voitures[i-plateau.nb_pistes/2].intersects(frog)) {
                        frog.dead = true;
                    }
                }
            }
        }, 0, 100);
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
        Log[] logs = new Log[plateau.nb_pistes*10];
        for (int i = 2; i < plateau.nb_pistes/2 + 1; i++) { // Start at number 2 because Number 1 is a safe lane
            logs[i] = new Log(plateau.get(i),scene, this.l_case);
            root.getChildren().add(logs[i].getImageView());
        }
        new Timer().scheduleAtFixedRate(new TimerTask() { //Refresh actual position (quicker)
            @Override
            public void run() {
                for (int i = 2; i < plateau.nb_pistes/2 + 1; i++) {
                    if (logs[i].intersects(frog)) {
                        frog.setOnNenuphar(true);
                    }
                }
            }
        }, 0, 10);
        new Timer().scheduleAtFixedRate(new TimerTask() { // Refresh visual position
            @Override
            public void run() {
                for (int i = 2; i < plateau.nb_pistes/2 + 1; i++) {
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
