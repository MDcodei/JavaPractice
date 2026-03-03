package intermediate;

class Loops{
    public static void main (String[] args){
        for (int i = 0; i < 8; i++) {
            System.out.println(i);
        }
        System.out.println("The sum is "+Sum(20,333));
    }

    public static String Sum(int i, int j){
        return ((i<j) ? String.valueOf(i+j) : "nothing because I is greater than J");
    }

    public final short Number_of_Wheels = 4;
    public final double calculateFuelConsumption (double distance, double fuelUsed ){
        return fuelUsed/distance * 100;
    }
}
