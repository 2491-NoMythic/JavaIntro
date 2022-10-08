package examples.e_strings;

import java.util.Locale;

public class StringsAreObjects {
    public static void main(String[] args) throws Exception {
        String hello1 = "hello";
        String hello2 = "hel" + "lo";

        if (hello1 == hello2) {
            System.out.println("Simple comparison of string literals works sometimes");
        }

        String hello3 = new String("hello");
        
        if (hello1 != hello3) {
            System.out.println("Objects are not ==");
        }

        if (hello1.equals(hello2)) {
            System.out.println("Object comparison works");
        }
    }
}
