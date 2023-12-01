import javax.swing.text.html.parser.Entity;
import java.util.*;

public class Part2 {

    private final Map<String, String> numbers = Map.of(
            "one", "1",
            "two", "2",
            "three", "3",
            "four", "4",
            "five", "5",
            "six", "6",
            "seven", "7",
            "eight", "8",
            "nine", "9"
    );

    public Integer processLines(List<String> lines) {
        int total = 0;

        for (String line : lines) {
            TreeMap<Integer, String> numberPositions = new TreeMap<>();
            // System.out.println("line = " + line);
            numbers.forEach((key, value) -> {
                if (line.contains(key)) {
                    int firstIndex = line.indexOf(key);
                    int lastIndex = line.lastIndexOf(key);
                    numberPositions.put(firstIndex, value);
                    if (lastIndex != firstIndex) {
                        numberPositions.put(lastIndex, value);
                    }
                }
            });

            for (int i = 0; i < line.length(); i++) {
                try {
                    int value = Integer.parseInt(String.valueOf(line.charAt(i)));
                    numberPositions.put(i, String.valueOf(line.charAt(i)));
                } catch (NumberFormatException ex) {
                    // Not a number
                }
            }

            // System.out.println("numberPositions = " + numberPositions);
            int calibrationValue = Integer.parseInt(numberPositions.firstEntry().getValue() + numberPositions.lastEntry().getValue());
            // System.out.println("calibrationValue = " + calibrationValue);
            total += calibrationValue;
        }
        // System.out.println("total = " + total);
        return total;
    }

    public boolean test(List<String> fileName) {
        return processLines(fileName).equals(455);
    }
}
