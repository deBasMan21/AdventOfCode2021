import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

public class day9 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean one) throws FileNotFoundException{
        Scanner s = new Scanner(new File("input.txt"));
        int[][] grid = new int[100][100];
        int score = 0;
        for(int i = 0; i < 100; i++){
            String[] numbers = s.nextLine().split("");
            for(int j = 0; j < numbers.length; j++) grid[i][j] = Integer.parseInt(numbers[j]);
        }
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                boolean lowest = (i == 0 || grid[i - 1][j] > grid[i][j]) && (i == 99 || grid[i + 1][j] > grid[i][j]) && (j == 0 || grid[i][j - 1] > grid[i][j]) && (j == 99 || grid[i][j + 1] > grid[i][j]);
                boolean isSmallest = (i == 0 || grid[i - 1][j] > grid[i][j]) && (i == 99 || grid[i + 1][j] > grid[i][j]) && (j == 0 || grid[i][j - 1] > grid[i][j]) && (j == 99 || grid[i][j + 1] > grid[i][j]);
                Set<String> values = new HashSet<>();
                score += isSmallest ? grid[i][j] + 1 : 0;
                if (lowest) isPossible(grid, i, j, values);
                sizes.add(values.size());
            }
        Collections.sort(sizes);
        return one ? score : sizes.get(sizes.size() - 1) * sizes.get(sizes.size() - 2) * sizes.get(sizes.size() - 3);
    }

    private static void isPossible(int[][] grid, int i, int j, Set<String> values) {
        if (grid[i][j] == 9) return;
        boolean exists = values.add(i + " " + j);
        if (exists && i != 0 && grid[i - 1][j] > grid[i][j]) isPossible(grid, i - 1, j, values);
        if (exists && i != 99 && grid[i + 1][j] > grid[i][j]) isPossible(grid, i + 1, j, values);
        if (exists && j != 0 && grid[i][j - 1] > grid[i][j]) isPossible(grid, i, j - 1, values);
        if (exists && j != 99 && grid[i][j + 1] > grid[i][j]) isPossible(grid, i, j + 1, values); 
    }
}
