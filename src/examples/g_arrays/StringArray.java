package examples.g_arrays;

public class StringArray {
    public static void main(String[] args) {
        // create a String array that can hold 3 names
        // note: it can't grow to hold more than three names after being defined as 3
        String[] names = new String[3];
        // put individual names into the array slots
        names[0] = "Juan";
        names[1] = "Kim";
        names[2] = "Dan";

        for (int i=0; i < 3; i++) {
            System.out.println(names[i]);
        }

        // create an int array and assign the items at the beginning
        String[] otherNames = new String[] {"Hue", "Sara", "Trish", "Dallas", "Jesus"};

        // change one of the names, Trish, to Anastasia
        // note that position 3 of the array is 2 because it starts at 0
        otherNames[2] = "Anastasia";

        for (int i=0; i < 5; i++) {
            System.out.println(otherNames[i]);
        }
    }
}
