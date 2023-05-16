import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part2 {
    public String processLines(List<String> lines) {
        List<String> stackRows = new ArrayList<>(lines.subList(0, lines.indexOf("")));
        List<List<Character>> stacks = Day5.getStacksFromRows(stackRows);

        List<String> movements = new ArrayList<>(lines.subList(lines.indexOf(""), lines.size()));
        for (String move : movements) {
            Pattern p = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            Matcher m = p.matcher(move);
            if (m.find()) {
                int moves = Integer.parseInt(m.group(1));
                int origin = Integer.parseInt(m.group(2)) - 1;
                int dest = Integer.parseInt(m.group(3)) - 1;

                List<Character> fromStack = stacks.get(origin);
                List<Character> toMove = new ArrayList<>();
                for (int i = fromStack.size() - moves; i < fromStack.size(); i++) {
                    toMove.add(fromStack.get(i));
                }
                for (Character character : toMove) {
                    stacks.get(dest).add(character);
                }
                for (int i = 0; i < toMove.size(); i++) {
                    stacks.get(origin).remove(stacks.get(origin).size() - 1);
                }
            }
        }


        StringBuilder result = new StringBuilder();
        for (List<Character> s : stacks) {
            result.append(s.get(s.size() - 1));
        }

        return result.toString();
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals("MCD");
    }
}