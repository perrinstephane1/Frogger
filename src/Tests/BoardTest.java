import javafx.scene.Group;
import junit.framework.TestCase;

//public class ihm extends Application {
//    Group root = new Group();
//}


public class BoardTest extends TestCase{
    public void testInit() {

        Group root = new Group();
        Board board = new Board(root, 4, 20);
    }
}
