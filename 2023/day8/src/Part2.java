import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Part2 {
    public Long processLines(List<String> lines) {
        String[] instructions = lines.get(0).split("");
        Map<String, List<String>> nodes = lines.subList(2, lines.size()).stream()
                .map(s -> s.replace(" ", ""))
                .map(s -> s.replace("(", ""))
                .map(s -> s.replace(")", ""))
                .map(Node::new)
                .collect(Collectors.toMap(Node::value, n -> List.of(n.left(), n.right())));

        List<String> current = nodes.keySet().stream()
                .filter(n -> n.endsWith("A"))
                .toList();

        List<Integer> steps = new ArrayList<>();
        for (String currentNode: current) {
            int count = 0;
            int i = 0;
            while (!currentNode.endsWith("Z")) {
                int instruction = instructions[i].equals("L") ? 0 : 1;
                currentNode = nodes.get(currentNode).get(instruction);

                count++;
                i = (i >= instructions.length - 1) ? 0 : i + 1;
            }
            steps.add(count);
        }

        return steps.stream()
                .mapToLong(Integer::longValue)
                .reduce(1, Part2::lcm);
    }

    public static Long lcm(long value1, long value2) {
        BigInteger number1 = BigInteger.valueOf(value1);
        BigInteger number2 = BigInteger.valueOf(value2);

        BigInteger gcd = number1.gcd(number2);
        BigInteger absProduct = number1.multiply(number2).abs();
        return absProduct.divide(gcd).longValue();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(6L);
    }
}