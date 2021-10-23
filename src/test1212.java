import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class test1212 extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        Label nameLabel = new Label("Nom");
        TextField pseudo = new TextField("Entrez votre pseudo");
        Button OKbutton= new Button("OK");
        GridPane gridPane = new GridPane();
        // setting the gridPane
        gridPane.setMinSize(400,200);
        gridPane.setPadding(new javafx.geometry.Insets(10,10,10,10));
        gridPane.setHgap(2);
        gridPane.setVgap(10);
        //gridPane.setAlignment(Pos.CENTER);
        gridPane.add(nameLabel,0,0);
        gridPane.add(pseudo,1,0);
        gridPane.add(OKbutton,2,0);


        // trying to build a menu
        MenuItem parametres = new MenuItem("Pseudo");
        Menu menu = new Menu("parametres");
        menu.getItems().addAll(parametres);
        MenuBar menuBar= new MenuBar(menu);
        Pane myPane = new VBox();
        myPane.getChildren().add(new Button("copy"));

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        // setting the scene
        Scene scene = new Scene(myPane,400,500);
        primaryStage.setTitle("Bonjour Wiwi");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
