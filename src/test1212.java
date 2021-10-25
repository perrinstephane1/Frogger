import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class test1212 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        GridPane gridPane = new GridPane();
        // setting the gridPane
        gridPane.setMinSize(400,200);
        gridPane.setPadding(new javafx.geometry.Insets(10,10,10,10));
        gridPane.setHgap(2);
        gridPane.setVgap(10);
        //gridPane.setAlignment(Pos.CENTER);


        // trying to build a menu
        MenuItem pseudo1 = new MenuItem("Pseudo 1");
        // Image newImage = MyImageUtils.getImage("/org/o7planning/javafx/icon/new-16.png");
        //buildItem.setGraphic(new ImageView(buildImage));
        //buildItem.setSelected(true);
        //CheckMenuItem buildItem = new CheckMenuItem("Build Automatically"); menu à cocher
        //SeparatorMenuItem separator= new SeparatorMenuItem(); pour séparer
        MenuItem pseudo2= new MenuItem("Pseudo 2");
        MenuItem nbre_routes = new MenuItem("Nombre de routes");
        MenuItem nbre_riviere=new MenuItem("Nombre de rivière");
        MenuItem deb = new MenuItem("Débutant");
        MenuItem inter = new MenuItem("Intermédiaire");
        MenuItem exp = new MenuItem("expert");
        MenuItem un_joueur = new MenuItem("1 joueur");
        MenuItem deux_joueurs = new MenuItem("2 joueurs");
        MenuItem bs = new MenuItem("Meilleurs scores");
        MenuItem pause = new MenuItem("Pause");
        SeparatorMenuItem separator= new SeparatorMenuItem();
        MenuItem quitter = new MenuItem("Quitter");
        Menu menu1 = new Menu("Paramètres");
        Menu menu2 = new Menu("Difficulté");
        Menu menu3 = new Menu("Mode de jeu");
        Menu menu4 = new Menu("Panthéon");
        Menu menu5 = new Menu("Jeu");
        menu1.getItems().addAll(pseudo1,pseudo2, nbre_routes,nbre_riviere);
        menu2.getItems().addAll(deb,inter,exp);
        menu3.getItems().addAll(un_joueur,deux_joueurs);
        menu4.getItems().addAll(bs);
        menu5.getItems().addAll(pause, separator,quitter);
        MenuBar menuBar= new MenuBar(menu1,menu2,menu5,menu3,menu4);
        gridPane.getChildren().add(menuBar);

        //trying to add things
        // action pour mettre le jeu en pause
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
                //TODO changer le nom dans le programme en tapant OK
            }
        });

        // setting the scene
        Scene scene = new Scene(gridPane,1000,500);
        primaryStage.setTitle("Bonjour Wiwi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}