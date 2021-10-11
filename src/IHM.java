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
    private int nb_case=15;

    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        Group root = new Group();
        Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*this.nb_case);
        scene.setFill(Color.web("#81c483"));

        Group root_end = new Group();
        Scene the_end = new Scene(root_end, this.l_case*this.nb_case, this.l_case*this.nb_case);
        the_end.setFill(Color.web("#d13318"));

        Frog frog = new Frog(0, 0, this.l_case, this.nb_case);
        Plateau plateau = new Plateau(root, this.nb_case, this.l_case, this.l_case*this.nb_case);
        Voiture voiture = new Voiture(plateau.get(1),scene, this.l_case);


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

                voiture.move();
            }
            if(e.getCode()==KeyCode.LEFT){
                frog.setDead(true);
                System.out.println(frog.getCoord());
                frog.left();
            }
            this.check_end(frog, primaryStage, the_end);
        };


        scene.addEventHandler(KeyEvent.KEY_PRESSED,keyListener);

        plateau.display();
        root.getChildren().add(frog.getImageView());
//        root.getChildren().add(voiture.getImageView());

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public void check_end(Frog frog, Stage primaryStage, Scene scene) {
        if (frog.isDead()) {
            primaryStage.setScene(scene);
        }
    }
}
