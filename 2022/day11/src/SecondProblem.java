import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SecondProblem {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Path.of("src/input_test.txt"))
                .collect(Collectors.toList());

        System.out.println(calculateInspections(getMonkeysFromLines(lines)));
    }

    private static List<Monkey> getMonkeysFromLines(List<String> lines) {
        List<Monkey> monkeys = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 7) {

            Pattern p = Pattern.compile("Monkey (\\d+):");
            Matcher m = p.matcher(lines.get(i));

            if (m.find()) {
                //System.out.println("Monkey: " + m.group(1));
            }

            String[] levels = lines.get(i + 1).split(":")[1].trim().split(",");
            List<Integer> itemLevels = Arrays.stream(levels)
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            p = Pattern.compile("  Operation: new = old (.*)");
            m = p.matcher(lines.get(i + 2));

            String operation = "";
            if (m.find()) {
                operation = m.group(1);
            }

            p = Pattern.compile("  Test: divisible by (\\d+)");
            m = p.matcher(lines.get(i + 3));

            Integer divisible = 0;
            if (m.find()) {
                divisible = Integer.parseInt(m.group(1));
            }

            p = Pattern.compile("    If true: throw to monkey (\\d+)");
            m = p.matcher(lines.get(i + 4));

            int trueMonkey = 0;
            if (m.find()) {
                trueMonkey = Integer.parseInt(m.group(1));
            }

            p = Pattern.compile("    If false: throw to monkey (\\d+)");
            m = p.matcher(lines.get(i + 5));

            int falseMonkey = 0;
            if (m.find()) {
                falseMonkey = Integer.parseInt(m.group(1));
            }

            monkeys.add(new Monkey(itemLevels, getOperation(operation), divisible, trueMonkey, falseMonkey));
        }

        return monkeys;
    }

    private static Function<Integer, Integer> getOperation(String operation) {
        String operator = operation.split(" ")[0];
        String value = operation.split(" ")[1];

        Function<Integer, Integer> result = null;

        if (value.equals("old")) {
            switch (operator) {
                case "+":
                    result = old -> old + old;
                    break;
                case "*":
                    result = old -> old * old;
                    break;
            }
        } else {
            switch (operator) {
                case "+":
                    result = old -> old + Integer.parseInt(value);
                    break;
                case "*":
                    result = old -> old * Integer.parseInt(value);
                    break;
            }
        }
        return result;
    }

    private static BigInteger calculateInspections(List<Monkey> monkeys) {

        //Integer adjustment = 9699690;
        /*for (int i = 0; i < monkeys.size(); i++) {
            adjustment *= monkeys.get(i).getDivisibleBy();
        }*/
        //System.out.println(adjustment);
        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                for (Integer item : monkey.getItems()) {
                    Integer level = monkey.getOperation().apply(item) % 9699690;
                    monkey.inspectItem();
                    if (level % monkey.getDivisibleBy() == 0) {
                        monkeys.get(monkey.getIfTrueMonkey()).getItems().add(level);
                    } else {
                        monkeys.get(monkey.getIfFalseMonkey()).getItems().add(level);
                    }
                }
                monkey.emptyItems();
                //if (i % 1000 == 0) {
                //System.out.println(i + " - Monkey " + monkeys.indexOf(monkey) + " has inspected " + monkey.getItemsInspected() + " items.");
                //System.out.println("------------------------------------------");
                //}
            }
        }

        List<BigInteger> collect = monkeys.stream()
                .map(Monkey::getItemsInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .map(BigInteger::valueOf)
                .collect(Collectors.toList());
        return collect.get(0).multiply(collect.get(1));
    }

}