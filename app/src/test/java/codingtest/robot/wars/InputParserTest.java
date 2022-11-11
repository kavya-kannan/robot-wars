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

class InputParserTest {

    @Test
    void parseArenaLimitSuccessfully() throws InvalidInputException {
        String arenaLimit = "5 5";
        Arena arena = InputParser.parseArenaLimit(arenaLimit);
        Arena expectedArena = new Arena(new Coordinate(5, 5));
        assertEquals(expectedArena, arena);
    }

    @Test
    void parseArenaLimitExtraInputs_throwException() {
        String arenaLimit = "5 5 5";
        Exception exception = assertThrows(InvalidInputException.class,
                () -> InputParser.parseArenaLimit(arenaLimit));

        String expectedMessage = "Invalid number of inputs";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseArenaLimitInvalidInputNonInteger_throwException() {
        String arenaLimit = "A 5";
        Exception exception = assertThrows(InvalidInputException.class,
                () -> InputParser.parseArenaLimit(arenaLimit));

        String expectedMessage = "Invalid coordinate";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseArenaLimitInvalidInputNegativeInteger_throwException() {
        String arenaLimit = "-5 5";
        Exception exception = assertThrows(InvalidInputException.class,
                () -> InputParser.parseArenaLimit(arenaLimit));

        String expectedMessage = "Invalid coordinate";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseRobotPositionSuccessfully() throws InvalidInputException {
        String robotPosition = "1 2 N";

        RobotPosition expectedPosition =
                new RobotPosition(new Coordinate(1, 2), Direction.N);
        assertEquals(expectedPosition, InputParser.parseRobotPosition(robotPosition));
    }

    @Test
    void parseRobotPositionInvalidDirection_throwException() {
        String robotPosition = "1 2 K";

        Exception exception = assertThrows(InvalidInputException.class,
                () -> InputParser.parseRobotPosition(robotPosition));

        String expectedMessage = "Invalid direction";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseRobotPositionInvalidCoordinate_throwException() {
        String robotPosition = "-1 2 N";

        Exception exception = assertThrows(InvalidInputException.class,
                () -> InputParser.parseRobotPosition(robotPosition));

        String expectedMessage = "Invalid coordinate";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void parseRobotMovementsSuccessfully() throws InvalidInputException{
        String robotMovements = "LMRMLMRMLM";
        List<Movement> movements = InputParser.parseRobotMovements(robotMovements);

        assertEquals(List.of(L, M, R, M, L, M, R, M, L, M ), movements);
    }

    @Test
    void parseRobotMovementsInvalidMove_throwException() {
        String robotMovements = "LMRMABMM";
        Exception exception = assertThrows(InvalidInputException.class,
                () -> InputParser.parseRobotMovements(robotMovements));

        String expectedMessage = "Invalid movement";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }
}