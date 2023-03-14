package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day9P2 {

    public static final String PATHNAME = "inputs\\day9.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        String[] rope = new String[10];
        List<String> tailCount = new ArrayList<>();
        tailCount.add("0, 0");
        for (int i = 0; i < 10; i++) {
            rope[i] = ("0, 0");
        }
        for (String line : lines) {
            String direction = line.split(" ")[0];
            String current = rope[0];
            String currentX = current.split(", ")[0];
            String currentY = current.split(", ")[1];
            int distance = Integer.parseInt(line.split(" ")[1]);
            int xValue = Integer.parseInt(currentX);
            int yValue = Integer.parseInt(currentY);
            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case "U" -> yValue++;
                    case "D" -> yValue--;
                    case "L" -> xValue--;
                    default -> xValue++;
                }
                rope[0] = (xValue + ", " + yValue);
                findTailOccurrences(rope, tailCount);
            }
        }
        System.out.println(tailCount.size());
    }

    private static void findTailOccurrences(String[] rope, List<String> tailCount) {
        for (int k = 0; k < rope.length - 1; k++) {
            if (isTouching(rope[k], rope[k + 1])) {
                if (isInRowOrColumn(rope[k], rope[k + 1])) moveDiagonally(rope, k);
                else moveStraight(rope, k);
            }
            else break;
        }
        String latestPosition = rope[9];
        if (!tailCount.contains(latestPosition)) tailCount.add(latestPosition);
    }

    public static boolean isTouching(String previous, String next) {
        String currentTailX = previous.split(", ")[0];
        String currentTailY = previous.split(", ")[1];
        String currentHeadX = next.split(", ")[0];
        String currentHeadY = next.split(", ")[1];
        int headMinusTailX = Integer.parseInt(currentHeadX) - Integer.parseInt(currentTailX);
        int headMinusTailY = Integer.parseInt(currentHeadY) - Integer.parseInt(currentTailY);
        return Math.abs(headMinusTailX) > 1 || Math.abs(headMinusTailY) > 1;
    }

    public static boolean isInRowOrColumn(String previous, String next) {
        String currentTailX = previous.split(", ")[0];
        String currentTailY = previous.split(", ")[1];
        String currentHeadX = next.split(", ")[0];
        String currentHeadY = next.split(", ")[1];
        int headMinusTailX = Integer.parseInt(currentHeadX) - Integer.parseInt(currentTailX);
        int headMinusTailY = Integer.parseInt(currentHeadY) - Integer.parseInt(currentTailY);
        return headMinusTailX != 0 && headMinusTailY != 0;
    }

    public static String getCurrentX(String knot) {
        return knot.split(", ")[0];
    }

    public static String getCurrentY(String knot) {
        return knot.split(", ")[1];
    }

    public static void moveStraight(String[] rope, int index) {
        int currentX = Integer.parseInt(getCurrentX(rope[index]));
        int nextX = Integer.parseInt(getCurrentX(rope[index + 1]));
        int currentY = Integer.parseInt(getCurrentY(rope[index]));
        int nextY = Integer.parseInt(getCurrentY(rope[index + 1]));
        if (currentX == nextX) {
            if (currentY > nextY) rope[index + 1] = (nextX + ", " + (nextY + 1));
            else rope[index + 1] = (nextX + ", " + (nextY - 1));
        }
        else if (currentY == nextY) {
            if (currentX > nextX) rope[index + 1] = ((nextX + 1) + ", " + nextY);
            else rope[index + 1] = ((nextX - 1) + ", " + nextY);
        }
    }

    public static void moveDiagonally(String[] rope, int index) {
        int currentX = Integer.parseInt(getCurrentX(rope[index]));
        int nextX = Integer.parseInt(getCurrentX(rope[index + 1]));
        int currentY = Integer.parseInt(getCurrentY(rope[index]));
        int nextY = Integer.parseInt(getCurrentY(rope[index + 1]));
        if (currentX > nextX && currentY > nextY) rope[index + 1] = ((nextX + 1) + ", " + (nextY + 1));
        else if (currentX < nextX && currentY > nextY) rope[index + 1] = ((nextX - 1) + ", " + (nextY + 1));
        else if (currentX > nextX && currentY < nextY) rope[index + 1] = ((nextX + 1) + ", " + (nextY - 1));
        else rope[index + 1] = ((nextX - 1) + ", " + (nextY - 1));
    }
}