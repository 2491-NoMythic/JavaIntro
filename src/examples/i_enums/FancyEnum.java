package examples.i_enums;

public enum FancyEnum {
    // enums can hold data aobut thier value
    FAST("Very Fast", 99),
    MEDIUM("About middle", 50),
    SLOW("Turtles", 10);

    // class instance variables to hold the data
    private String description;
    private int speedValue;

    // a private constructor to be called when each enum value is created
    private FancyEnum(String description, int speedValue) {
        // this refers to the class instance variable
        // so we are setting this (class instance description) to the method parameter description
        this.description = description;
        this.speedValue = speedValue;
    }

    public String getDescription() {
        // we don't need to use 'this' here becuase there is only one description in the method
        return this.description;
    }

    public int getSpeedValue() {
        return speedValue;
    }
}
