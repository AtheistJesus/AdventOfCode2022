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
        List<List<String>> tailVisited = new ArrayList<>();
        List<String> tailCount = new ArrayList<>();
        tailCount.add("0, 0");
        for (int i = 0; i < 10; i++) {
            tailVisited.add(new ArrayList<>());
        }
        for (int i = 0; i < 10; i++) {
            tailVisited.get(i).add("0, 0");
        }
        for (String line : lines) {
            String direction = line.split(" ")[0];
            List<String> current = tailVisited.get(0);
            String currentX = current.get(current.size() - 1).split(", ")[0];
            String currentY = current.get(current.size() - 1).split(", ")[1];
            int distance = Integer.parseInt(line.split(" ")[1]);
            switch (direction) {
                case "U":
                    for (int j = Integer.parseInt(currentY) + 1; j <= Integer.parseInt(currentY) + distance; j++) {
                        tailVisited.get(0).add(currentX + ", " + j);
                        for (int k = 0; k < tailVisited.size() - 1; k++) {
                            if (isTouching(tailVisited.get(k), tailVisited.get(k + 1))) {
                                if (isInRowOrColumn(tailVisited.get(k), tailVisited.get(k + 1))) moveDiagonally(tailVisited, k);
                                else moveStraight(tailVisited, k);
                            }
                            else break;
                        }
                        String latestPosition = tailVisited.get(9).get(tailVisited.get(9).size() - 1);
                        if (!tailCount.contains(latestPosition)) tailCount.add(latestPosition);
                    }
                    break;
                case "D":
                    for (int j = Integer.parseInt(currentY) - 1; j >= Integer.parseInt(currentY) - distance; j--) {
                        tailVisited.get(0).add(currentX + ", " + j);
                        for (int k = 0; k < tailVisited.size() - 1; k++) {
                            if (isTouching(tailVisited.get(k), tailVisited.get(k + 1))) {
                                if (isInRowOrColumn(tailVisited.get(k), tailVisited.get(k + 1))) moveDiagonally(tailVisited, k);
                                else moveStraight(tailVisited, k);
                            }
                            else break;
                        }
                        String latestPosition = tailVisited.get(9).get(tailVisited.get(9).size() - 1);
                        if (!tailCount.contains(latestPosition)) tailCount.add(latestPosition);
                    }
                    break;
                case "L":
                    for (int j = Integer.parseInt(currentX) - 1; j >= Integer.parseInt(currentX) - distance; j--) {
                        tailVisited.get(0).add(j + ", " + currentY);
                        for (int k = 0; k < tailVisited.size() - 1; k++) {
                            if (isTouching(tailVisited.get(k), tailVisited.get(k + 1))) {
                                if (isInRowOrColumn(tailVisited.get(k), tailVisited.get(k + 1))) moveDiagonally(tailVisited, k);
                                else moveStraight(tailVisited, k);
                            }
                            else break;
                        }
                        String latestPosition = tailVisited.get(9).get(tailVisited.get(9).size() - 1);
                        if (!tailCount.contains(latestPosition)) tailCount.add(latestPosition);
                    }
                    break;
                default:
                    for (int j = Integer.parseInt(currentX) + 1; j <= Integer.parseInt(currentX) + distance; j++) {
                        tailVisited.get(0).add(j + ", " + currentY);
                        for (int k = 0; k < tailVisited.size() - 1; k++) {
                            if (isTouching(tailVisited.get(k), tailVisited.get(k + 1))) {
                                if (isInRowOrColumn(tailVisited.get(k), tailVisited.get(k + 1))) moveDiagonally(tailVisited, k);
                                else moveStraight(tailVisited, k);
                            }
                            else break;
                        }
                        String latestPosition = tailVisited.get(9).get(tailVisited.get(9).size() - 1);
                        if (!tailCount.contains(latestPosition)) tailCount.add(latestPosition);
                    }
                    break;
            }
        }
        System.out.println(tailCount.size());
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
        if (getCurrentX(rope.get(index)).equals(getCurrentX(rope.get(index + 1)))) {
            if (Integer.parseInt(getCurrentY(rope.get(index))) >
                    Integer.parseInt(getCurrentY(rope.get(index + 1)))) {
                rope.get(index + 1).add(Integer.parseInt(getCurrentX(rope.get(index + 1)))
                        + ", " + (Integer.parseInt(getCurrentY(rope.get(index + 1))) + 1));
            }
            else {
                rope.get(index + 1).add(Integer.parseInt(getCurrentX(rope.get(index + 1)))
                        + ", " + (Integer.parseInt(getCurrentY(rope.get(index + 1))) - 1));
            }
        }
        else if (getCurrentY(rope.get(index)).equals(getCurrentY(rope.get(index + 1)))) {
            if (Integer.parseInt(getCurrentX(rope.get(index))) >
                    Integer.parseInt(getCurrentX(rope.get(index + 1)))) {
                rope.get(index + 1).add((Integer.parseInt(getCurrentX(rope.get(index + 1))) + 1)
                        + ", " + Integer.parseInt(getCurrentY(rope.get(index + 1))));
            }
            else {
                rope.get(index + 1).add((Integer.parseInt(getCurrentX(rope.get(index + 1))) - 1)
                        + ", " + Integer.parseInt(getCurrentY(rope.get(index + 1))));
            }
        }
    }

    public static void moveDiagonally(List<List<String>> rope, int index) {
        if (Integer.parseInt(getCurrentX(rope.get(index))) > Integer.parseInt(getCurrentX(rope.get(index + 1))) &&
                Integer.parseInt(getCurrentY(rope.get(index))) > Integer.parseInt(getCurrentY(rope.get(index + 1)))) {
            rope.get(index + 1).add((Integer.parseInt(getCurrentX(rope.get(index + 1))) + 1) + ", " +
                    (Integer.parseInt(getCurrentY(rope.get(index + 1))) + 1));
        }
        else if (Integer.parseInt(getCurrentX(rope.get(index))) < Integer.parseInt(getCurrentX(rope.get(index + 1))) &&
                Integer.parseInt(getCurrentY(rope.get(index))) > Integer.parseInt(getCurrentY(rope.get(index + 1)))) {
            rope.get(index + 1).add((Integer.parseInt(getCurrentX(rope.get(index + 1))) - 1) + ", " +
                    (Integer.parseInt(getCurrentY(rope.get(index + 1))) + 1));
        }
        else if (Integer.parseInt(getCurrentX(rope.get(index))) > Integer.parseInt(getCurrentX(rope.get(index + 1))) &&
                Integer.parseInt(getCurrentY(rope.get(index))) < Integer.parseInt(getCurrentY(rope.get(index + 1)))) {
            rope.get(index + 1).add((Integer.parseInt(getCurrentX(rope.get(index + 1))) + 1) + ", " +
                    (Integer.parseInt(getCurrentY(rope.get(index + 1))) - 1));
        }
        else if (Integer.parseInt(getCurrentX(rope.get(index))) < Integer.parseInt(getCurrentX(rope.get(index + 1))) &&
                Integer.parseInt(getCurrentY(rope.get(index))) < Integer.parseInt(getCurrentY(rope.get(index + 1)))) {
            rope.get(index + 1).add((Integer.parseInt(getCurrentX(rope.get(index + 1))) - 1) + ", " +
                    (Integer.parseInt(getCurrentY(rope.get(index + 1))) - 1));
        }
    }
}