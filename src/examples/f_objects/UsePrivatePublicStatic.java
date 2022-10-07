package examples.f_objects;

public class UsePrivatePublicStatic {
    public static void main(String[] args) {
        PrivatePublicStatic one = new PrivatePublicStatic();
        // if the int hiddenInt is private in the PrivatePublicStatic class we can't use it directly, we need a method
        one.setHiddenInt(10);
        // if the int visibleInt is public in the PrivatePublicStatic class we can use it directly
        one.visibleInt = 20;

        PrivatePublicStatic two = new PrivatePublicStatic();
        two.setHiddenInt(100);
        two.visibleInt = 200;

        // the two class variables are still seperate no matter if private of public
        System.out.println("one hidden: " + one.getHiddenInt());
        System.out.println("one visible: " + one.visibleInt);
        System.out.println("two hidden: " + two.getHiddenInt());
        System.out.println("two visible: " + two.visibleInt);

        // if something is static though, the instances share the variable
        // we started the counter at 0
        one.increment();
        // now the counter is at 1
        two.increment();
        // now the counter is at 2 in BOTH classes

        System.out.println("one counter: " + one.getCounter());
        System.out.println("two counter: " + two.getCounter());
    }   
}
