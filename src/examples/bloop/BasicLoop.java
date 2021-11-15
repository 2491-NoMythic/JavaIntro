package examples.bloop;

public class BasicLoop {
    public static void main(String[] args) throws Exception {
        // a for loop, starts at a value, and loops as many times as you want
        // it starts by defining an integer i that will be the index of our loop
        // it will continue until the condition of i < 5 is no longer true
        // we need to manually increase our index
        for (int i=0; i < 5; i++) {
            System.out.println("Hello, World! " + i);
        }
    }

}
