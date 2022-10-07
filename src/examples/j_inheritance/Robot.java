package examples.j_inheritance;

// an interface is like a contract, a list of methods that other classes will implemnet
// we are saying that to be a robot you will need to define these methods, but we are not saying what they have to do
public interface Robot {
    void move();
    String getType();
}
