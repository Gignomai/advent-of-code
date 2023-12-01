import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class Movement {
    int moves;
    int origin;
    int dest;

    public Movement(String move) {
        Pattern p = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        Matcher m = p.matcher(move);
        if (m.find()) {
            moves = Integer.parseInt(m.group(1));
            origin = Integer.parseInt(m.group(2)) - 1;
            dest = Integer.parseInt(m.group(3)) - 1;
        }
    }
}

public class SupplyCargo {
    private final List<List<Character>> stacks;
    private final List<String> movements;

    public SupplyCargo(List<String> input) {
        stacks = getStacksFromRows(new ArrayList<>(input.subList(0, input.indexOf(""))));
        movements = new ArrayList<>(input.subList(input.indexOf("") + 1, input.size()));
    }

    private List<List<Character>> getStacksFromRows(List<String> stackRows) {
        int stackNum = stackRows.get(stackRows.size() - 1)
                .replace(" ", "")
                .length();
        List<List<Character>> charsStacks = new ArrayList<>();
        for (int i = 0; i < stackNum; i++) {
            charsStacks.add(new ArrayList<>());
        }

        for (int r = stackRows.size() - 2; r >= 0; r--) {
            String row = stackRows.get(r);
            int col = 0;
            for (int i = 1; i < row.length(); i += 4) {
                if (row.charAt(i) != ' ') {
                    charsStacks.get(col).add(row.charAt(i));
                }
                col++;
            }
        }

        return charsStacks;
    }

    public String crateMover9000Operate() {
        for (String move : movements) {
            Movement movement = new Movement(move);

            List<Character> fromStack = stacks.get(movement.origin);
            List<Character> toStack = stacks.get(movement.dest);
            for (int i = 0; i < movement.moves; i++) {
                toStack.add(fromStack.get(fromStack.size() - 1));
                fromStack.remove(fromStack.size() - 1);
            }
        }

        StringBuilder result = new StringBuilder();
        for (List<Character> s : stacks) {
            result.append(s.get(s.size() - 1));
        }

        return result.toString();
    }

    public String crateMover9001Operate() {
        for (String move : movements) {
            Movement movement = new Movement(move);

            List<Character> fromStack = stacks.get(movement.origin);
            List<Character> toMove = new ArrayList<>();
            for (int i = fromStack.size() - movement.moves; i < fromStack.size(); i++) {
                toMove.add(fromStack.get(i));
            }
            for (Character character : toMove) {
                stacks.get(movement.dest).add(character);
            }
            for (int i = 0; i < toMove.size(); i++) {
                stacks.get(movement.origin).remove(stacks.get(movement.origin).size() - 1);
            }
        }

        StringBuilder result = new StringBuilder();
        for (List<Character> s : stacks) {
            result.append(s.get(s.size() - 1));
        }

        return result.toString();
    }
}
