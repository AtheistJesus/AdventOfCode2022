package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day4P1 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

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
            if (firstNum <= thirdNum && secondNum >= fourthNum) overlapping++;
            else if (thirdNum <= firstNum && fourthNum >= secondNum) overlapping++;
        }
        System.out.println(overlapping);
    }
}