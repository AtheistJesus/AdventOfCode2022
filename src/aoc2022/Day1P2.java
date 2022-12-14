package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day1P2 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

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
        int i = 0;
        int removal = 0;
        while (i < calories.size()) {
            if (calories.get(i) > max) {
                max = calories.get(i);
                removal = i;
            }
            else i++;
        }
        calories.remove(removal);
        int secondMax = calories.get(0);
        i = 0;
        while (i < calories.size()) {
            if (calories.get(i) > secondMax) {
                secondMax = calories.get(i);
                removal = i;
            }
            else i++;
        }
        calories.remove(removal);
        int thirdMax = calories.get(0);
        i = 0;
        while (i < calories.size()) {
            if (calories.get(i) > thirdMax) {
                thirdMax = calories.get(i);
                removal = i;
            }
            else i++;
        }
        calories.remove(removal);
        System.out.println(max + secondMax + thirdMax);
    }
}