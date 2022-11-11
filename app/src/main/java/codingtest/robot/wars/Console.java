package codingtest.robot.wars;

import codingtest.robot.wars.exceptions.InvalidInputException;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class Console {
    private final Arena arena;
    private final List<RobotPosition> robotPositions = new ArrayList<>();

    public RobotPosition moveRobot(RobotPosition initialPosition, List<Movement> movements) throws InvalidInputException {
        validateInitialPosition(initialPosition);

        Robot robot = new Robot(initialPosition);
        movements.forEach(movement -> {
            switch (movement) {
                case L -> robot.rotateLeft();
                case R -> robot.rotateRight();
                case M -> moveIfPossible(robot);
            }
        });

        robotPositions.add(robot.position());
        return robot.position();
    }

    private void moveIfPossible(Robot robot) {
        if (isValidPosition(robot.getPositionAfterMove())) {
            robot.move();
        }
    }

    private boolean isValidPosition(RobotPosition robotPosition) {
        return isWithinArena(robotPosition)
                && isGridAvailable(robotPosition.getCoordinate());
    }

    private boolean isWithinArena(RobotPosition robotPosition) {
        int x = robotPosition.getCoordinate().getX();
        int y = robotPosition.getCoordinate().getY();
        return x >= 0 && x <= arena.arenaLimit().getX()
                && y >= 0 && y <= arena.arenaLimit().getY();
    }

    private boolean isGridAvailable(Coordinate robotPosition) {
        for (RobotPosition position : robotPositions) {
            if (position.getCoordinate().equals(robotPosition)) {
                return false;
            }
        }

        return true;
    }

    private void validateInitialPosition(RobotPosition robotPosition) throws InvalidInputException {
        int x = robotPosition.getCoordinate().getX();
        int y = robotPosition.getCoordinate().getY();

        if (!isValidPosition(robotPosition)) {
            throw new InvalidInputException("Error placing robot at " + x + " " + y + ".");
        }
    }
}
