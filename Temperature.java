import java.util.Scanner;

public class Temperature {
    private double temperature;
    private char type; // 'C' for Celsius, 'F' for Fahrenheit

    public Temperature() {
        temperature = 0.0;
        type = 'C';
    }

    public Temperature(double temp, char t) {
        temperature = temp;
        type = Character.toUpperCase(t); // Preserve the input type
        // No immediate conversion; handle it in toFahrenheit() and toCelsius()
    }

    // Accessor method 1: Get temperature
    public double getTemperature() {
        return temperature;
    }

    // Accessor method 2: Get type
    public char getType() {
        return type;
    }

    // Mutator method 1: Set temperature
    public void setTemperature(double temp) {
        temperature = temp;
        // No conversion here; rely on type for conversion methods
    }

    // Mutator method 2: Set type and compute equivalent
    public void setType(char t) {
        t = Character.toUpperCase(t);
        if (t != type) {
            if (t == 'F' && type == 'C') {
                temperature = (temperature * 9 / 5) + 32; // Convert C to F
            } else if (t == 'C' && type == 'F') {
                temperature = (temperature - 32) * 5 / 9; // Convert F to C
            }
            type = t;
        }
    }

    // Method to compute equivalent Fahrenheit
    public double toFahrenheit() {
        if (type == 'C') {
            return (temperature * 9 / 5) + 32;
        }
        return temperature;
    }

    // Method to compute equivalent Celsius
    public double toCelsius() {
        if (type == 'F') {
            return (temperature - 32) * 5 / 9;
        }
        return temperature;
    }

    // Main method to test the class with user input
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // Introduction
        System.out.println("\nTemperature Conversion Program");
        System.out.println("----------------------------------------");
        System.out.println("Labaratory Exercise No. 7");
        System.out.println("Name : Espragera, Allyza Jane D. - IT2R5");
        System.out.println("----------------------------------------");

        System.out.print("Enter temperature value: ");
        double temp;
        try {
            temp = scanner.nextDouble();
        } catch (Exception e) {
            System.out.println("Invalid number entered. Please run again with a valid number.");
            scanner.close();
            return;
        }

        scanner.nextLine(); // Consume newline
        System.out.print("\nEnter temperature type (C for Celsius, F for Fahrenheit): ");
        String typeStr = scanner.nextLine().trim().toUpperCase();
        char type = (typeStr.length() > 0) ? typeStr.charAt(0) : 'C';

        if (type != 'C' && type != 'F') {
            System.out.println("Invalid type. Using default type C.");
            type = 'C';
        }

        Temperature tempObj = new Temperature(temp, type);
        System.out.println("\nResults:");
        System.out.println("Temperature: " + tempObj.getTemperature() + "°" + tempObj.getType());

        // Display only the equivalent based on input type
        if (type == 'C') {
            System.out.println("Equivalent Fahrenheit: " + String.format("%.2f", tempObj.toFahrenheit()) + "°F");
        } else if (type == 'F') {
            System.out.println("Equivalent Celsius: " + String.format("%.2f", tempObj.toCelsius()) + "°C");
        }

        scanner.close();
    }
}