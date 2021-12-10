import java.io.*;
import java.util.*;

public class day10 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static Long exerciseOne(boolean one) throws FileNotFoundException{
        Scanner s = new Scanner(new File("input.txt"));
        int score = 0;
        ArrayList<Long> scores = new ArrayList<>();
        HashMap<String, Integer> characterValues = new HashMap<>(Map.of(")", 3, "]", 57, "}", 1197, ">", 25137)), characterValuesExTwo = new HashMap<>(Map.of("(", 1, "[", 2, "{", 3, "<", 4));
        HashMap<String, String> characterOpposites = new HashMap<>(Map.of("(", ")", "[", "]", "{", "}", "<", ">"));
        while(s.hasNextLine()){
            String[] inputLine = s.nextLine().split("");
            Stack<String> charStack = new Stack<>();
            boolean isCorrupt = false;
            for (String string : inputLine)
                if(string.equals("(") || string.equals("[") || string.equals("{") || string.equals("<")) charStack.push(string);
                else if(characterOpposites.get(charStack.peek()).equals(string)) charStack.pop();
                else {
                    score += characterValues.get(string);
                    isCorrupt = true;
                    break;
                }
            long subScore = 0;
            for(int i = charStack.size(); i > 0; i--) subScore = !isCorrupt ? subScore * 5 + characterValuesExTwo.get(charStack.pop()) : subScore;
            if(subScore > 0) scores.add(subScore);
        }
        Collections.sort(scores);
        return one ? score : scores.get(scores.size() / 2);
    }
}
