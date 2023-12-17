import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
    public Long processLines(List<String> lines) {
        List<Long> locations = new ArrayList<>();
        // Create Maps
        List<List<List<Long>>> mappings = new ArrayList<>();
        List<List<Long>> mapping = new ArrayList<>();
        for (int i = 3; i < lines.size(); i++) {
            if (lines.get(i).isEmpty()) {
                mappings.add(mapping);
                mapping = new ArrayList<>();
                i ++;
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
        Map<Long,Long> memo = new HashMap<>();
        String[] seeds = lines.get(0).split(":")[1].trim().split(" ");
        for (int i = 0; i < seeds.length; i += 2) {
            long seedRangeStart = Long.parseLong(seeds[i]);
            long seedRangeLength = Long.parseLong(seeds[i + 1]);
            for (long seed = seedRangeStart; seed < seedRangeStart + seedRangeLength; seed++) {
                long mappedValue = seed;

                for (List<List<Long>> map: mappings) {
                    for(List<Long> values: map) {
                        if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
                            mappedValue = values.get(0) + mappedValue - values.get(1);
                            break;
                        }
                    }
                }

                // System.out.println("mappedValue = " + mappedValue);
                locations.add(mappedValue);

            }
        }


        // Return closer (lower) location
        return locations.stream()
                .mapToLong(Long::valueOf)
                .min()
                .orElse(0L);
        // System.out.println("result = " + result);
//        return 46L;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(46L);
    }
}