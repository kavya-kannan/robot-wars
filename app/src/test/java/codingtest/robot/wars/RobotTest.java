package codingtest.robot.wars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RobotTest {

    @Test
    void rotateLeft() {
        Robot robot = new Robot(new RobotPosition(new Coordinate(1,  2),  Direction.N));
        robot.rotateLeft();

        assertEquals(Direction.W, robot.position().getDirection());
    }

    @Test
    void rotateRight() {
        Robot robot = new Robot(new RobotPosition(new Coordinate(1, 2), Direction.N));
        robot.rotateRight();

        assertEquals(Direction.E, robot.position().getDirection());
    }

    @Test
    void getPositionAfterMove() {
        Robot robot = new Robot(new RobotPosition(new Coordinate(1, 2), Direction.N));
        RobotPosition newPosition = robot.getPositionAfterMove();

        assertEquals(new Coordinate(1, 3), newPosition.getCoordinate());
    }

    @Test
    void move() {
        Robot robot = new Robot(new RobotPosition(new Coordinate(1, 2), Direction.E));
        RobotPosition expectedNewPosition = new RobotPosition(new Coordinate(2, 2), Direction.E);

        robot.move();
        assertEquals(expectedNewPosition, robot.position());
    }
}