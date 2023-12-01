import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part1 {
    public Integer processLines(List<String> lines) {
        return lines.stream()
                .map(s -> s.replaceAll("\\D*", ""))
                .map(s -> s.charAt(0) + s.substring(s.length() - 1))
                .mapToInt(Integer::valueOf)
                .sum();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(142);
    }
}
