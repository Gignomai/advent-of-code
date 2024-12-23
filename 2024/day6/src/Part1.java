import model.Grid;
import model.Route;

import java.util.List;

public class Part1 {
    public Integer processLines(List<String> lines) {
        Route route = new Route(new Grid(lines));

        int visited = route.positionsVisited();
        System.out.println("visited = " + visited);
        return visited;
    }

    public boolean test(List<String> lines) {
        return processLines(lines).equals(41);
    }

}