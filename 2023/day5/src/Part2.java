import java.util.List;

public class Part2 {
    public Long processLines(List<String> lines) {
        // Create Maps
        long[][][] mappings = new long[7][60][3];
        int mappingCount = 0;
        int mappingRange = 0;
        for (int line = 3; line < lines.size(); line++) {
            if (lines.get(line).isEmpty()) {
                mappingRange = 0;
                mappingCount++;
                line++;
            } else {
                String[] parts = lines.get(line).split(" ");
                mappings[mappingCount][mappingRange][0] = Long.parseLong(parts[0]);
                mappings[mappingCount][mappingRange][1] = Long.parseLong(parts[1]);
                mappings[mappingCount][mappingRange][2] = Long.parseLong(parts[2]);
                mappingRange++;
            }
        }

        // Map seeds to location
        long location = Long.MAX_VALUE;
        String[] seeds = lines.get(0).split(":")[1].trim().split(" ");

        for (int i = 0; i < seeds.length; i += 2) {
            long seedRangeStart = Long.parseLong(seeds[i]);
            long seedRangeLength = Long.parseLong(seeds[i + 1]);
//            System.out.println("seedRangeStart = " + seedRangeStart + " seedRangeLength = " + seedRangeLength);
            for (long seed = seedRangeStart; seed < seedRangeStart + (seedRangeLength - 1); seed++) {
                long mappedValue = seed;
                for (long[][] map : mappings) {
                    for (long[] values : map) {
                        if (mappedValue >= values[1] && mappedValue <= (values[1] + values[2])) {
                            mappedValue = values[0] + mappedValue - values[1];
                            break;
                        }
                    }
                }
                // System.out.println("mappedValue = " + mappedValue);
                if (mappedValue < location) {
                    location = mappedValue;
                }
            }
        }

        return location; // NO 6082853 Alto
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(46L);
    }
}