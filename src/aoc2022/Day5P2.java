package aoc2022;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class Day5P2 {

    public static final String PATHNAME = "C:\\Users\\Damian\\Documents\\Numbers.txt";

    public static void main(String[] args) throws IOException {
        String lines = Files.readString(Path.of(PATHNAME));
        String[] split = lines.split("\r\n\r\n");
        String stack = split[0];
        String[] rows = stack.split("\n");
        String instructions = split[1];
        String[] instructionRows = instructions.split("\r\n");
        List<List<Character>> rowList = new ArrayList<>();
        createRowList(rows, rowList);
        List<List<Character>> columns = new ArrayList<>();
        createColumns(rows, rowList, columns);
        for (int i = 0; i < instructionRows.length; i++) {
            instructionRows[i] = instructionRows[i].replace("move ", "");
            instructionRows[i] = instructionRows[i].replace(" from ", "");
            instructionRows[i] = instructionRows[i].replace(" to ", "");
        }
        moveCrates(instructionRows, columns);
        for (List<Character> column : columns) {
            System.out.print(column.get(0));
        }
    }

    private static void moveCrates(String[] instructionRows, List<List<Character>> columns) {
        for (String instruction : instructionRows) {
            int move, from, to;
            if (instruction.length() == 3) {
                move = Integer.parseInt(instruction.charAt(0) + "");
                from = Integer.parseInt(instruction.charAt(1) + "");
                to = Integer.parseInt(instruction.charAt(2) + "");
            }
            else {
                move = Integer.parseInt(instruction.charAt(0) + "" + instruction.charAt(1));
                from = Integer.parseInt(instruction.charAt(2) + "");
                to = Integer.parseInt(instruction.charAt(3) + "");
            }
            List<Character> moved = new ArrayList<>();
            for (int i = 0; i < move; i++) {
                moved.add(columns.get(from - 1).get(i));
            }
            for (int i = move - 1; i >= 0; i--) {
                columns.get(to - 1).add(0, moved.get(i));
                columns.get(from - 1).remove(moved.get(i));
            }
        }
    }

    private static void createRowList(String[] rows, List<List<Character>> rowList) {
        for (int i = 0; i < rows.length - 1; i++) {
            rowList.add(new ArrayList<>());
        }
        for (int i = 0; i < rowList.size(); i++) {
            for (int j = 0; j < rows[i].length(); j += 4) {
                rowList.get(i).add(rows[i].charAt(j+1));
            }
        }
    }

    private static void createColumns(String[] rows, List<List<Character>> rowList, List<List<Character>> columns) {
        for (int i = 0; i < rows.length; i++) {
            columns.add(new ArrayList<>());
        }
        for (int i = 0; i < columns.size(); i++) {
            for (List<Character> characters : rowList) {
                columns.get(i).add(characters.get(i));
            }
        }
        for (List<Character> column : columns) {
            for (int j = 0; j < column.size();) {
                if (column.get(j) == ' ') column.remove(column.get(j));
                else j++;
            }
        }
    }
}