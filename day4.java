import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class day4 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean one) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        String[] numberOrder = s.nextLine().split(",");
        LinkedList<int[][]> cards = new LinkedList<>();
        int indexOfQuickest = 0, quickestAmount = one ? 100 : 0, lastNumber = 0, i = 0, score = 0;
        while (s.hasNext()) {
            int[][] bingoCard = new int[5][5];
            for (int k = 0; k < 5; k++)
                for (int j = 0; j < 5; j++)
                    bingoCard[k][j] = Integer.parseInt(s.next());
            cards.add(bingoCard);
            for (int j = 0; j < numberOrder.length; j++) {
                for (int k = 0; k < 5; k++)
                    for (int l = 0; l < 5; l++)
                        if (cards.get(i)[k][l] == Integer.parseInt(numberOrder[j]))
                            cards.get(i)[k][l] = -1;
                boolean win = false;
                for (int k = 0; k < 5; k++) {
                    int valueHor = 0, valueVer = 0;
                    for (int l = 0; l < 5; l++) {
                        valueHor += cards.get(i)[k][l];
                        valueVer += cards.get(i)[l][k];
                    }
                    if (valueVer == -5 || valueHor == -5)
                        win = true;
                }
                if (win) {
                    lastNumber = one ? (j < quickestAmount ? Integer.parseInt(numberOrder[j]) : lastNumber)
                            : (j > quickestAmount ? Integer.parseInt(numberOrder[j]) : lastNumber);
                    indexOfQuickest = one ? (j < quickestAmount ? i : indexOfQuickest)
                            : (j > quickestAmount ? i : indexOfQuickest);
                    if (!one) {
                        quickestAmount = j > quickestAmount ? j : quickestAmount;
                        j = numberOrder.length - 1;
                    } else if (j < quickestAmount) {
                        quickestAmount = j;
                        j = numberOrder.length - 1;
                    }
                }
            }
            i++;
        }
        for (int k = 0; k < 5; k++)
            for (int j = 0; j < 5; j++)
                if (cards.get(indexOfQuickest)[k][j] != -1)
                    score += cards.get(indexOfQuickest)[k][j];
        return score * lastNumber;
    }
}