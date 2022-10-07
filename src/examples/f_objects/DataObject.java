package examples.f_objects;

// the power of object is their ability to contain data
public class DataObject {
    // the data object class will hold one int. the class variable is called data
    // it is priate meaning that any class trying to see data, will not see it. only the public methods
    private int data;

    // this is the default constructor, called if we do a "new DataObject()"
    public DataObject() {
        data = 42;
    }

    // this is another constructor, called if we want to initialize data: "new DataObject(22)"
    public DataObject(int value) {
        data = value;
    }

    // this will return the value of data
    public int getData() {
        return data;
    }

    // we can change the value of data at anytime with this method
    public void setData(int value) {
        data = value;
    }

    // we can also have other methods that do stuff to our data
    public void squareData() {
        data = data * data;
    }
}
