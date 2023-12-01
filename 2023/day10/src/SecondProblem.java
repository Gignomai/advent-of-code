import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SecondProblem {

    private static int x = 1;
    private static int currentCycle = 1;
    private static String crtLine = "";
    private static int currentLine = 0;

    public static void main(String[] args) throws IOException {

        Files.lines(Path.of("src/input_test.txt"))
                .forEach(SecondProblem::processOrder);
    }

    private static void processOrder(String order) {
        if (order.equals("noop")) {
            checkCycle();
            currentCycle++;
        } else {
            checkCycle();
            currentCycle++;
            checkCycle();
            currentCycle++;
            x += Integer.parseInt(order.split(" ")[1]);
        }
    }

    private static void checkCycle() {
        int position = currentCycle - (currentLine * 40) - 1;
        if (position >= x - 1 && position <= x + 1) {
            crtLine += "#";
        } else {
            crtLine += ".";
        }
        if (currentCycle % 40 == 0) {
            System.out.println(crtLine);
            crtLine = "";
            currentLine++;
        }
    }

}