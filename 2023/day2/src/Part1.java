import java.util.List;

public class Part1 {

    public Integer processLines(List<String> lines) {
        return lines.stream()
                .map(Game::new)
                .filter(Game::isValid)
                .mapToInt(Game::getId)
                .sum();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(8);
    }
}