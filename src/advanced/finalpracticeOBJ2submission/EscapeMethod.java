package advanced.finalpracticeOBJ2submission;

public enum EscapeMethod {
    CAR(1, "CAR"),
    BIKE(2, "BIKE"),
    BOAT(3, "BOAT"),
    ON_FOOT(4, "ON_FOOT");

    private int choice;
    private String name;

    EscapeMethod(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getChoice() {
        return choice;
    }

    public static EscapeMethod fromChoice(int choice) {
        for (EscapeMethod method : EscapeMethod.values()) {
            if (method.choice == choice) {
                return method;
            }
        }
        return null;
    }
}

