import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {

    public Long processLines(List<String> lines) {
        List<List<Long>> row = lines.stream()
                .map(s -> s.split(" "))
                .map(Arrays::asList)
                .map(this::toNumberList)
                .toList();
        long total = 0L;
        for (List<Long> numbers : row) {
            List<List<Long>> sequences = new ArrayList<>(List.of(numbers));
            boolean allZeros;
            do {
                allZeros = true;
                List<Long> currentSequence = sequences.get(sequences.size() - 1);
                List<Long> nextSequence = new ArrayList<>();
                for (int i = 0; i < currentSequence.size() - 1; i++) {
                    long value = currentSequence.get(i + 1) - currentSequence.get(i);
                    nextSequence.add(value);
                    if (value != 0) {
                        allZeros = false;
                    }
                }
                sequences.add(nextSequence);
                total += currentSequence.get(currentSequence.size() - 1);
            } while (!allZeros);
        }

        return total;
    }

    private List<Long> toNumberList(List<String> strings) {
        return strings.stream()
                .map(Long::parseLong)
                .toList();
    }


    public boolean test(List<String> lines) {
        return processLines(lines).equals(114L);
    }
}