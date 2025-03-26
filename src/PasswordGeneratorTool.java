import java.awt.*;
import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.datatransfer.*;
import java.io.*;
import java.util.*;
// Main class 
public class PasswordGeneratorTool extends JFrame {
    // Constructor to initialize the GUI
    public PasswordGeneratorTool() {
        setTitle("Password Generator"); 
        setSize(500, 300); 
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(10, 10)); 
        setLocationRelativeTo(null); 
        getContentPane().setBackground(Color.BLACK); 

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(2, 1, 10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        mainPanel.setBackground(Color.BLACK); 

        JButton generatePasswordButton = createStyledButton("Generate Password");
        JButton passwordStrengthCheckButton = createStyledButton("Password Strength Check");

        // action listeners to buttons
        generatePasswordButton.addActionListener(_ -> openPasswordGeneratorWindow());
        passwordStrengthCheckButton.addActionListener(_ -> openPasswordStrengthCheckWindow());

        mainPanel.add(generatePasswordButton);
        mainPanel.add(passwordStrengthCheckButton);

        add(mainPanel, BorderLayout.CENTER);
    }

    // Helper method to create styled buttons
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text) {
            @Override
            protected void paintComponent(Graphics g) {
                // Custom painting for rounded corners and neon border
                Graphics2D g2 = (Graphics2D) g.create();
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(getBackground());
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                g2.setColor(new Color(0, 255, 255));
                g2.setStroke(new BasicStroke(2)); 
                g2.drawRoundRect(1, 1, getWidth() - 3, getHeight() - 3, 20, 20); 
                g2.dispose();
                super.paintComponent(g);
            }
        };
        button.setFont(new Font("Arial", Font.BOLD, 14)); 
        button.setBackground(new Color(30, 30, 30)); 
        button.setForeground(new Color(0, 255, 255));
        button.setFocusPainted(false); 
        button.setBorder(BorderFactory.createEmptyBorder(15, 20, 15, 20));
        button.setContentAreaFilled(false); 
        button.setOpaque(false); 

        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                button.setBackground(new Color(50, 50, 50)); 
                button.setForeground(new Color(255, 0, 255)); 
            }
            @Override
            public void mouseExited(MouseEvent e) {
                button.setBackground(new Color(30, 30, 30)); 
                button.setForeground(new Color(0, 255, 255)); 
            }
        });

        return button;
    }

    private JCheckBox createStyledCheckBox(String text) {
        JCheckBox checkBox = new JCheckBox(text);
        checkBox.setFont(new Font("Arial", Font.PLAIN, 14)); 
        checkBox.setBackground(Color.BLACK); 
        checkBox.setForeground(new Color(0, 255, 255)); 
        checkBox.setFocusPainted(false); 
        checkBox.setOpaque(false); 

        checkBox.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                checkBox.setForeground(new Color(255, 0, 255)); 
            }
            @Override
            public void mouseExited(MouseEvent e) {
                checkBox.setForeground(new Color(0, 255, 255)); 
            }
        });
        return checkBox;
    }

    // Method to open the password generator window
    private void openPasswordGeneratorWindow() {
        JFrame generatorFrame = new JFrame("Generate Password");
        generatorFrame.setSize(500, 750); 
        generatorFrame.setLayout(new BorderLayout(10, 10)); 
        generatorFrame.getContentPane().setBackground(Color.BLACK); 
        generatorFrame.setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(8, 1, 10, 10)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); 
        mainPanel.setBackground(Color.BLACK);

        // Panel for password length input
        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.setBackground(Color.BLACK); 
        JLabel lengthLabel = new JLabel("Password Length:");
        lengthLabel.setForeground(new Color(0, 255, 255));
        lengthPanel.add(lengthLabel);
        JSpinner lengthSpinner = new JSpinner(new SpinnerNumberModel(8, 4, 32, 1)); 
        lengthSpinner.setFont(new Font("Arial", Font.PLAIN, 14)); 
        lengthSpinner.setBackground(new Color(30, 30, 30)); 
        lengthSpinner.setForeground(new Color(0, 255, 255)); 
        lengthPanel.add(lengthSpinner);
        mainPanel.add(lengthPanel);

        // Checkboxes for character types
        JCheckBox uppercaseCheckBox = createStyledCheckBox("Uppercase Letters (A-Z)");
        JCheckBox lowercaseCheckBox = createStyledCheckBox("Lowercase Letters (a-z)");
        JCheckBox numbersCheckBox = createStyledCheckBox("Numbers (0-9)");
        JCheckBox specialCharsCheckBox = createStyledCheckBox("Special Characters (!@#$%^&*)");
        mainPanel.add(uppercaseCheckBox);
        mainPanel.add(lowercaseCheckBox);
        mainPanel.add(numbersCheckBox);
        mainPanel.add(specialCharsCheckBox);

        // Button to generate password
        JButton generateButton = createStyledButton("Generate Password");
        JTextField passwordField = new JTextField();
        passwordField.setEditable(false); //if true password can be editable
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14)); 
        passwordField.setBackground(new Color(30, 30, 30));
        passwordField.setForeground(new Color(0, 255, 255));
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 255, 255)), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        generateButton.addActionListener(_ -> {
            // Generate password based on user input
            String password = generatePassword(
                    (int) lengthSpinner.getValue(),
                    uppercaseCheckBox.isSelected(),
                    lowercaseCheckBox.isSelected(),
                    numbersCheckBox.isSelected(),
                    specialCharsCheckBox.isSelected()
            );
            passwordField.setText(password); 
        });
        mainPanel.add(generateButton);
        mainPanel.add(passwordField);

        // Panel for copy and clear buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.setBackground(Color.BLACK); 
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        JButton copyButton = createStyledButton("Copy to Clipboard");
        copyButton.addActionListener(_ -> copyToClipboard(passwordField.getText())); // Copy password to clipboard
        JButton clearButton = createStyledButton("Clear");
        clearButton.addActionListener(_ -> passwordField.setText("")); // Clear password field
        buttonPanel.add(copyButton);
        buttonPanel.add(clearButton);
        mainPanel.add(buttonPanel);

        generatorFrame.add(mainPanel, BorderLayout.CENTER); 
        generatorFrame.setVisible(true); 
    }

    // Method to open the password strength check window
    private void openPasswordStrengthCheckWindow() {
        JFrame strengthCheckFrame = new JFrame("Password Strength Check");
        strengthCheckFrame.setSize(500, 300);
        strengthCheckFrame.setLayout(new GridLayout(4, 1, 10, 10));
        strengthCheckFrame.getContentPane().setBackground(Color.BLACK); 
        strengthCheckFrame.setLocationRelativeTo(null); 

        // Text field to display password
        JTextField passwordField = new JTextField();
        passwordField.setEditable(true); // if false password can not be editable
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14)); 
        passwordField.setBackground(new Color(30, 30, 30)); 
        passwordField.setForeground(new Color(0, 255, 255)); 
        passwordField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(0, 255, 255)), 
                BorderFactory.createEmptyBorder(5, 10, 5, 10) 
        ));
        strengthCheckFrame.add(passwordField);

        // Button to paste password from clipboard
        JButton pasteButton = createStyledButton("Paste from Clipboard");
        pasteButton.addActionListener(_ -> {
            try {
                Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
                String password = (String) clipboard.getData(DataFlavor.stringFlavor); // Get text from clipboard
                passwordField.setText(password); 
            } catch (UnsupportedFlavorException | IOException ex) {
                JOptionPane.showMessageDialog(strengthCheckFrame, "Failed to paste from clipboard."); 
            }
        });
        strengthCheckFrame.add(pasteButton);

        // Button to check password strength
        JButton checkStrengthButton = createStyledButton("Check Strength");
        checkStrengthButton.addActionListener(_ -> {
            String password = passwordField.getText();
            if (password.isEmpty()) {
                JOptionPane.showMessageDialog(strengthCheckFrame, "No password provided.");
            } else {
                String strengthMessage = calculatePasswordStrength(password); 
                JOptionPane.showMessageDialog(strengthCheckFrame, "Password Strength: " + strengthMessage); 
            }
        });
        strengthCheckFrame.add(checkStrengthButton);

        strengthCheckFrame.setVisible(true); 
    }

    // Method to generate a random password
    private String generatePassword(int length, boolean uppercase, boolean lowercase, boolean numbers, boolean specialChars) {
        StringBuilder password = new StringBuilder();
        String uppercaseLetters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String lowercaseLetters = "abcdefghijklmnopqrstuvwxyz";
        String numberChars = "0123456789";
        String specialCharacters = "!@#$%^&*";

        // Ensure at least one character of each selected type is included
        if (uppercase) password.append(getRandomChar(uppercaseLetters));
        if (lowercase) password.append(getRandomChar(lowercaseLetters));
        if (numbers) password.append(getRandomChar(numberChars));
        if (specialChars) password.append(getRandomChar(specialCharacters));

        // Combine all selected character types
        String allChars = "";
        if (uppercase) allChars += uppercaseLetters;
        if (lowercase) allChars += lowercaseLetters;
        if (numbers) allChars += numberChars;
        if (specialChars) allChars += specialCharacters;

        if (allChars.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select at least one character type.");
            return "";
        }

        // Fill the rest of the password with random characters
        Random random = new Random();
        while (password.length() < length) {
            int index = random.nextInt(allChars.length());
            password.append(allChars.charAt(index));
        }

        // Shuffle the password to ensure randomness
        char[] passwordArray = password.toString().toCharArray();
        for (int i = 0; i < passwordArray.length; i++) {
            int randomIndex = random.nextInt(passwordArray.length);
            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[randomIndex];
            passwordArray[randomIndex] = temp;
        }
        return new String(passwordArray);
    }

    // Helper method to get a random character from a string
    private char getRandomChar(String charSet) {
        Random random = new Random();
        int index = random.nextInt(charSet.length());
        return charSet.charAt(index);
    }

    // Method to copy text to the clipboard
    private void copyToClipboard(String text) {
        if (!text.isEmpty()) {
            StringSelection selection = new StringSelection(text);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(selection, null); // Copy text to clipboard
            JOptionPane.showMessageDialog(this, "Password copied to clipboard!"); 
        } else {
            JOptionPane.showMessageDialog(this, "No password generated yet."); 
        }
    }

    private String calculatePasswordStrength(String password) {
        int strength = 0;
        if (password.length() >= 8) strength++; 
        if (password.matches(".*[A-Z].*")) strength++; 
        if (password.matches(".*[a-z].*")) strength++; 
        if (password.matches(".*[0-9].*")) strength++; 
        if (password.matches(".*[!@#$%^&*].*")) strength++; 

        if (strength < 3) return "Weak";
        else if (strength < 5) return "Medium";
        else return "Strong";
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            PasswordGeneratorTool tool = new PasswordGeneratorTool();
            tool.setVisible(true);
        });
    }
}