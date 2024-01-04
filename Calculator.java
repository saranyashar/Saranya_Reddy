import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {

    // Create frame
    private JFrame frame;

    // Create a textfield
    private JTextField textfield;

    // Store operator and operands
    private String operator;
    private double num1, num2, result;

    // Create constructor
    public Calculator() {
        // Set the operator to an empty string initially
        operator = "";

        // Create a frame
        frame = new JFrame("Calculator");

        // Create a textfield
        textfield = new JTextField();

        // Set the textfield to non-editable
        textfield.setEditable(false);

        // Create number buttons
        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
        }

        // Create operator buttons
        JButton addButton = new JButton("+");
        JButton subButton = new JButton("-");
        JButton mulButton = new JButton("*");
        JButton divButton = new JButton("/");
        JButton eqButton = new JButton("=");
        JButton clrButton = new JButton("C");

        // Add action listeners to operator buttons
        addButton.addActionListener(this);
        subButton.addActionListener(this);
        mulButton.addActionListener(this);
        divButton.addActionListener(this);
        eqButton.addActionListener(this);
        clrButton.addActionListener(this);

        // Create panel
        JPanel panel = new JPanel();

        // Set layout
        panel.setLayout(new GridLayout(4, 4, 10, 10));

        // Add components to panel
        panel.add(numberButtons[1]);
        panel.add(numberButtons[2]);
        panel.add(numberButtons[3]);
        panel.add(addButton);

        panel.add(numberButtons[4]);
        panel.add(numberButtons[5]);
        panel.add(numberButtons[6]);
        panel.add(subButton);

        panel.add(numberButtons[7]);
        panel.add(numberButtons[8]);
        panel.add(numberButtons[9]);
        panel.add(mulButton);

        panel.add(clrButton);
        panel.add(numberButtons[0]);
        panel.add(eqButton);
        panel.add(divButton);

        // Add textfield and panel to frame
        frame.add(textfield, BorderLayout.NORTH);
        frame.add(panel);

        // Set default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set frame size and visibility
        frame.setSize(400, 400);
        frame.setVisible(true);
    }

    // Handle button clicks
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Check if the button pressed is a number
        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9')) {
            textfield.setText(textfield.getText() + command);
        } else if (command.charAt(0) == 'C') {
            // Clear the textfield
            textfield.setText("");
            num1 = num2 = result = 0;
            operator = "";
        } else if (command.charAt(0) == '=') {
            // Perform calculation when "=" is pressed
            num2 = Double.parseDouble(textfield.getText());

            switch (operator) {
                case "+":
                    result = num1 + num2;
                    break;
                case "-":
                    result = num1 - num2;
                    break;
                case "*":
                    result = num1 * num2;
                    break;
                case "/":
                    if (num2 != 0) {
                        result = num1 / num2;
                    } else {
                        textfield.setText("Error");
                        return;
                    }
                    break;
            }

            // Display the result
            textfield.setText(String.valueOf(result));
            num1 = result;
            operator = "";
        } else {
            // Handle operator buttons
            if (!operator.isEmpty()) {
                return;
            }
            operator = command;
            num1 = Double.parseDouble(textfield.getText());
            textfield.setText("");
        }
    }

    // Main method to start the application
    public static void main(String[] args) {
        new Calculator();
    }
}
