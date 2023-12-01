import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class FirstProblem {

    private static int x = 1;
    private static int currentCycle = 1;
    private static int signalStrengthSum = 0;

    public static void main(String[] args) throws IOException {

        Files.lines(Path.of("src/input.txt"))
                .forEach(FirstProblem::processOrder);
        System.out.println("Total: " + signalStrengthSum);
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
        if (currentCycle == 20 || currentCycle == 60 || currentCycle == 100 || currentCycle == 140
                || currentCycle == 180 || currentCycle == 220) {
            System.out.println("cycle: " + currentCycle + " x: " + x);
            signalStrengthSum += currentCycle * x;
        }
    }
}