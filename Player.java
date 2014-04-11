import java.util.Scanner;


public class Player{
    Board context;
    char mark;
    
    Scanner kbd;
    
    public Player(char mark, Board context){
        this.context = context;
        this.mark = mark;
        
        kbd = new Scanner(System.in);
    }
    
    public void playTurn(){
        int x = kbd.nextInt();
        int y = kbd.nextInt();

        if(context.isValid(x,y))
            context.placePiece(mark, x, y);
        else{
            System.out.println("Please enter a valid coordinate.");
            playTurn();
        }
    }
    
}