import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

class Monkey {

    private List<Long> items;
    private final Function<Long, Long> operation;
    private final Integer divisibleBy;
    private final Integer ifTrueMonkey;
    private final Integer ifFalseMonkey;
    private Integer itemsInspected;

    public Monkey(List<Long> items, Function<Long, Long> operation, Integer divisibleBy, Integer ifTrueMonkey,
                  Integer ifFalseMonkey) {
        this.items = items;
        this.operation = operation;
        this.divisibleBy = divisibleBy;
        this.ifTrueMonkey = ifTrueMonkey;
        this.ifFalseMonkey = ifFalseMonkey;
        this.itemsInspected = 0;
    }

    public List<Long> getItems() {
        return items;
    }

    public Function<Long, Long> getOperation() {
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

    public void addItem(Long level) {
        this.items.add(level);
    }

    public static List<Monkey> getMonkeysFromLines(List<String> lines) {
        List<Monkey> monkeys = new ArrayList<>();

        for (int i = 0; i < lines.size(); i += 7) {
            final String[] levels = lines.get(i + 1).split(":")[1].trim().split(",");
            final List<Long> itemLevels = Arrays.stream(levels)
                    .map(String::trim)
                    .map(Long::parseLong)
                    .collect(Collectors.toList());
            final String operation = lines.get(i + 2).substring(23);
            final int divisible = Integer.parseInt(lines.get(i + 3).substring(21));
            final int trueMonkey = Integer.parseInt(lines.get(i + 4).substring(29));
            final int falseMonkey = Integer.parseInt(lines.get(i + 5).substring(30));

            monkeys.add(new Monkey(itemLevels, getOperation(operation), divisible, trueMonkey, falseMonkey));
        }

        return monkeys;
    }

    private static Function<Long, Long> getOperation(String operation) {
        String operator = operation.split(" ")[0];
        String value = operation.split(" ")[1];

        return operator.equals("+") ? old -> old + (value.equals("old") ? old : Long.parseLong(value))
                : old -> old * (value.equals("old") ? old : Long.parseLong(value));
    }
}