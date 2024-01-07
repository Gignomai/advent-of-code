import java.util.Collections;
import java.util.List;

public class Part1 {

    public Long processLines(List<String> lines){
        List<Hand> hands = new java.util.ArrayList<>(lines.stream()
                .map(line -> line.split(" "))
                .map(parts -> new Hand(parts[0], parts[1]))
                .toList());
        Collections.sort(hands);

        long total = 0L;
        for (int i = 0; i < hands.size(); i++) {
            total += (hands.get(i).getBid() * (i + 1L));
        }

        return total;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(6440L);
    }
}