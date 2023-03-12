package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day9P2 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        List<List<String>> rope = new ArrayList<>();
        List<String> tailCount = new ArrayList<>();
        tailCount.add("0, 0");
        for (int i = 0; i < 10; i++) {
            rope.add(new ArrayList<>());
        }
        for (int i = 0; i < 10; i++) {
            rope.get(i).add("0, 0");
        }
        for (String line : lines) {
            String direction = line.split(" ")[0];
            List<String> current = rope.get(0);
            String currentX = current.get(current.size() - 1).split(", ")[0];
            String currentY = current.get(current.size() - 1).split(", ")[1];
            int distance = Integer.parseInt(line.split(" ")[1]);
            int xValue = Integer.parseInt(currentX);
            int yValue = Integer.parseInt(currentY);
            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case "U" -> {
                        rope.get(0).add(xValue + ", " + (yValue + 1));
                        yValue++;
                    }
                    case "D" -> {
                        rope.get(0).add(xValue + ", " + (yValue - 1));
                        yValue--;
                    }
                    case "L" -> {
                        rope.get(0).add((xValue - 1) + ", " + yValue);
                        xValue--;
                    }
                    default -> {
                        rope.get(0).add((xValue + 1) + ", " + yValue);
                        xValue++;
                    }
                }
                findTailOccurrences(rope, tailCount);
            }
        }
        System.out.println(tailCount.size());
    }

    private static void findTailOccurrences(List<List<String>> rope, List<String> tailCount) {
        for (int k = 0; k < rope.size() - 1; k++) {
            if (isTouching(rope.get(k), rope.get(k + 1))) {
                if (isInRowOrColumn(rope.get(k), rope.get(k + 1))) moveDiagonally(rope, k);
                else moveStraight(rope, k);
            }
            else break;
        }
        String latestPosition = rope.get(9).get(rope.get(9).size() - 1);
        if (!tailCount.contains(latestPosition)) tailCount.add(latestPosition);
    }

    public static boolean isTouching(List<String> previous, List<String> next) {
        String currentTailX = previous.get(previous.size() - 1).split(", ")[0];
        String currentTailY = previous.get(previous.size() - 1).split(", ")[1];
        String currentHeadX = next.get(next.size() - 1).split(", ")[0];
        String currentHeadY = next.get(next.size() - 1).split(", ")[1];
        int headMinusTailX = Integer.parseInt(currentHeadX) - Integer.parseInt(currentTailX);
        int headMinusTailY = Integer.parseInt(currentHeadY) - Integer.parseInt(currentTailY);
        return Math.abs(headMinusTailX) > 1 || Math.abs(headMinusTailY) > 1;
    }

    public static boolean isInRowOrColumn(List<String> previous, List<String> next) {
        String currentTailX = previous.get(previous.size() - 1).split(", ")[0];
        String currentTailY = previous.get(previous.size() - 1).split(", ")[1];
        String currentHeadX = next.get(next.size() - 1).split(", ")[0];
        String currentHeadY = next.get(next.size() - 1).split(", ")[1];
        int headMinusTailX = Integer.parseInt(currentHeadX) - Integer.parseInt(currentTailX);
        int headMinusTailY = Integer.parseInt(currentHeadY) - Integer.parseInt(currentTailY);
        return headMinusTailX != 0 && headMinusTailY != 0;
    }

    public static String getCurrentX(List<String> knot) {
        return knot.get(knot.size() - 1).split(", ")[0];
    }

    public static String getCurrentY(List<String> knot) {
        return knot.get(knot.size() - 1).split(", ")[1];
    }

    public static void moveStraight(List<List<String>> rope, int index) {
        int currentX = Integer.parseInt(getCurrentX(rope.get(index)));
        int nextX = Integer.parseInt(getCurrentX(rope.get(index + 1)));
        int currentY = Integer.parseInt(getCurrentY(rope.get(index)));
        int nextY = Integer.parseInt(getCurrentY(rope.get(index + 1)));
        if (currentX == nextX) {
            if (currentY > nextY) rope.get(index + 1).add(nextX + ", " + (nextY + 1));
            else rope.get(index + 1).add(nextX + ", " + (nextY - 1));
        }
        else if (currentY == nextY) {
            if (currentX > nextX) rope.get(index + 1).add((nextX + 1) + ", " + nextY);
            else rope.get(index + 1).add((nextX - 1) + ", " + nextY);
        }
    }

    public static void moveDiagonally(List<List<String>> rope, int index) {
        int currentX = Integer.parseInt(getCurrentX(rope.get(index)));
        int nextX = Integer.parseInt(getCurrentX(rope.get(index + 1)));
        int currentY = Integer.parseInt(getCurrentY(rope.get(index)));
        int nextY = Integer.parseInt(getCurrentY(rope.get(index + 1)));
        if (currentX > nextX && currentY > nextY) rope.get(index + 1).add((nextX + 1) + ", " + (nextY + 1));
        else if (currentX < nextX && currentY > nextY) rope.get(index + 1).add((nextX - 1) + ", " + (nextY + 1));
        else if (currentX > nextX && currentY < nextY) rope.get(index + 1).add((nextX + 1) + ", " + (nextY - 1));
        else rope.get(index + 1).add((nextX - 1) + ", " + (nextY - 1));
    }
}