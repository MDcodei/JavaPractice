package advanced.finalpracticeOBJ2submission;

public enum Difficulty {
    EASY(1, "EASY"),
    MEDIUM(2, "MEDIUM"),
    HARD(3, "HARD");

    private int choice;
    private String name;

    Difficulty(int choice, String name) {
        this.choice = choice;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getChoice() {
        return choice;
    }

    public static Difficulty fromChoice(int choice) {
        for (Difficulty diff : Difficulty.values()) {
            if (diff.choice == choice) {
                return diff;
            }
        }
        return null;
    }
}

