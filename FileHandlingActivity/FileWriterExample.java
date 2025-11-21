import java.io.*;

public class FileWriterExample {
    // Create a PrintWriter stream as a member of the class
    private PrintWriter outStream;

    public FileWriterExample() {
        try {
            // Create PrintWriter that will overwrite existing file
            outStream = new PrintWriter(new FileWriter("ADE.txt", false));
            System.out.println("Successfully connected to ADE.txt file");
        } catch (IOException e) {
            System.out.println("Error creating file: " + e.getMessage());
        }
    }

    public void writeToFile(String text) {
        if (outStream != null) {
            outStream.println(text);
            System.out.println("Written to file: " + text);
        }
    }

    public void closeStream() {
        if (outStream != null) {
            outStream.close();
            System.out.println("File stream closed");
        }
    }

    public static void main(String[] args) {
        // Create instance of the class
        FileWriterExample fileWriter = new FileWriterExample();
        
        // Write some sample data to the file
        fileWriter.writeToFile("Hello, this is a test!");
        fileWriter.writeToFile("My initials are AJDE short for Allyza Jane Dungog Espragera");
        fileWriter.writeToFile("This file was created by the program");
        fileWriter.writeToFile("Current date: " + java.time.LocalDate.now());
        
        // Close the stream
        fileWriter.closeStream();
        
        System.out.println("\nProgram completed successfully!");
        System.out.println("Check the ADE.txt file in your project directory");
    }
}