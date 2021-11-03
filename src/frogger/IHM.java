package frogger;

import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

/**
 * This class represents the human-machine interface
 * @author Williams HOARAU
 * @author Louis JOGUET
 * @author Aurelien PARAIRE
 * @author Stephane PERRIN
 *
 */
public class IHM extends Application {
    private int l_case=50;
    private int nb_case=12;
    private double speed_h = 6;
    /** Number of cars in the game */
    protected int car_count = 0;
    /** Number of logs in the game */
    protected int log_count = 0;
    private final Car[] cars = new Car[100];
    private final Log[] logs = new Log[100];
    private final HallOfFame hallOfFame = new HallOfFame();


    private Frog frog;
    private Frog frog2;
    private Group root;
    private Scene scene;
    private Board board;




    /**
     * Method to launch the game
     * @param args args
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * This method launches the first menu of the application.
     * This menu allows the player to choose the game mode (1 or 2 player), the game mode (normal or infinite) and the difficulty (3 levels)
     * The menu also proposes a help on how to use the game and gives information about the developers
     */
    @Override
    public void start(Stage primaryStage) throws InterruptedException {
        menu();
    }

    /**
     * This method updates the position of all the moving components of the game (frog, logs and cars) and the timer of the player
     * This method also verify is the frog is not hit by a car, on the river. If it is the case, the game ends
     * @param primaryStage This Stage is the window on which the game is displayed
     * @param joueurs This boolean determines if the game is in single or two player mode
     * @param name1 This String is the name of the first player
     * @param name2 This String is the name of the second player
     */
    public void update_state(Stage primaryStage, boolean joueurs, String name1, String name2,int dif){
        Timer timer =  new Timer();
        TimerTask timertask = new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    for (int i = 1; i < log_count + 1; i++) {
                        int numero_lane = (int) (frog.getY() / l_case);
                        if (logs[i].intersects(frog)) {
                            frog.setOnLog(true);
                            TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), frog.getImageView());
                            if (logs[i].lane.direction == 0) {
                                trans.setByX(-speed_h);
                                if (frog.getX() >= -50) {
                                    frog.setLocation((int) (frog.getX() - speed_h), (int) frog.getY());
                                    trans.play();
                                }
                            } else {
                                trans.setByX(speed_h);
                                if (frog.getX() <= scene.getWidth()) {
                                    frog.setLocation((int) (frog.getX() + speed_h), (int) frog.getY());
                                    trans.play();
                                }
                            }
                        } else if (board.get(numero_lane).type_lane != 1) {
                            frog.setOnLog(false);
                        }

                        if (joueurs) {
                            int numero_lane2 = (int) (frog2.getY() / l_case);
                            if (logs[i].intersects(frog2)) {
                                frog2.setOnLog(true);
                                TranslateTransition trans = new TranslateTransition(Duration.seconds(0.001), frog2.getImageView());
                                if (logs[i].lane.direction == 0) {
                                    trans.setByX(-speed_h);
                                    if (frog2.getX() >= -50) {
                                        frog2.setLocation((int) (frog2.getX() - speed_h), (int) frog2.getY());
                                        trans.play();
                                    }
                                } else {
                                    trans.setByX(speed_h);
                                    if (frog2.getX() <= scene.getWidth()) {
                                        frog2.setLocation((int) (frog2.getX() + speed_h), (int) frog2.getY());
                                        trans.play();
                                    }
                                }
                            } else if (board.get(numero_lane2).type_lane != 1) {
                                frog2.setOnLog(false);
                            }
                        }

