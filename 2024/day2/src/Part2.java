import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Part2 {
    public Integer processLines(List<String> lines) {
        return lines.stream()
                .map(l -> l.split(" "))
                .map(Arrays::asList)
                .map(this::isValid)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int isValid(List<String> line) {
        if (evaluate(line) == 1) {
            return 1;
        } else {
            for (int i = 0; i < line.size(); i++) {
                ArrayList<String> subList = new ArrayList<>(line);
                subList.remove(subList.get(i));
                int result = evaluate(subList);
                if (result == 1) {
                    return 1;
                }
            }
            return 0;
        }
    }

    private int evaluate(List<String> line) {
        boolean asc = true;
        for (int i = 0; i < line.size() - 1; i++) {
            int num1 = Integer.parseInt(line.get(i));
            int num2 = Integer.parseInt(line.get(i + 1));
            int diff = abs(num1 - num2);

            if (i == 0 && num1 > num2) {
                asc = false;
            }

            if ((diff > 3) || (num1 > num2 && asc) || (num1 < num2 && !asc) || (num1 == num2)) {
                return 0;
            }
        }

        return 1;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(4);
    }
}