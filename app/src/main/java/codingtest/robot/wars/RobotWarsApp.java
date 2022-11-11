/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package codingtest.robot.wars;

import codingtest.robot.wars.exceptions.InvalidInputException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RobotWarsApp {
    private final static int NUMBER_OF_ROBOTS = 2;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the inputs: ");

        try {
            Arena arena = InputParser.parseArenaLimit(scanner.nextLine());
            Console console = new Console(arena);
            List<RobotPosition> finalPositions = new ArrayList<>();

            for (int i = 0; i < NUMBER_OF_ROBOTS; i++) {
                RobotPosition robotPosition = InputParser.parseRobotPosition(scanner.nextLine());
                List<Movement> robotMovements = InputParser.parseRobotMovements(scanner.nextLine());
                finalPositions.add(console.moveRobot(robotPosition, robotMovements));
            }

            finalPositions.forEach(RobotPosition::printFinalPosition);
        } catch (InvalidInputException e) {
            System.err.println(e.getMessage());
        }
    }
}