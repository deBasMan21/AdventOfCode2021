import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map.Entry;

public class day7 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean one) throws FileNotFoundException {
        String[] input = new Scanner(new File("input.txt")).nextLine().split(",");
        HashMap<Integer, Integer> crabs = new HashMap<>();
        for (String string : input)
            crabs.put(Integer.parseInt(string),
                    crabs.get(Integer.parseInt(string)) != null ? crabs.get(Integer.parseInt(string)) + 1 : 1);
        int amount = 0;
        for (int i = 1; i < 2000; i++) {
            int fuel = 0;
            for (Entry<Integer, Integer> crab : crabs.entrySet())
                fuel += one ? Math.abs(crab.getKey() - i) * crab.getValue()
                        : Math.abs(crab.getKey() - i) * (Math
                                .abs(crab.getKey() - i) + 1) / 2 * crab.getValue();
            amount = fuel < amount || amount == 0 ? fuel : amount;
        }
        return amount;
    }
}
