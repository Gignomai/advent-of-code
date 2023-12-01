import java.util.List;

public class Part2 {
    public Integer processLines(List<String> lines) {
        return lines.stream()
                .mapToInt(Part2::getScore)
                .sum();
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

    public boolean test(List<String> fileName) {
        return processLines(fileName).equals(12);
    }
}