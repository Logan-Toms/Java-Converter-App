import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * @Author: Logan Toms
 * @Date: 4/25/2023
 * This program is a converter that converts miles to kilometers and Fahrenheit to Celsius.
 */

 // Converter class
 // This class is the parent class for the TemperatureConverter and DistanceConverter classes.
class Converter { // Parent class
    // Private attribute for input of data type double
    private double input;

    // Default constructor with no parameter which sets input to Double.NaN [Not a Number] 
    public Converter() {
        input = Double.NaN;
    }

    // Overloaded constructor with input for parameter
    public Converter(double input) {
        this.input = input;
    }

    // Method convert() which returns input value
    public double convert() {
        return input;
    }
}

// TemperatureConverter class
// This class converts Fahrenheit to Celsius.
class TemperatureConverter extends Converter { // Child class

    // Constructors which call parent constructors
    public TemperatureConverter() {
        super();
    }


    public TemperatureConverter(double input) {
        super(input);
    }

    @Override // Convert Fahrenheit to Celsius
    // Overridden convert() method to convert input Fahrenheit temperature to Celsius and returns the value.
    public double convert() {
        // If the instance has no input value or not a number, it should return Double.NaN 
        if (Double.isNaN(super.convert())) {
            return Double.NaN;
        } else {
            // Use the following formula for conversion: C = ((F-32) * 5)/9
            double result = (super.convert() - 32) * 5 / 9;
            // Round the result to the first decimal point
            result = Math.round(result * 10.0) / 10.0;
            return result;
        }
    }
}

// DistanceConverter class
// This class converts miles to kilometers.
class DistanceConverter extends Converter { // Child class

    // Constructors which call parent constructors 
    public DistanceConverter() {
        super();
    }

    public DistanceConverter(double input) {
        super(input);
    }

    @Override // Convert miles to kilometers
    // Overridden convert() method to convert input distance in miles to distance in kilometers and returns the value.
    public double convert() {
        // If the instance has no input value or not a number, it should return Double.NaN
        if (Double.isNaN(super.convert())) {
            return Double.NaN;
        } else {
            // Used the following formula for conversion: KM = M * 1.609.
            double result = super.convert() * 1.609;
            // Round the result to the second decimal point
            result = Math.round(result * 100.0) / 100.0;
            return result;
        }
    }
}

// GUIConverter class
// This class creates the GUI for the converter.
// javax.swing.SwingUtilities is NOT used
class GUIConverter extends JFrame {
    private JButton distanceButton, temperatureButton, exitButton;

    // Constructor
    // GUIConverter class using JFrame and JPanel
    public GUIConverter() {
        setTitle("Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Window is centered in the computer screen
        setLocationRelativeTo(null);
    
        JLabel label = new JLabel("Please select which converter you would like:");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        add(label, BorderLayout.NORTH);
    
        Dimension buttonSize = new Dimension(130, 40); // Set same button size for all three buttons
        
        // GUI will have 3 buttons: Distance, Temperature, and Exit that generate prompts
        distanceButton = new JButton("Distance");
        distanceButton.addActionListener(new DistanceButtonListener());
        distanceButton.setPreferredSize(buttonSize);
        temperatureButton = new JButton("Temperature");
        temperatureButton.addActionListener(new TemperatureButtonListener());
        temperatureButton.setPreferredSize(buttonSize);
        exitButton = new JButton("Exit");
        exitButton.addActionListener(new ExitButtonListener());
        exitButton.setPreferredSize(buttonSize);
    
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Set space between buttons and panel edges
        buttonPanel.add(distanceButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Set space between buttons
        buttonPanel.add(temperatureButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(10, 0))); // Set space between buttons and panel edges
    
        JPanel exitButtonPanel = new JPanel();
        exitButtonPanel.setLayout(new BoxLayout(exitButtonPanel, BoxLayout.X_AXIS));
        exitButtonPanel.add(Box.createHorizontalGlue());
        exitButtonPanel.add(exitButton);
        exitButtonPanel.add(Box.createHorizontalGlue());
    
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(buttonPanel, BorderLayout.NORTH);
        mainPanel.add(exitButtonPanel, BorderLayout.CENTER);
    
        add(mainPanel, BorderLayout.CENTER);
        pack();
        setPreferredSize(new Dimension(300, 160)); // Set window size
        setSize(getPreferredSize());
    }


    /*
    * Action listeners
    * These classes are used to handle the button clicks.
    */

    // Action listener for the distance button
    //When user clicks Distance, an input dialog will pop up where user can type value and click OK
    private class DistanceButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // JOptionPane used to get input from the user
            String inputStr = JOptionPane.showInputDialog("Input distance in miles to convert to kilometers: ");
            if (inputStr != null) {
                // Once user clicks OK, message dialog will pop up. 
                try {
                    double input = Double.parseDouble(inputStr);
                    // Create a DistanceConverter object and call the convert method
                    DistanceConverter dc = new DistanceConverter(input);
                    double result = dc.convert();
                    // JOptionPane used to display the result
                    JOptionPane.showMessageDialog(null, input + " miles equals " + result + " kilometers.");
                } catch (NumberFormatException ex) {
                    // JOptionPane used to display an error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            } // After review of the result, the OK is clicked the program returns to the original window. 
        }
    }

    // Action listener for the temperature button
    private class TemperatureButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            // JOptionPane used to get input from the user
            // When user clicks on Temperature button, an input dialog will pop up to input value and click OK
            String inputStr = JOptionPane.showInputDialog("Input Fahrenheit temp to convert to Celsius: ");
            if (inputStr != null) {
                // when clicks OK, the message dialog with pop up with converted result
                try {
                    double input = Double.parseDouble(inputStr);
                    // Create a TemperatureConverter object and call the convert method
                    TemperatureConverter tc = new TemperatureConverter(input);
                    double result = tc.convert();
                    // JOptionPane used to display the result
                    JOptionPane.showMessageDialog(null, input + " F equals " + result + " C.");
                } catch (NumberFormatException ex) {
                    // JOptionPane used to display an error message
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a valid number.");
                }
            }
        }
    }

    // Action listener for the exit button
    // When user clicks Exit, the program will terminate
    private class ExitButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

}

// Main class
// This class is used as a driver for the program.
public class CMIS242ASG3LoganToms {
    public static void main(String[] args) throws Exception {
        // Create a GUIConverter object and make it visible
        GUIConverter gui = new GUIConverter();
        gui.setVisible(true);
    }
}
