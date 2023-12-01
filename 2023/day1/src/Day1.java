import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day1 {
    private static final String INPUT_TEST_FILE_NAME = "src/input_test.txt";
    private static final String INPUT_FILE_NAME = "src/input.txt";
    public static final String SRC_INPUT_TEST_PART_2_TXT = "src/input_test_part2.txt";

    public static void main(String[] args) {
        List<String> testLines = processFile(INPUT_TEST_FILE_NAME);
        List<String> productionLines = processFile(INPUT_FILE_NAME);

        Part1 part1 = new Part1();
        if (part1.test(testLines)) {
            // Part 1 result: 54951
            System.out.println("Part 1 result: " + part1.processLines(productionLines));
        } else {
            System.out.println("Error on Part1");
        }

        testLines = processFile(SRC_INPUT_TEST_PART_2_TXT);
        Part2 part2 = new Part2();
        if(part2.test(testLines)) {
            // Part 2 result: 55218
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
