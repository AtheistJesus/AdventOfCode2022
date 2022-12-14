package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2P1 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";
    public static int score = 0;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        for (String line : lines) {
            String[] choices = line.split(" ");
            if (win(choices)) {
                score += 6;
            }
            else if (draw(choices)) {
                score += 3;
            }
            updateScore(choices);
        }
        System.out.println(score);
    }
    public static boolean win(String[] choices) {
        return choices[0].equals("A") && choices[1].equals("Y") ||
        choices[0].equals("B") && choices[1].equals("Z") ||
        choices[0].equals("C") && choices[1].equals("X");
    }

    public static boolean draw(String[] choices) {
        return choices[0].equals("A") && choices[1].equals("X") ||
                choices[0].equals("B") && choices[1].equals("Y") ||
                choices[0].equals("C") && choices[1].equals("Z");
    }
    public static void updateScore(String[] choices) {
        if (choices[1].equals("X")) score += 1;
        else if (choices[1].equals("Y")) score += 2;
        else score += 3;
    }
}