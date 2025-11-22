import java.io.*;
import java.util.List;

public class ReadEmployeeData {
    public static void main(String[] args) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("emp.dat"))) {
            @SuppressWarnings("unchecked")
            List<Employee> employees = (List<Employee>) ois.readObject();
            
            System.out.println("=== Employee Data from emp.dat ===");
            for (Employee emp : employees) {
                System.out.println("Name: " + emp.getName() + ", Net Income: " + emp.getNetIncome());
            }
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}