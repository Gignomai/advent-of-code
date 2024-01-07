import java.util.Collections;
import java.util.List;

public class Part2 {
    public Long processLines(List<String> lines){
        List<HandJoker> hands = new java.util.ArrayList<>(lines.stream()
                .map(line -> line.split(" "))
                .map(parts -> new HandJoker(parts[0], parts[1]))
                .toList());

        Collections.sort(hands);
        System.out.println("hands = " + hands);

        long total = 0L;

        for (int i = 0; i < hands.size(); i++) {
            total += (hands.get(i).getBid() * (i + 1L));
        }

        return total;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(5905L);
    }
}