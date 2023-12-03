public class Game {
    private int id;
    private boolean isValid;

    public Game(String game) {
        isValid = true;
        id = Integer.parseInt(game.split(":")[0].split(" ")[1]);
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
    }

    public int getId() {
        return id;
    }

    public boolean isValid() {
        return isValid;
    }
}
