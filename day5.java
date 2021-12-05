import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class day5 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean one) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));
        int[][] map = new int[1000][1000];
        int counter = 0;
        while (s.hasNext()) {
            String[] input = s.nextLine().split(" -> "), loc1 = input[0].split(","), loc2 = input[1].split(",");
            int[] location1 = { Integer.parseInt(loc1[0]), Integer.parseInt(loc1[1]) },
                    location2 = { Integer.parseInt(loc2[0]), Integer.parseInt(loc2[1]) };
            if (location1[0] == location2[0]) {
                for (int i = (location1[1] > location2[1] ? location2[1]
                        : location1[1]); i <= (location1[1] > location2[1] ? location1[1] : location2[1]); i++) {
                    map[location1[0]][i] += 1;
                    counter += map[location1[0]][i] == 2 ? 1 : 0;
                }
            } else if (location1[1] == location2[1]) {
                for (int i = (location1[0] > location2[0] ? location2[0]
                        : location1[0]); i <= (location1[0] > location2[0] ? location1[0] : location2[0]); i++) {
                    map[i][location1[1]] += 1;
                    counter += map[i][location1[1]] == 2 ? 1 : 0;
                }
            } else if (!one) {
                if (location1[0] < location2[0] && location1[1] < location2[1])
                    for (int i = location1[0]; i <= location2[0];)
                        for (int j = location1[1]; j <= location2[1];) {
                            map[i][j] += 1;
                            counter += map[i][j] == 2 ? 1 : 0;
                            i++;
                            j++;
                        }
                else if (location1[0] < location2[0] && location1[1] > location2[1])
                    for (int i = location1[0]; i <= location2[0];)
                        for (int j = location1[1]; j >= location2[1];) {
                            map[i][j] += 1;
                            counter += map[i][j] == 2 ? 1 : 0;
                            i++;
                            j--;
                        }
                else if (location1[0] > location2[0] && location1[1] < location2[1])
                    for (int i = location1[0]; i >= location2[0];)
                        for (int j = location1[1]; j <= location2[1];) {
                            map[i][j] += 1;
                            counter += map[i][j] == 2 ? 1 : 0;
                            i--;
                            j++;
                        }
                else if (location1[0] > location2[0] && location1[1] > location2[1])
                    for (int i = location1[0]; i >= location2[0];)
                        for (int j = location1[1]; j >= location2[1];) {
                            map[i][j] += 1;
                            counter += map[i][j] == 2 ? 1 : 0;
                            i--;
                            j--;
                        }
            }
        }
        return counter;
    }
}
