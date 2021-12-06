import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day6 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static long exerciseOne(boolean one) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        String[] input = s.nextLine().split(",");
        HashMap<Integer, Long> fish = new HashMap<>();
        for (String string : input) fish.put(Integer.parseInt(string), fish.get(Integer.parseInt(string)) != null ? fish.get(Integer.parseInt(string)) + 1L : 1L);
        long zero = 0, value = 0;
        for (int i = 0; i < (one ? 80 : 256); i++) {
            zero = fish.get(0) != null ? fish.get(0) : 0;
            for (int j = 0; j < 9; j++)
                if (j == 6) fish.put(j, (fish.get(j + 1) != null ? fish.get(j + 1) : 0) + zero);
                else if (j == 8) fish.put(j, zero);
                else fish.put(j, fish.get(j + 1) != null ? fish.get(j + 1) : 0);
        }
        return fish.values().stream().mapToLong(Long::longValue).sum();
    }
}
