import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SecondProblem {
    public static void main(String[] args) {

        try {
            File myObj = new File("src/input.txt");
            Scanner myReader = new Scanner(myObj);

            List<String> stackRows = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                if (data.isEmpty()) {
                    break;
                } else {
                    stackRows.add(data);
                }
            }
            List<List<Character>> stacks = getStacksFromRows(stackRows);
            while (myReader.hasNextLine()) {
                String line = myReader.nextLine();

                Pattern p = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
                Matcher m = p.matcher(line);
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
                System.out.println(stacks);
            }
            myReader.close();

            StringBuilder result = new StringBuilder();
            for (List<Character> s : stacks) {
                result.append(s.get(s.size() - 1));
            }
            System.out.println(result);
        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: An error occurred.");
            e.printStackTrace();
        }
    }

    private static List<List<Character>> getStacksFromRows(List<String> stackRows) {
        int rowSize = stackRows.size() - 1;
        int stackNum = stackRows.get(rowSize)
                .replace(" ", "")
                .length();

        Character[][] stacks = new Character[stackNum][rowSize];

        for (int i = 0; i < rowSize; i++) {
            String row = stackRows.get(i);
            int count = 0;
            for (int j = 1; j < row.length(); j = j + 4) {
                if (row.charAt(j) != ' ') {
                    stacks[count][i] = row.charAt(j);
                }
                count++;
            }
        }

        List<List<Character>> charsStacks = new ArrayList<>();
        for (Character[] characters : stacks) {
            Stack<Character> stack = new Stack<>();
            for (int j = characters.length - 1; j >= 0; j--) {
                if (characters[j] != null) {
                    stack.push(characters[j]);
                }
            }
            charsStacks.add(stack);
        }
        return charsStacks;
    }
}