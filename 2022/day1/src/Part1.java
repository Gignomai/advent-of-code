import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Part1 {
    public Integer processLines(List<String> calories) {
        List<Integer> groupedCalories = new ArrayList<>();
        int elfCalories = 0;

        for (int i = 0; i < calories.size(); i++) {
            String line = calories.get(i);
            if (!line.isEmpty()) {
                elfCalories += Integer.parseInt(line);
                if (i == calories.size() - 1){
                    groupedCalories.add(elfCalories);
                }
            } else {
                groupedCalories.add(elfCalories);
                elfCalories = 0;
            }
        }

        groupedCalories.sort(Collections.reverseOrder());
        return groupedCalories.get(0);
    }

    public boolean testPart1(List<String> lines) {
        return processLines(lines).equals(24000);
    }
}
