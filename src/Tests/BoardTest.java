package Tests;
import Frogger.Board;
import Frogger.Frog;
import javafx.scene.Group;
import javafx.scene.Scene;
import junit.framework.TestCase;

public class BoardTest extends TestCase {
    private final int l_case=50;
    private final int nb_case=12;

//    Frog frog = new Frog((this.nb_case) * this.l_case /2 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case, "frog8bit.png");
//    Frog frog2 = new Frog(0 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case, "frog8bit2.png");

    Group root = new Group();
//    Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*(this.nb_case+1));
//    Board board = new Board(root, this.nb_case, this.l_case);

    public void testInit(){
        Board board = new Board(root, this.nb_case, this.l_case);
        assertNotNull(board);
        assertSame("Frogger.Board", board.getClass().getTypeName());
    }

    public void testgetChrono(){

    }

    public void testgetChronoToFloat(){

    }

    public void testaddLane(){

    }

    public void testgetGridPane(){

    }

    public void testdecalage(){

    }

    public void testauto_down(){

    }

    public void testinvert(){

    }




}
