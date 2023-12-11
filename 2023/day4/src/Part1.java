import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Part1 {
    public Integer processLines(List<String> lines) {
        int total = 0;

        List<Card> cards = lines.stream()
                .map(Card::new)
                .toList();

        for (Card card: cards) {
            int count = 0;
            for (Integer winner: card.getWinners()) {
                if (card.getNumbers().contains(winner)) {
                    if (count == 0) {
                        count++;
                    } else {
                        count *= 2;
                    }
                }
            }

            total += count;
        }

        return total;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(13);
    }
}