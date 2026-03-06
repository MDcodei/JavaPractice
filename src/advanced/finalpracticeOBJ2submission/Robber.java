package advanced.finalpracticeOBJ2submission;

public enum Robber {
    ROB(1, "Rob", "The head"),
    BOBBY(2, "Bobby", "The mountain");

    private int choice;
    private String name;
    private String nickname;

    Robber(int choice, String name, String nickname) {
        this.choice = choice;
        this.name = name;
        this.nickname = nickname;
    }

    public String getName() {
        return name;
    }

    public String getNickname() {
        return nickname;
    }

    public int getChoice() {
        return choice;
    }

    public static Robber fromChoice(int choice) {
        for (Robber robber : Robber.values()) {
            if (robber.choice == choice) {
                return robber;
            }
        }
        return null;
    }
}

