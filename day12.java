import java.io.*;
import java.util.*;

public class day12 {
    public static int counter = 0;
    public static void main(String[] args) throws Exception {
        System.out.println(exerciseOne(true));
        System.out.println(exerciseOne(false));
    }

    public static int exerciseOne(boolean one) throws Exception{
        Map<String, Set<String>> connections = new HashMap<>();
        counter = 0;
        Scanner s = new Scanner(new File("input.txt"));
        LinkedList<String> inputs = new LinkedList<>();
        while(s.hasNextLine()) inputs.add(s.nextLine());
        for (String string : inputs) {
            String[] caves = string.split("-");
            connections.put(caves[0], new HashSet<>(Set.of(caves[1])));
            connections.put(caves[1], new HashSet<>(Set.of(caves[0])));
        }
        for (String string : inputs) {
            String[] caves = string.split("-");
            connections.get(caves[0]).add(caves[1]);
            connections.get(caves[1]).add(caves[0]);
        }
        findPath("start", "end", new LinkedList<>(), one, connections);
        return counter;
    }

    public static void findPath(String start, String end, List<String> visited, boolean one, Map<String, Set<String>> connections) throws Exception{
        visited.add(start);
        if (start.equals(end)) counter++;
        else
            for (String connection : connections.get(start)) {
                LinkedList<String> currentPath = new LinkedList<>(visited);
                if (canVisit(connection, visited, one)) findPath(connection, end, visited, one, connections);
                visited = new LinkedList<>(currentPath);
            }
    }

    private static boolean canVisit(String connection, List<String> visited, boolean one) {
        if (connection.equals("start")) return false;
        if (Character.isUpperCase(connection.charAt(0)) || !visited.contains(connection)) return true;
        HashSet<String> uniqueSmallCavesVisited = new HashSet<>();
        LinkedList<String> smallCavesVisited = new LinkedList<>();
        for (String string : visited) 
            if(Character.isLowerCase(string.charAt(0))) {
                smallCavesVisited.add(string); 
                uniqueSmallCavesVisited.add(string);
            }
        return one ? !visited.contains(connection) : (uniqueSmallCavesVisited.size() == smallCavesVisited.size());
    }
}