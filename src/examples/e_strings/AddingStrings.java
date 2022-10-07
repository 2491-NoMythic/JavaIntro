package examples.e_strings;

public class AddingStrings {

    // strings can be concatenated as if you were adding them
    public static void main(String[] args) throws Exception {
        String hello = "Hello";
        String world = "World";

        String greeting = hello + " " + world + "!";

        System.out.println(greeting);
        System.out.println(hello + " " + world + " works too");
    }
}
