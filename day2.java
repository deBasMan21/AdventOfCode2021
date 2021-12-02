import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day2 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(excerciseOne());
        System.out.println(excerciseTwo());
    }

    public static int excerciseOne() throws FileNotFoundException {
        File f = new File("input.txt");
        Scanner s = new Scanner(f);

        int depth = 0;
        int hor = 0;

        while (s.hasNext()) {
            String key = s.next();
            String value = s.next();

            if (key.equals("up")) {
                depth -= Integer.valueOf(value);
            } else if (key.equals("down")) {
                depth += Integer.valueOf(value);
            } else if (key.equals("forward")) {
                hor += Integer.valueOf(value);
            }
        }

        return depth * hor;
    }

    public static int excerciseTwo() throws FileNotFoundException {
        File f = new File("input.txt");
        Scanner s = new Scanner(f);

        int aim = 0;
        int hor = 0;
        int depth = 0;

        while (s.hasNext()) {
            String key = s.next();
            String value = s.next();

            if (key.equals("up")) {
                aim -= Integer.valueOf(value);
            } else if (key.equals("down")) {
                aim += Integer.valueOf(value);
            } else if (key.equals("forward")) {
                hor += Integer.valueOf(value);
                depth += aim * Integer.valueOf(value);
            }
        }

        return depth * hor;
    }
}