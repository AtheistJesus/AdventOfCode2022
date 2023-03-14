package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1P1 {

    public static final String PATHNAME = "inputs\\day1.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        List<Integer> calories = new ArrayList<>();
        int count = 0;
        for (int i = 0; i < lines.size(); i++) {
            if (lines.get(i).length() == 0) {
                int cals = 0;
                for(int j = count; j < i; j++) {
                    cals += Integer.parseInt(lines.get(j));
                }
                calories.add(cals);
                count = i + 1;
            }
        }
        int max = calories.get(0);
        for (int i = 1; i < calories.size(); i++) {
            if (calories.get(i) > max) {
                max = calories.get(i);
            }
        }
        System.out.println(max);
    }
}