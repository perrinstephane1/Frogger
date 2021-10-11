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
        Plateau plateau = new Plateau(root);

        EventHandler<KeyEvent> keyListener = e -> {
            if(e.getCode()== KeyCode.UP){
                frog.up();
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
        plateau.display();
        root.getChildren().add(frog.getImageView());

        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
