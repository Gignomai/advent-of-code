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
        List<Integer> parts = new ArrayList<>();
        Map<Integer, Map<Integer, String>> numbers = new TreeMap<>();
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

                for (Entry<Integer, String> number : numbers.get(lineIndex - 1).entrySet()) {
                    int value = number.getKey();
                    int start = Integer.parseInt(number.getValue().split(",")[0]);
                    int end = Integer.parseInt(number.getValue().split(",")[1]);

                    if (position >= start - 1 && position <= end + 1) {
                        total += value;
                    }
                }

                //Check inline symbols
                for (Entry<Integer, String> number : numbers.get(lineIndex).entrySet()) {
                    int value = number.getKey();
                    int start = Integer.parseInt(number.getValue().split(",")[0]);
                    int end = Integer.parseInt(number.getValue().split(",")[1]);
                    if (position == start - 1 || position == end + 1) {
                        total += value;
                    }
                }

                //Check next line symbols
                for (Entry<Integer, String> number : numbers.get(lineIndex + 1).entrySet()) {
                    int value = number.getKey();
                    int start = Integer.parseInt(number.getValue().split(",")[0]);
                    int end = Integer.parseInt(number.getValue().split(",")[1]);
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
//            System.out.println("matcher = " + matcher.group());
            result.add(matcher.start());
        }

        return result;
    }

    private Map<Integer, String> getNumbersFromLine(String line) {
        System.out.println("line = " + line);
        HashMap<Integer, String> result = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String group = matcher.group(1);
            int number = Integer.parseInt(group);
            String range = matcher.start() + "," + (matcher.end() - 1);
            result.put(number, range);
        }
        System.out.println("result = " + result);
        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(4361);
    }

    public boolean test2(List<String> lines) {
        return processLines(lines).equals(49741);
    }
}