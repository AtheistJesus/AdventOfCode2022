package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;

public class Day9P2 {

    public static final String PATHNAME = "inputs\\day9.txt";
    private static final Point[] rope = new Point[10];
    private static final HashSet<String> tailCount = new HashSet<>();

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        for (int i = 0; i < 10; i++) {
            rope[i] = new Point(0, 0);
        }
        for (String line : lines) {
            String direction = line.split(" ")[0];
            int distance = Integer.parseInt(line.split(" ")[1]);
            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case "U" -> rope[0].y++;
                    case "D" -> rope[0].y--;
                    case "L" -> rope[0].x--;
                    case "R" -> rope[0].x++;
                }
                findTailOccurrences();
            }
        }
        System.out.println(tailCount.size());
    }

    private static void findTailOccurrences() {
        for (int k = 0; k < rope.length - 1; k++) {
            Point currentKnot = rope[k];
            Point nextKnot = rope[k + 1];
            if (Math.abs(currentKnot.x - nextKnot.x) > 1 || Math.abs(currentKnot.y - nextKnot.y) > 1) {
                nextKnot.x += Integer.signum(currentKnot.x - nextKnot.x);
                nextKnot.y += Integer.signum(currentKnot.y - nextKnot.y);
            }
        }
        Point latestPosition = rope[9];
        tailCount.add(latestPosition.x + ", " + latestPosition.y);
    }

}

class Point {

    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}