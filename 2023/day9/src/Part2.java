import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part2 {

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
            List<Long> results = new ArrayList<>();
            do {
                allZeros = true;
                List<Long> currentSequence = sequences.get(sequences.size() - 1);
                List<Long> nextSequence = new ArrayList<>();
                for (int i = currentSequence.size() - 1; i > 0; i--) {
                    long value = currentSequence.get(i) - currentSequence.get(i - 1);
                    nextSequence.add(0, value);
                    if (value != 0) {
                        allZeros = false;
                    }
                }
                sequences.add(nextSequence);
                results.add(currentSequence.get(0));
            } while (!allZeros);

            results.add(0L);
            for (int i = results.size() - 1; i > 0; i--) {
                total += (results.get(i - 1) - results.get(i));
            }
//            total += results.stream()
//                    .mapToLong(Long::longValue)
//                    .sum();
        }

        return total;
    }

    private List<Long> toNumberList(List<String> strings) {
        return strings.stream()
                .map(Long::parseLong)
                .toList();
    }


    public boolean test(List<String> lines) {
        return processLines(lines).equals(2L);
    }
}