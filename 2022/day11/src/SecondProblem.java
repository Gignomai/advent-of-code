import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SecondProblem {

    public static void main(String[] args) throws IOException {
        try (var lines = Files.lines(Path.of("src/input.txt"))) {
            List<Monkey> monkeys = Monkey.getMonkeysFromLines(lines.collect(Collectors.toList()));
            System.out.println(calculateInspections(monkeys));
        }
    }

    private static BigInteger calculateInspections(List<Monkey> monkeys) {
        Long modulo = monkeys.stream()
                .map(Monkey::getDivisibleBy)
                .map(Integer::longValue)
                .reduce((v1, v2) -> v1 * v2)
                .orElse(0L);

        for (int i = 0; i < 10000; i++) {
            for (Monkey monkey : monkeys) {
                for (Long item : monkey.getItems()) {
                    monkey.inspectItem();
                    Long itemLevelWhileInspecting = monkey.getOperation().apply(item);
                    long level = itemLevelWhileInspecting % modulo;
                    int nextMonkey = level % monkey.getDivisibleBy() == 0 ? monkey.getIfTrueMonkey() : monkey.getIfFalseMonkey();
                    monkeys.get(nextMonkey).addItem(level);
                }
                monkey.emptyItems();
            }
        }

        return monkeys.stream()
                .map(Monkey::getItemsInspected)
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .map(m -> BigInteger.valueOf(Long.valueOf(m)))
                .reduce((m1, m2) -> m1.multiply(m2))
                .orElse(BigInteger.ZERO);
    }

}