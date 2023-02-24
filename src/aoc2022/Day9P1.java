package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day9P1 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        List<String> headVisited = new ArrayList<>();
        List<String> tailVisited = new ArrayList<>();
        List<String> tailCount = new ArrayList<>();
        headVisited.add("0, 0");
        tailVisited.add("0, 0");
        tailCount.add("0, 0");
        for (String line : lines) {
            String direction = line.split(" ")[0];
            String currentX = headVisited.get(headVisited.size() - 1).split(", ")[0];
            String currentY = headVisited.get(headVisited.size() - 1).split(", ")[1];
            int distance = Integer.parseInt(line.split(" ")[1]);
            switch (direction) {
                case "D":
                    for (int j = Integer.parseInt(currentY) - 1; j >= Integer.parseInt(currentY) - distance; j--) {
                        headVisited.add(currentX + ", " + j);
                        if (isTouching(tailVisited, headVisited))
                            tailVisited.add(headVisited.get(headVisited.size() - 2));
                        if (!tailCount.contains(tailVisited.get(tailVisited.size() - 1)))
                            tailCount.add(tailVisited.get(tailVisited.size() - 1));
                    }
                    break;
                case "U":
                    for (int j = Integer.parseInt(currentY) + 1; j <= Integer.parseInt(currentY) + distance; j++) {
                        headVisited.add(currentX + ", " + j);
                        if (isTouching(tailVisited, headVisited))
                            tailVisited.add(headVisited.get(headVisited.size() - 2));
                        if (!tailCount.contains(tailVisited.get(tailVisited.size() - 1)))
                            tailCount.add(tailVisited.get(tailVisited.size() - 1));
                    }
                    break;
                case "L":
                    for (int j = Integer.parseInt(currentX) - 1; j >= Integer.parseInt(currentX) - distance; j--) {
                        headVisited.add(j + ", " + currentY);
                        if (isTouching(tailVisited, headVisited))
                            tailVisited.add(headVisited.get(headVisited.size() - 2));
                        if (!tailCount.contains(tailVisited.get(tailVisited.size() - 1)))
                            tailCount.add(tailVisited.get(tailVisited.size() - 1));
                    }
                    break;
                default:
                    for (int j = Integer.parseInt(currentX) + 1; j <= Integer.parseInt(currentX) + distance; j++) {
                        headVisited.add(j + ", " + currentY);
                        if (isTouching(tailVisited, headVisited))
                            tailVisited.add(headVisited.get(headVisited.size() - 2));
                        if (!tailCount.contains(tailVisited.get(tailVisited.size() - 1)))
                            tailCount.add(tailVisited.get(tailVisited.size() - 1));
                    }
                    break;
            }
        }
        System.out.println(tailCount.size());
    }
    public static boolean isTouching(List<String> tail, List<String> head) {
        String currentTailX = tail.get(tail.size() - 1).split(", ")[0];
        String currentTailY = tail.get(tail.size() - 1).split(", ")[1];
        String currentHeadX = head.get(head.size() - 1).split(", ")[0];
        String currentHeadY = head.get(head.size() - 1).split(", ")[1];
        int headMinusTailX = Integer.parseInt(currentHeadX) - Integer.parseInt(currentTailX);
        int headMinusTailY = Integer.parseInt(currentHeadY) - Integer.parseInt(currentTailY);
        return Math.abs(headMinusTailX) > 1 || Math.abs(headMinusTailY) > 1;
    }
}