import java.io.*;
import java.util.*;

public class day11 {
    public static void main(String[] args) throws FileNotFoundException {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean one) throws FileNotFoundException{
        Scanner s = new Scanner(new File("input.txt"));
        int[][] octopusses = new int[10][10];
        int index = 0, flashes = 0, syncedFlashIndex = 0;
        while(s.hasNextLine()){
            String[] line = s.nextLine().split("");
            for (int j = 0; j < line.length; j++) octopusses[index][j] = Integer.parseInt(line[j]);
            index++;
        }
        for(int flashCount = 0; flashCount != 100;){
            flashCount = 0;
            for (int i = 0; i < octopusses.length; i++)
                for (int j = 0; j < octopusses[i].length; j++) octopusses[i][j] = octopusses[i][j] == -1 ? 1 : octopusses[i][j] + 1;
            boolean loopContinue = true;
            while (loopContinue) {
                loopContinue = false;
                for (int i = 0; i < octopusses.length; i++)
                    for (int j = 0; j < octopusses[i].length; j++)
                        if (octopusses[i][j] > 9) {
                            loopContinue = true;
                            octopusses[i][j] = -1;
                            flashes += syncedFlashIndex < 100 ? 1 : 0;
                            flashCount++;
                            int[][] adjacents = {{i - 1, j - 1},{i - 1, j},{i -  1, j + 1},{i, j - 1},{i, j + 1},{i + 1, j - 1},{i + 1, j},{i + 1, j + 1}};
                            for (int[] adjacent : adjacents)
                                if ((adjacent[0] >= 0 && adjacent[0] < octopusses.length) && (adjacent[1] >= 0 && adjacent[1] < octopusses.length))
                                    if (octopusses[adjacent[0]][adjacent[1]] != -1) octopusses[adjacent[0]][adjacent[1]]++;
                        }
            }
            syncedFlashIndex++;
        }
        return one ? flashes : syncedFlashIndex;
    }
}