package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day11P1 {

    public static final String PATHNAME = "inputs\\day11.txt";

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.readAllLines(Path.of(PATHNAME));
        int monkeyCount = 0;
        for (int i = 0; i < lines.size(); i+= 7) monkeyCount++;
        int[] monkeys = new int[monkeyCount];
        List<List<Integer>> currentItems = new ArrayList<>();
        for (int i = 0; i < monkeyCount; i++) {
            currentItems.add(new ArrayList<>());
        }
        for (int round = 0; round < 20; round++) {
            for (int i = 0; i < lines.size(); i += 7) {
                int currentMonkey = Integer.parseInt(lines.get(i).split(" ")[1].charAt(0) + "");
                if (round == 0) {
                    String firstSplit = lines.get(i + 1).split(": ")[1];
                    String[] items = firstSplit.split(", ");
                    for (String item : items) {
                        currentItems.get(currentMonkey).add(Integer.parseInt(item));
                    }
                    monkeys[currentMonkey] += items.length;
                }
                for (int k = 0; k < currentItems.get(currentMonkey).size(); k++) {
                    currentItems.get(currentMonkey).set(k, operation(currentItems.get(currentMonkey).get(k), lines.get(i + 2)));
                }
                int test = Integer.parseInt(lines.get(i + 3).trim().split(" ")[3]);
                int firstMonkey = Integer.parseInt(lines.get(i + 4).trim().split(" ")[5]);
                int secondMonkey = Integer.parseInt(lines.get(i + 5).trim().split(" ")[5]);
                currentItems.get(currentMonkey).replaceAll(integer -> integer / 3);
                for (int item : currentItems.get(currentMonkey)) {
                    if (item % test == 0) {
                        currentItems.get(firstMonkey).add(item);
                        if (round != 19) monkeys[firstMonkey]++;
                        else {
                            if (currentMonkey < firstMonkey) monkeys[firstMonkey]++;
                        }
                    }
                    else {
                        currentItems.get(secondMonkey).add(item);
                        if (round != 19) monkeys[secondMonkey]++;
                        else {
                            if (currentMonkey < secondMonkey) monkeys[secondMonkey]++;
                        }
                    }
                }
                currentItems.get(currentMonkey).clear();
            }
        }
        Arrays.sort(monkeys);
        System.out.println(monkeys[monkeys.length - 1] * monkeys[monkeys.length - 2]);
    }

    public static int operation(int number, String line) {
        String firstSplit = line.split(" = ")[1];
        firstSplit = firstSplit.replaceAll("old", number + "");
        String[] info = firstSplit.split(" ");
        if (info[1].equals("+")) return Integer.parseInt(info[0]) + Integer.parseInt(info[2]);
        else return Integer.parseInt(info[0]) * Integer.parseInt(info[2]);
    }
}