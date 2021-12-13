import java.io.*;
import java.util.*;

public class day13 {
    public static void main(String[] args) throws Exception {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean one) throws Exception{
        Scanner s = new Scanner(new File("inputTest.txt"));
        String[][] grid = new String[15][15];
        boolean fillingDone = false;
        LinkedList<String> folds = new LinkedList<>();
        while(s.hasNextLine()){
            String[] coordinates = s.nextLine().split(",");
            if(fillingDone) folds.add(coordinates[0]);
            if(coordinates[0].equals("")) fillingDone = true;
            if(!fillingDone) grid[Integer.parseInt(coordinates[0])][Integer.parseInt(coordinates[1])] = "#";
        }
        for (String string : folds) {
            fold(grid, string);
        }
        return 0;
    }

    public static void fold(String[][] grid, String fold){
        String[] foldCoordinates = fold.split(" ");
        int coordinate = Integer.parseInt(foldCoordinates[2].substring(2));
        if(foldCoordinates[2].charAt(0) == 'x'){
            print(grid);
            String[][] foldPart = new String[15][15];
            int index = 0;
            for(int i = grid.length - 1; i > coordinate ; i--){
                for (int j = 0; j < grid[0].length; j++) {
                    foldPart[index][j] = grid[i][j];
                }
                index++;
            }
            System.out.println();
            System.out.println();
            System.out.println();
            print(foldPart);
        } else {
            System.out.println(foldCoordinates[2].substring(2));
        }
    }

    private static void print(String[][] grid){
        for (String[] strings : grid) {
            for (String strings2 : strings) {
                if(strings2 != null){
                    System.out.print(strings2 + "  ");
                } else {
                    System.out.print(".  ");
                }
            }
            System.out.println();
        }
    }
}