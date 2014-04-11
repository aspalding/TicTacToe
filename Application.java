import java.util.Scanner;


public class Application{
    public static void main(String[] x){
        System.out.println("To play the game, enter two coordinates separated by a space. " +
                "For example, on a 3x3 board, 1 1 would place a mark in the center of the board.");

        Board game = new Board(3);
        BoardState check = new BoardState(game.getGame());

        Scanner kbd = new Scanner(System.in);

        System.out.println("Would you like to go first? (y,n)");
        String choice = kbd.next();

        if(choice.equalsIgnoreCase("y")){
            Player human = new Player('x', game);
            AIPlayer ai = new AIPlayer('o', game);

            while(!check.terminalState()){
                System.out.println(game + "\nEnter coordinates (x,y):");
                human.playTurn();

                if(new BoardState(game.getGame()).winner('x')){
                    System.out.println(game + "\nX IS WINNER");
                    System.exit(0);
                }

                System.out.println(game);
                ai.playTurn();

                if(new BoardState(game.getGame()).winner('o')){
                    System.out.println(game + "\nO IS WINNER");
                    System.exit(0);
                }
            
                check = new BoardState(game.getGame());
            }
        }
        else{
            AIPlayer ai = new AIPlayer('x', game);
            Player human = new Player('o', game);

            while(!check.terminalState()){
                System.out.println(game);
                ai.playTurn();

                if(new BoardState(game.getGame()).winner('x')){
                    System.out.println(game + "\nX IS WINNER");
                    System.exit(0);
                }

                System.out.println(game + "\nEnter coordinates (x,y):");
                human.playTurn();

                if(new BoardState(game.getGame()).winner('o')){
                    System.out.println(game + "\nO IS WINNER");
                    System.exit(0);
                }

                check = new BoardState(game.getGame());
            }
        }

        System.out.println(game + "\nTie game.");
        System.exit(0);
    }
}