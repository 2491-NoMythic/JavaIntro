// the package statement tells us what folders the code is in
package examples.a_helloworld;

// Java requries all code to be in a class
// the public part means that it is visible to everyone - we will learn more about tht later
public class HelloWorld {
    // if you want to "run" a java program, it needs to have a 'main' method just like this
    // public - everyone can see is, static - only one main method
    // 'main' - the name of the method, String[] args - an array of strings
    // throws exceptions - error handling
    // we will learn more about all of these things later
    public static void main(String[] args) throws Exception {
        System.out.println("Hello, World!");
    }
}