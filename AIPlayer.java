import java.util.ArrayList;


public class AIPlayer{
    Board context;
    char mark;

    int bestX;
    int bestY;

    public AIPlayer(char mark, Board context){
        this.context = context;
        this.mark = mark;

        bestX = -1;
        bestY = -1;
    }
    
    public void playTurn(){
        minimax(context.getGame(), 2, true);

        context.placePiece(mark, bestX, bestY);
    }

    public int minimax(char[][] state, int depth, boolean maximizingPlayer){
        BoardState node = new BoardState(state);

        if(depth == 0 || new BoardState(state).terminalState()){
            return score(node);
        }

        if(maximizingPlayer){
            int bestValue = Integer.MIN_VALUE;
            ArrayList<int[]> moves = node.moves();

            for(int[] move : moves){
                state[move[0]][move[1]] = mark;

                int value = minimax(state, depth - 1, false);

                if(value > bestValue){
                    bestValue = value;
                    bestX = move[0];
                    bestY = move[1];
                }

                state[move[0]][move[1]] = '-';
            }

            return bestValue;
        }
        else{
            int bestValue = Integer.MAX_VALUE;
            ArrayList<int[]> moves = node.moves();

            for(int[] move : moves){
                state[move[0]][move[1]] = context.getOpposingMark(mark);

                int value = minimax(state, depth - 1, true);

                if(value < bestValue){
                    bestValue = value;
                    bestX = move[0];
                    bestY = move[1];
                }

                state[move[0]][move[1]] = '-';
            }

            return bestValue;
        }
    }

    private int score(BoardState node){ //Utility function. Block, stop forks, watch for winning setups.
        if(node.winner(mark)){
            return 10;
        }
        else if(node.winner(context.getOpposingMark(mark))){
            return -10;
        }
        else if(node.middleAndCorner(mark)){
            return 5;
        }
        else if(node.middleAndCorner(context.getOpposingMark(mark))){
            return -5;
        }
        else if(node.winningPosition(mark)){
            return 2;
        }
        else if(node.winningPosition(context.getOpposingMark(mark))){
            return -2;
        }
        else{
            return 0;
        }
    }
}