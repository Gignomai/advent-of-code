import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public Integer processLines(List<String> lines) {
        List<Integer> times = getNumbersFromLine(lines.get(0));
        List<Integer> distances = getNumbersFromLine(lines.get(1));
        int total = 1;

        for (int i = 0; i < times.size(); i++) {
            int count = 0;
            int time = times.get(i);
            int distance = distances.get(i);

            for (int j = 1; j < time; j++) {
                if (j * (time- j) > distance) {
                    count ++;
                }
            }

            total *= count;
        }

        return total;
    }

    private List<Integer> getNumbersFromLine(String line) {
        List<Integer> result = new ArrayList<>();
        Pattern pattern = Pattern.compile("(\\d+)");
        Matcher matcher = pattern.matcher(line);

        while (matcher.find()) {
            String group = matcher.group(1);
            result.add(Integer.parseInt(group));
        }

        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(288);
    }

}