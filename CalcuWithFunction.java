import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CalcuWithFunction extends JFrame implements ActionListener {
    
    // Calculator state
    private double currentNumber = 0;
    private double storedNumber = 0;
    private String currentOperator = "";
    private boolean newNumber = true;
    private double memory = 0;
    
    private JTextField display;
    
    public CalcuWithFunction() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Calculator - With Function");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        
        // Set smaller, compact size
        setPreferredSize(new Dimension(280, 380));
        
        JPanel mainPanel = new JPanel(new BorderLayout(5, 5));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(8, 8, 8, 8));
        mainPanel.setBackground(new Color(240, 240, 240));
        
        createMenuBar();
        display = createDisplay();
        JPanel buttonPanel = createButtonPanel();
        
        mainPanel.add(display, BorderLayout.NORTH);
        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    
    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setPreferredSize(new Dimension(0, 20));
        
        String[] menuNames = {"Calculator", "View", "Edit", "Help"};
        for (String name : menuNames) {
            JMenu menu = new JMenu(name);
            menu.setFont(new Font("Segoe UI", Font.PLAIN, 11));
            menuBar.add(menu);
        }
        
        setJMenuBar(menuBar);
    }
    
    private JTextField createDisplay() {
        JTextField displayField = new JTextField("0");
        displayField.setHorizontalAlignment(JTextField.RIGHT);
        displayField.setFont(new Font("Segoe UI", Font.BOLD, 16));
        displayField.setPreferredSize(new Dimension(0, 35));
        displayField.setEditable(false);
        displayField.setBackground(Color.WHITE);
        displayField.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(150, 150, 150)),
            BorderFactory.createEmptyBorder(0, 5, 0, 5)
        ));
        return displayField;
    }
    
    private JPanel createButtonPanel() {
        JPanel panel = new JPanel(new GridLayout(6, 5, 3, 3));
        panel.setBackground(new Color(240, 240, 240));
        
        // Button layout matching your specification
        String[] buttonLabels = {
            "MC", "MR", "MS", "M+", "M-",
            "CE", "C", "+", "√", "",
            "7", "8", "9", "/", "%",
            "4", "5", "6", "*", "1/x",
            "1", "2", "3", "-", "=",
            "0", ".", "+", "", ""
        };
        
        for (String label : buttonLabels) {
            JButton button;
            if (label.isEmpty()) {
                button = new JButton();
                button.setEnabled(false);
                button.setBackground(panel.getBackground());
            } else {
                button = createButton(label);
                button.addActionListener(this);
            }
            panel.add(button);
        }
        
        return panel;
    }
    
    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        button.setFocusPainted(false);
        button.setMargin(new Insets(2, 2, 2, 2));
        
        // Modern, flat design
        button.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createLineBorder(new Color(180, 180, 180)),
            BorderFactory.createEmptyBorder(4, 0, 4, 0)
        ));
        
        // Color scheme
        if (text.equals("=")) {
            button.setBackground(new Color(0, 120, 215));
            button.setForeground(Color.WHITE);
        } else if (isOperator(text) || text.equals("√") || text.equals("%") || text.equals("1/x")) {
            button.setBackground(new Color(250, 250, 250));
        } else if (isMemoryButton(text)) {
            button.setBackground(new Color(230, 230, 230));
            button.setFont(new Font("Segoe UI", Font.PLAIN, 10));
        } else if (text.equals("C") || text.equals("CE")) {
            button.setBackground(new Color(255, 230, 230));
        } else {
            button.setBackground(Color.WHITE);
        }
        
        return button;
    }
    
    // ACTION HANDLER
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        
        if (command.matches("[0-9]")) {
            handleNumberInput(command);
        }
        else if (command.equals(".")) {
            handleDecimalPoint();
        }
        else if (command.matches("[+\\-*/]")) {
            handleOperator(command);
        }
        else if (command.equals("=")) {
            calculateResult();
        }
        else if (command.equals("C")) {
            clearAll();
        }
        else if (command.equals("CE")) {
            clearEntry();
        }
        else if (command.equals("MC")) {
            memoryClear();
        }
        else if (command.equals("MR")) {
            memoryRecall();
        }
        else if (command.equals("MS")) {
            memoryStore();
        }
        else if (command.equals("M+")) {
            memoryAdd();
        }
        else if (command.equals("√")) {
            calculateSquareRoot();
        }
        else if (command.equals("%")) {
            calculatePercentage();
        }
        else if (command.equals("1/x")) {
            calculateReciprocal();
        }
    }
    
    // FUNCTIONALITY METHODS
    private void handleNumberInput(String number) {
        if (newNumber) {
            display.setText(number);
            newNumber = false;
        } else {
            String currentText = display.getText();
            if (currentText.equals("0")) {
                display.setText(number);
            } else {
                display.setText(currentText + number);
            }
        }
        currentNumber = Double.parseDouble(display.getText());
    }
    
    private void handleDecimalPoint() {
        if (newNumber) {
            display.setText("0.");
            newNumber = false;
        } else if (!display.getText().contains(".")) {
            display.setText(display.getText() + ".");
        }
    }
    
    private void handleOperator(String operator) {
        if (!currentOperator.isEmpty()) {
            calculateResult();
        }
        storedNumber = currentNumber;
        currentOperator = operator;
        newNumber = true;
    }
    
    private void calculateResult() {
        if (currentOperator.isEmpty()) return;
        
        double result = switch (currentOperator) {
            case "+" -> storedNumber + currentNumber;
            case "-" -> storedNumber - currentNumber;
            case "*" -> storedNumber * currentNumber;
            case "/" -> {
                if (currentNumber != 0) {
                    yield storedNumber / currentNumber;
                } else {
                    display.setText("Error");
                    currentOperator = "";
                    newNumber = true;
                    yield Double.NaN;
                }
            }
            default -> 0.0;
        };
        
        if (Double.isNaN(result)) return;
        
        display.setText(formatResult(result));
        currentNumber = result;
        currentOperator = "";
        newNumber = true;
    }
    
    private void calculateSquareRoot() {
        if (currentNumber >= 0) {
            double result = Math.sqrt(currentNumber);
            display.setText(formatResult(result));
            currentNumber = result;
        } else {
            display.setText("Error");
        }
        newNumber = true;
    }
    
    private void calculatePercentage() {
        double result = currentNumber / 100;
        display.setText(formatResult(result));
        currentNumber = result;
        newNumber = true;
    }
    
    private void calculateReciprocal() {
        if (currentNumber != 0) {
            double result = 1 / currentNumber;
            display.setText(formatResult(result));
            currentNumber = result;
        } else {
            display.setText("Error");
        }
        newNumber = true;
    }
    
    private void memoryStore() {
        memory = currentNumber;
        newNumber = true;
    }
    
    private void memoryRecall() {
        display.setText(formatResult(memory));
        currentNumber = memory;
        newNumber = true;
    }
    
    private void memoryClear() {
        memory = 0;
    }
    
    private void memoryAdd() {
        memory += currentNumber;
        newNumber = true;
    }
    
    private void clearAll() {
        currentNumber = 0;
        storedNumber = 0;
        currentOperator = "";
        display.setText("0");
        newNumber = true;
    }
    
    private void clearEntry() {
        currentNumber = 0;
        display.setText("0");
        newNumber = true;
    }
    
    private String formatResult(double result) {
        if (result == (long) result) {
            return String.format("%d", (long) result);
        } else {
            // Remove trailing zeros
            String formatted = String.format("%.10f", result);
            return formatted.replaceAll("0*$", "").replaceAll("\\.$", "");
        }
    }
    
    private boolean isOperator(String text) {
        return text.equals("+") || text.equals("-") || text.equals("*") || text.equals("/");
    }
    
    private boolean isMemoryButton(String text) {
        return text.equals("MC") || text.equals("MR") || text.equals("MS") || 
               text.equals("M+") || text.equals("M-");
    }
    
    public static void main(String[] args) {
        // Simple and safe main method
        SwingUtilities.invokeLater(() -> {
            new CalcuWithFunction().setVisible(true);
        });
    }
}