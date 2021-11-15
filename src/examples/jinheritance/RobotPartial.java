package examples.jinheritance;

// a class is abstract if it does not implment ALL of the interface it said it would
// RobotInterface has two methods, move and getType, but only getType is implemented here
// The type and getType are defined here as they will be common for all Robots
public abstract class RobotPartial implements Robot {
    
    private String type;

    public RobotPartial(String type) {
        this.type = type;
    }
    
    public String getType() {
        return type;
    }
}
