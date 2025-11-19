import java.awt.*;
import javax.swing.*;

public class CalcuWithoutFunction extends JFrame {
    
    private JTextField display;
    
    public CalcuWithoutFunction() {
        initializeUI();
    }
    
    private void initializeUI() {
        setTitle("Calculator - No Function");
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
                // No action listener for non-functional version
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
            new CalcuWithoutFunction().setVisible(true);
        });
    }
}