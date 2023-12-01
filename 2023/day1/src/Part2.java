import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

public class Part2 {

    private final TreeMap<String, String> numbers;

    public Part2() {
        numbers = new TreeMap<>();
        numbers.put("one","1");
        numbers.put("two","2");
        numbers.put("three","3");
        numbers.put("four","4");
        numbers.put("five","5");
        numbers.put("six","6");
        numbers.put("seven","7");
        numbers.put("eight","8");
        numbers.put("nine","9");
    }

    public Integer processLines(List<String> lines) {
        return lines.stream()
                .peek(System.out::println)
                .map(this::replaceNamesForNumbers)
                .peek(System.out::println)
                .map(s -> s.replaceAll("\\D*", ""))
                .peek(System.out::println)
                .map(s -> s.charAt(0) + s.substring(s.length() - 1))
                .peek(System.out::println)
                .mapToInt(Integer::valueOf)
                .sum();
    }

    private String replaceNamesForNumbers(String line) {
        for (Entry<String, String> entry : numbers.entrySet()) {
            line = line.replaceAll(entry.getKey(), entry.getKey() + entry.getValue());
        }

        return line;
    }

    public boolean test(List<String> fileName) {
        return processLines(fileName).equals(281);
    }
}
