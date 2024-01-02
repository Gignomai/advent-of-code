import java.util.HashMap;
import java.util.List;

public class Part2 {
    public Integer processLines(List<String> lines) {
        List<Card> cards = lines.stream()
                .map(Card::new)
                .toList();

        HashMap<Integer, Integer> cardCount = new HashMap<>();
        for (int i = 1; i <= lines.size(); i++) {
            cardCount.put(i, 1);
        }

        int lineNumber = 1;
        for (Card card: cards) {
            int count = 0;

            for (Integer winner : card.getWinners()) {
                if (card.getNumbers().contains(winner)) {
                    count++;
                }
            }

            for (int i = 1; i <= count; i++) {
                Integer currentCardCopies = cardCount.get(lineNumber);
                Integer copies = cardCount.get(lineNumber + i);

                cardCount.put(lineNumber + i, copies + currentCardCopies);
            }

            lineNumber++;
        }

        Integer total = 0;
        for (var entry: cardCount.entrySet()) {
            total += entry.getValue();
        }

        return total;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(30);
    }
}