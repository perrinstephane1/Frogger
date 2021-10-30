package Tests;

import com.company.Board;
import javafx.scene.Group;
import junit.framework.TestCase;



public class BoardTest extends TestCase {
    public void testInit() {
        Group root = new Group();
        Board board = new Board(root, 4, 20);
    }
}
