public class Board{
    private char[][] game;

    public Board(int size){
        game = new char[size][size];

        for(int x = 0; x < game.length; x++)
            for(int y = 0; y < game.length; y++)
                game[x][y] = '-';
    }
    
    public void placePiece(char mark, int x, int y){
        game[x][y] = mark;
    }
    
    public boolean isValid(int x, int y){ //A move is valid if it is within the range of the board and does not have a mark already.
        return x >= 0 && x < game.length && y >= 0 && y < game.length && game[x][y] == '-';
    }
    
    public char[][] getGame(){ return game; }

    public char getOpposingMark(char mark){ //Takes the mark of player returns opponent.
        if(mark == 'x')
            return 'o';
        else
            return 'x';
    }
    
    public String toString(){ //Handy string representation of the game.
        String board = "  ";

        for(int i = 0; i < game.length; i++)
            board += i + " ";

        board += "\n";

        for(int x = 0; x < game.length; x++){
            board += x + " ";
            for(int y = 0; y < game.length; y++){
                board += game[x][y] + " ";
            }
            board += "\n";
        }
        return board;
    }
}