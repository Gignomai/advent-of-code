import java.util.List;
import java.util.stream.Stream;

public class Part2 {
    public Integer processLines(List<String> lines) {
        int total = 0;

        for (String line: lines) {

            List<Integer> winners = winnersFormCard(line);
            List<Integer> numbers = numbersFromCard(line);
            int count = 0;
            for (Integer winner: winners) {
                if (numbers.contains(winner)) {
                    if (count == 0) {
                        count++;
                    } else {
                        count *= 2;
                    }
                }
            }

            total += count;
        }
        System.out.println("total = " + total);
        return total;
    }

    private List<Integer> winnersFormCard(String line) {
        String firstPart = line.split("\\|")[0];
        return Stream.of(firstPart.split(":")[1].trim().split(" "))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    private List<Integer> numbersFromCard(String line) {
        String part = line.split("\\|")[1];
        return Stream.of(part.split(" "))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(30);
    }
}