package codingtest.robot.wars;

import codingtest.robot.wars.exceptions.InvalidInputException;
import org.junit.jupiter.api.Test;

import java.util.List;

import static codingtest.robot.wars.Movement.L;
import static codingtest.robot.wars.Movement.M;
import static codingtest.robot.wars.Movement.R;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ConsoleTest {
    @Test
    void moveRobotSuccessfully() throws InvalidInputException {
        Arena arena = new Arena(new Coordinate(5, 5));
        Console console = new Console(arena);
        RobotPosition initialPosition = new RobotPosition(new Coordinate(1, 2), Direction.N);
        List<Movement> movements = List.of(L, M, L, M, L, M, L, M, M);

        RobotPosition expectedFinalPosition = new RobotPosition(new Coordinate(1, 3), Direction.N);
        assertEquals(expectedFinalPosition, console.moveRobot(initialPosition, movements));
    }

    @Test
    void moveRobotOutsideArenaLimit() throws InvalidInputException {
        Arena arena = new Arena(new Coordinate(5, 5));
        Console console = new Console(arena);
        RobotPosition initialPosition = new RobotPosition(new Coordinate(0, 0), Direction.W);
        List<Movement> movements = List.of(M, M);

        RobotPosition expectedFinalPosition = new RobotPosition(new Coordinate(0, 0), Direction.W);

        assertEquals(expectedFinalPosition, console.moveRobot(initialPosition, movements));
    }

    @Test
    void moveRobotWhichIsNotWithinArenaLimit_throwException() {
        Arena arena = new Arena(new Coordinate(5, 5));
        Console console = new Console(arena);
        RobotPosition initialPosition = new RobotPosition(new Coordinate(7, 9), Direction.N);
        List<Movement> movements = List.of(L, M, R, M, L, M, R, M);

        Exception exception = assertThrows(InvalidInputException.class,
                () -> console.moveRobot(initialPosition, movements));

        String expectedMessage = "Error placing robot at ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void moveRobotWhoseInitialPositionIsOccupiedByAnotherRobot_throwException() throws InvalidInputException {
        Arena arena = new Arena(new Coordinate(5, 5));
        Console console = new Console(arena);
        RobotPosition firstRobotInitialPosition = new RobotPosition(new Coordinate(1, 2), Direction.N);
        List<Movement> firstRobotMovements = List.of(L, M, L, M, L, M, M);

        RobotPosition firstRobotFinalPosition = console.moveRobot(firstRobotInitialPosition, firstRobotMovements);

        RobotPosition secondRobotInitialPosition = firstRobotFinalPosition;
        List<Movement> secondRobotMovements = List.of(L, M, L, M, L, M);

        Exception exception = assertThrows(InvalidInputException.class,
                () -> console.moveRobot(secondRobotInitialPosition, secondRobotMovements));

        String expectedMessage = "Error placing robot at ";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}