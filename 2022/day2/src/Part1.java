import java.util.List;

public class Part1 {
    public Integer processLines(List<String> lines) {
        return lines.stream()
                .mapToInt(Part1::getScore)
                .sum();
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

    public boolean test(List<String> lines) {
        return processLines(lines).equals(15);
    }
}