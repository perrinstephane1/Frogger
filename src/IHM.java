import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class IHM extends Application {
    private double l_case=70;
    private int nb_case=12;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*this.nb_case);
        scene.setFill(Color.web("#81c483"));
        Frog frog = new Frog(0, 0, this.l_case, this.nb_case);
        Plateau plateau = new Plateau(root, this.nb_case, this.l_case, this.l_case*this.nb_case);

        EventHandler<KeyEvent> keyListener = e -> {
            if(e.getCode()== KeyCode.UP){
                System.out.println(frog.getCoord());
                frog.up();
            }
            if(e.getCode()==KeyCode.DOWN){
                System.out.println(frog.getCoord());
                frog.down();
            }
            if(e.getCode()==KeyCode.RIGHT){
                System.out.println(frog.getCoord());
                frog.right();
            }
            if(e.getCode()==KeyCode.LEFT){
                System.out.println(frog.getCoord());
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
