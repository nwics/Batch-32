package day3.Challenge;

public enum Type {
    SUV("Car"),
    TAXI("Car"),
    PRIVATE_JET("Plane"),
    BOAT("Boat");

    private final String type;

    Type(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

}
