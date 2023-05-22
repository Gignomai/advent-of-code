import java.util.List;

public class HeightMap {
    int [][] grid;

    public HeightMap(List<String> lines){
        int numberOfLines = lines.size();
        int lineLength = lines.get(0).length();
        grid = new int[numberOfLines][lineLength];

        for (int i = 0; i < numberOfLines; i++) {
            char[] chars = lines.get(i).toCharArray();
            for (int j = 0; j < lineLength; j++) {
                grid[i][j] = Integer.parseInt(String.valueOf(chars[j]));
            }
        }
    }
}
