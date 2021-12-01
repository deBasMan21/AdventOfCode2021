import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne());
        System.out.println(exerciseTwo());
    }

    public static int exerciseOne() throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner s = new Scanner(inputFile);

        int counter = 0;

        String current = "";
        String previous = "";

        while (s.hasNext()) {
            previous = current;
            current = s.next();

            if (current != "" && previous != "") {
                if (Integer.valueOf(previous) < Integer.valueOf(current)) {
                    counter++;
                }
            }
        }

        return counter;
    }

    public static int exerciseTwo() throws FileNotFoundException {
        File inputFile = new File("input.txt");
        Scanner s = new Scanner(inputFile);

        int counter = 0;

        String current = "";
        String previous = "";
        String preprevious = "";
        String oldest = "";

        while (s.hasNext()) {
            oldest = preprevious;
            preprevious = previous;
            previous = current;
            current = s.next();

            if (current != "" && previous != "" && preprevious != "" && oldest != "") {
                int sum1 = Integer.valueOf(current) + Integer.valueOf(previous) + Integer.valueOf(preprevious);
                int sum2 = Integer.valueOf(previous) + Integer.valueOf(preprevious) + Integer.valueOf(oldest);
                if (sum2 < sum1) {
                    counter++;
                }
            }
        }

        return counter;
    }
}