package intermediate.finalpracticeOBJ1submission;

public class Criminal extends Person {

    public Criminal(String name, String nickname, int yearOfBorn, String expertIn, Item[] items) {
        super(name, nickname, yearOfBorn, expertIn, items);
    }

    public static final int SUCCESS_PERCENTAGE = 20;

    public void printBioData(){
        System.out.println("Criminal Person");

        super.printBioData();
    }
}
