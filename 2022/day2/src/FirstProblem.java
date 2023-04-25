import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class FirstProblem {
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("src/input.txt"))) {
            Integer total = lines
                    .mapToInt(FirstProblem::getScore)
                    .sum();
            System.out.println(total);
        }
    }

    private static Integer getScore(String s) {
        Integer playValue = switch (s.charAt(s.length() - 1)) {
            case 'X' -> 1;
            case 'Y' -> 2;
            case 'Z' -> 3;
            default -> 0;
        };

        Integer roundResult = switch (s) {
            case "A X", "B Y", "C Z" -> 3;
            case "A Y", "B Z", "C X" -> 6;
            default -> 0;
        };

        return playValue + roundResult;
    }
}