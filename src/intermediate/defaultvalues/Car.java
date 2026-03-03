package intermediate.defaultvalues;

public class Car {
    private byte numberOfSeats;
    private short horsepower;
    private long registrationNumber;
    private int price;

    private float fuelConsumption;
    private double preciseFuelConsumption;

    private boolean isDamaged;
    private char energyEfficiencyCategory;
    private String color;

    private Boolean hasElectricEngine;
    private Engine engine;

    public void getDetails(){
        System.out.println("Number of seats: "+ numberOfSeats);
        System.out.println("Horsepower: "+horsepower);
        System.out.println("Price: "+price);
        System.out.println("Registration Number: "+registrationNumber);
        System.out.println("Fuel consumption: "+fuelConsumption);
        System.out.println("Precise fuel consumption: "+ isDamaged);
        System.out.println("Energy efficiency category: "+ energyEfficiencyCategory);
        System.out.println("Car's color: "+ hasElectricEngine);
        System.out.println("The engine this car has is: " + engine);
    }
}
