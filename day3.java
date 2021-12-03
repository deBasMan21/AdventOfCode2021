import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Scanner;

public class day3 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(excerciseOne());
        System.out.println(exerciseTwo());
    }

    public static int excerciseOne() throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        LinkedList<HashMap<Integer, Integer>> bits = new LinkedList<>();
        String highest = "", lowest = "";
        for (int i = 0; i < 12; i++) {
            HashMap<Integer, Integer> map = new HashMap<>();
            map.put(1, 0);
            map.put(0, 0);
            bits.add(map);
        }
        while (s.hasNext()) {
            String[] input = s.next().split("");
            for (int i = 0; i < input.length; i++) {
                int amount = bits.get(i).get(Integer.valueOf(input[i])) + 1;
                bits.get(i).put(Integer.valueOf(input[i]), amount);
            }
        }
        for (int i = 0; i < 12; i++) {
            int one = bits.get(i).get(0), two = bits.get(i).get(1);
            if (Math.max(Integer.valueOf(one), Integer.valueOf(two)) == Integer.valueOf(one)) {
                highest += "1";
                lowest += "0";
            } else {
                highest += "0";
                lowest += "1";
            }
        }
        return Integer.parseInt(highest, 2) * Integer.parseInt(lowest, 2);
    }

    public static int exerciseTwo() throws FileNotFoundException {
        return twoHelper(true) * twoHelper(false);
    }

    private static int twoHelper(boolean one) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        LinkedList<String> list = new LinkedList<>();
        LinkedList<String> listOne = new LinkedList<>();
        LinkedList<String> listTwo = new LinkedList<>();
        while (s.hasNext()) {
            list.add(s.next());
        }
        int amountOne = 0, amountTwo = 0;
        for (int j = 0; j < 12; j++) {
            if (listOne.size() + listTwo.size() == 1) {
                continue;
            }
            amountOne = 0;
            amountTwo = 0;
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
            if (one) {
                if (amountOne >= amountTwo) {
                    list = listOne;
                } else {
                    list = listTwo;
                }
            } else {

                if (amountOne < amountTwo && amountOne != 0) {
                    list = listOne;
                } else {
                    list = listTwo;
                }
            }
        }

        return amountOne == 1 ? Integer.parseInt(listOne.get(0), 2) : Integer.parseInt(listTwo.get(0), 2);
    }
}