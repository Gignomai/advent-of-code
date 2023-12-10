import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Integer processLines(List<String> lines) {
        Map<Integer, Map<Integer, Integer>> numbers = new TreeMap<>();
        Map<Integer, List<Integer>> symbols = new TreeMap<>();

        for (int i = 0; i < lines.size(); i++) {
            numbers.put(i, getNumbersFromLine(lines.get(i)));
            symbols.put(i, getSymbolsFromLine(lines.get(i)));
        }

        int total = 0;
        for (Entry<Integer, List<Integer>> line : symbols.entrySet()) {
            Integer lineIndex = line.getKey();
            List<Integer> lineSymbols = line.getValue();

            for (Integer position : lineSymbols) {
                // Check previous line numbers
                for (Entry<Integer, Integer> number : numbers.get(lineIndex - 1).entrySet()) {
                    int value = number.getValue();
                    int start = number.getKey();
                    int end = (start + String.valueOf(value).length()) - 1;

                    if (position >= start - 1 && position <= end + 1) {
                        total += value;
                    }
                }

                // Check inline numbers
                for (Entry<Integer, Integer> number : numbers.get(lineIndex).entrySet()) {
                    int value = number.getValue();
                    int start = number.getKey();
                    int end = (start + String.valueOf(value).length()) - 1;
                    if (position == start - 1 || position == end + 1) {
                        total += value;
                    }
                }

                // Check next line numbers
                for (Entry<Integer, Integer> number : numbers.get(lineIndex + 1).entrySet()) {
                    int value = number.getValue();
                    int start = number.getKey();
                    int end = (start + String.valueOf(value).length()) - 1;
                    if (position >= start - 1 && position <= end + 1) {
                        total += value;
                    }
                }

            }
        }

        System.out.println("total = " + total);
        return total;
    }

    private List<Integer> getSymbolsFromLine(String line) {
        List<Integer> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("([^.0-9])");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            result.add(matcher.start());
        }

        return result;
    }

    private Map<Integer, Integer> getNumbersFromLine(String line) {
        HashMap<Integer, Integer> result = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String group = matcher.group(1);
            result.put(matcher.start(), Integer.parseInt(group));
        }

        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(4361);
    }

    public boolean test2(List<String> lines) {
        return processLines(lines).equals(49741);
    }
}