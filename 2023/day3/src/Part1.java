import java.util.*;
import java.util.Map.Entry;
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

        for (Entry<Integer, Map<Integer, String>> line : numbers.entrySet()) {
            Integer lineIndex = line.getKey();
            Map<Integer, String> lineNumbers = line.getValue();

            for (Entry<Integer, String> number : lineNumbers.entrySet()) {
                int value = number.getKey();
                int start = Integer.parseInt(number.getValue().split(",")[0]);
                int end = Integer.parseInt(number.getValue().split(",")[1]);
                boolean isAdjacent = false;

                if (lineIndex > 0) {
                    // Check previous line symbols
                    for (Integer symPos : symbols.get(lineIndex - 1)) {
                        if (symPos >= start - 1 && symPos <= end + 1) {
                            parts.add(value);
                            isAdjacent = true;
                            break;
                        }
                    }
                }

                //Check inline symbols
                if (!isAdjacent) {
                    for (Integer symPos : symbols.get(lineIndex)) {
                        if (symPos == start - 1 || symPos == end + 1) {
                            parts.add(value);
                            isAdjacent = true;
                            break;
                        }
                    }
                }

                if (!isAdjacent && lineIndex < numbers.size() - 1) {
                    //Check next line symbols
                    for (Integer symPos : symbols.get(lineIndex + 1)) {
                        if (symPos >= start - 1 && symPos <= end + 1) {
                            parts.add(value);
                            break;
                        }
                    }
                }
            }
        }

//        System.out.println("symbols = " + symbols);
//        System.out.println("numbers = " + numbers);
        int total = parts.stream()
                .mapToInt(Integer::intValue)
                .sum();
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
        HashMap<Integer, String> result = new HashMap<>();
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String group = matcher.group(1);
            int number = Integer.parseInt(group);
            String range = matcher.start() + "," + (matcher.end() - 1);
            result.put(number, range);
        }

        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(4361);
    }

    public boolean test2(List<String> lines) {
        return processLines(lines).equals(53780);
    }
}