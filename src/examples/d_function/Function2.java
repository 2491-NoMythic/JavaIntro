package examples.d_function;

public class Function2 {

    // The function add takes two integers and returns the sum of the two
    public static int add(int one, int two) {
        return one + two;
    }

    public static void main(String[] args) throws Exception {
        
        // we can create this variable once and reuse it
        int value = 0;

        value = add(3, 7);

        System.out.println(value);

        value = add(23, 24);

        System.out.println(value);
    }
}
