package examples.c_ifthen;

// change the value of aValue and number to get different results

public class SimpleIfThen {
    public static void main(String[] args) throws Exception {
        // a variable that can only be true or false is a boolean
        boolean aValue = true;

        // this is how we make comparisons: if something, so that, else do something else
        if (aValue == true) {
            System.out.println("It's true!");
        } else {
            System.out.println("It's false");
        }

        // an integer number is called an int
        int number = 1;

        // we can chain comparisons too
        if (number == 1) {
            System.out.println("one");
        } else if (number == 2) {
            System.out.println("two");
        } else if (number == 3) {
            System.out.println("three");
        } else {
            System.out.println("no idea");
        }
    }
}
