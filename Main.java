package SimplifiedBlackjack;
import java.util.Scanner;

public class Main {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String args[]) {
        Game game = new Game();
        System.out.println("Welcome to Simplified Blackjack!");
        System.out.print("Enter the number of players: ");
        int playerCount = sc.nextInt();
        for (int i = 0; i < playerCount; i++) {
            game.addPlayer(new Player());
        }
        game.startGame();
        

    }
}