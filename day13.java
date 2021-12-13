import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class day13 {
    public static void main(String[] args) throws FileNotFoundException{
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static Object exerciseOne(boolean one) throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("input.txt"));
        Set<Dot> dots = new HashSet<>();
        while (scanner.hasNext()) {
            String line = scanner.next();
            if (line.equals("fold")) break;
            String[] split = line.split(",");
            dots.add(new Dot().set(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
        }
        LinkedList<int[]> folds = new LinkedList<>();
        while (scanner.hasNext()) {
            if (!scanner.next().equals("along")) continue;
            String inst = scanner.next();
            folds.add(new int[]{inst.charAt(0) == 'x' ? 0 : 1, Integer.parseInt(inst.substring(2))});
        }
        fold(dots, folds.subList(0, 1));
        if(one) return dots.size();
        fold(dots, folds.subList(1, folds.size()));
        int maxX = max(dots, 0), maxY = max(dots, 1);
        Dot d = new Dot();
        String returnValue = "";
        for (int i = 0; i <= maxY; i++) {
            for (int j = 0; j <= maxX; j++) returnValue += dots.contains(d.set(j, i)) ? '#' : '.';
            returnValue += "\n";
        }
        return returnValue;
    }

    public static void fold(Set<Dot> dots, List<int[]> folds){
        for (int i = 0; i < folds.size(); i++) {
            int a = folds.get(i)[0], mid = folds.get(i)[1];
            dots.stream()
                .filter(d -> a == 0 ? d.x > mid : d.y > mid)
                .collect(Collectors.toList())
                    .stream()
                    .peek(dots::remove)
                    .map(d -> a == 0 ? d.set(mid * 2 - d.x, d.y) : d.set(d.x, mid * 2 - d.y))
                    .forEach(dots::add);
        }
    }

    public static int max(Set<Dot> dots, int a){
        int biggest = 0;
        for (Dot dot : dots) biggest = a == 0 ? (dot.x > biggest ? dot.x : biggest) : (dot.y > biggest ? dot.y : biggest);
        return biggest;
    }

    public static class Dot{
        public int x, y;

        public Dot set(int x, int y){
            this.x = x;
            this.y = y;
            return this;
        }

        @Override
        public boolean equals(Object o){
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Dot dot = (Dot)o;
            return x == dot.x && y == dot.y;
        }

        @Override
        public int hashCode(){
            return Objects.hash(x, y);
        }
    }
}