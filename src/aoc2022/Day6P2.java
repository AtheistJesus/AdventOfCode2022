package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Day6P2 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

    public static void main(String[] args) throws IOException {
        String lines = Files.readString(Path.of(PATHNAME));
        int marker = 14;
        for (int i = 0; i < lines.length() - 14; i++) {
            if (allDifferent(lines.substring(i, i + 14))) break;
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