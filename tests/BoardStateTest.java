import org.junit.Test;

import java.util.ArrayList;


public class BoardStateTest {
    @Test
    public void testWinner() throws Exception {
        char[][] winner = {
                {'x', 'x', '-'},
                {'o','x','o'},
                {'x','o','x'}
        };

        BoardState game = new BoardState(winner);
        assert game.winner('x');
        assert !game.winner('o');
    }

    @Test
    public void testWinningPosition() throws Exception {
        char[][] winningPosition = {
                {'x', 'x', '-'},
                {'-','-','-'},
                {'x','o','x'}
        };

        BoardState game = new BoardState(winningPosition);
        assert game.winningPosition('x');
        assert !game.winningPosition('o');
    }

    @Test
    public void testTerminalState() throws Exception {
        char[][] winningPosition = {
                {'x', 'o', '-'},
                {'-','x','-'},
                {'o','o','x'}
        };

        BoardState winGame = new BoardState(winningPosition);
        assert winGame.terminalState();

        char[][] tiePosition = {
                {'x', 'o', 'x'},
                {'x','x','o'},
                {'o','o','x'}
        };

        BoardState tieGame = new BoardState(tiePosition);
        assert tieGame.terminalState();

        char[][] notTerminal = {
                {'x', 'o', 'x'},
                {'x','-','o'},
                {'-','o','x'}
        };

        BoardState notOver = new BoardState(notTerminal);
        assert !notOver.terminalState();
    }

    @Test
    public void testIsFull() throws Exception {
        char[][] full = {
                {'o', 'o', 'x'},
                {'x','x','o'},
                {'o','x','x'}
        };

        BoardState fullGame = new BoardState(full);
        assert fullGame.isFull();

        char[][] notFull = {
                {'x', 'o', 'x'},
                {'-','-','-'},
                {'o','o','x'}
        };

        BoardState notFullGame = new BoardState(notFull);
        assert !notFullGame.isFull();
    }

    @Test
    public void testMoves() throws Exception {
        char[][] winner = {
                {'x', 'x', 'o'},
                {'o','-','o'},
                {'x','o','x'}
        };

        BoardState game = new BoardState(winner);
        ArrayList<int[]> moves = new ArrayList<int[]>(game.moves());
        assert moves.get(0)[0] == 1 && moves.get(0)[1] == 1;
    }
}
