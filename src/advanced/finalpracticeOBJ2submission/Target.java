package advanced.finalpracticeOBJ2submission;

public enum Target {
    BANK("Bank", 1),
    MANSION("Mansion", 2),
    POST_OFFICE("Post Office", 3),
    SUPERMARKET("Supermarket", 4);

    private String name;
    private int choice;

    Target(String name, int choice) {
        this.name = name;
        this.choice = choice;
    }

    public String getName() {
        return name;
    }

    public int getChoice() {
        return choice;
    }

    public static Target fromChoice(int choice) {
        for (Target target : Target.values()) {
            if (target.choice == choice) {
                return target;
            }
        }
        return null;
    }
}

