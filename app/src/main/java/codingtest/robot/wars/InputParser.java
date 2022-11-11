package codingtest.robot.wars;

import codingtest.robot.wars.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;

public class InputParser {
    public static Arena parseArenaLimit(String arenaLimit) throws InvalidInputException {
        String[] coord = arenaLimit.trim().split(" ");
        validateInputSize(coord, 2);
        Coordinate upperRightCorner = parseCoordinate(coord);
        validateArenaLimit(upperRightCorner);
        return new Arena(upperRightCorner);
    }

    private static void validateArenaLimit(Coordinate arenaLimit) throws InvalidInputException {
        if (arenaLimit.getX() < 1 || arenaLimit.getY() < 1) {
            throw new InvalidInputException("Invalid arena size.");
        }
    }

    private static void validateInputSize(String[] inputs, int expectedSize) throws InvalidInputException {
        if (inputs.length != expectedSize) {
            throw new InvalidInputException("Invalid number of inputs.");
        }
    }

    private static Coordinate parseCoordinate(String[] inputs) throws InvalidInputException {
        int x = parseInteger(inputs[0]);
        int y = parseInteger(inputs[1]);

        return new Coordinate(x, y);
    }

    public static RobotPosition parseRobotPosition(String robotPosition) throws InvalidInputException {
        String[] position = robotPosition.trim().toUpperCase().split(" ");
        validateInputSize(position, 3);
        return new RobotPosition(parseCoordinate(position), parseDirection(position[2]));
    }

    private static int parseInteger(String string) throws InvalidInputException {
        try {
            int x = Integer.parseInt(string);
            if (x < 0) {
                throw new InvalidInputException("Invalid coordinate " + x + ".");
            }
            return x;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Invalid coordinate " + string + ".");
        }
    }

    private static Direction parseDirection(String dir) throws InvalidInputException {
        try {
            return Direction.valueOf(dir);
        } catch (IllegalArgumentException e) {
            throw new InvalidInputException("Invalid direction " + dir + ".");
        }
    }

    public static List<Movement> parseRobotMovements(String robotMovements) throws InvalidInputException {
        List<Movement> movements = new ArrayList<>();

        for (char c : robotMovements.trim().toUpperCase().toCharArray()) {
            try {
                movements.add(Movement.valueOf(String.valueOf(c)));
            } catch (IllegalArgumentException e) {
                throw new InvalidInputException("Invalid movement " + c + ".");
            }
        }

        return movements;
    }
}
