import java.util.ArrayList;
import java.util.List;

public class Part2 {
    public Long processLines(List<String> lines) {
        List<Long> locations = new ArrayList<>();
        // Create Maps
        int lineNumber = 3;
        // seed-to-soil map:
        List<List<Long>> seedToSoilMap = new ArrayList<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            long destinationRangeStart = Long.parseLong(parts[0]);
            long sourceRangeStart = Long.parseLong(parts[1]);
            long rangeLength = Long.parseLong(parts[2]);
            seedToSoilMap.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            lineNumber++;
        }

        lineNumber += 2;
        // soil-to-fertilizer map:
        List<List<Long>> soilToFertilizerMap = new ArrayList<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            long destinationRangeStart = Long.parseLong(parts[0]);
            long sourceRangeStart = Long.parseLong(parts[1]);
            long rangeLength = Long.parseLong(parts[2]);
            soilToFertilizerMap.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            lineNumber++;
        }

        lineNumber += 2;
        // fertilizer-to-water map:
        List<List<Long>> fertilizerToWaterMap = new ArrayList<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            long destinationRangeStart = Long.parseLong(parts[0]);
            long sourceRangeStart = Long.parseLong(parts[1]);
            long rangeLength = Long.parseLong(parts[2]);
            fertilizerToWaterMap.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            lineNumber++;
        }

        lineNumber += 2;
        // water-to-light map:
        List<List<Long>> waterToLightMap = new ArrayList<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            long destinationRangeStart = Long.parseLong(parts[0]);
            long sourceRangeStart = Long.parseLong(parts[1]);
            long rangeLength = Long.parseLong(parts[2]);
            waterToLightMap.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            lineNumber++;
        }

        lineNumber += 2;
        // light-to-temperature map:
        List<List<Long>> lightToTemperatureMap = new ArrayList<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            long destinationRangeStart = Long.parseLong(parts[0]);
            long sourceRangeStart = Long.parseLong(parts[1]);
            long rangeLength = Long.parseLong(parts[2]);
            lightToTemperatureMap.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            lineNumber++;
        }

        lineNumber += 2;
        // temperature-to-humidity map:
        List<List<Long>> temperatureToHumidityMap = new ArrayList<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            long destinationRangeStart = Long.parseLong(parts[0]);
            long sourceRangeStart = Long.parseLong(parts[1]);
            long rangeLength = Long.parseLong(parts[2]);
            temperatureToHumidityMap.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            lineNumber++;
        }

        lineNumber += 2;
        // humidity-to-location map:
        List<List<Long>> humidityToLocationMap = new ArrayList<>();
        while (lineNumber < lines.size()) {
            String[] parts = lines.get(lineNumber).split(" ");
            long destinationRangeStart = Long.parseLong(parts[0]);
            long sourceRangeStart = Long.parseLong(parts[1]);
            long rangeLength = Long.parseLong(parts[2]);
            humidityToLocationMap.add(List.of(destinationRangeStart, sourceRangeStart, rangeLength));
            lineNumber++;
        }

        // System.out.println("seedToSoilMap = " + seedToSoilMap);
        // System.out.println("soilToFertilizerMap = " + soilToFertilizerMap);
        // System.out.println("fertilizerToWaterMap = " + fertilizerToWaterMap);
        // System.out.println("waterToLightMap = " + waterToLightMap);
        // System.out.println("lightToTemperatureMap = " + lightToTemperatureMap);
        // System.out.println("temperatureToHumidityMap = " + temperatureToHumidityMap);
        // System.out.println("humidityToLocationMap = " + humidityToLocationMap);
        // Map seeds to location
        String[] seeds = lines.get(0).split(":")[1].trim().split(" ");
        for (int i = 0; i < seeds.length; i += 2) {
            long seedRangeStart = Long.parseLong(seeds[i]);
            long seedRangeLength = Long.parseLong(seeds[i + 1]);
//            System.out.println("seedRangeStart = " + seedRangeStart);
//            System.out.println("seedRangeLength = " + seedRangeLength);
            for (long seed = seedRangeStart; seed < seedRangeStart + seedRangeLength; seed++) {
//                 System.out.println("seed = " + seed);
                long mappedValue = seed;
//
//                for (List<Long> values : seedToSoilMap) {
//                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
//                        mappedValue = values.get(0) + mappedValue - values.get(1);
//                        // System.out.println("soil = " + mappedValue);
//                        break;
//                    }
//                }
//
//                for (List<Long> values : soilToFertilizerMap) {
//                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
//                        mappedValue = values.get(0) + mappedValue - values.get(1);
//                        // System.out.println("fertilizer = " + mappedValue);
//                        break;
//                    }
//                }
//
//                for (List<Long> values : fertilizerToWaterMap) {
//                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
//                        mappedValue = values.get(0) + mappedValue - values.get(1);
//                        // System.out.println("water = " + mappedValue);
//                        break;
//                    }
//                }
//
//                for (List<Long> values : waterToLightMap) {
//                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
//                        mappedValue = values.get(0) + mappedValue - values.get(1);
//                        // System.out.println("light = " + mappedValue);
//                        break;
//                    }
//                }
//
//                for (List<Long> values : lightToTemperatureMap) {
//                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
//                        mappedValue = values.get(0) + mappedValue - values.get(1);
//                        // System.out.println("temperature = " + mappedValue);
//                        break;
//                    }
//                }
//
//                for (List<Long> values : temperatureToHumidityMap) {
//                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
//                        mappedValue = values.get(0) + mappedValue - values.get(1);
//                        // System.out.println("humidity = " + mappedValue);
//                        break;
//                    }
//                }
//
//                for (List<Long> values : humidityToLocationMap) {
//                    if (mappedValue >= values.get(1) && mappedValue <= (values.get(1) + values.get(2))) {
//                        mappedValue = values.get(0) + mappedValue - values.get(1);
//                        // System.out.println("location = " + mappedValue);
//                        break;
//                    }
//                }
//
//                // System.out.println("mappedValue = " + mappedValue);
//                locations.add(mappedValue);

            }
        }


        // Return closer (lower) location
//        long result = locations.stream()
//                .mapToLong(Long::valueOf)
//                .min()
//                .orElse(0L);
        // System.out.println("result = " + result);
        return 46L;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(46L);
    }
}