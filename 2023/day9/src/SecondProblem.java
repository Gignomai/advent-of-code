import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class SecondProblem {

    public static Set<Position> positions = new HashSet<>();
    public static Position[] rope = new Position[10];

    public static void main(String[] args) throws IOException {
        for (int i = 0; i < 10; i++) {
            rope[i] = new Position(0, 0);
        }

        Files.lines(Path.of("src/input.txt"))
                .forEach(SecondProblem::processMovement);

        //System.out.println(positions);
        System.out.println(positions.size());
    }

    private static void processMovement(String move) {
        String direction = move.split(" ")[0];
        int times = Integer.parseInt(move.split(" ")[1]);

        for (int i = 0; i < times; i++) {

            int x = rope[0].x();
            int y = rope[0].y();
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
            rope[0] = new Position(x, y);
            //System.out.println("H:" + rope[0]);
            updateRopePositions();
            positions.add(rope[9]);
        }
    }

    private static void updateRopePositions() {
        for (int i = 0; i < rope.length - 1; i++) {
            Position currentPos = rope[i];
            Position nextPos = rope[i + 1];

            if (Math.abs(currentPos.x() - nextPos.x()) > 1 || Math.abs(currentPos.y() - nextPos.y()) > 1) {
                int x = Math.abs(nextPos.x() - currentPos.x()) > 1 ? Math.min(nextPos.x(), currentPos.x()) + 1 : currentPos.x();
                int y = Math.abs(nextPos.y() - currentPos.y()) > 1 ? Math.min(nextPos.y(), currentPos.y()) + 1 : currentPos.y();

                rope[i + 1] = new Position(x, y);
            }


            /*if (currentPos.x() != nextPos.x() && currentPos.y() != nextPos.y()) {
                if ((xDif > 1 && yDif == 1) || (xDif == 1 && yDif > 1)) {
                    x++;
                    y++;
                } else if ((xDif > 1 && yDif == -1) || (xDif == 1 && yDif < -1)) {
                    x++;
                    y--;
                } else if ((xDif < -1 && yDif == 1) || (xDif == -1 && yDif > 1)) {
                    x--;
                    y++;
                } else if ((xDif < -1 && yDif == -1) || (xDif == -1 && yDif < -1)) {
                    x--;
                    y--;
                }
            } else {
                if (xDif > 1) {
                    x++;
                } else if (xDif < -1) {
                    x--;
                } else if (yDif > 1) {
                    y++;
                } else if (yDif < -1) {
                    y--;
                }
            }

            rope[i] = new Position(x, y);*/
            // System.out.println(i + ":" + rope[i]);
        }
    }
}