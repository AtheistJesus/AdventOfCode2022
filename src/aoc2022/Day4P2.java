package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day4P2 {

    public static final String PATHNAME = "inputs\\day4.txt";

    public static int overlapping = 0;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        for (String line : lines) {
            String[] sections = line.split(",");
            String sectionOne = sections[0];
            String sectionTwo = sections[1];
            String[] firstPair = sectionOne.split("-");
            String[] secondPair = sectionTwo.split("-");
            int firstNum = Integer.parseInt(firstPair[0]);
            int secondNum = Integer.parseInt(firstPair[1]);
            int thirdNum = Integer.parseInt(secondPair[0]);
            int fourthNum = Integer.parseInt(secondPair[1]);
            List<Integer> firstNumbers = new ArrayList<>();
            List<Integer> secondNumbers = new ArrayList<>();
            for (int i = firstNum; i <= secondNum; i++) {
                firstNumbers.add(i);
            }
            for (int i = thirdNum; i <= fourthNum; i++) {
                secondNumbers.add(i);
            }
            for (int number : firstNumbers) {
                if (secondNumbers.contains(number)) {
                    overlapping++;
                    break;
                }
            }
        }
        System.out.println(overlapping);
    }
}