import java.util.ArrayList;
import java.util.List;


record Range(Long start, Long end) {
    public Range(String start, String length) {
        this(Long.parseLong(start), Long.parseLong(start) + Long.parseLong(length) - 1L);
    }
}

record Mapping(Range source, Range destination) {
}

record Category(List<Mapping> mappings) {
}

public class Part2 {
    public Long processLines(List<String> lines) {
        List<Range> seeds = new ArrayList<>();
        String[] parts = lines.get(0)
                .split(":")[1]
                .trim()
                .split(" ");
        for (int i = 0; i < parts.length; i += 2) {
            long start = Long.parseLong(parts[i]);
            long length = Long.parseLong(parts[i + 1]);

            seeds.add(new Range(start, start + length - 1));
        }

        List<Category> categories = new ArrayList<>();
        List<Mapping> mappings = new ArrayList<>();
        for (int line = 3; line < lines.size(); line++) {
            if (lines.get(line).isEmpty()) {
                categories.add(new Category(mappings));
                mappings = new ArrayList<>();
                line++;
            } else {
                parts = lines.get(line).split(" ");
                Range source = new Range(parts[1], parts[2]);
                Range destination = new Range(parts[0], parts[2]);
                mappings.add(new Mapping(source, destination));
            }
        }
        categories.add(new Category(mappings));

        // Map seeds to location
        List<Range> mappedRanges = seeds;
        for (Category category : categories) {
            List<Range> aux = new ArrayList<>();
            while (!mappedRanges.isEmpty()) {
                Range mappedRange = mappedRanges.get(0);
                boolean hasMapping = false;
                for (Mapping mapping : category.mappings()) {
                    Range overlap = new Range(Math.max(mappedRange.start(), mapping.source().start()),
                            Math.min(mappedRange.end(), mapping.source().end()));

                    if (overlap.start() < overlap.end()) {
                        aux.add(new Range(overlap.start() - mapping.source().start() + mapping.destination().start(),
                                overlap.end() - mapping.source().start() + mapping.destination().start()));

                        if (overlap.start() > mappedRange.start()) {
                            seeds.add(new Range(mappedRange.start(), overlap.start() - 1));
                        }
                        if (mappedRange.end() > overlap.end()) {
                            seeds.add(new Range(overlap.end() + 1, mappedRange.end()));
                        }

                        hasMapping = true;
                        break;
                    }
                }
                if (!hasMapping) {
                    aux.add(mappedRange);
                }
                mappedRanges.remove(0);
            }
            mappedRanges = aux;
        }

        return mappedRanges.stream()
                .map(Range::start)
                .mapToLong(Long::longValue)
                .min()
                .orElse(0L);
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(46L);
    }
}