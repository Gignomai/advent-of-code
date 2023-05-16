import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Part1 {
    public String processLines(List<String> lines) {
        List<String> stackRows = new ArrayList<>(lines.subList(0, lines.indexOf("")));
        List<List<Character>> stacks = getStacksFromRows(stackRows);

        List<String> movements = new ArrayList<>(lines.subList(lines.indexOf(""), lines.size()));
        for (String move : movements) {
            Pattern p = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
            Matcher m = p.matcher(move);
            if (m.find()) {
                int moves = Integer.parseInt(m.group(1));
                int origin = Integer.parseInt(m.group(2)) - 1;
                int dest = Integer.parseInt(m.group(3)) - 1;

                List<Character> fromStack = stacks.get(origin);
                List<Character> toStack = stacks.get(dest);
                for (int i = 0; i < moves; i++) {
                    toStack.add(fromStack.get(fromStack.size() - 1));
                    fromStack.remove(fromStack.size() - 1);
                }
            }
        }

        StringBuilder result = new StringBuilder();
        for (List<Character> s : stacks) {
            result.append(s.get(s.size() - 1));
        }

        return result.toString();
    }

    private static List<List<Character>> getStacksFromRows(List<String> stackRows) {
        String lastRow = stackRows.get(stackRows.size() - 1).trim();
        int numberOfStacks = Integer.parseInt(lastRow.substring(lastRow.length() - 1));

        List<List<Character>> charsStacks = new ArrayList<>();
        for (int i = 1; i <= numberOfStacks; i++) {
            List<Character> stack = new ArrayList<>();
            for (int j = 0; j < stackRows.size() - 2; j++) {
                int col = i == 1 ? 1 : i + 3;
                Character letter = stackRows.get(j).charAt(col);
                if (!letter.equals(' ')) {
                    stack.add(letter);
                }
            }
            charsStacks.add(stack);
        }






//        for (int r = 0; r < stackRows.size(); r++) {
//            String row = stackRows.get(r);
//            for (int i = 1; i < row.length(); i += 4) {
//                if (row.charAt(i) != ' ') {
//                    if (charsStacks.size() <= r) {
//                        charsStacks.add(new ArrayList<>());
//                    }
//                    charsStacks.get(r).add(row.charAt(i));
//                }
//            }
//        }

//
//        int rowSize = stackRows.size() - 1;
//        int stackNum = stackRows.get(rowSize)
//                .replace(" ", "")
//                .length();
//
//        Character stacks[][] = new Character[stackNum][rowSize];
//
//        for (int i = 0; i < rowSize; i++) {
//            String row = stackRows.get(i);
//            int count = 0;
//            for (int j = 1; j < row.length(); j = j + 4) {
//                if (row.charAt(j) != ' ') {
//                    stacks[count][i] = row.charAt(j);
//                }
//                count++;
//            }
//        }
//
//        List<List<Character>> charsStacks = new ArrayList<>();
//        for (int i = 0; i < stacks.length; i++) {
//            Stack<Character> stack = new Stack<>();
//            for (int j = stacks[i].length - 1; j >= 0; j--) {
//                if (stacks[i][j] != null) {
//                    stack.push(stacks[i][j]);
//                }
//            }
//            charsStacks.add(stack);
//        }
        return charsStacks;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals("CMZ");
    }
}