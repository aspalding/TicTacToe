import org.junit.Test;


public class BoardTest {

    @Test
    public void testIsValid() throws Exception {
        Board game = new Board(3);
        assert !game.isValid(3,3);
        assert !game.isValid(-1,-1);

        assert game.isValid(2,2);
        assert game.isValid(0,0);
    }

    @Test
    public void testGetOpposingMark() throws Exception {
        Board game = new Board(3);
        assert game.getOpposingMark('x') == 'o';
        assert game.getOpposingMark('o') == 'x';

        assert game.getOpposingMark('x') != 'x';
        assert game.getOpposingMark('o') != 'o';
    }
}
