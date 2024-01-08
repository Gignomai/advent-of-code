import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part1 {

    public Integer processLines(List<String> lines) {
        String[] instructions = lines.get(0).split("");
        Map<String, List<String>> nodes = new HashMap<>();

        for (int i = 2; i < lines.size(); i++) {
            String clear = lines.get(i).replace(" ", "")
                    .replace("(", "")
                    .replace(")", "");
            String name = clear.split("=")[0];
            String children = clear.split("=")[1];
            nodes.put(name, List.of(children.split(",")));
        }

        String currentNode = "AAA";
        int count = 0;
        int i = 0;
        while (!currentNode.equals("ZZZ")) {
            int instruction = instructions[i].equals("L") ? 0 : 1;
            currentNode = nodes.get(currentNode).get(instruction);

            count++;
            if (i == instructions.length - 1) {
                i = 0;
            } else {
                i++;
            }
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