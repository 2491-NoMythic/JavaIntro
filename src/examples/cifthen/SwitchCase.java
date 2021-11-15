package examples.cifthen;

public class SwitchCase {
    public static void main(String[] args) {

        int value = 2;

        // to avoid having too many nested if/else statements you can use a switch/case instead
        // you can also use enums (check the enum section) but you can't use Strings
        switch(value) {
            case 1: 
                System.out.println("one");
                break;
            case 2:
                System.out.println("two");
                break;
            case 3:
                System.out.println("three");
                break;
            default:
                System.out.println("how should I know");
        }
    }
}
