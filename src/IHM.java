import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class IHM extends Application {

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, 400, 400);
        scene.setFill(Color.web("#81c483"));
        Frog frog = new Frog(20, 20, scene);
        Piste piste1 = new Piste("route", true,1,2,1, true );
        Voiture voiture1 = new Voiture(piste1, scene);
        Piste piste2 = new Piste("route", true,-1,2,1, true );
        Voiture voiture2 = new Voiture(piste2, scene);



        EventHandler<KeyEvent> keyListener = e -> {
            if(e.getCode()== KeyCode.UP){
                frog.up();
                voiture1.move();
                voiture2.move();
            }
            if(e.getCode()==KeyCode.DOWN){
                frog.down();
            }
            if(e.getCode()==KeyCode.RIGHT){
                frog.right();
            }
            if(e.getCode()==KeyCode.LEFT){
                frog.left();
            }
        };

        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().add(frog.getImageView());
        root.getChildren().add(voiture1.getImageView());
        root.getChildren().add(voiture2.getImageView());
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
