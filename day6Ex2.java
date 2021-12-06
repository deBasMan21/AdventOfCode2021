import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class day6Ex2 {

    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseTwo());
    }

    public static long exerciseTwo() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        String[] input = s.nextLine().split(",");
        HashMap<Integer, Long> fish = new HashMap<>();
        for (String string : input) {
            fish.put(Integer.parseInt(string),
                    fish.get(Integer.parseInt(string)) != null ? fish.get(Integer.parseInt(string)) + 1L : 1L);
        }
        long zero = 0;
        for (int i = 0; i < 256; i++) {
            zero = fish.get(0) != null ? fish.get(0) : 0;
            fish.put(0, fish.get(1) != null ? fish.get(1) : 0);
            fish.put(1, fish.get(2) != null ? fish.get(2) : 0);
            fish.put(2, fish.get(3) != null ? fish.get(3) : 0);
            fish.put(3, fish.get(4) != null ? fish.get(4) : 0);
            fish.put(4, fish.get(5) != null ? fish.get(5) : 0);
            fish.put(5, fish.get(6) != null ? fish.get(6) : 0);
            fish.put(6, (fish.get(7) != null ? fish.get(7) : 0) + zero);
            fish.put(7, fish.get(8) != null ? fish.get(8) : 0);
            fish.put(8, zero);
        }
        long value = 0L;
        for (Map.Entry<Integer, Long> entry : fish.entrySet()) {
            value += entry.getValue();
        }
        return value;
    }
}
