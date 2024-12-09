import java.util.Arrays;
import java.util.List;

import static java.lang.Math.abs;

public class Part1 {

    public Integer processLines(List<String> lines) {
        return lines.stream()
                .map(l -> l.split(" "))
                .map(this::isValid)
                .mapToInt(Integer::intValue)
                .sum();
    }

    private int isValid(String[] line) {
        boolean asc = true;
        for (int i = 0; i < line.length - 1; i++) {
            int num1 = Integer.parseInt(line[i]);
            int num2 = Integer.parseInt(line[i + 1]);
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
        return processLines(lines).equals(2);
    }
}