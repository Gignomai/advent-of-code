import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day5 {
    private static final String INPUT_TEST_FILE_NAME = "src/input_test.txt";
    private static final String INPUT_FILE_NAME = "src/input.txt";

    public static void main(String[] args) {
        List<String> testLines = processFile(INPUT_TEST_FILE_NAME);
        List<String> productionLines = processFile(INPUT_FILE_NAME);

        Part1 part1 = new Part1();
        if (part1.test(testLines)) {
            System.out.println("Part 1 result: " + part1.processLines(productionLines));
        } else {
            System.out.println("Error on Part1");
        }

        Part2 part2 = new Part2();
        if(part2.test(testLines)) {
            System.out.println("Part 2 result: " + part2.processLines(productionLines));
        } else {
            System.out.println("Error on Part2");
        }
    }

    public static List<String> processFile(String fileName) {
        try (Stream<String> lines = Files.lines(Path.of(fileName))) {
            return lines.toList();
        } catch (IOException e) {
            System.out.println("File not found.");
            return new ArrayList<>();
        }
    }

    public static List<List<Character>> getStacksFromRows(List<String> stackRows) {
        int stackNum = stackRows.get(stackRows.size() - 1)
                .replace(" ", "")
                .length();
        List<List<Character>> charsStacks = new ArrayList<>();
        for (int i = 0; i < stackNum; i++) {
            charsStacks.add(new ArrayList<>());
        }

        for (int r = stackRows.size() - 2; r >= 0; r--) {
            String row = stackRows.get(r);
            int col = 0;
            for (int i = 1; i < row.length(); i += 4) {
                if (row.charAt(i) != ' ') {
                    charsStacks.get(col).add(row.charAt(i));
                }
                col++;
            }
        }

        return charsStacks;
    }
}
