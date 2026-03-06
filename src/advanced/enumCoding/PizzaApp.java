package advanced;

public class PizzaApp {
    public static void main() {

        System.out.println("Available sizes:");
        for (PizzaSize pizzaSize: PizzaSize.values()){
            System.out.println("- " + (pizzaSize.name().toLowerCase()));
        }
        System.out.println();
        Pizza pizzaOrder = new Pizza("Margareta", PizzaSize.MEDIUM);
        System.out.println("I ordered the following pizza: ");
        System.out.println("Name: "+ pizzaOrder.getName());
        System.out.println("Size: "+ pizzaOrder.getPizzaSize());
        System.out.println("Price: "+ pizzaOrder.getPrice());
    }
}
