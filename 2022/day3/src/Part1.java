import java.util.List;

public class Part1 {


    public Long processLines(List<String> lines) {
        return lines.stream()
                .mapToLong(Part1::getPriority)
                .sum();
    }

    private static Integer getPriority(String s) {
        String part1 = s.substring(0, s.length() / 2);
        String part2 = s.substring(s.length() / 2);

        return part1.chars()
                .filter(c -> part2.indexOf(c) >= 0)
                .mapToObj(c -> (char) c)
                .findAny()
                .map(finding -> Day3.LETTERS.indexOf(finding) + 1)
                .orElseThrow();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(157L);
    }
}