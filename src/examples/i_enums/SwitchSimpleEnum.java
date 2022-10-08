package examples.i_enums;

public class SwitchSimpleEnum {

    public static void main(String[] args) {
        // switch/case can only use integers - or enums!
        SimpleEnum speed = SimpleEnum.MEDIUM;

        switch (speed) {
            // only need to say SLOW, not SimpleEnum.SLOW;
            case SLOW:
                System.out.println("Too slow!!");
                break;
            case MEDIUM:
                System.out.println("Just right.");
                break;
            case FAST:
                System.out.println("Too fast!!");
                break;
        }
        // if we use an enum that only has 3 possibilities, we only need three cases. No default required.
    }
}
