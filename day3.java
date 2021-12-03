import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

public class day3 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(excerciseOne());
        System.out.println(exerciseTwo(true) * exerciseTwo(false));
    }

    public static int excerciseOne() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        LinkedList<HashMap<Integer, Integer>> bits = new LinkedList<>();
        String highest = "", lowest = "";
        for (int i = 0; i < 12; i++) bits.add(new HashMap<Integer, Integer>(Map.of(1, 0, 0, 0)));
        while (s.hasNext()) {
            String[] input = s.next().split("");
            for (int i = 0; i < input.length; i++) bits.get(i).put(Integer.valueOf(input[i]), bits.get(i).get(Integer.valueOf(input[i])) + 1);
        }
        for (int i = 0; i < 12; i++)
            if (Math.max(bits.get(i).get(0), bits.get(i).get(1)) == bits.get(i).get(0)) {
                highest += "1";
                lowest += "0";
            } else {
                highest += "0";
                lowest += "1";
            }
        return Integer.parseInt(highest, 2) * Integer.parseInt(lowest, 2);
    }

    public static int exerciseTwo(boolean one) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        LinkedList<String> list = new LinkedList<>(), listOne = new LinkedList<>(), listTwo = new LinkedList<>();
        while (s.hasNext()) list.add(s.next());
        int amountOne = 0, amountTwo = 0;
        for (int j = 0; j < 12; j++) {
            if (amountOne + amountTwo == 1) continue;
            amountOne = amountTwo = 0;
            listOne = new LinkedList<>();
            listTwo = new LinkedList<>();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i).charAt(j) == '1') {
                    amountOne++;
                    listOne.add(list.get(i));
                } else {
                    amountTwo++;
                    listTwo.add(list.get(i));
                }
            }
            list = one ? (amountOne >= amountTwo ? listOne : listTwo) : (amountOne < amountTwo && amountOne != 0 ? listOne : listTwo);
        }
        return amountOne == 1 ? Integer.parseInt(listOne.get(0), 2) : Integer.parseInt(listTwo.get(0), 2);
    }
}
