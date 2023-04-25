import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class SecondProblem {
    public static void main(String[] args) throws IOException {
        try (Stream<String> lines = Files.lines(Path.of("src/input.txt"))) {
            Integer total = lines
                    .mapToInt(SecondProblem::getScore)
                    .sum();
            System.out.println(total);
        }
    }

    private static Integer getScore(String strategy) {
        return getPlayValue(strategy) + getRoundResult(getMyPlay(strategy));
    }

    private static Integer getPlayValue(String strategy) {
        return switch (strategy) {
            case "A Y", "B X", "C Z" -> 1;
            case "A Z", "C X", "B Y" -> 2;
            case "A X", "B Z", "C Y" -> 3;
            default -> 0;
        };
    }

    private static char getMyPlay(String strategy) {
        return strategy.charAt(strategy.length() - 1);
    }

    private static Integer getRoundResult(char play) {
        return switch (play) {
            case 'Y' -> 3;
            case 'Z' -> 6;
            default -> 0;
        };
    }
}