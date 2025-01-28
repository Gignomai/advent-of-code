import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class Part2 {
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

        System.out.println("total = " + total);
        return total;
    }

    private boolean canBeProduced(long testValue, List<Long> numbers) {
        int operatorsLength = numbers.size() - 1;
        double combinations = pow(3, operatorsLength);
        System.out.println("testValue = " + testValue);
        System.out.println("combinations = " + (int) combinations);
        System.out.println("operatorsLength = " + operatorsLength);

        for (int i = 0; i < combinations; i++) {
            StringBuilder sb = new StringBuilder();
            for (int j = 0; j < operatorsLength; j++) {
                sb.append(i%3);
            }
            System.out.println(sb);
        }


//        for (int i = 0; i < combinations; i++) {
//            String combination = convertToBinary(i, operatorsLength);
//
//            long result = numbers.get(0);
//            for (int j = 1; j < numbers.size(); j++) {
//                if (combination.charAt(j - 1) == '0') {
//                    result += numbers.get(j);
//                } else {
//                    result *= numbers.get(j);
//                }
//            }
//            System.out.println("total = " + result);
//            if (result == testValue) {
//                return true;
//            }
//        }

        return false;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(11387L);
    }
}