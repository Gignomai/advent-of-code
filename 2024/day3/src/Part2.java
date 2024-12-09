import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Part2 {

    public Integer processLines(List<String> lines) {
        int result = 0;
        boolean active = true;

        for (String line : lines) {
            Pattern pattern = Pattern.compile("(do\\(\\)|don't\\(\\)|mul\\(\\d+,\\d+\\))");
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String group1 = matcher.group(1);
                //System.out.println("group1 = " + group1);
                if (group1.equals("don't()")) {
                    active = false;
                } else if (group1.equals("do()")) {
                    active = true;
                } else if (active) {
                    String part1 = group1.split(",")[0].replace("mul(", "");
                    String part2 = group1.split(",")[1].replace(")", "");

                    int num1 = Integer.parseInt(part1);
                    int num2 = Integer.parseInt(part2);
                    result += num1 * num2;
                }
            }
        }

        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(48);
    }
}