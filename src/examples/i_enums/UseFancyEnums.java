package examples.i_enums;

public class UseFancyEnums {
    public static void main(String[] args) {
        // we can assign robotSpped to a specific FancyEnum value ie. FAST
        FancyEnum robotSpeed = FancyEnum.FAST;

        // you can access the properties of each enum value with the get methods on the enum
        System.out.println(robotSpeed.getDescription());
        System.out.println(robotSpeed.getSpeedValue());

        // you can make comparisons too
        if (FancyEnum.FAST == robotSpeed) {
            System.out.println("Slow down there cowboy");
        }
    }
}
