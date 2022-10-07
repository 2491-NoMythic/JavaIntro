package examples.j_inheritance;

// we extend RobotPartial so that we get the functionality that RobotPartial abstract class provides
public class FlyingRobot extends RobotPartial {

    // create a default constructor that will call the RobotPartial constructor
    public FlyingRobot() {
        super("Flying");
    }

    // we must 'override' and implement this method from the Robot interface because RobotPartial did not
    // if this was a real robot, it would need to turn on the jets to make the robot fly
    @Override
    public void move() {
        System.out.println("Look, I am flying!");
    }
    
}
