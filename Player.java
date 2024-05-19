package SimplifiedBlackjack;
import java.util.ArrayList;
import java.util.Scanner;

public class Player {
    private static final Scanner sc = new Scanner(System.in);
    private static int playerCount = 0;
    private String name;
    private ArrayList<String> hand;
    private ArrayList<Integer> handValues;
    private int rounds;
    private int handValue;
    private boolean drawing;

    public Player() {
        System.out.printf("Enter player %d's name: ", ++playerCount);
        name = sc.nextLine();
        hand = new ArrayList<String>();
        handValues = new ArrayList<Integer>();
        drawing = true;
        rounds = 0;
    }

    public String getName() {
        return name;
    }

    public boolean isDrawing() {
        return drawing;
    }

    public void stopDrawing() {
        drawing = false;
    }

    public void incrementRounds() {
        rounds++;
    }

    public int getRounds() {
        return rounds;
    }

    public void addCard(String card, int value) {
        hand.add(card);
        handValues.add(value);
        handValue += value;
    }

    public void increaseHandValue(int value) {
        handValue += value;
    }

    public ArrayList<String> getHand() {
        return hand;
    }

    public int getHandValue() {
        int total = 0;
        for (int value : handValues) {
            total += value;
        }
        return total;
    }

    public void printHand() {
        System.out.println(name + "'s hand:\n");
        for (int i = 0; i < hand.size(); i++) {
            System.out.printf(" - %s: %s\n", hand.get(i), handValues.get(i));
        }
        System.out.println("\nTotal: " + getHandValue());
    }


}
