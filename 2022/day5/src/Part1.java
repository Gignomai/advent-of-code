import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    public static void main(String[] args) throws IOException {

        try {
            File myObj = new File("src/input_test.txt");
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
//            System.out.println(stacks);

            while (myReader.hasNextLine()) {
                Pattern p = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
                Matcher m = p.matcher(myReader.nextLine());
                if (m.find()) {
                    int moves = Integer.parseInt(m.group(1));
                    int origin = Integer.parseInt(m.group(2)) - 1;
                    int dest = Integer.parseInt(m.group(3)) - 1;

                    for (int i = 0; i < moves; i++) {
                        //stacks.get(dest).push(stacks.get(origin).get());
                    }
                }
                //System.out.println(stacks);
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

        Character stacks[][] = new Character[stackNum][rowSize];

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
        for (int i = 0; i < stacks.length; i++) {
            Stack<Character> stack = new Stack<>();
            for (int j = stacks[i].length - 1; j >= 0; j--) {
                if (stacks[i][j] != null) {
                    stack.push(stacks[i][j]);
                }
            }
            charsStacks.add(stack);
        }
        return charsStacks;
    }
//
//    public boolean test(List<String> lines) {
//        return processLines(lines).equals(4L);
//    }
}