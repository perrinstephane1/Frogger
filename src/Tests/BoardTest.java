package Tests;
import frogger.Board;
import frogger.Lane;
import javafx.scene.Group;
import javafx.scene.text.Text;
import junit.framework.TestCase;

public class BoardTest extends TestCase {

//    Frog frog = new Frog((this.nb_case) * this.l_case /2 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case, "frog8bit.png");
//    Frog frog2 = new Frog(0 , (this.nb_case -1)* this.l_case, this.l_case, this.nb_case, "frog8bit2.png");

    Group root = new Group();
//    Scene scene = new Scene(root, this.l_case*this.nb_case, this.l_case*(this.nb_case+1));
//    Board board = new Board(root, this.nb_case, this.l_case);

    public void testInit(){
        int l_case=50;
        int nb_case=12;
        Board board = new Board(nb_case, l_case);
        assertNotNull(board);
        assertSame("frogger.Board", board.getClass().getTypeName());
    }

    public void testgetChrono() throws InterruptedException {
        int l_case=50;
        int nb_case=12;
        Board board = new Board(nb_case, l_case);
        board.chrono.start();
        Thread.sleep(1000);
        board.chrono.stop();
        Text text = new Text("Time : 1,0 s");
        assertEquals(text, board.getChrono());
    }

    public void testgetChronoToFloat() throws InterruptedException {
        int l_case=50;
        int nb_case=12;
        Board board = new Board(nb_case, l_case);
        board.chrono.start();
        Thread.sleep(1000);
        board.chrono.stop();

        float time = board.chrono.getElapsedCenti();
        assertEquals(100.0, time);
    }

    public void testaddLane(){
        int l_case=50;
        int nb_case=12;
        Board board = new Board(nb_case, l_case);
        Lane lane = new Lane(13, board,  13%2, 1, Math.random(), 1);
        board.add(lane);
        assertEquals(1.0, board.get(13).direction);
        assertEquals(1,board.get(13).type_lane);
        assertEquals(13, board.get(13).numero_lane);
        assertEquals(1.0, board.get(13).speed);

        lane = new Lane(14, board,  14%2, 3, Math.random(), 2);
        board.add(lane);
        assertEquals(0.0, board.get(14).direction);
        assertEquals(2,board.get(14).type_lane);
        assertEquals(14, board.get(14).numero_lane);
        assertEquals(3.0, board.get(14).speed);

        lane = new Lane(15, board,  15%2, 7, Math.random(), 3);
        board.add(lane);
        assertEquals(1.0, board.get(15).direction);
        assertEquals(3,board.get(15).type_lane);
        assertEquals(15, board.get(15).numero_lane);
        assertEquals(7.0, board.get(15).speed);
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
