import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FirstProblem {

    public static void main(String[] args) throws IOException {
        try(var lines = Files.lines(Path.of("src/input.txt"))) {
            List<Monkey> monkeys = Monkey.getMonkeysFromLines(lines.collect(Collectors.toList()));
            System.out.println(calculateInspections(monkeys));
        }
    }

    private static Integer calculateInspections(List<Monkey> monkeys) {
        for (int i = 0; i < 20; i++) {
            for (Monkey monkey : monkeys) {
                for (Long item : monkey.getItems()) {
                    monkey.inspectItem();
                    Long itemLevelWhileInspecting = monkey.getOperation().apply(item);
                    long level = itemLevelWhileInspecting / 3;
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
                .reduce((m1, m2) -> m1 * m2)
                .orElse(0);
    }

}