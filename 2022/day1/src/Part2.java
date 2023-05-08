import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {

    public static final int MAX_SIZE = 3;

    public Integer processLines(List<String> lines) {
        return getGroupedCaloriesTotals(lines).stream()
                .sorted(Collections.reverseOrder())
                .limit(MAX_SIZE)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private static List<Integer> getGroupedCaloriesTotals(List<String> calories) {
        List<Integer> totals = new ArrayList<>();
        int subtotal = 0;

        for (int i = 0; i < calories.size(); i++) {
            String line = calories.get(i);
            if (line.isEmpty()) {
                totals.add(subtotal);
                subtotal = 0;
            } else {
                subtotal += Integer.parseInt(line);
                if (i == calories.size() - 1) {
                    totals.add(subtotal);
                }
            }
        }

        return totals;
    }

    public boolean test(List<String> fileName) {
        return processLines(fileName).equals(45000);
    }
}
