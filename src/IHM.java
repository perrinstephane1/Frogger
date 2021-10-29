import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

// TODO Problem de hitbox entre frog et elements mobiles
// TODO Mettre fin au jeu quand on est mort et pas seulement a l'interface graphique
// TODO Restart apres une defaite


public class IHM extends Application {
    private int l_case=50;
    private int nb_case=12;
    private double speed_down = 1;
    private double speed_h = 6;
    protected int compteur_voiture = 0;
    protected int compteur_log = 0;
    private Voiture[] voitures = new Voiture[100];
    private Log[] logs = new Log[100];
    private int difficulte = 1;



    Frog frog = new Frog((this.nb_case) * this.l_case /2 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case);
    Group root = new Group();
    Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*(this.nb_case+1));
    Plateau plateau = new Plateau(root, this.nb_case, this.l_case);


    GridPane deadwindow = new GridPane();
    Text deadText = new Text("You're dead !");
    Scene the_end = new Scene(deadwindow, this.l_case*this.nb_case, this.l_case*this.nb_case);

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // Premières choses
        Menu frogger = new Menu("Frogger");
        MenuItem infos = new MenuItem("Informations");
        MenuItem quit = new MenuItem("Quitter");
        SeparatorMenuItem separator1= new SeparatorMenuItem();
        frogger.getItems().addAll(infos,separator1,quit);
        Menu aide = new Menu("Aide");
        MenuItem joueurs=new Menu("Nombre de joueurs");
        MenuItem diff=new Menu("Difficulté");
        MenuItem mode=new Menu("Mode de jeu");
        SeparatorMenuItem separator2= new SeparatorMenuItem();
        aide.getItems().addAll(joueurs,separator2,diff,mode);
        MenuBar menuBar=new MenuBar(frogger,aide);

        //actions du menu
        infos.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label explication = new Label("Ce projet a été réalisé par des étudiants en 2ème année à l'ENSTA Bretagne," +
                        "de spécialité SNS et SOIA. Il a pour objectif de montrer que nous avons une certaine maîtrise du langage " +
                        "de programmation JAVA et notemment du module JavaFX. \n \n" +
                        "Nous avons donc créé un jeu 'frogger' dont le but est simple : vous êtes la grenouille et devez vous rendre" +
                        "en haut de l'écran sans vous faire écraser par une voiture ni tomber dans l'eau. Les rondins vous porteront pour " +
                        "traverser la rivière. \n" +
                        "BONNE CHANCE !!");
                Button OKbutton = new Button("OK");

                GridPane gridPane = new GridPane();
                gridPane.setPadding(new javafx.geometry.Insets(10,10,0,10));
                explication.setWrapText(true);
                gridPane.add(explication,0,0);
                gridPane.add(OKbutton,0,1);
                GridPane.setHalignment(OKbutton,HPos.CENTER);
                Stage stage = new Stage();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                stage.setTitle("Aide : informations");
                stage.setScene(new Scene(gridPane, 350, 250));
                stage.show();
            }
        });
        quit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        quit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
            }
        });
        joueurs.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label explication = new Label("Ce jeu vous permet de jouer à un ou deux joueurs. \n" +
                        "Le mode à un joueur se joue simplement avec les flèches directionnelles.\n" +
                        "Le mode multijoueur vera s'affronter deux joueurs, un dont l'écran sera à gauche et qui utilisera QZSD" +
                        " et l'autre qui utilisera les flèches directionnelles à droite.");
                Button OKbutton = new Button("OK");

                GridPane gridPane = new GridPane();
                gridPane.setPadding(new javafx.geometry.Insets(10,10,0,10));
                explication.setWrapText(true);
                gridPane.add(explication,0,0);
                gridPane.add(OKbutton,0,1);
                GridPane.setHalignment(OKbutton,HPos.CENTER);
                Stage stage = new Stage();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                stage.setTitle("Aide : informations");
                stage.setScene(new Scene(gridPane, 300, 175));
                stage.show();
            }
        });
        diff.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label explication = new Label("Ce jeu vous permet de jouer avec plusieurs niveaux de difficulté : \n" +
                        "1. Le mode débutant dans lequel les voitures et rondins ne vont pas très vite \n" +
                        "2. Le mode intermédiaire dans lequel ils se déplacent plus vite \n" +
                        "3. Le mode expert dans lequel ils n'iront jamais ausis vite !!  \n");
                Button OKbutton = new Button("OK");

                GridPane gridPane = new GridPane();
                gridPane.setPadding(new javafx.geometry.Insets(10,10,0,10));
                explication.setWrapText(true);
                gridPane.add(explication,0,0);
                gridPane.add(OKbutton,0,1);
                GridPane.setHalignment(OKbutton,HPos.CENTER);
                Stage stage = new Stage();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                stage.setTitle("Aide : informations");
                stage.setScene(new Scene(gridPane, 400, 175));
                stage.show();
            }
        });
        mode.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Label explication = new Label("Ce jeu vous permet de jouer de deux façons différentes : \n" +
                        "1. Le mode FINI se termine dès que vous arrivez en haut de l'écran. \n" +
                        "2. Le mode INFINI ne se termine que lorsque votre grenouille meurt.");
                Button OKbutton = new Button("OK");

                GridPane gridPane = new GridPane();
                gridPane.setPadding(new javafx.geometry.Insets(10,10,0,10));
                explication.setWrapText(true);
                gridPane.add(explication,0,0);
                gridPane.add(OKbutton,0,1);
                GridPane.setHalignment(OKbutton,HPos.CENTER);
                Stage stage = new Stage();
                OKbutton.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        stage.close();
                    }
                });
                stage.setTitle("Aide : informations");
                stage.setScene(new Scene(gridPane, 250, 150));
                stage.show();
            }
        });


        // création de la gridpane
        GridPane gridPane=new GridPane();
        // Pour contrôler les écarts à partir d'en haut/gauche/droite/bas
        //gridPane.setPadding(new javafx.geometry.Insets(0,0,0,1));
        //Pour fixer la largeur d'une colonne
        //gridPane.getColumnConstraints().add(new ColumnConstraints(100));
        gridPane.setHgap(6);
        gridPane.setVgap(4);
        gridPane.setAlignment(Pos.CENTER);
        Label n_joueur = new Label("Nombre de joueurs");
        ChoiceBox<Integer> choix_joueurs = new ChoiceBox<Integer>();
        choix_joueurs.getItems().addAll(1,2);
        Label mode1 = new Label("Mode de jeu");
        ChoiceBox<String> choix_mode = new ChoiceBox<String>();
        choix_mode.getItems().addAll("Fini", "Infini");
        Label diffi = new Label("Difficulté");
        ChoiceBox<String> choix_diff= new ChoiceBox<String>();
        choix_diff.getItems().addAll("Débutant","Intermédiaire","Expert");
        Button play=new Button("PLAY");
        play.setTextFill(Color.RED);
        play.setFont(Font.font ("Calibri",80));
        Label copyright = new Label(" © Williams HOARAU, Louis JOGUET, Aurélien PARAIRE & Stéphane PERRIN \n" +
                "Promotion ENSTA Bretagne 2021");
        copyright.setWrapText(true);
        copyright.setAlignment(Pos.BOTTOM_CENTER);

        //TODO faire le PLAY Bouton ^^
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                primaryStage.close();
                boolean test;
                try {
                    test=choix_joueurs.getValue().equals(2);
                    System.out.println("valeur correcte");
                } catch (Exception e) {
                    System.out.println("correction");
                    test = false;
                }
                boolean deux_joueurs=test;
                System.out.println(deux_joueurs);
                boolean fini_test;
                try{
                    fini_test = (choix_mode.getValue().equals("Fini"));
                } catch (Exception e) {
                    fini_test = true;
                } ;
                boolean fini=fini_test;
                avant_commencer(deux_joueurs,fini);
            }
        });

        // ajout sur la gridpane
        gridPane.add(n_joueur,1,1);
        GridPane.setHalignment(n_joueur,HPos.RIGHT);
        gridPane.add(choix_joueurs,2,1);
        GridPane.setHalignment(choix_joueurs, HPos.LEFT);
        gridPane.add(mode1,1,3);
        GridPane.setHalignment(mode1,HPos.RIGHT);
        gridPane.add(choix_mode,2,3);
        GridPane.setHalignment(choix_mode, HPos.LEFT);
        gridPane.add(diffi,1,5);
        GridPane.setHalignment(diffi,HPos.RIGHT);
        gridPane.add(choix_diff,2,5);
        GridPane.setHalignment(choix_diff, HPos.LEFT);
        gridPane.add(play,0,7,3,1);
        GridPane.setHalignment(play, HPos.CENTER);
        gridPane.add(copyright,1,8,2,1);

        //ajout du menu
        //gridPane.getChildren().add(menuBar);
        gridPane.add(menuBar,0,0,3,1);
        //gridPane.add(menuBar1,1,1);
        // setting the scene
        Scene scene = new Scene(gridPane,275,337);
        scene.setFill(Color.BLUE);
        primaryStage.setTitle("Frogger");
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void update_state(Stage primaryStage,Frog frog){
        new Timer().scheduleAtFixedRate(new TimerTask() {
        @Override
            public void run() {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {

                        for (int i = 1; i < compteur_log + 1; i++) {
                            int numero_piste = (int) (frog.getY()/l_case);
                            if (logs[i].intersects(frog)) {
                                frog.setOnLog(true);
                                TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), frog.getImageView());
                                if (logs[i].piste.sens == 0){
                                    trans.setByX(-speed_h);
                                    if (frog.getX()>=-50) {
                                        frog.setLocation((int)(frog.getX()-speed_h), (int)frog.getY());
                                        trans.play();
                                    }
                                } else {
                                    trans.setByX(speed_h);
                                    if (frog.getX() <= scene.getWidth()) {
                                        frog.setLocation((int)(frog.getX() + speed_h), (int)frog.getY());
                                        trans.play();
                                    }
                                }
                            }
                            else if (plateau.get(numero_piste).type_piste != 1){
                                frog.setOnLog(false);
                            }
                            logs[i].move(speed_h);
                        }
                        for (int i = 1; i < compteur_voiture+1; i++) {
                            voitures[i].move(speed_h);
                            if (voitures[i].intersects(frog)) {
                                primaryStage.setScene(the_end);
                                System.out.println("collision");
                            }
                        }
                        int numero_piste = (int) (frog.getY()/l_case);
                        if (numero_piste == 0) {
                            plateau.chrono.stop();
                            System.out.println(plateau.chrono.getElapsedSeconds());
                        }

                        if (plateau.get(numero_piste).type_piste == 1 && !(frog.isOnLog())){ // if the frog is on the river and not on a log
//                            frog.dead = true;
                            System.out.println("noyée");
                            primaryStage.setScene(the_end);
                        }
                        if (frog.getX() < -l_case|| frog.getX() > l_case * nb_case || frog.getY() < 0 || frog.getY() > l_case * nb_case) { //if the frig is out of map
                            primaryStage.setScene(the_end);
                        }
                        if (frog.dead) {
                            primaryStage.setScene(the_end);
                        }

                        displayTime();

                        frog.setOnLog(false);
                    }
                });
            }
        }, 0, 50);
    }

    private void displayTime() {
        Text txt = plateau.getChrono();
        txt.setFont(new Font(l_case*0.8));
        txt.setWrappingWidth(nb_case*l_case);
        txt.setTextAlignment(TextAlignment.CENTER);
        txt.setFill(Color.BROWN);
        txt.setStrokeWidth(1);
        txt.setStroke(Color.BLUE);
        plateau.get(nb_case).gridPane.getChildren().set(0, txt);
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
    }
    public void initLog(Log[] logs,  int nombre_log) {
        for (int i = 2; i < plateau.nb_pistes/2 + 1; i++) { // Start at number 2 because Number 1 is a safe lane
            for (int j = 0; j<nombre_log; j++) {
                compteur_log += 1;
                logs[compteur_log] = new Log(plateau.get(i), scene, this.l_case);
                boolean collisions_log = true;
                while(collisions_log){
                    if (compteur_log >=2 && logs[compteur_log-1].intersects(logs[compteur_log])){// checks for collisions
                        logs[compteur_log] = new Log(plateau.get(i), scene, this.l_case);
                    }
                    if (compteur_log >=3 && (logs[compteur_log-2].intersects(logs[compteur_log]) || logs[compteur_log-1].intersects(logs[compteur_log]) )    ){// checks for collisions
                        logs[compteur_log] = new Log(plateau.get(i), scene, this.l_case);
                    }
                    else{
                        collisions_log = false;
                    }
                }
                root.getChildren().add(logs[compteur_log].getImageView());
            }
        }
    }
    public void joue(){
        Stage primaryStage= new Stage();
        scene.setFill(Color.BLACK);

        deadwindow.setAlignment(Pos.CENTER);
        deadText.setStyle("-fx-font: normal bold "+this.l_case+"px 'serif' ");
        deadwindow.add(deadText, 0, 0);
        the_end.setFill(Color.web("#d13318"));


        EventHandler<KeyEvent> keyListener = e -> {
            if (!plateau.chrono.isRunning) {
                plateau.chrono.start();
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
                plateau.auto_down((int) speed_down);
                for (int i = plateau.nb_pistes/2 +1; i < plateau.nb_pistes; i++) {
                    this.voitures[i - plateau.nb_pistes / 2].auto_down((int) speed_down);
                }
            }
        };


        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().add(plateau.getGridPane());

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
        update_state(primaryStage,frog);

        root.getChildren().add(frog.getImageView());
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }
    public void avant_commencer (boolean joueurs,boolean fini){
        // joueurs = true si 2 joueurs
        // fini = true si on joue en mode fini
        Label pseudo1 = new Label("Pseudo 1");
        TextField nom1 = new TextField("Entrez votre pseudo");
        Label exp_1 = new Label("Ce joueur jouera avec les flèches directionnelles");
        Button play = new Button("START PLAYING");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new javafx.geometry.Insets(0,10,0,10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);
        gridPane.getColumnConstraints().addAll(new ColumnConstraints(100),
                new ColumnConstraints(150),
                new ColumnConstraints(50),
                new ColumnConstraints(110),
                new ColumnConstraints(150));
        gridPane.add(pseudo1,0,1);
        GridPane.setHalignment(pseudo1, HPos.RIGHT);
        gridPane.add(nom1,1,1);
        gridPane.add(exp_1,0,2,2,1);
        if (joueurs){
            Label pseudo2 = new Label("Pseudo 2");
            TextField nom2 = new TextField("Entrez votre pseudo");
            Label exp_2 = new Label("Ce joueur jouera avec les touches QWSD");
            gridPane.add(pseudo2,3,1);
            GridPane.setHalignment(pseudo2, HPos.RIGHT);
            gridPane.add(nom2,4,1);
            gridPane.add(exp_2,3,2,2,1);
        }
        if (fini){
            Label b_routes = new Label("Nombre de routes");
            Label n_rivieres = new Label("Largeur de la rivière");
            ChoiceBox<Integer> n_r = new ChoiceBox<Integer>();
            n_r.getItems().addAll(1,2,3,4);
            ChoiceBox<Integer> l_r = new ChoiceBox<Integer>();
            l_r.getItems().addAll(1,2,3,4);
            gridPane.add(b_routes,0,3);
            GridPane.setHalignment(b_routes,HPos.RIGHT);
            gridPane.add(n_r,1,3);
            gridPane.add(n_rivieres ,3,3);
            GridPane.setHalignment(n_rivieres,HPos.RIGHT);
            gridPane.add(l_r,4,3);
        }
        gridPane.add(play,2,8,1,1);
        GridPane.setHalignment(play,HPos.CENTER);
        Stage stage = new Stage();
        play.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                stage.close();
                //TODO lancer le vrai jeu
                joue();
            }
        });
        stage.setTitle("Avant de commencer à jouer...");
        stage.setScene(new Scene(gridPane, 600, 150));
        stage.show();
    }
}
