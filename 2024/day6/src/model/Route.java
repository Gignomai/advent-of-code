package model;

import java.util.ArrayList;
import java.util.List;

public class Route {
    private final Grid grid;
    private final ArrayList<Position> positions;
    private Directions direction;
    private boolean stop;

    public Route(Grid grid) {
        this.grid = grid;
        positions = new ArrayList<>(List.of(grid.getStart()));
        direction = Directions.UP;
        stop = false;
    }

    public boolean keepMoving() {
        return !this.stop;
    }

    public int positionsVisited() {
        while (this.keepMoving()) {
            this.move();
        }

        return positions.size();
    }

    public boolean stillInsideTheGrid(Position position) {
        return grid.isInsideTheGrid(position);
    }

    public void move() {
        Position position = positions.getLast();
        //System.out.println("position = " + position);
        Position next = getNextPosition(position);
        //System.out.println("next = " + next);

        if (stillInsideTheGrid(next)) {
            if (grid.checkIfPositionIsBlocked(next)) {
                direction = turnRight();
                //System.out.println("direction = " + direction);
            } else {
                positions.remove(next);
                positions.add(next);
                //System.out.println("positions = " + positions);
            }
        } else {
            stop = true;
        }
    }

    private Directions turnRight() {
        return switch (direction) {
            case UP -> Directions.RIGHT;
            case LEFT -> Directions.UP;
            case DOWN -> Directions.LEFT;
            case RIGHT -> Directions.DOWN;
        };
    }

    private Position getNextPosition(Position position) {
        return switch (direction) {
            case UP -> new Position(position.x(), position.y() - 1);
            case LEFT -> new Position(position.x() - 1, position.y());
            case DOWN -> new Position(position.x(), position.y() + 1);
            case RIGHT -> new Position(position.x() + 1, position.y());
        };
    }

    public boolean hasLoops() {
        int repeatedPositions = 0;

        while(keepMoving()) {
            Position position = positions.getLast();
            Position next = getNextPosition(position);

            if (grid.isVisited(position.x(), position.y())){
                repeatedPositions++;
            } else {
                grid.markAsVisited(position.x(), position.y());
            }

            if (repeatedPositions > 15000) {
                return true;
            }

            if (stillInsideTheGrid(next)) {
                if (grid.checkIfPositionIsBlocked(next)) {
                    direction = turnRight();
                } else {
                    positions.add(next);
                }
            } else {
                stop = true;
            }
        }
        return false;
    }
}
