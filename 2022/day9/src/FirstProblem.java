import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

record Position(int x, int y) {
}

public class FirstProblem {

    public static Set<Position> positions = new HashSet<>();
    public static Position headPos = new Position(0, 0);
    public static Position tailPos = new Position(0, 0);

    public static void main(String[] args) throws IOException {
        Files.lines(Path.of("src/input.txt"))
                .forEach(FirstProblem::processMovement);

        System.out.println(positions);
        System.out.println(positions.size());
    }

    private static void processMovement(String move) {
        String direction = move.split(" ")[0];
        int times = Integer.parseInt(move.split(" ")[1]);

        for (int i = 0; i < times; i++) {

            int x = headPos.x();
            int y = headPos.y();
            switch (direction) {
                case "U":
                    y++;
                    break;
                case "R":
                    x++;
                    break;
                case "D":
                    y--;
                    break;
                case "L":
                    x--;
                    break;
            }
            headPos = new Position(x, y);
            updateTailPosition();
            positions.add(tailPos);
        }
    }

    private static void updateTailPosition() {
        int x = tailPos.x();
        int y = tailPos.y();


        if (tailPos.y() == headPos.y() && tailPos.x() != headPos.x()) {
            int dif = headPos.x() - tailPos.x();
            if (dif > 1) {
                x++;
            }
            if (dif < -1) {
                x--;
            }
        }
        if (tailPos.x() == headPos.x() && tailPos.y() != headPos.y()) {
            int dif = headPos.y() - tailPos.y();
            if (dif > 1) {
                y++;
            }
            if (dif < -1) {
                y--;
            }
        }

        if (tailPos.y() != headPos.y() && tailPos.x() != headPos.x()) {
            int xDif = headPos.x() - tailPos.x();
            int yDif = headPos.y() - tailPos.y();

            if (xDif > 1) {
                x++;
                y = y + yDif;
            }
            if (xDif < -1) {
                x--;
                y = y + yDif;
            }
            if (yDif > 1) {
                y++;
                x = x + xDif;
            }
            if (yDif < -1) {
                y--;
                x = x + xDif;
            }
        }

        tailPos = new Position(x, y);
        System.out.println("H:" + headPos);
        System.out.println("T:" + tailPos);
    }

}