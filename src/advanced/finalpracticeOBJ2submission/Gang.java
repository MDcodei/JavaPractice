package advanced.finalpracticeOBJ2submission;

import java.util.Random;

public class Gang {

    private Criminal[] criminals = new Criminal[2];
    private Random randomNumberGenerator = new Random();
    private double sumRobbedValue;


    public Gang() {
        Item[] robItems = {
                new Item("gun", 1.5),
                new Item("knife", 2.5)
        };
        criminals[0] = new Criminal("Rob", "The head", 1976, "breaking in", robItems);

        Item[] bobbyItems = {
                new Item("bat", 1.5),
                new Item("scissors", 2.5)
        };
        criminals[1] = new Criminal("Bobby", "The mountain", 1978, "knocking out", bobbyItems);

    }




    public double getSumRobbedValue(){
        return sumRobbedValue;
    }

    public Criminal getCriminalByName(String name){
        for (Criminal criminal : criminals){
            if (criminal.getName().equalsIgnoreCase(name)){
                return criminal;
            }
        }
        return null;
    }

    public Criminal[] getCriminals() {
        return criminals.clone(); // Return a copy to prevent external modification
    }

    public void getGangInfo(){
        for (Criminal criminal : criminals){
            System.out.println("______________________");
            criminal.printBioData();
        }
    }

    private boolean isSuccessfulRobbery(){
        int randomNumber = randomNumberGenerator.nextInt(1,100);
        int success = criminals.length * Criminal.SUCCESS_PERCENTAGE;

        return randomNumber < success;
    }

    public void letsRob(Building[] buildings){
        int randombuilding = randomNumberGenerator.nextInt(buildings.length);
        if (isSuccessfulRobbery()){
            System.out.println("The gang managed to rob the following items from the "+buildings[randombuilding].getName());
            for (Item item: buildings[randombuilding].getItems()){
                sumRobbedValue += item.getValue();
                System.out.println("-"+item.getName());
            }
        }
        else{
            System.out.println("The gang tried to rob the "+buildings[randombuilding].getName()+" but they failed");
        }
    }


}
