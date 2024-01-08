import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Part2 {
    public Integer processLines(List<String> lines) {
        String[] instructions = lines.get(0).split("");
        Map<String, List<String>> nodes = new HashMap<>();
        List<String> visited = new ArrayList<>();

        for (int i = 2; i < lines.size(); i++) {
            String clear = lines.get(i).replace(" ", "")
                    .replace("(", "")
                    .replace(")", "");
            String name = clear.split("=")[0];
            String children = clear.split("=")[1];
            nodes.put(name, List.of(children.split(",")));
            if (name.endsWith("A")) {
                visited.add(name);
            }
        }

        boolean exit = false;
        int count = 0;
        int i = 0;
        while (!exit) {
            List<String> currentNodes = new ArrayList<>();
            int instruction = instructions[i].equals("L") ? 0 : 1;

            for (String node: visited) {
                currentNodes.add(nodes.get(node).get(instruction));
            }

            boolean isEndingStep = true;
            for (String destination: currentNodes) {
                if (!destination.endsWith("Z")){
                    isEndingStep = false;
                    break;
                }
            }

            visited = currentNodes;
            exit = isEndingStep;
            count++;
            if (i == instructions.length - 1) {
                System.out.println("count = " + count);
                i = 0;
            } else {
                i++;
            }
        }

        return count;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(6);
    }
}