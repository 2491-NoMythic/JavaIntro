package examples.jinheritance;

import java.util.ArrayList;
import java.util.List;

public class UseRobot {
    
    public static void main(String[] args) {

        // we can create an instance of a FlyingRobot and call it's methods
        FlyingRobot flyingRobot = new FlyingRobot();
        System.out.println("type: " + flyingRobot.getType());
        flyingRobot.move();

        // above, we created a FlyingRobot, and we called the move() method on the flyingRobot instance.
        // but we can also create an instance of Robot. We will 'new' a specific robot, but hold it in a Robot variable.
        // remember the interface robot is the contract we implmented

        Robot rollingRobot = new RollingRobot();
        System.out.println("type: " + rollingRobot.getType());
        rollingRobot.move();

        // when we told the rollingRobot, to getType, Java still knows that the Robot is really a RollingRobot
        // so it knows that it needs to call the getType method on a RollingRobot. But wait.
        // the rollingRoblt didn't implement the getType method, RobotPartial did, so it executes the method there

        // ok, so we kinda knew which robot was which there, but what if we put them in a list?
        // a list can hold more than just a list of strings, a list is an interface that ArrayList implements

        List<Robot> robotList = new ArrayList<>();
        robotList.add(new FlyingRobot());
        robotList.add(new RollingRobot());
        robotList.add(new WaterRobot());

        // now we have a list of a bunch of robots and we can tell them each to move without knowing which robot it is
        // we don't have to know how the robot will actually move. each robot has implemented that method for themselves

        for (Robot robot : robotList) {
            System.out.print(robot.getType() + " : ");
            robot.move();
        }
        
    }
}
