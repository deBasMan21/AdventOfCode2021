import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;

public class day1 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne());
        System.out.println(exerciseTwo());
    }

    public static int exerciseOne() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        int counter = 0;
        String current = "", previous = "";
        while (s.hasNext()) {
            previous = current;
            current = s.next();
            if (current != "" && previous != "")
                if (Integer.valueOf(previous) < Integer.valueOf(current))
                    counter++;
        }
        return counter;
    }

    public static int exerciseTwo() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        int counter = 0;
        LinkedList<String> list = new LinkedList<>();
        while (s.hasNext()) {
            list.add(s.next());
            if (list.size() > 4) {
                list.removeFirst();
                if ((Integer.valueOf(list.get(2)) + Integer.valueOf(list.get(1))
                        + Integer.valueOf(list.get(0))) < (Integer.valueOf(list.get(3)) + Integer.valueOf(list.get(2))
                                + Integer.valueOf(list.get(1))))
                    counter++;
            }
        }
        return counter;
    }
}