import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static java.lang.Math.pow;

public class Part1 {

    public Integer processLines(List<String> lines) {
        int total = 0;
        for (String line : lines) {
            int testValue = Integer.parseInt(line.split(":")[0]);
            List<Integer> nums = Stream.of(line.split(":")[1].trim().split(" "))
                    .map(Integer::parseInt)
                    .toList();

            if (canBeProduced(testValue, nums)) {
                total += testValue;
            }

        }

        System.out.println("total = " + total);
        return total;
    }

    private boolean canBeProduced(int testValue, List<Integer> numbers) {
        String[] operators = {"+", "*"};

        double combinations = pow(2, numbers.size() - 1);
        System.out.println("combinations = " + (int) combinations);
        for (int i = 0; i < combinations; i++) {
            for (int j = 0; j < numbers.size() - 1; j++) {


                for (int n = 0; n < 2; n++) {
                    System.out.println(operators.get(n));
                }
            }


//            StringBuilder num = new StringBuilder();
//            for (int j = 0; j < numbers.size(); j++) {
//                num.append(numbers.get(j));
//                if (j < numbers.size() - 1) {
//                    num.append(operators.get(i + j));
//                }
//            }
//            System.out.println(num.toString().trim());
        }

        return false;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(3749);
    }
}