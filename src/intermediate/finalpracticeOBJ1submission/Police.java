package intermediate.finalpracticeOBJ1submission;

import java.util.Random;

public class Police {
    private Detective adamPalmer;

    private Random randomNumberGenerator = new Random();

    Police(){
        Item[] adamItems = new Item[2];
        adamItems[0] = new Item("Revolver", 500.0);
        adamItems[1] = new Item("Magnifying glass", 10.0);
        adamPalmer = new Detective("Adam Palmer", "Coyote", 1966, "chess", adamItems);
    }

    private boolean areCriminalsCaught(){
        int randomnum = randomNumberGenerator.nextInt(1, 100);
        return randomnum < Detective.SUCCESS_PERCENTAGE;
    }

    public boolean catchCriminals(Gang gang){
        if (areCriminalsCaught()){
            System.out.println(adamPalmer.getName()+ " managed to catch the gang.");
            if (gang.getSumRobbedValue()>0){
                System.out.println("The stolen item are recovered. Their overall value is estimated to $"+ gang.getSumRobbedValue());
            }
            else{
                System.out.println("The gang couldn't steal anything.");
            }
            return true;
        }
        else{
            System.out.println(adamPalmer.getName()+ " didn't managed to catch the gang.");
            System.out.println("They managed to steal items valued $"+ gang.getSumRobbedValue());
            return false;
        }
    }
}
