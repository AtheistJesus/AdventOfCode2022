package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day3P2 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

    public static int totalPriority = 0;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));

        for (int i = 0; i < lines.size(); i += 3) {
            List<String> currentGroup = new ArrayList<>();
            for (int j = i; j < i + 3; j++) {
                currentGroup.add(lines.get(j));
            }
            List<Character> firstLine = new ArrayList<>();
            List<Character> secondLine = new ArrayList<>();
            List<Character> thirdLine = new ArrayList<>();
            for (int j = 0; j < currentGroup.size(); j++) {
                if (j == 0) {
                    for (int k = 0; k < currentGroup.get(j).length(); k++) {
                        if (!firstLine.contains(currentGroup.get(j).charAt(k))) {
                            firstLine.add(currentGroup.get(j).charAt(k));
                        }
                    }
                }
                else if (j == 1) {
                    for (int k = 0; k < currentGroup.get(j).length(); k++) {
                        if (!secondLine.contains(currentGroup.get(j).charAt(k))) {
                            secondLine.add(currentGroup.get(j).charAt(k));
                        }
                    }
                }
                else {
                    for (int k = 0; k < currentGroup.get(j).length(); k++) {
                        if (!thirdLine.contains(currentGroup.get(j).charAt(k))) {
                            thirdLine.add(currentGroup.get(j).charAt(k));
                        }
                    }
                }
            }
            for (Character item : firstLine) {
                if (secondLine.contains(item) && thirdLine.contains(item)) {
                    if (Character.isUpperCase(item)) totalPriority += ((int)item - 38);
                    else totalPriority += ((int)item - 96);
                }
            }
        }
        System.out.println(totalPriority);
    }
}