import java.util.ArrayList;


public class BoardState{
    char[][] state;

    int need; //Amount of marks in a row needed to win.
    int dia_x;
    int dia_y;
    
    public BoardState(char[][] game){
        state = game;

        need = state.length;

        dia_x = 0;
        dia_y = state.length - 1;
    }
    
    public boolean winner(char mark){ //If a player has need marks, return true. (Used in utility and to determine outcome of game)
        return checkHor(mark) == need || checkVer(mark) == need || checkDia1(mark) == need || checkDia2(mark) == need;
    }

    public boolean winningPosition(char mark){ //If a player has need - 1, return true. (Used for utility function)
        return checkHor(mark) == need - 1 || checkVer(mark) == need - 1 || checkDia1(mark) == need - 1 || checkDia2(mark) == need - 1;
    }

    public boolean middleAndCorner(char mark){ //Stops forking moves. (Used for utility function)
        if(state[(state.length - 1)/2][(state.length - 1)/2] == mark)
            return state[0][0] == mark && state[0][state.length - 1] == mark || state[state.length - 1][0] == mark && state[state.length - 1][state.length - 1] == mark
                    || state[0][0] == mark && state[state.length - 1][0] == mark || state[0][state.length - 1] == mark && state[state.length - 1][state.length - 1] == mark;

        return false;
    }

    public boolean terminalState(){ //If the board is full or has a winner, return true.
        return isFull() || winner('x') || winner('o');
    }

    public boolean isFull(){ //Count all the marks if the board is full the game is over.
        int count = 0;

        for(int x = 0; x < state.length; x++)
            for(int y = 0; y < state.length; y++)
                if(state[x][y] != '-')
                    count ++;

        return count == Math.pow(state.length, 2);
    }

    public ArrayList<int[]> moves(){ //Generates all moves for a given board state.
        ArrayList<int[]> moves = new ArrayList<int[]>();
        
        for(int i = 0; i < state.length; i++)
            for(int j = 0; j < state.length; j++)
                if(isValid(i, j)){
                    int[] newMove = new int[2];
                    newMove[0] = i;
                    newMove[1] = j;
                    moves.add(newMove);
                }
        return moves;
    }
    
    private boolean isValid(int x, int y){ 
        return x >= 0 && x < state.length && y >= 0 && y < state.length && state[x][y] == '-';
    }
    
    private int checkHor(char mark){ //Count horizontal.
        int count = 0;

        for(int i = 0; i < state.length; i++){
            count = 0;
            for(int j = 0; j < state.length; j++){
                if(state[i][j] == mark)
                    count++;
                if(count == need)
                    return count;
            }
        }
        return count;
    }

    private int checkVer(char mark){ //Count vertical.
        int count = 0;

        for(int i = 0; i < state.length; i++){
            count = 0;
            for(int j = 0; j < state.length; j++){
                if(state[j][i] == mark)
                    count++;
                if(count == need)
                    return count;
            }
        }
        return count;
    }

    private int checkDia1(char mark){ //Count first diagonal.
        int count = 0;

        for(int i = 0; i < state.length; i++){
            if(state[i][i] == mark)
                count ++;
        }

        return count;
    }

    private int checkDia2(char mark){ //Count second diagonal.
        int count = 0;

        int nx = dia_x;
        int ny = dia_y;

        while(nx < state.length && ny >= 0){ 
            if(state[nx][ny] == mark)
                count ++;

            nx++;
            ny--;
        }

        return count;
    }

    public String toString(){ //Handy string representation of the game.
        String board = "  ";

        for(int i = 0; i < state.length; i++)
            board += i + " ";

        board += "\n";

        for(int x = 0; x < state.length; x++){
            board += x + " ";
            for(int y = 0; y < state.length; y++){
                board += state[x][y] + " ";
            }
            board += "\n";
        }
        return board;
    }
    
}