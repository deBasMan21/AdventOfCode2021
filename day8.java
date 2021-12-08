import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

public class day8 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean exOne) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        LinkedList<String> input = new LinkedList<>();
        int total = 0;
        while (s.hasNextLine()) input.add(s.nextLine());
        for (String strings : input) {
            char[] segments = { ' ', ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
            String[] numbers = strings.split(" | "), answer = { numbers[11], numbers[12], numbers[13], numbers[14] }, options = { "abcefg", "cf", "acdeg", "acdfg", "bcdf", "abdfg", "abdefg", "acf", "abcdefg", "abcdfg" }, segmentLetters = { "a", "b", "c", "d", "e", "f", "g" };
            int[] amounts = {0, 0, 0, 0, 0, 0, 0, 0};
            String twoChar = "", threeChar = "", value = "";
            HashMap<String, Integer> letters = new HashMap<>(), values = new HashMap<>(Map.of("8", 2, "4", 4, "9", 5, "6", 1));
            for (int i = 10; i < 15; i++) numbers[i] = "";
            for (String string : numbers)
                if (string.length() == 2) twoChar = string;
                else if (string.length() == 3) threeChar = string;
            segments[0] = threeChar.replace(twoChar.charAt(0), ' ').replace(twoChar.charAt(1), ' ').trim().charAt(0);
            for (String string : numbers)
                for (int i = 0; i < string.length(); i++) letters.put(string.charAt(i) + "", letters.getOrDefault(string.charAt(i) + "", 0) + 1);
            for (Entry<String, Integer> entry : letters.entrySet())
                segments[values.getOrDefault(entry.getValue().toString(), 7)] = entry.getKey().charAt(0) != segments[0] ? entry.getKey().charAt(0) : segments[values.get(entry.getValue() + "")];
            for (String string : numbers)
                if (string.length() == 4) segments[3] = string.replace(segments[1], ' ').replace(segments[2], ' ').replace(segments[5], ' ').trim().charAt(0);
            for (String string : numbers)
                if (string.length() == 7) segments[6] = string.replace(segments[0], ' ').replace(segments[1], ' ').replace(segments[2], ' ').replace(segments[3], ' ').replace(segments[4], ' ').replace(segments[5], ' ').trim().charAt(0);
            for (String string : answer) amounts[string.length()] += 1;
            for (String string : answer) {
                String result = "";
                for (int i = 0; i < string.length(); i++)
                    for (int j = 0; j < 7; j++)
                        if (string.charAt(i) == segments[j]) result += segmentLetters[j];
                char[] charArray = result.toCharArray();
                Arrays.sort(charArray);
                for (int i = 0; i < options.length; i++)
                    if (new String(charArray).equals(options[i])) value += i + "";
            }
            total += exOne ? amounts[2] + amounts[4] + amounts[3] + amounts[7] : Integer.parseInt(value);
        }
        return total;
    }
}
