import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class day4 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne());
        System.out.println(exerciseTwo());
    }

    public static int exerciseOne() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        String[] numberOrder = s.nextLine().split(",");
        LinkedList<int[][]> cards = createCards();
        int indexOfQuickest = 0, quickestAmount = 100, lastNumber = 0;
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < numberOrder.length; j++) {
                cards.set(i, testNumber(cards.get(i), Integer.parseInt(numberOrder[j])));
                if (testWin(cards.get(i)) && j < quickestAmount) {
                    lastNumber = Integer.parseInt(numberOrder[j]);
                    indexOfQuickest = i;
                    quickestAmount = j;
                    j = numberOrder.length - 1;
                }
            }
        }
        return calculateScore(cards.get(indexOfQuickest)) * lastNumber;
    }

    public static int exerciseTwo() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        String[] numberOrder = s.nextLine().split(",");
        LinkedList<int[][]> cards = createCards();
        int indexOfQuickest = 0, slowest = 0, lastNumber = 0;
        for (int i = 0; i < cards.size(); i++) {
            for (int j = 0; j < numberOrder.length; j++) {
                cards.set(i, testNumber(cards.get(i), Integer.parseInt(numberOrder[j])));
                if (testWin(cards.get(i))) {
                    if (j > slowest) {
                        slowest = j;
                        lastNumber = Integer.parseInt(numberOrder[j]);
                        indexOfQuickest = i;
                    }
                    j = numberOrder.length - 1;
                }
            }
        }
        return calculateScore(cards.get(indexOfQuickest)) * lastNumber;
    }

    private static LinkedList<int[][]> createCards() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        s.nextLine();
        LinkedList<int[][]> cards = new LinkedList<>();
        while (s.hasNext()) {
            int[][] bingoCard = new int[5][5];
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    bingoCard[i][j] = Integer.parseInt(s.next());
                }
            }
            cards.add(bingoCard);
        }
        return cards;
    }

    private static boolean testWin(int[][] card) {
        for (int k = 0; k < 5; k++) {
            int valueHor = 0, valueVer = 0;
            for (int l = 0; l < 5; l++) {
                valueHor += card[k][l];
                valueVer += card[l][k];
            }
            if (valueVer == -5 || valueHor == -5)
                return true;
        }
        return false;
    }

    private static int[][] testNumber(int[][] card, int num) {
        for (int k = 0; k < 5; k++) {
            for (int l = 0; l < 5; l++) {
                if (card[k][l] == num)
                    card[k][l] = -1;
            }
        }
        return card;
    }

    private static int calculateScore(int[][] card) {
        int score = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (card[i][j] != -1)
                    score += card[i][j];
            }
        }
        return score;
    }
}