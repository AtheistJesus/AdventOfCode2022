package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class Day8P1 {

    public static final String PATHNAME = "inputs\\day8.txt";
    public static int visible = 0;

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
                boolean visibleTop = true;
                boolean visibleBottom = true;
                boolean visibleLeft = true;
                boolean visibleRight = true;
                for (int k = 0; k < j; k++) {
                    if (trees[i][k] >= current) {
                        visibleLeft = false;
                        break;
                    }
                }
                for (int k = j + 1; k < lines.get(i).length(); k++) {
                    if (trees[i][k] >= current) {
                        visibleRight = false;
                        break;
                    }
                }
                for (int k = 0; k < i; k++) {
                    if (trees[k][j] >= current) {
                        visibleTop = false;
                        break;
                    }
                }
                for (int k = i + 1; k < lines.size(); k++) {
                    if (trees[k][j] >= current) {
                        visibleBottom = false;
                        break;
                    }
                }
                if (visibleTop || visibleBottom || visibleLeft || visibleRight) visible++;
            }
        }
        System.out.println(visible);
    }
}