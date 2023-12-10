import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part2 {

    public Integer processLines(List<String> lines) {
        Map<Integer, Map<Integer, Integer>> numbers = new TreeMap<>();
        Map<Integer, Map<Integer, String>> symbols = new TreeMap<>();

        for (int i = 0; i < lines.size(); i++) {
            numbers.put(i, getNumbersFromLine(lines.get(i)));
            symbols.put(i, getSymbolsFromLine(lines.get(i)));
        }

        int total = 0;
        for (Map.Entry<Integer, Map<Integer, String>> line : symbols.entrySet()) {
            Integer lineIndex = line.getKey();
            Map<Integer, String> lineSymbols = line.getValue();

            for (Map.Entry<Integer, String> symbol : lineSymbols.entrySet()) {
                int position = symbol.getKey();
                String symbolValue = symbol.getValue();

                if (symbolValue.equals("*")) {
                    int count = 0;
                    int subtotal = 1;

                    // Check previous line numbers
                    for (Map.Entry<Integer, Integer> number : numbers.get(lineIndex - 1).entrySet()) {
                        int numberValue = number.getValue();
                        int start = number.getKey();
                        int end = (start + String.valueOf(numberValue).length()) - 1;

                        if (position >= start - 1 && position <= end + 1) {
                            count++;
                            subtotal *= numberValue;
                        }
                    }

                    // Check inline numbers
                    for (Map.Entry<Integer, Integer> number : numbers.get(lineIndex).entrySet()) {
                        int numberValue = number.getValue();
                        int start = number.getKey();
                        int end = (start + String.valueOf(numberValue).length()) - 1;
                        if (position == start - 1 || position == end + 1) {
                            count++;
                            subtotal *= numberValue;
                        }
                    }

                    // Check next line numbers
                    for (Map.Entry<Integer, Integer> number : numbers.get(lineIndex + 1).entrySet()) {
                        int numberValue = number.getValue();
                        int start = number.getKey();
                        int end = (start + String.valueOf(numberValue).length()) - 1;
                        if (position >= start - 1 && position <= end + 1) {
                            count++;
                            subtotal *= numberValue;
                        }
                    }

                    if (count == 2) {
                        total += subtotal;
                    }
                }
            }
        }

        System.out.println("total = " + total);
        return total;
    }

    private Map<Integer, String> getSymbolsFromLine(String line) {
        Map<Integer, String> result = new HashMap<>();
        Pattern pattern = Pattern.compile("([^.0-9])");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String group = matcher.group(1);
            result.put(matcher.start(), group);
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
        return processLines(lines).equals(467835);
    }
}