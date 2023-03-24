package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day10P2 {

    public static final String PATHNAME = "inputs\\day10.txt";
    public static int cycle = 1, value = 1;
    public static List<String> pixels = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        for (int i = 0; i < 6; i++) {
            pixels.add("");
        }
        for (String line : lines) {
            if (line.startsWith("addx")) {
                addPixel();
                cycle++;
                addPixel();
                cycle++;
                value += Integer.parseInt(line.split(" ")[1]);
            }
            else {
                addPixel();
                cycle++;
            }
        }
        for (String row : pixels) {
            System.out.println(row);
        }
    }
    public static void addPixel() {
        int row = 0;
        if (cycle >= 41 && cycle <= 80) row = 1;
        else if (cycle >= 81 && cycle <= 120) row = 2;
        else if (cycle >= 121 && cycle <= 160) row = 3;
        else if (cycle >= 161 && cycle <= 200) row = 4;
        else if (cycle >= 201 && cycle <= 240) row = 5;
        if (value - 1 == pixels.get(row).length() || value == pixels.get(row).length() || value + 1 == pixels.get(row).length())
            pixels.set(row, pixels.get(row) + "#");
        else pixels.set(row, pixels.get(row) + ".");
    }
}