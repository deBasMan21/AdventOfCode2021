import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
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
        int total = 0, sum = 0;
        while (s.hasNextLine()) input.add(s.nextLine());
        for (String strings : input) {
            char[] segments = { ' ', ' ', ' ', ' ', ' ', ' ', ' ' };
            String[] numbers = strings.split(" | "), answer = { numbers[11], numbers[12], numbers[13], numbers[14] };
            for (int i = 10; i < 15; i++) numbers[i] = "";
            String twoChar = "", threeChar = "";
            for (String string : numbers)
                if (string.length() == 2) twoChar = string;
                else if (string.length() == 3) threeChar = string;
            for (int i = 0; i < 2; i++) threeChar = threeChar.replace(twoChar.charAt(i), ' ');
            segments[0] = threeChar.trim().charAt(0);
            HashMap<String, Integer> letters = new HashMap<>();
            for (String string : numbers)
                for (int i = 0; i < string.length(); i++) letters.put(string.charAt(i) + "", letters.getOrDefault(string.charAt(i) + "", 0) + 1);
            for (Entry<String, Integer> entry : letters.entrySet())
                if (entry.getValue() == 6) segments[1] = entry.getKey().charAt(0);
                else if (entry.getValue() == 8 && entry.getKey().charAt(0) != segments[0]) segments[2] = entry.getKey().charAt(0);
                else if (entry.getValue() == 4) segments[4] = entry.getKey().charAt(0);
                else if (entry.getValue() == 9) segments[5] = entry.getKey().charAt(0);
            for (String string : numbers)
                if (string.length() == 4) segments[3] = string.replace(segments[1], ' ').replace(segments[2], ' ').replace(segments[5], ' ').trim().charAt(0);
            for (String string : numbers)
                if (string.length() == 7) segments[6] = string.replace(segments[0], ' ').replace(segments[1], ' ').replace(segments[2], ' ').replace(segments[3], ' ').replace(segments[4], ' ').replace(segments[5], ' ').trim().charAt(0);
            int one = 0, four = 0, seven = 0, eight = 0;
            for (String string : answer)
                if (string.length() == 2) one++;
                else if (string.length() == 4) four++;
                else if (string.length() == 3) seven++;
                else if (string.length() == 7) eight++;
            String value = "";
            for (String string : answer) {
                String result = "";
                for (int i = 0; i < string.length(); i++)
                    if (string.charAt(i) == segments[0]) result += "a";
                    else if (string.charAt(i) == segments[1]) result += "b";
                    else if (string.charAt(i) == segments[2]) result += "c";
                    else if (string.charAt(i) == segments[3]) result += "d";
                    else if (string.charAt(i) == segments[4]) result += "e";
                    else if (string.charAt(i) == segments[5]) result += "f";
                    else if (string.charAt(i) == segments[6]) result += "g";
                value += decodeString(result) + "";
            }
            total += one + four + seven + eight;
            sum += Integer.parseInt(value);
        }
        return exOne ? total : sum;
    }

    private static int decodeString(String input) {
        if (input.length() == 2) return 1;
        else if (input.length() == 4) return 4;
        else if (input.length() == 3) return 7;
        else if (input.length() == 7) return 8;
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        if (new String(charArray).equals("abcefg")) return 0;
        else if (new String(charArray).equals("acdeg")) return 2;
        else if (new String(charArray).equals("acdfg")) return 3;
        else if (new String(charArray).equals("abdfg")) return 5;
        else if (new String(charArray).equals("abdefg")) return 6;
        else if (new String(charArray).equals("abcdfg")) return 9;
        else return -1;
    }
}
