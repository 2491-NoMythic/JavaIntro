package examples.ienums;

public class UseSimpleEnum {

    // by defining a function that takes an enum parameter you can limit the options to just those in an enum
    public static void enumFunction(SimpleEnum speed) {
        System.out.println(speed);
    }

    public static void main(String[] args) {
        System.out.println(SimpleEnum.FAST);
        enumFunction(SimpleEnum.SLOW);
    }
}
