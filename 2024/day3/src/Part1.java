import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {

    public Integer processLines(List<String> lines) {
        int result = 0;

        for (String line : lines) {
            Pattern pattern = Pattern.compile("mul\\((\\d+),(\\d+)\\)");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String group1 = matcher.group(1);
                String group2 = matcher.group(2);
                result += Integer.parseInt(group1) * Integer.parseInt(group2);
            }
        }

        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(161);
    }

}