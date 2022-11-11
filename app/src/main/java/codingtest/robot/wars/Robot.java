package codingtest.robot.wars;

import static codingtest.robot.wars.Direction.E;
import static codingtest.robot.wars.Direction.N;
import static codingtest.robot.wars.Direction.S;
import static codingtest.robot.wars.Direction.W;

public record Robot(RobotPosition position) {
    public void rotateLeft() {
        switch (this.position.getDirection()) {
            case N -> this.position.setDirection(W);
            case E -> this.position.setDirection(N);
            case S -> this.position.setDirection(E);
            case W -> this.position.setDirection(S);
        }
    }

    public void rotateRight() {
        switch (this.position.getDirection()) {
            case N -> this.position.setDirection(E);
            case E -> this.position.setDirection(S);
            case S -> this.position.setDirection(W);
            case W -> this.position.setDirection(N);
        }
    }

    public RobotPosition getPositionAfterMove() {
        Coordinate nextCoordinate = new Coordinate(position.getCoordinate().getX(), position.getCoordinate().getY());
        switch (position.getDirection()) {
            case N -> nextCoordinate.setY(nextCoordinate.getY() + 1);
            case E -> nextCoordinate.setX(nextCoordinate.getX() + 1);
            case S -> nextCoordinate.setY(nextCoordinate.getY() - 1);
            case W -> nextCoordinate.setX(nextCoordinate.getX() - 1);
        }

        return new RobotPosition(nextCoordinate, position.getDirection());
    }

    public void move() {
        this.position.setCoordinate(getPositionAfterMove().getCoordinate());
    }
}
