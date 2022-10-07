package examples.b_loop;

// you can even have inner loops that work inside another loop
public class DoubleLoop {
    public static void main(String[] args) {
        // loop variables don't have to have single character names
        for (int outer=0; outer < 10; outer++) {
            System.out.println("outer loop: " + outer);
            // loops can go backwards too
            for (int inner=10; inner > 0; inner--) {
                System.out.println("inner: " + inner);
            }
        }
    }
}
