package examples.d_function;

public class Function {

    // this function takes a character string and prints it
    // note the word "static" here. we need it because 'main' is static
    // we will learn more about this when we look at classes more
    public static void printStuff(String stuff) {
        System.out.println(stuff);
    }

    public static void main(String[] args) throws Exception {
        printStuff("Hello World");
        printStuff("Programming is cool");
    }
}
