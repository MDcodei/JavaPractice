package intermediate.finalpracticeOBJ1submission;

public class City {

    private Building[] buildings = new Building[4];


    public City(){
        Item[] bankItems = {
                new Item("letter opener", 1.5),
                new Item("Stamp", 2.5)
        };
        buildings[0] = new Building("Bank", bankItems);

        Item[] mansionItems = {
                new Item("Pair of fancy shoes", 25),
                new Item("Broken glass", 0.1)
        };
        buildings[1] = new Building("Mansion", mansionItems);

        Item[] postOfficeItems = {
                new Item("Letter to Jenny", 1.5),
                new Item("Pencil", 2.0)
        };
        buildings[2] = new Building("Post Office", postOfficeItems);

        Item[] superMarketItems = {
                new Item("A loaf of bread", 2.5),
                new Item("A baf of tea", 6.5)
        };
        buildings[3] = new Building("Supermarket", superMarketItems);

    }

    public Building[] getBuildings() {
        return buildings;
    }
}
