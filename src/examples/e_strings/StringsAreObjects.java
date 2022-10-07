package examples.e_strings;

public class StringsAreObjects {
    public static void main(String[] args) throws Exception {
        String hello1 = "Hello";
        String hello2 = "Hello";

        if (hello1 == hello2) {
            System.out.println("Simple comparison of string literals works");
        }

        String hello3 = new String("Hello");
        
        if (hello1 != hello3) {
            System.out.println("Objects are not ==");
        }

        if (hello1.equals(hello2)) {
            System.out.println("Object comparison works");
        }
    }
}
