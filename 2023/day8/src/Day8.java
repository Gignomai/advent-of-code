import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day8 {
    private static final String INPUT_TEST_FILE_NAME = "src/input_test.txt";
    private static final String INPUT_TEST_FILE_NAME_2 = "src/input_test2.txt";
    private static final String INPUT_TEST_FILE_NAME_3 = "src/input_test3.txt";
    private static final String INPUT_FILE_NAME = "src/input.txt";

    public static void main(String[] args) {
        List<String> testLines = processFile(INPUT_TEST_FILE_NAME);
        List<String> testLines2 = processFile(INPUT_TEST_FILE_NAME_2);
        List<String> testLines3 = processFile(INPUT_TEST_FILE_NAME_3);
        List<String> productionLines = processFile(INPUT_FILE_NAME);

        Part1 part1 = new Part1();
        if (part1.test(testLines) && part1.test2(testLines2)) {
            // Part 1 result: 19667
            System.out.println("Part 1 result: " + part1.processLines(productionLines));
        } else {
            System.out.println("Error on Part1");
        }

        Part2 part2 = new Part2();
        if (part2.test(testLines3)) {
            // Part 2 result: 19185263738117
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
}
