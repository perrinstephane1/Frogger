import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Frog extends Application {
    private double abscisse;
    private double ordonnee;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, 300, 300);

        Circle c = new Circle(150, 150, 20);
        c.setFill(Color.RED);

        TranslateTransition trans_up = new TranslateTransition(Duration.seconds(0.001), c);
        trans_up.setByY(-10f);
        TranslateTransition trans_down = new TranslateTransition(Duration.seconds(0.001), c);
        trans_down.setByY(10f);
        TranslateTransition trans_R = new TranslateTransition(Duration.seconds(0.001), c);
        trans_R.setByX(10f);
        TranslateTransition trans_L = new TranslateTransition(Duration.seconds(0.001), c);
        trans_L.setByX(-10f);

        EventHandler<KeyEvent> keyListener = new
                EventHandler<KeyEvent>(){
                    @Override
                    public void handle(KeyEvent e) {
                        if(e.getCode()== KeyCode.UP){
                            trans_up.play();
                        }
                        if(e.getCode()==KeyCode.DOWN){
                            trans_down.play();
                        }
                        if(e.getCode()==KeyCode.RIGHT){
                            trans_R.play();
                        }
                        if(e.getCode()==KeyCode.LEFT){
                            trans_L.play();
                        }
                    }
                };

        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);
        root.getChildren().add(c);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
