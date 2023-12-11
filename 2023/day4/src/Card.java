import java.util.List;
import java.util.stream.Stream;

public class Card {
    private final List<Integer> winners;
    private final List<Integer> numbers;

    public Card(String input) {
        winners = winnersFormCard(input);
        numbers = numbersFromCard(input);
    }

    private List<Integer> winnersFormCard(String input) {
        String firstPart = input.split("\\|")[0];
        return Stream.of(firstPart.split(":")[1].trim().split(" "))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    private List<Integer> numbersFromCard(String input) {
        String part = input.split("\\|")[1];
        return Stream.of(part.split(" "))
                .filter(s -> !s.isEmpty())
                .map(Integer::parseInt)
                .toList();
    }

    public List<Integer> getNumbers() {
        return numbers;
    }

    public List<Integer> getWinners() {
        return winners;
    }
}
