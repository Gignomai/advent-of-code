import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part2 {

    public static final int MAX_SIZE = 3;

    public Integer processCaloriesInput(List<String> calories) {
        return getGroupedCaloriesTotals(calories).stream()
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

    public boolean testPart2(List<String> fileName) {
        return processCaloriesInput(fileName).equals(45000);
    }
}
