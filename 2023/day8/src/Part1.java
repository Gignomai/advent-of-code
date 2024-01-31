import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

record Node(String value, String left, String right) {
    public Node(String node) {
        this(node.split("=")[0],
                node.split("=")[1].split(",")[0],
                node.split("=")[1].split(",")[1]);
    }
}

public class Part1 {

    public Integer processLines(List<String> lines) {
        String[] instructions = lines.get(0).split("");
        Map<String, List<String>> nodes = lines.subList(2, lines.size()).stream()
                .map(s -> s.replace(" ", ""))
                .map(s -> s.replace("(", ""))
                .map(s -> s.replace(")", ""))
                .map(Node::new)
                .collect(Collectors.toMap(Node::value, n -> List.of(n.left(), n.right())));

        String currentNode = "AAA";
        int count = 0;
        int i = 0;
        while (!currentNode.equals("ZZZ")) {
            int instruction = instructions[i].equals("L") ? 0 : 1;
            currentNode = nodes.get(currentNode).get(instruction);
            count++;
            i = i == instructions.length - 1 ? 0 : i + 1;
        }

        return count;
    }


    public boolean test(List<String> lines) {
        return processLines(lines).equals(2);
    }

    public boolean test2(List<String> lines) {
        return processLines(lines).equals(6);
    }
}