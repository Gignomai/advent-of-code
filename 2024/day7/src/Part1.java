import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class Part1 {

    public Long processLines(List<String> lines) {
        long total = 0;

        for (String line : lines) {
            long testValue = Long.parseLong(line.split(":")[0]);
            List<Long> numbers = Stream.of(line.split(":")[1].trim().split(" "))
                    .map(Long::parseLong)
                    .toList();

            if (canBeProduced(testValue, numbers)) {
                total += testValue;
            }
        }

        return total;
    }

    private boolean canBeProduced(long testValue, List<Long> numbers) {
        int operatorsLength = numbers.size() - 1;
        double combinations = pow(2, operatorsLength);

        for (int i = 0; i < combinations; i++) {
            String combination = convertToBinary(i, operatorsLength);

            long result = numbers.get(0);
            for (int j = 1; j < numbers.size(); j++) {
                if (combination.charAt(j - 1) == '0') {
                    result += numbers.get(j);
                } else {
                    result *= numbers.get(j);
                }
            }

            if (result == testValue) {
                return true;
            }
        }

        return false;
    }

    private static String convertToBinary(int number, int length) {
        StringBuilder indexes = new StringBuilder(Integer.toBinaryString(number));
        int requiredLength = length - indexes.length();
        for (int j = 0; j < requiredLength; j++) {
            indexes.insert(0, "0");
        }
        return indexes.toString();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(3749L);
    }
}