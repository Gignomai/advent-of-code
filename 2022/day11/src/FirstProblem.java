import java.io.IOException;
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

class Monkey {

    private List<Integer> items;
    private Function<Integer, Integer> operation;
    private Integer divisibleBy;
    private Integer ifTrueMonkey;
    private Integer ifFalseMonkey;
    private Integer itemsInspected;

    public Monkey(List<Integer> items, Function<Integer, Integer> operation, Integer divisibleBy, Integer ifTrueMonkey,
                  Integer ifFalseMonkey) {
        this.items = items;
        this.operation = operation;
        this.divisibleBy = divisibleBy;
        this.ifTrueMonkey = ifTrueMonkey;
        this.ifFalseMonkey = ifFalseMonkey;
        this.itemsInspected = 0;
    }

    public List<Integer> getItems() {
        return items;
    }

    public Function<Integer, Integer> getOperation() {
        return operation;
    }

    public Integer getDivisibleBy() {
        return divisibleBy;
    }

    public Integer getIfTrueMonkey() {
        return ifTrueMonkey;
    }

    public Integer getIfFalseMonkey() {
        return ifFalseMonkey;
    }

    public Integer getItemsInspected() {
        return itemsInspected;
    }

    public void inspectItem() {
        this.itemsInspected++;
    }

    public void emptyItems() {
        this.items = new ArrayList<>();
    }
}


public class FirstProblem {

    public static void main(String[] args) throws IOException {
        List<String> lines = Files.lines(Path.of("src/input.txt"))
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

            int divisible = 0;
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

    private static Integer calculateInspections(List<Monkey> monkeys) {
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                for (Integer item : monkey.getItems()) {
                    int itemLevelWhileInspecting = monkey.getOperation().apply(item);
                    monkey.inspectItem();
                    int level = itemLevelWhileInspecting / 3;
                    if (level % monkey.getDivisibleBy() == 0) {
                        monkeys.get(monkey.getIfTrueMonkey()).getItems().add(level);
                    } else {
                        monkeys.get(monkey.getIfFalseMonkey()).getItems().add(level);
                    }
                }
                monkey.emptyItems();
                System.out.println(i + " - Monkey " + monkeys.indexOf(monkey) + " has inspected " + monkey.getItemsInspected() + " items.");
            }
            /*monkeys.stream()
                    .map(Monkey::getItems)
                    .forEach(System.out::println);*/
            //System.out.println("------------------------------------------");
        }

        List<Integer> collect = monkeys.stream()
                .map(Monkey::getItemsInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .collect(Collectors.toList());
        return collect.get(0) * collect.get(1);
    }

}