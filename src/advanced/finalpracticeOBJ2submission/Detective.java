package advanced.finalpracticeOBJ2submission;

public class Detective extends Person {


    public Detective(String name, String nickname, int yearOfBorn, String expertIn, Item[] items) {
        super(name, nickname, yearOfBorn, expertIn, items);
    }

    public static int SUCCESS_PERCENTAGE = 30;

    public void printBioData(){
        System.out.println("Detective");
        super.printBioData();
    }
}
