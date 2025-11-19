import java.util.Scanner;

public class RectangleObjects {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        // Get input for first rectangle
        System.out.println("First Rectangle:");
        System.out.print("Enter width: ");
        double width1 = input.nextDouble();
        System.out.print("Enter height: ");
        double height1 = input.nextDouble();

        // Get input for second rectangle
        System.out.println("\nSecond Rectangle:");
        System.out.print("Enter width: ");
        double width2 = input.nextDouble();
        System.out.print("Enter height: ");
        double height2 = input.nextDouble();

        // Create two Rectangle objects
        Rectangle rect1 = new Rectangle(width1, height1);
        Rectangle rect2 = new Rectangle(width2, height2);

        // Display results for Rectangle 1
        System.out.println("\nRectangle 1 Results:");
        System.out.println("Width: " + rect1.getWidth());
        System.out.println("Height: " + rect1.getHeight());
        System.out.println("Area: " + rect1.getArea());
        System.out.println("Perimeter: " + rect1.getPerimeter());

        // Display results for Rectangle 2
        System.out.println("\nRectangle 2 Results:");
        System.out.println("Width: " + rect2.getWidth());
        System.out.println("Height: " + rect2.getHeight());
        System.out.println("Area: " + rect2.getArea());
        System.out.println("Perimeter: " + rect2.getPerimeter());

        input.close();
    }
}

class Rectangle {
    private final double width;
    private final double height;

    // Constructor
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }

    // Getter for width
    public double getWidth() {
        return width;
    }

    // Getter for height
    public double getHeight() {
        return height;
    }

    // Calculate area
    public double getArea() {
        return width * height;
    }

    // Calculate perimeter
    public double getPerimeter() {
        return 2 * (width + height);
    }
}