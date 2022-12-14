package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3P1 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

    public static int totalPriority = 0;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));

        for (String line : lines) {
            String firstHalf = line.substring(0, line.length() / 2);
            List<Character> firstList = new ArrayList<>();
            String secondHalf = line.substring(line.length() / 2);
            List<Character> secondList = new ArrayList<>();
            for (int i = 0; i < firstHalf.length(); i++) {
                if (!firstList.contains(firstHalf.charAt(i))) {
                    firstList.add(firstHalf.charAt(i));
                }
            }
            for (int i = 0; i < secondHalf.length(); i++) {
                if (!secondList.contains(secondHalf.charAt(i))) {
                    secondList.add(secondHalf.charAt(i));
                }
            }
            for (Character item : firstList) {
                if (secondList.contains(item)) {
                    if (Character.isUpperCase(item)) totalPriority += ((int)item - 38);
                    else totalPriority += ((int)item - 96);
                }
            }
        }
        System.out.println(totalPriority);
    }
}