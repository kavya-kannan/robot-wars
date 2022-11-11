package codingtest.robot.wars;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class RobotPosition {
    private Coordinate coordinate;
    private Direction direction;

    public void printFinalPosition() {
        int x = this.coordinate.getX();
        int y = this.coordinate.getY();

        System.out.println(x + " " + y + " " + this.direction);
    }
}
