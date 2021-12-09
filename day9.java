import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
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
        int index = 0, score = 0;
        while(s.hasNextLine()){
            String[] numbers = s.nextLine().split("");
            for(int j = 0; j < numbers.length; j++) grid[index][j] = Integer.parseInt(numbers[j]);
            index++;
        }
        List<Integer> sizes = new ArrayList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++) {
                boolean lowest = (i == 0 || grid[i - 1][j] > grid[i][j]) && (i == 99 || grid[i + 1][j] > grid[i][j]) && (j == 0 || grid[i][j - 1] > grid[i][j]) && (j == 99 || grid[i][j + 1] > grid[i][j]);
                boolean isSmallest = (i == 0 || grid[i - 1][j] > grid[i][j]) && (i == 99 || grid[i + 1][j] > grid[i][j]) && (j == 0 || grid[i][j - 1] > grid[i][j]) && (j == 99 || grid[i][j + 1] > grid[i][j]);
                Set<GridEntry> values = new HashSet<>();
                score += isSmallest ? grid[i][j] + 1 : 0;
                if (lowest) {
                    isPossible(grid, i, j, values);
                    sizes.add(values.size());
                }
            }
        sizes.sort(Comparator.naturalOrder());
        return one ? score : sizes.get(sizes.size() - 1) * sizes.get(sizes.size() - 2) * sizes.get(sizes.size() - 3);
    }

    private static void isPossible(int[][] grid, int i, int j, Set<GridEntry> values) {
        if (grid[i][j] == 9) return;
        boolean exists = values.add(new GridEntry(i, j));
        if (exists && i != 0 && grid[i - 1][j] > grid[i][j]) isPossible(grid, i - 1, j, values);
        if (exists && i != 99 && grid[i + 1][j] > grid[i][j]) isPossible(grid, i + 1, j, values);
        if (exists && j != 0 && grid[i][j - 1] > grid[i][j]) isPossible(grid, i, j - 1, values);
        if (exists && j != 99 && grid[i][j + 1] > grid[i][j]) isPossible(grid, i, j + 1, values); 
    }

    private static class GridEntry {
        int x;
        int y;
 
        public GridEntry(int x, int y) {
            this.x = x;
            this.y = y;
        }
 
        @Override
        public boolean equals(Object obj) {
            GridEntry point = (GridEntry) obj;
            return x == point.x && y == point.y;
        }
 
        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
