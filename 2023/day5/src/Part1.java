import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Part1 {
    public Long processLines(List<String> lines) {
        long location = Long.MAX_VALUE;
        // Get list of seeds
        List<Long> seeds = Arrays.stream(
                        lines.get(0)
                                .split(":")[1]
                                .trim()
                                .split(" "))
                .map(Long::valueOf)
                .toList();
        // Create Maps
        List<List<List<Long>>> mappings = new ArrayList<>();
        List<List<Long>> mapping = new ArrayList<>();
        for (int i = 3; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                mappings.add(mapping);
                mapping = new ArrayList<>();
                i++;
            } else {
                String[] parts = lines.get(i).split(" ");
                long destinationRangeStart = Long.parseLong(parts[0]);
                long sourceRangeStart = Long.parseLong(parts[1]);
                long rangeLength = Long.parseLong(parts[2]);
                mapping.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            }
        }
        mappings.add(mapping);

        // Map seeds to location
        for (Long seed : seeds) {
            long mappedValue = seed;

            for (List<List<Long>> map : mappings) {
                for (List<Long> values : map) {
                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
                        mappedValue = values.get(0) + mappedValue - values.get(1);
                        break;
                    }
                }
            }
            if (mappedValue < location) {
                location = mappedValue;
            }
        }
        // Return closer (lower) location
        return location;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(35L);
    }
}