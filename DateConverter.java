import java.util.Scanner;

public class DateConverter {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char repeat;

        do {
            // Input month, day, and year
            System.out.print("Enter month: ");
            int month = scanner.nextInt();
            System.out.print("Enter day: ");
            int day = scanner.nextInt();
            System.out.print("Enter year: ");
            int year = scanner.nextInt();

            // Convert month number to name
            String monthName = "";
            switch (month) {
                case 1: monthName = "January"; break;
                case 2: monthName = "February"; break;
                case 3: monthName = "March"; break;
                case 4: monthName = "April"; break;
                case 5: monthName = "May"; break;
                case 6: monthName = "June"; break;
                case 7: monthName = "July"; break;
                case 8: monthName = "August"; break;
                case 9: monthName = "September"; break;
                case 10: monthName = "October"; break;
                case 11: monthName = "November"; break;
                case 12: monthName = "December"; break;
                default: monthName = "Invalid";
            }

            // Display formal date
            if (!monthName.equals("Invalid")) {
                System.out.println("Date: " + monthName + " " + day + ", " + year);
            } else {
                System.out.println("Invalid month entered.");
            }

            // Ask to repeat
            System.out.print("Do you want to continue? (y/n): ");
            repeat = scanner.next().charAt(0);
        } while (repeat == 'y' || repeat == 'Y');

        scanner.close();
    }
}