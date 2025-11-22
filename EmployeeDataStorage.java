import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Employee implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private double netIncome;

    public Employee(String name, double netIncome) {
        this.name = name;
        this.netIncome = netIncome;
    }

    public String getName() {
        return name;
    }

    public double getNetIncome() {
        return netIncome;
    }

    @Override
    public String toString() {
        return String.format("Name: %-20s Net Income: %,.2f", name, netIncome);
    }
}

public class EmployeeDataStorage {
    private static final String FILE_NAME = "emp.dat";
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();
        
        System.out.println("=== Employee Data Entry ===");
        System.out.println("Enter employee data for your family members:");
        System.out.println("--------------------------------------------");
        
        // Pre-defined family members (as per your request)
        String[] familyMembers = {
            "Roland Espragera",
            "Liza D. Espragera",
            "Hannah Espragera",
            "Roland Espragera Jr"
        };
        
        // Collect data for each family member
        for (String familyMember : familyMembers) {
            System.out.println("\nEntering data for: " + familyMember);
            System.out.print("Enter net income for " + familyMember + ": ");
            
            double netIncome = 0;
            boolean validInput = false;
            
            while (!validInput) {
                try {
                    netIncome = scanner.nextDouble();
                    if (netIncome >= 0) {
                        validInput = true;
                    } else {
                        System.out.print("Please enter a non-negative income: ");
                    }
                } catch (Exception e) {
                    System.out.print("Invalid input. Please enter a valid number: ");
                    scanner.next(); // Clear invalid input
                }
            }
            
            employees.add(new Employee(familyMember, netIncome));
            System.out.println("Data saved for " + familyMember);
        }
        
        // Store data to binary file
        storeEmployeesToFile(employees);
        
        // Read and display stored data
        readAndDisplayEmployees();
        
        scanner.close();
    }
    
    private static void storeEmployeesToFile(List<Employee> employees) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(FILE_NAME))) {
            
            oos.writeObject(employees);
            System.out.println("\n Successfully stored " + employees.size() + 
                             " employee records to " + FILE_NAME);
            
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }
    
    private static void readAndDisplayEmployees() {
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(FILE_NAME))) {
            
            @SuppressWarnings("unchecked")
            List<Employee> storedEmployees = (List<Employee>) ois.readObject();
            
            System.out.println("\n=== Stored Employee Data ===");
            System.out.println("File: " + FILE_NAME);
            System.out.println("--------------------------------------------");
            
            double totalIncome = 0;
            for (Employee emp : storedEmployees) {
                System.out.println(emp);
                totalIncome += emp.getNetIncome();
            }
            
            System.out.println("--------------------------------------------");
            System.out.printf("Total Family Net Income: %,.2f%n", totalIncome);
            System.out.println("Total Employees: " + storedEmployees.size());
            
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}