package examples.garrays;

public class IntArray {
    public static void main(String[] args) {
        // create an int array that can hold 3 items
        // note: it can't grow to hold more than three items after being defined as 3
        int[] stuff = new int[3];
        // put individual items into the array slots
        stuff[0] = 22;
        stuff[1] = 51;
        stuff[2] = 99;

        for (int i=0; i < 3; i++) {
            System.out.println(stuff[i]);
        }

        // create an int array and assign the items at the beginning
        int[] other = new int[] {2, 8, 5, 3, 7};

        // change one of the items, the 5, to a 555
        // note that position 3 of the array is 2 becuase it starts at 0
        other[2] = 555;

        for (int i=0; i < 5; i++) {
            System.out.println(other[i]);
        }
    }
}
