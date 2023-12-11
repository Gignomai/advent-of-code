import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

public class Part1 {
    public Integer processLines(List<String> lines) {
        List<Integer> locations = new ArrayList<>();
        // Get list of seeds
        List<Integer> seeds = Arrays.stream(lines.get(0).split(":")[1].trim().split(" "))
                .map(Integer::valueOf)
                .toList();
        // Create Maps
        int lineNumber = 3;
        // seed-to-soil map:
        TreeMap<Integer, Integer> seedToSoilMap = new TreeMap<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            int destinationRangeStart = Integer.parseInt(parts[0]);
            int sourceRangeStart = Integer.parseInt(parts[1]);
            int rangeLength = Integer.parseInt(parts[2]);
            for (int i = 0; i < rangeLength; i++) {
                seedToSoilMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }
            lineNumber++;
        }

        lineNumber += 2;
        // soil-to-fertilizer map:
        TreeMap<Integer, Integer> soilToFertilizerMap = new TreeMap<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            int destinationRangeStart = Integer.parseInt(parts[0]);
            int sourceRangeStart = Integer.parseInt(parts[1]);
            int rangeLength = Integer.parseInt(parts[2]);
            for (int i = 0; i < rangeLength; i++) {
                soilToFertilizerMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }
            lineNumber++;
        }

        lineNumber += 2;
        // fertilizer-to-water map:
        TreeMap<Integer, Integer> fertilizerToWaterMap = new TreeMap<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            int destinationRangeStart = Integer.parseInt(parts[0]);
            int sourceRangeStart = Integer.parseInt(parts[1]);
            int rangeLength = Integer.parseInt(parts[2]);
            for (int i = 0; i < rangeLength; i++) {
                fertilizerToWaterMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }
            lineNumber++;
        }

        lineNumber += 2;
        // water-to-light map:
        TreeMap<Integer, Integer> waterToLightMap = new TreeMap<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            int destinationRangeStart = Integer.parseInt(parts[0]);
            int sourceRangeStart = Integer.parseInt(parts[1]);
            int rangeLength = Integer.parseInt(parts[2]);
            for (int i = 0; i < rangeLength; i++) {
                waterToLightMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }
            lineNumber++;
        }

        lineNumber += 2;
        // light-to-temperature map:
        TreeMap<Integer, Integer> lightToTemperatureMap = new TreeMap<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            int destinationRangeStart = Integer.parseInt(parts[0]);
            int sourceRangeStart = Integer.parseInt(parts[1]);
            int rangeLength = Integer.parseInt(parts[2]);
            for (int i = 0; i < rangeLength; i++) {
                lightToTemperatureMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }
            lineNumber++;
        }

        lineNumber += 2;
        // temperature-to-humidity map:
        TreeMap<Integer, Integer> temperatureToHumidityMap = new TreeMap<>();
        while (!lines.get(lineNumber).isEmpty()) {
            String[] parts = lines.get(lineNumber).split(" ");
            int destinationRangeStart = Integer.parseInt(parts[0]);
            int sourceRangeStart = Integer.parseInt(parts[1]);
            int rangeLength = Integer.parseInt(parts[2]);
            for (int i = 0; i < rangeLength; i++) {
                temperatureToHumidityMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }
            lineNumber++;
        }

        lineNumber += 2;
        // humidity-to-location map:
        TreeMap<Integer, Integer> humidityToLocationMap = new TreeMap<>();
        while (lineNumber < lines.size()) {
            String[] parts = lines.get(lineNumber).split(" ");
            int destinationRangeStart = Integer.parseInt(parts[0]);
            int sourceRangeStart = Integer.parseInt(parts[1]);
            int rangeLength = Integer.parseInt(parts[2]);
            for (int i = 0; i < rangeLength; i++) {
                humidityToLocationMap.put(sourceRangeStart + i, destinationRangeStart + i);
            }
            lineNumber++;
        }

        System.out.println("seedToSoilMap = " + seedToSoilMap);
        System.out.println("soilToFertilizerMap = " + soilToFertilizerMap);
        System.out.println("fertilizerToWaterMap = " + fertilizerToWaterMap);
        System.out.println("waterToLightMap = " + waterToLightMap);
        System.out.println("lightToTemperatureMap = " + lightToTemperatureMap);
        System.out.println("temperatureToHumidityMap = " + temperatureToHumidityMap);
        System.out.println("humidityToLocationMap = " + humidityToLocationMap);
        // Map seeds to location
        for (Integer seed : seeds) {
            System.out.println("seed = " + seed);
            int mappedValue = seedToSoilMap.getOrDefault(seed, seed);
//            System.out.println("soil = " + mappedValue);
            if (soilToFertilizerMap.containsKey(mappedValue)) {
                mappedValue = soilToFertilizerMap.get(seed);
//                System.out.println("fertilizer = " + mappedValue);
            }
            if (fertilizerToWaterMap.containsKey(mappedValue)) {
                mappedValue = fertilizerToWaterMap.get(seed);
//                System.out.println("Water = " + mappedValue);
            }
            if (waterToLightMap.containsKey(mappedValue)) {
                mappedValue = waterToLightMap.get(mappedValue);
//                System.out.println("Light = " + mappedValue);
            }
            if (lightToTemperatureMap.containsKey(mappedValue)) {
                mappedValue = lightToTemperatureMap.get(mappedValue);
//                System.out.println("temperature = " + mappedValue);
            }
            if (temperatureToHumidityMap.containsKey(mappedValue)) {
                mappedValue = temperatureToHumidityMap.get(mappedValue);
            }
            if (humidityToLocationMap.containsKey(mappedValue)) {
                mappedValue = humidityToLocationMap.get(mappedValue);
            }
            System.out.println("mappedValue = " + mappedValue);
            locations.add(mappedValue);
        }
        // Return closer (lower) location
        int result = locations.stream()
                .mapToInt(Integer::intValue)
                .min()
                .orElse(-1);
        System.out.println("result = " + result);
        return result;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(35);
    }
}