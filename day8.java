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
        while (s.hasNextLine()) {
            input.add(s.nextLine());
        }
        for (String strings : input) {
            char segmentA = ' ', segmentB = ' ', segmentC = ' ', segmentD = ' ', segmentE = ' ', segmentF = ' ',
                    segmentG = ' ';

            String[] numbers = strings.split(" | ");
            String[] answer = { numbers[11], numbers[12], numbers[13], numbers[14] };
            for (int i = 10; i < 15; i++) {
                numbers[i] = "";
            }
            String twoChar = "", threeChar = "";
            for (String string : numbers) {
                switch (string.length()) {
                    case 2:
                        twoChar = string;
                        break;
                    case 3:
                        threeChar = string;
                        break;
                }
            }
            for (int i = 0; i < 2; i++)
                threeChar = threeChar.replace(twoChar.charAt(i), ' ');
            segmentA = threeChar.trim().charAt(0);

            HashMap<String, Integer> letters = new HashMap<>();
            for (String string : numbers) {
                for (int i = 0; i < string.length(); i++) {
                    letters.put(string.charAt(i) + "", letters.getOrDefault(string.charAt(i) + "", 0) + 1);
                }
            }

            for (Entry<String, Integer> entry : letters.entrySet()) {
                switch (entry.getValue()) {
                    case 6:
                        segmentB = entry.getKey().charAt(0);
                        break;
                    case 8:
                        if (entry.getKey().charAt(0) != segmentA) {
                            segmentC = entry.getKey().charAt(0);
                        }
                        break;
                    case 4:
                        segmentE = entry.getKey().charAt(0);
                        break;
                    case 9:
                        segmentF = entry.getKey().charAt(0);
                }
            }
            for (String string : numbers) {
                switch (string.length()) {
                    case 4:
                        string = string.replace(segmentB, ' ').replace(segmentC, ' ').replace(segmentF, ' ').trim();
                        segmentD = string.charAt(0);
                }
            }
            for (String string : numbers) {
                switch (string.length()) {
                    case 7:
                        string = string.replace(segmentA, ' ').replace(segmentB, ' ').replace(segmentC, ' ')
                                .replace(segmentD, ' ').replace(segmentE, ' ').replace(segmentF, ' ').trim();
                        segmentG = string.charAt(0);
                }
            }

            int one = 0, four = 0, seven = 0, eight = 0;
            for (String string : answer) {
                switch (string.length()) {
                    case 2:
                        one++;
                        break;
                    case 4:
                        four++;
                        break;
                    case 3:
                        seven++;
                        break;
                    case 7:
                        eight++;
                        break;
                }
            }
            String value = "";
            for (String string : answer) {
                String result = "";
                for (int i = 0; i < string.length(); i++) {
                    if (string.charAt(i) == segmentA) {
                        result += "a";
                    } else if (string.charAt(i) == segmentB) {
                        result += "b";
                    } else if (string.charAt(i) == segmentC) {
                        result += "c";
                    } else if (string.charAt(i) == segmentD) {
                        result += "d";
                    } else if (string.charAt(i) == segmentE) {
                        result += "e";
                    } else if (string.charAt(i) == segmentF) {
                        result += "f";
                    } else if (string.charAt(i) == segmentG) {
                        result += "g";
                    }
                }
                value += decodeString(result) + "";
            }
            total += one + four + seven + eight;
            sum += Integer.parseInt(value);
        }
        return exOne ? total : sum;
    }

    private static int decodeString(String input) {
        switch (input.length()) {
            case 2:
                return 1;
            case 4:
                return 4;
            case 3:
                return 7;
            case 7:
                return 8;
        }
        char[] charArray = input.toCharArray();
        Arrays.sort(charArray);
        String sorted = new String(charArray);
        switch (sorted) {
            case "abcefg":
                return 0;
            case "acdeg":
                return 2;
            case "acdfg":
                return 3;
            case "abdfg":
                return 5;
            case "abdefg":
                return 6;
            case "abcdfg":
                return 9;
        }
        return -1;
    }
}