import java.util.List;

public class Part1 {

    public Integer processLines(List<String> lines) {
        int total = 0;

        for (String game : lines) {
            boolean isValid = true;
            int id = Integer.parseInt(game.split(":")[0].split(" ")[1]);
            String plays = game.split(":")[1];

            for (String play : plays.split(";")) {
                String[] draws = play.split(",");
                for (String draw: draws) {
                    String[] parts = draw.trim().split(" ");
                    int number = Integer.parseInt(parts[0]);
                    String color = parts[1];
                    if ((color.equals("red") && number > 12)
                            || (color.equals("green") && number > 13)
                            || (color.equals("blue") && number > 14)) {
                        isValid = false;
                    }
                }
            }

            if (isValid) {
                total += id;
            }
        }

        return total;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(8);
    }
}