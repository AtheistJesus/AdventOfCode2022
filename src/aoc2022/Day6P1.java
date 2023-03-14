package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day6P1 {

    public static final String PATHNAME = "inputs\\day6.txt";

    public static void main(String[] args) throws IOException {
        String lines = Files.readString(Path.of(PATHNAME));
        int marker = 4;
        for (int i = 0; i < lines.length() - 4; i++) {
            if (allDifferent(lines.substring(i, i + 4))) break;
            else marker++;
        }
        System.out.println(marker);
    }
    private static boolean allDifferent(String str) {
        for (int i = 0; i < str.length(); i++) {
            for (int j = i + 1; j < str.length(); j++) {
                if (str.charAt(i) == str.charAt(j)) return false;
            }
        }
        return true;
    }
}