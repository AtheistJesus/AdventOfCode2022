package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day2P2 {

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
        return choices[1].equals("Z");
    }

    public static boolean draw(String[] choices) {
        return choices[1].equals("Y");
    }
    public static void updateScore(String[] choices) {
        if (choices[0].equals("A")) {
            if (choices[1].equals("X")) score += 3;
            else if (choices[1].equals("Y")) score += 1;
            else score += 2;
        }
        else if (choices[0].equals("B")) {
            if (choices[1].equals("X")) score += 1;
            else if (choices[1].equals("Y")) score += 2;
            else score += 3;
        }
        else {
            if (choices[1].equals("X")) score += 2;
            else if (choices[1].equals("Y")) score += 3;
            else score += 1;
        }
    }
}