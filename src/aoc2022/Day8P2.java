package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day8P2 {

    public static final String PATHNAME = "inputs\\day8.txt";
    public static int highScore = 0;

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        int[][] trees = new int[lines.size()][lines.get(0).length()];
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                trees[i][j] = Integer.parseInt(lines.get(i).charAt(j) + "");
            }
        }
        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).length(); j++) {
                int current = trees[i][j];
                int visibleTop = 0;
                int visibleBottom = 0;
                int visibleLeft = 0;
                int visibleRight = 0;
                for (int k = j - 1; k >= 0; k--) {
                    visibleLeft++;
                    if (trees[i][k] >= current) break;
                }
                for (int k = j + 1; k < lines.get(i).length(); k++) {
                    visibleRight++;
                    if (trees[i][k] >= current) break;
                }
                for (int k = i - 1; k >= 0; k--) {
                    visibleTop++;
                    if (trees[k][j] >= current) break;
                }
                for (int k = i + 1; k < lines.size(); k++) {
                    visibleBottom++;
                    if (trees[k][j] >= current) break;
                }

                int scenicScore = visibleLeft * visibleRight * visibleTop * visibleBottom;
                if (scenicScore > highScore) {
                    //System.out.println(i + " " + j);
                    highScore = scenicScore;
                }
            }
        }
        System.out.println(highScore);
    }
}