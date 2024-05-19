package SimplifiedBlackjack;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;

public class Game {
    private static String[] deckTitles = {"Ace", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten", "Jack", "Queen", "King"};
    private static int[] deckValues =    {1,     2,     3,       4,      5,      6,     7,       8,       9,      10,    11,     12,      13};
    private static final Scanner sc = new Scanner(System.in);
    private ArrayList<Player> players = new ArrayList<Player>();
    private int target;

    public Game() {
        players = new ArrayList<Player>();
        target = (int) (Math.random()*6+25);
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void startGame() {
        System.out.println("Starting game...");
        while (playersDrawing()) {
            for (Player player : players) {
                if (player.isDrawing()) {
                    player.incrementRounds();
                    int cardIndex = (int) (Math.random() * 13);
                    player.addCard(deckTitles[cardIndex], deckValues[cardIndex]);
                    player.printHand();
                    if (player.getHandValue() > target) {
                        System.out.printf("%s, you went over the target.\n", player.getName());
                        player.stopDrawing();
                    } else {
                        System.out.printf("%s, do you want to stop drawing? (y/n) ", player.getName());
                        if (sc.nextLine().equals("y")) {
                            player.stopDrawing();
                        }
                    }
                    
                    System.out.println();
                }
            }
        }
        for (Player player : players) {
            System.out.printf("%s's hand: %d\n", player.getName(), player.getHandValue());
        }
        Player winner = evaluateWinner();
        System.out.printf("The target value was %d.\n", target);
        System.out.printf("The winner is %s with a hand value of %d!\n", winner.getName(), winner.getHandValue());
    }

    public boolean playersDrawing() {
        for (Player player : players) {
            if (player.isDrawing()) {
                return true;
            }
        }
        return false;
    }

    public int calcDifference(int value) {
        return Math.abs(target - value);
    }

    public Player evaluateWinner() {
        Player winner = players.get(0);
        int winnerValue = winner.getHandValue();
        for (Player player : players) {
            int currValue = player.getHandValue();
            if (player.getHand().indexOf("Ace") != -1 && calcDifference(currValue + 13) < calcDifference(currValue)) {
                System.out.println(player.getName() + " has an Ace that can be counted as 14 (Bringing them closer to the target).");
                player.increaseHandValue(13);
            }
            if (calcDifference(currValue) < calcDifference(winnerValue)) {
                winner = player;
            } else if (calcDifference(currValue) == calcDifference(winnerValue) && player.getRounds() < winner.getRounds()) { // Line 77
                winner = player;
            }
        }
        return winner;
    }
}
