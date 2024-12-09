import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.lang.Math.abs;

public class Part2 {

    public Long processLines(List<String> lines) {
        List<Integer> firstColumn = new ArrayList<>();
        List<Integer> secondColumn = new ArrayList<>();

        for (String line: lines) {
            firstColumn.add(Integer.valueOf(line.split("   ")[0]));
            secondColumn.add(Integer.valueOf(line.split("   ")[1]));
        }

        long total = 0;
        for (int i = 0; i < lines.size(); i++) {
            int finalI = i;
            long count = secondColumn.stream().filter(n -> Objects.equals(n, firstColumn.get(finalI))).count();
            total += firstColumn.get(i) * count;
        }

        return total;
    }

    public boolean test(List<String> fileName) {
        return processLines(fileName).equals(31L);
    }
}
