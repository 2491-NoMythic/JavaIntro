package examples.jinheritance;

// we extend RobotPartial so that we get the functionality that RobotPartial abstract class provides
public class RollingRobot extends RobotPartial {
    
    // create a default constructor that will call the RobotPartial constructor
    public RollingRobot() {
        super("Rolling");
    }

    // we must 'override' and implement this method from the Robot interface because RobotPartial did not
    // if this was a real robot, it would need to spin the correct motors to make the robot roll along
    @Override
    public void move() {
        System.out.println("Rolling like a competition robot should");
    }
}
