import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Integer processLines(List<String> lines) {
        Set<Integer> parts = new HashSet<>();
        List<Map<Integer, String>> numbers = new ArrayList<>();
        List<List<Integer>> symbols = new ArrayList<>();

        for (int i = 0; i < lines.size(); i++) {
            Map<Integer, String> nums = getNumbersFromLine(lines.get(i));
            List<Integer> syms = getSymbolsFromLine(lines.get(i));

            for (Entry<Integer, String> entry: nums.entrySet()) {
                String[] range = entry.getValue().split(",");
                int start = Integer.parseInt(range[0]);
                int end = Integer.parseInt(range[1]);

                for (Integer symPos: syms) {
                    if (symPos == start - 1 || symPos == end + 1) {
                        parts.add(entry.getKey());
                        break;
                    }
                }
            }

            if (i > 0) {
                for (Entry<Integer, String> entry: nums.entrySet()) {
                    String[] range = entry.getValue().split(",");
                    int start = Integer.parseInt(range[0]);
                    int end = Integer.parseInt(range[1]);

                    for (Integer symPos: symbols.get(i - 1)) {
                        if (symPos >= start - 1 && symPos <= end + 1) {
                            parts.add(entry.getKey());
                            break;
                        }
                    }
                }

                for (Integer symPos: syms) {
                    for (Entry<Integer, String> entry: numbers.get(i - 1).entrySet()) {
                        String[] range = entry.getValue().split(",");
                        int start = Integer.parseInt(range[0]);
                        int end = Integer.parseInt(range[1]);

                        if (symPos >= start - 1 && symPos <= end + 1) {
                            parts.add(entry.getKey());
                            break;
                        }
                    }
                }
            }

            numbers.add(nums);
            symbols.add(syms);
        }

        System.out.println("symbols = " + symbols);
        System.out.println("numbers = " + numbers);

        return parts.stream()
                .mapToInt(Integer::intValue)
                .sum();
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
}