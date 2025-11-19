// Scanner
import java.util.Scanner;

// Class using my Family Name
public class Espragera {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int num1, num2, sum, modulo, quotient, highest;

        // Input
        System.out.print("Enter the first number: ");
        num1 = scanner.nextInt();
        System.out.print("Enter the second number: ");
        num2 = scanner.nextInt();

        // Calculations
        sum = num1 + num2;
        modulo = num1 % num2;
        quotient = num1 / num2;
        highest = (num1 > num2) ? num1 : num2;

        // Output
        System.out.println("Sum: " + sum);
        System.out.println("Modulo: " + modulo);
        System.out.println("Highest number: " + highest);
        System.out.println("Quotient: " + quotient);

        scanner.close();
    }
}