                        logs[i].move(speed_h);
                    }
                    for (int i = 1; i < car_count + 1; i++) {
                        cars[i].move(speed_h);
                        if (cars[i].intersects(frog)) {
                            System.out.println("colision");
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                            //primaryStage.setScene(deadScene());
                            death(primaryStage,joueurs,true,dif);
                        }
                        else if (joueurs) {
                            if (cars[i].intersects(frog) || cars[i].intersects(frog2)) {
                                timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                                timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                                //primaryStage.setScene(deadScene());
                                death(primaryStage,joueurs,true,dif);
                            }
                        }
                    }

                    int numero_lane = (int) (frog.getY() / l_case);
                    int numero_lane2 = (int) (frog2.getY() / l_case);


                    if (joueurs) {
                        if (numero_lane == 0 && numero_lane2 == 0) {
                            board.chrono.stop();
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                            //primaryStage.setScene(victoryScene());
                            victory(primaryStage,joueurs,true,dif);
                        }
                        else if ((board.get(numero_lane).type_lane == 1 && !(frog.isOnLog())) || (board.get(numero_lane2).type_lane == 1 && !(frog2.isOnLog()))) { // if the frog is on the river and not on a log
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                            //primaryStage.setScene(deadScene());
                            death(primaryStage,joueurs,true,dif);
                        }
                        else if ((frog.getX() < -l_case || frog.getX() > l_case * nb_case || frog.getY() < 0 || frog.getY() > l_case * nb_case) || (frog2.getX() < -l_case || frog2.getX() > l_case * nb_case || frog2.getY() < 0 || frog2.getY() > l_case * nb_case)) { //if the frig is out of map
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                            //primaryStage.setScene(deadScene());
                            death(primaryStage,joueurs,true,dif);
                        }
                    }
                    else{
                        if (numero_lane == 0 ) {
                            board.chrono.stop();
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                            hallOfFame.addScore(name1, board.getChronoToFloat());
                            hallOfFame.display();
                            //primaryStage.setScene(victoryScene());
                            victory(primaryStage,joueurs,true,dif);
                        }
                        else if (board.get(numero_lane).type_lane == 1 && !(frog.isOnLog())){ // if the frog is on the river and not on a log
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                            //primaryStage.setScene(deadScene());
                            death(primaryStage,joueurs,true,dif);
                        }
                        else if (frog.getX() < -l_case|| frog.getX() > l_case * nb_case || frog.getY() < 0 || frog.getY() > l_case * nb_case) { //if the frig is out of map
                            timer.cancel();  // Terminates this timer, discarding any currently scheduled tasks.
                            timer.purge();   // Removes all cancelled tasks from this timer's task queue.                                µ
                            //primaryStage.setScene(deadScene());
                            death(primaryStage,joueurs,true,dif);
                        }
                    }

                    displayTime();

                    frog.setOnLog(false);
                    frog2.setOnLog(false);
                });
            }
        };
        timer.scheduleAtFixedRate(timertask, 0, 50);
    }

    /**
     * This method display the timer at the bottom of the game window
     */
    private void displayTime() {
        Text txt = board.getChrono();
        txt.setFont(new Font(l_case*0.8));
        txt.setWrappingWidth(nb_case*l_case);
        txt.setTextAlignment(TextAlignment.CENTER);
        txt.setFill(Color.BROWN);
        txt.setStrokeWidth(1);
        txt.setStroke(Color.BLUE);
        board.get(nb_case).gridPane.getChildren().set(0, txt);
    }

    /**
     * This method intitialize every car required for the game
     * @param cars This Car[] contains all the cars generated on the map
     * @param nombre_voiture This int corresponds to the number of cars on the map
     * */
    public void initCar(Car[] cars, int nombre_voiture) {
        car_count = 0;
        for (int i = board.nb_case/2 +1; i < board.nb_case; i++) {
            for (int j = 0; j<nombre_voiture; j++){
                car_count += 1;
                cars[car_count] = new Car(board.get(i),scene, this.l_case);
                boolean collisions_car = true;
                while(collisions_car){
                    if (car_count ==2 && cars[car_count-1].intersects(cars[car_count])){// checks for collisions
                        cars[car_count] = new Car(board.get(i), scene, this.l_case);
                    }
                    if (car_count >=3 && (cars[car_count-2].intersects(cars[car_count]) || cars[car_count-1].intersects(cars[car_count]) )    ){// checks for collisions
                        cars[car_count] = new Car(board.get(i), scene, this.l_case);
                    }
                    else{
                        collisions_car = false;
                    }
                }
                root.getChildren().add(cars[car_count].getImageView());
            }
        }
    }

    /**
     * This method intitialize every log required for the game
     * @param logs This Log[] contains all the logs generated on the map
     * @param nombre_log This int corresponds to the number of logs on the map
     */
    public void initLog(Log[] logs, int nombre_log) {
        log_count = 0;
        for (int i = 2; i < this.board.nb_case/2 + 1; i++) { // Start at number 2 because Number 1 is a safe lane
            for (int j = 0; j<nombre_log; j++) {
                log_count += 1;
                logs[log_count] = new Log(this.board.get(i), this.scene, this.l_case);
                boolean collisions_log = true;
                while(collisions_log){
                    if (log_count >=2 && logs[log_count-1].intersects(logs[log_count])){// checks for collisions
                        logs[log_count] = new Log(board.get(i), scene, this.l_case);
                    }
                    if (log_count >=3 && (logs[log_count-2].intersects(logs[log_count]) || logs[log_count-1].intersects(logs[log_count]) )    ){// checks for collisions
                        logs[log_count] = new Log(board.get(i), scene, this.l_case);
                    }
                    else{
                        collisions_log = false;
                    }
                }
                this.root.getChildren().add(logs[log_count].getImageView());
            }
        }
    }

    /**
     * this method launches the first menu of the game
     */
    public void menu() throws InterruptedException {
        this.hallOfFame.load("Scores.txt");

        Stage primaryStage=new Stage();
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
        infos.setOnAction(event -> {
            Label explication = new Label("Ce projet a été réalisé par des étudiants en 2ème année à l'ENSTA Bretagne," +
                    "de spécialité SNS et SOIA. Il a pour objectif de montrer que nous avons une certaine maîtrise du langage " +
                    "de programmation JAVA et notemment du module JavaFX. \n \n" +
                    "Nous avons donc créé un jeu 'frogger' dont le but est simple : vous êtes la grenouille et devez vous rendre " +
                    "en haut de l'écran sans vous faire écraser par une voiture ni tomber dans l'eau. Les rondins vous porteront pour " +
                    "traverser la rivière. \n" +
                    "BONNE CHANCE !!");
            helpInfo(explication);
        });
        quit.setAccelerator(KeyCombination.keyCombination("Ctrl+X"));
        quit.setOnAction(event -> primaryStage.close());
        joueurs.setOnAction(event -> {
            Label explication = new Label("Ce jeu vous permet de jouer à un ou deux joueurs. \n" +
                    "Le mode à un joueur se joue simplement avec les flèches directionnelles.\n" +
                    "Le mode multijoueur vera s'affronter deux joueurs, un dont l'écran sera à gauche et qui utilisera QZSD" +
                    " et l'autre qui utilisera les flèches directionnelles à droite.");
            helpInfo(explication);
        });
        diff.setOnAction(event -> {
            Label explication = new Label("Ce jeu vous permet de jouer avec plusieurs niveaux de difficulté : \n" +
                    "1. Le mode débutant dans lequel les cars et rondins ne vont pas très vite \n" +
                    "2. Le mode intermédiaire dans lequel ils se déplacent plus vite \n" +
                    "3. Le mode expert dans lequel ils n'iront jamais aussi vite !!  \n");
            helpInfo(explication);
        });
        mode.setOnAction(event -> {
            Label explication = new Label("Ce jeu vous permet de jouer de deux façons différentes : \n" +
                    "1. Le mode FINI se termine dès que vous arrivez en haut de l'écran. \n" +
                    "2. Le mode INFINI ne se termine que lorsque votre grenouille meurt. \n" +
                    "malheureusement, nous n'avons pas eu le temps d'implémenter une version correcte avec un défilement infini. " +
                    "Ce bouton ne sert donc à rien");
            helpInfo(explication);
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
        ChoiceBox<Integer> choix_joueurs = new ChoiceBox<>();
        choix_joueurs.getItems().addAll(1,2);
        Label mode1 = new Label("Mode de jeu");
        ChoiceBox<String> choix_mode = new ChoiceBox<>();
        choix_mode.getItems().addAll("Fini", "Infini");
        Label diffi = new Label("Difficulté");
        ChoiceBox<String> choix_diff= new ChoiceBox<>();
        choix_diff.getItems().addAll("Débutant","Intermédiaire","Expert");
        Button play=new Button("PLAY");
        play.setTextFill(Color.RED);
        play.setFont(Font.font ("Calibri",80));
        Label copyright = new Label(" © Williams HOARAU, Louis JOGUET, Aurélien PARAIRE & Stéphane PERRIN \n" +
                "Promotion ENSTA Bretagne 2021");
        copyright.setWrapText(true);
        copyright.setAlignment(Pos.BOTTOM_CENTER);

        play.setOnAction(event -> {
            primaryStage.close();
            boolean test;
            try {
                test=choix_joueurs.getValue().equals(2);
            } catch (Exception e) {
                test = false;
            }
            boolean deux_joueurs=test;
            boolean fini_test;
            try{
                fini_test = (choix_mode.getValue().equals("Fini"));
            } catch (Exception e) {
                fini_test = true;
            }
            boolean fini=fini_test;

            String dif;
            try {
                if (choix_diff.getValue()==null){
                    dif="Débutant";
                } else {
                    dif = choix_diff.getValue();
                }
            } catch (Exception e) {
                dif="Débutant";
            }
            int dif_i;
            switch (dif){
                case "Intermédiaire":
                    dif_i=2;
                    break;
                case "Expert" :
                    dif_i=3;
                    break;
                default:
                    dif_i=1;
                    break;
            }
            avant_commencer(deux_joueurs,fini,dif_i);
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
        //primaryStage.getIcons().add(new Image("frog8bit.png"));
    }

    /**
     * This method launches the games after the player selected all the required options before
     * @param dif_i This int is the difficulty of the game from 1 to 3. 1 is the lowest difficulty.
     * @param joueurs This boolean determines if the game is in single or two player mode.
     * @param name1 This String is the pseudo chosen by the first player.
     * @param name2 This String is the pseudo chosen by the second player.
     *
     */
    public void joue(boolean joueurs, String name1, String name2, int dif_i){
        float factor= 0.5F;
        System.out.println(factor);
        if (joueurs){
            System.out.println("on est rentré");
            factor=2/3F;
        }
        System.out.println(factor);
        this.frog = new Frog((int) ((this.nb_case) * this.l_case*factor), (this.nb_case -1)* this.l_case, this.l_case, this.nb_case, "frog8bit.png");
        this.frog2 = new Frog((this.nb_case) * this.l_case /3 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case, "frog8bit2.png");

        this.root = new Group();
        this.scene = new Scene(this.root, this.l_case*this.nb_case, this.l_case*(this.nb_case+1));
        this.board = new Board(this.nb_case, this.l_case);

        Stage primaryStage= new Stage();
        this.scene.setFill(Color.BLACK);



        EventHandler<KeyEvent> keyListener = e -> {
            if (!board.chrono.isRunning) {
                board.chrono.start();
            }
            if(e.getCode()== KeyCode.UP){
                frog.up();
            } else if (e.getCode()==KeyCode.DOWN){
                frog.down();
            } else if (e.getCode()==KeyCode.RIGHT){
                frog.right();
            } else if (e.getCode()==KeyCode.LEFT) {
                frog.left();
            }else if (e.getCode()==KeyCode.Z){
                frog2.up();
            }else if (e.getCode()==KeyCode.S){
                frog2.down();
            } else if (e.getCode()==KeyCode.D){
                frog2.right();
            } else if (e.getCode()==KeyCode.Q){
                frog2.left();
            }
        };

        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().add(board.getGridPane());

        if (dif_i ==3){ // expert
            speed_h = 6;
            initLog(logs, 1);
            initCar(cars, 3);
        }else if (dif_i ==2){ // intermediaire
            speed_h = 4;
            initLog(logs, 2);
            initCar(cars, 2);
        }else{ // debutant
            speed_h = 3;
            initLog(logs, 2);
            initCar(cars, 1);
        }
        update_state(primaryStage, joueurs, name1, name2,dif_i);

        root.getChildren().add(frog.getImageView());
        if (joueurs){
            root.getChildren().add(frog2.getImageView());
        }
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * This method allows the player to change his pseudo and the number of lane in the game
     * @param dif_i This int is the difficulty of the game from 1 to 3. 1 is the lowest difficulty.
     * @param fini This boolean determines if the game is in finite or infinite mode
     * @param joueurs This boolean determines if the game is in single or two player mode
     *
     */
    public void avant_commencer(boolean joueurs, boolean fini, int dif_i){

        Label pseudo1 = new Label("Pseudo 1");
        TextField nom1 = new TextField("Joueur 1");
        Label exp_1 = new Label("Ce joueur jouera avec les flèches directionnelles");
        Label pseudo2 = new Label("Pseudo 2");
        TextField nom2 = new TextField("Joueur 2");
        Label exp_2 = new Label("Ce joueur jouera avec les touches QZSD");
        Button play = new Button("START PLAYING");
        ChoiceBox<Integer> n_r = new ChoiceBox<>();
        ChoiceBox<Integer> l_r = new ChoiceBox<>();

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
            gridPane.add(pseudo2,3,1);
            GridPane.setHalignment(pseudo2, HPos.RIGHT);
            gridPane.add(nom2,4,1);
            gridPane.add(exp_2,3,2,2,1);
        }
        if (fini){
            Label b_routes = new Label("Nombre de routes");
            Label n_rivieres = new Label("Largeur de la rivière");
            n_r.getItems().addAll(8,10,12,15, 20, 25, 30);
            l_r.getItems().addAll(30,50,70);
            gridPane.add(b_routes,0,3);
            GridPane.setHalignment(b_routes,HPos.RIGHT);
            gridPane.add(n_r,1,3);
            gridPane.add(n_rivieres ,3,3);
            GridPane.setHalignment(n_rivieres,HPos.RIGHT);
            gridPane.add(l_r,4,3);

        }
        gridPane.add(play,1,8,3,1);
        GridPane.setHalignment(play,HPos.CENTER);
        Stage stage = new Stage();
        play.setOnAction(event -> {
            stage.close();
            //TODO lancer le vrai jeu
            String name1;
            try{
                name1= String.valueOf(nom1.getCharacters());
            } catch(Exception e) {
                name1="Joueur 1";
            }
            String name2;
            try{
                name2= String.valueOf(nom2.getCharacters());
            } catch(Exception e) {
                name2="Joueur 2";
            }
            try {
                nb_case = n_r.getValue();
            } catch (Exception e) {
                nb_case = 10;
            }
            try {
                l_case = l_r.getValue();
            } catch (Exception e) {
                l_case = 50;
            }

            joue(joueurs,name1,name2,dif_i);

        });
        stage.setTitle("Avant de commencer à jouer...");
        stage.setScene(new Scene(gridPane, 600, 150));
        stage.show();
    }


    private void helpInfo(Label explication) {
        Button OKbutton = new Button("OK");

        GridPane gridPane = new GridPane();
        gridPane.setPadding(new javafx.geometry.Insets(10,10,0,10));
        explication.setWrapText(true);
        gridPane.add(explication,0,0);
        gridPane.add(OKbutton,0,1);
        GridPane.setHalignment(OKbutton,HPos.CENTER);
        Stage stage = new Stage();
        OKbutton.setOnAction(event -> stage.close());
        stage.setTitle("Aide : informations");
        stage.setScene(new Scene(gridPane, 350, 250));
        stage.show();
    }

    /**
     * this method launches the death window and propose some actions
     * @param primaryStage game window to shut down
     * @param joueurs parameters to memorize to relaunch the game
     * @param fini parameters to memorize to relaunch the game
     * @param dif parameters to memorize to relaunch the game
     */
    private void death(Stage primaryStage,boolean joueurs,boolean fini,int dif) {
        Stage stage=new Stage();

        GridPane deadwindow = new GridPane();
        Text deadText = new Text("You're dead !");
        Button restartButton=new Button("PLAY AGAIN");
        Button menuButton=new Button("Retourner au menu");
        Button HallOfFame=new Button("Hall of Fame");
        Button quit=new Button("QUITTER");
        Scene deadScene = new Scene(deadwindow, 450, 200);

        paramScene(deadwindow, deadText, restartButton, menuButton, HallOfFame, quit, stage, deadScene, primaryStage, joueurs, fini, dif, "Défaite");
    }
    /**
     * this method launches the victory window and propose some actions
     * @param primaryStage game window to shut down
     * @param joueurs parameters to memorize to relaunch the game
     * @param fini parameters to memorize to relaunch the game
     * @param dif parameters to memorize to relaunch the game
     */
    private void victory(Stage primaryStage,boolean joueurs,boolean fini,int dif){
        Stage stage=new Stage();
        GridPane victoryWindow = new GridPane();
        board.chrono.stop();
        this.hallOfFame.save("Scores.txt");
        Scene victoryScene = new Scene(victoryWindow, l_case*nb_case, l_case*nb_case);
        Text winText = new Text("You have won in "+board.getChronoToFloat()+"s !");
        Button restartButton=new Button("PLAY AGAIN");
        Button menuButton=new Button("Retourner au menu");
        Button HallOfFame=new Button("Hall of Fame");
        Button quit=new Button("QUITTER");
        paramScene(victoryWindow, winText, restartButton, menuButton, HallOfFame, quit, stage, victoryScene, primaryStage, joueurs, fini, dif, "Victoire");

    }

    private void paramScene(GridPane window, Text text, Button restartButton, Button menuButton, Button HallOfFame, Button quit, Stage stage, Scene scene, Stage primaryStage, Boolean joueurs, Boolean fini, Integer dif, String title) {

        window.setAlignment(Pos.CENTER);
        text.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, this.l_case));
        text.setWrappingWidth(this.l_case*this.nb_case);
        text.setTextAlignment(TextAlignment.CENTER);
        restartButton.setTextAlignment(TextAlignment.CENTER);
        window.add(text, 0, 0,1,1);
        window.add(restartButton, 0, 1);
        window.add(menuButton,0,2);
        window.add(HallOfFame,0,3);
        window.add(quit,0,4);
        GridPane.setHalignment(restartButton,HPos.CENTER);
        GridPane.setHalignment(menuButton,HPos.CENTER);
        GridPane.setHalignment(HallOfFame,HPos.CENTER);
        GridPane.setHalignment(quit,HPos.CENTER);
        stage.setScene(scene);
        stage.setTitle(title);
        stage.setResizable(false);
        stage.show();
        //setting the buttons
        quit.setOnAction(event -> System.exit(0));
        menuButton.setOnAction(event -> {
            stage.close();
            primaryStage.close();
            try {
                menu();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        restartButton.setOnAction(event -> {
            primaryStage.close();
            stage.close();
            avant_commencer(joueurs,fini,dif);
        });
        HallOfFame.setOnAction(event -> hallOfFame.affiche());
    }
}

