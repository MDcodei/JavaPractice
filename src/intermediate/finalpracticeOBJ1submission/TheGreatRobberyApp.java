package intermediate.finalpracticeOBJ1submission;

public class TheGreatRobberyApp {
    public static void main(String[] args){
        City city = new City();
        Gang gang = new Gang();
        Police police = new Police();
        gang.getGangInfo();
        gang.letsRob(city.getBuildings());
        police.catchCriminals(gang);
    }
}
