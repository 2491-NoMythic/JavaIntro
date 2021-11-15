package examples.fobjects;

public class UseSimpleObject {
    // SimpleObject is not a program that can be run. It does have a method we want to use though.
    public static void main(String[] args) throws Exception {
        // before we can use SimpleObject we need an 'instance' of SimpleObject
        // without an instance, SimpleObject just describes what it does, it can't actually do anything
        // we use 'new' to create a new instance
        // note that the name of the variable that holds the reference to our instance of SimpleObject can be called what ever we want
        SimpleObject myThing = new SimpleObject();
        
        // now we can call methods on myThing, which is an instance of SimpleObject
        int value = myThing.add(55, 27);

        System.out.println("value is:" + value);
    }
}
