import java.util.List;

public class Part2 {
    public Integer processLines(List<String> lines) {
        int total = 0;

        for (String game : lines) {
            //System.out.println("game = " + game);
            String plays = game.split(":")[1];
            int red = 1;
            int green = 1;
            int blue = 1;
            for (String play : plays.split(";")) {
                //System.out.println("play = " + play);
                String[] draws = play.split(",");
                for (String draw : draws) {
                    String[] parts = draw.trim().split(" ");
                    int number = Integer.parseInt(parts[0]);
                    String color = parts[1];
//                    System.out.println("number = " + number);
//                    System.out.println("color = " + color);
                    if (color.equals("red") && number > red) {
                        red = number;
                    }
                    if (color.equals("green") && number > green) {
                        green = number;
                    }
                    if (color.equals("blue") && number > blue) {
                        blue = number;
                    }
                }
            }
            total += (red * green * blue);
        }

        return total;
    }

    public boolean test(List<String> fileName) {
        return processLines(fileName).equals(2286);
    }
}