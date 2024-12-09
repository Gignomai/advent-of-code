import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class Day3 {
    private static final String INPUT_TEST_FILE_NAME = "src/input_test.txt";
    private static final String INPUT_TEST_FILE_NAME2 = "src/input_test2.txt";
    private static final String INPUT_FILE_NAME = "src/input.txt";

    public static void main(String[] args) {
        List<String> testLines = processFile(INPUT_TEST_FILE_NAME);
        List<String> testLines2 = processFile(INPUT_TEST_FILE_NAME2);
        List<String> productionLines = processFile(INPUT_FILE_NAME);

        Part1 part1 = new Part1();
        if (part1.test(testLines)) {
            //result = 175615763
            System.out.println("Part 1 result: " + part1.processLines(productionLines));
        } else {
            System.out.println("Error on Part1");
        }

        Part2 part2 = new Part2();
        if(part2.test(testLines2)) {
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
