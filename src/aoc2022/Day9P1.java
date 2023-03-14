package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day9P1 {

    public static final String PATHNAME = "inputs\\day9.txt";

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
            int xValue = Integer.parseInt(currentX);
            int yValue = Integer.parseInt(currentY);
            int distance = Integer.parseInt(line.split(" ")[1]);
            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case "U" -> {
                        headVisited.add(xValue + ", " + (yValue + 1));
                        yValue++;
                    }
                    case "D" -> {
                        headVisited.add(xValue + ", " + (yValue - 1));
                        yValue--;
                    }
                    case "L" -> {
                        headVisited.add((xValue - 1) + ", " + yValue);
                        xValue--;
                    }
                    default -> {
                        headVisited.add((xValue + 1) + ", " + yValue);
                        xValue++;
                    }
                }
                findTailOccurrences(headVisited, tailVisited, tailCount);
            }
        }
        System.out.println(tailCount.size());
    }

    private static void findTailOccurrences(List<String> headVisited, List<String> tailVisited, List<String> tailCount) {
        if (isTouching(tailVisited, headVisited))
            tailVisited.add(headVisited.get(headVisited.size() - 2));
        if (!tailCount.contains(tailVisited.get(tailVisited.size() - 1)))
            tailCount.add(tailVisited.get(tailVisited.size() - 1));
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