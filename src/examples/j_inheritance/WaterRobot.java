package examples.j_inheritance;

// we extend RobotPartial so that we get the functionality that RobotPartial abstract class provides
public class WaterRobot extends RobotPartial {
    
    // create a default constructor that will call the RobotPartial constructor
    public WaterRobot() {
        super("Water");
    }

    // we must 'override' and implement this method from the Robot interface because RobotPartial did not
    // if this was a real robot, it would need to spin the correct motors to make the robot swim
    @Override
    public void move() {
        System.out.println("Just keep swimming, just keep swimming...");
    }
}
