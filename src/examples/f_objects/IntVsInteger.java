package examples.f_objects;

public class IntVsInteger {
    public static void main(String[] args) {
        // an int has a value that starts from 0. it can't be 'nothing'
        int aInt = 0;

        // an Integer can be set to nothing, or null meaning we have reserved a name, but not given it a value yet
        Integer aInteger = null;

        // because an Integer is an object, we can assign it a value like we do we int. We need to create an Integer
        aInteger = Integer.valueOf(0);

        // we can even get an integer from a string
        aInteger = Integer.valueOf("0");

        // to compare we should get the int value
        if (aInt == aInteger.intValue()) {
            System.out.println("Tada");
        }

        // just like how we compared strings, we compare Integers the same
        Integer anotherInteger = Integer.valueOf(0);
        if (aInteger.equals(anotherInteger)) {
            System.out.println("equals");
        }
    }
}
