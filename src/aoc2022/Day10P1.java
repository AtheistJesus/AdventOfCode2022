package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Day10P1 {

    public static final String PATHNAME = "inputs\\day10.txt";

    public static int cycle = 1, value = 1;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        Map<Integer, Integer> values = new HashMap<>();
        for (String line : lines) {
            if (line.startsWith("addx")) {
                values.put(cycle, value);
                cycle++;
                values.put(cycle, value);
                cycle++;
                value += Integer.parseInt(line.split(" ")[1]);
            }
            else {
                values.put(cycle, value);
                cycle++;
            }
        }
        System.out.println((values.get(20) * 20) + (values.get(60) * 60) +
                (values.get(100) * 100) + (values.get(140) * 140) + (values.get(180) * 180) + (values.get(220) * 220));
    }
}