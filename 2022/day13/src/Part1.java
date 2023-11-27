import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {
    public Integer processLines(List<String> lines) {
        String left = "";
        String right = "";
        int counter = 1;
        int total = 0;
        for (String s : lines) {
            if (!s.isEmpty()) {
                if (left.isEmpty()) {
                    left = s;
                } else if (right.isEmpty()) {
                    right = s;
                }
            } else {
                if (checkRightOrder(left, right)) {
                    total += counter;
                }
                left = "";
                right = "";
            }
            counter++;
        }

        return total;
    }

    private boolean checkRightOrder(String left, String right) {
        List<String> leftElements = getElementsOfList(left);
        List<String> rightElements = getElementsOfList(right);

        return compareElements(leftElements, rightElements);
    }

    private List<String> getElementsOfList(String part) {
        Pattern.compile("^\\[(.+)\\]$")
                .matcher(part)
                .results()
                .map(MatchResult::group)
                .map(s -> s.substring(1, s.length() -1))
                .forEach(System.out::println);
                //.map(s -> s.split(","))

        return List.of("");
    }

    private boolean compareElements(List<String> leftElements, List<String> rightElements) {
        for (String s : leftElements) {
            System.out.println("s = " + s);
        }
        for (String s : rightElements) {
            System.out.println("s = " + s);
        }
        return false;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(13);
    }
}
