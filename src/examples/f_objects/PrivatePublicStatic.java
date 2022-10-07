package examples.f_objects;

public class PrivatePublicStatic {
    // note the private - not accessable to other classes
    private int hiddenInt;

    // note the public - other classes can modify this directly
    public int visibleInt;

    // note the static - this value is shared with all instances
    private static int counter = 0;

    public void setHiddenInt(int value) {
        hiddenInt = value;
    }

    public int getHiddenInt() {
        return hiddenInt;
    }

    public void increment() {
        counter++;
    }

    public int getCounter() {
        return counter;
    }
}
