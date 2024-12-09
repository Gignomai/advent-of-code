import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;

public class Part1 {
    public Integer processLines(List<String> lines) {
        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();

        for (String line: lines) {
            firstColumn.add(Integer.valueOf(line.split("   ")[0]));
            secondColumn.add(Integer.valueOf(line.split("   ")[1]));
        }

        firstColumn.sort(Integer::compareTo);
        secondColumn.sort(Integer::compareTo);

        int total = 0;
        for (int i = 0; i < lines.size(); i++) {
            total += abs(firstColumn.get(i) - secondColumn.get(i));
        }

        return total;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(11);
    }
}
