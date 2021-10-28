import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class menu extends Application {

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
        primaryStage.setResizable(false);
        primaryStage.setTitle("Frogger");
        primaryStage.setScene(scene);
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
                //IHM.launch(); commande à trouver
            }
        });
        stage.setTitle("Avant de commencer à jouer...");
        stage.setScene(new Scene(gridPane, 600, 150));
        stage.setResizable(false);
        stage.show();
    }
}
