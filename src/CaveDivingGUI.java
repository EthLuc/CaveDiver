import javax.swing.*;
import java.awt.*;

/**
 * Creates the main window for the cave escape game. 
 * Let's the user enter a depth rating to find an escape route.
 */
public class CaveDivingGUI extends JFrame {
    private CaveComponent caveComponent;
    private JTextField depthField;
    private JButton escapeButton, newCaveButton;
    private JLabel statusLabel;
    
/**
 * Constructs the CaveDivingGUI.
 */
    public CaveDivingGUI() {
        setTitle("Cave Diver - Find a Escape Route");
        setLayout(new BorderLayout());

        
        statusLabel = new JLabel("The diver begins in the upper-left corner and escapes by reaching the lower-right corner");
        add(statusLabel, BorderLayout.NORTH);
        
        
        caveComponent = new CaveComponent();
        add(caveComponent, BorderLayout.CENTER);

        
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(new JLabel("Enter the diver's depth rating:"));
        depthField = new JTextField(5);
        bottomPanel.add(depthField);
        escapeButton = new JButton("Escape");
        newCaveButton = new JButton("New Cave");
        bottomPanel.add(escapeButton);
        bottomPanel.add(newCaveButton);
        add(bottomPanel, BorderLayout.SOUTH);

       
        escapeButton.addActionListener(e -> startEscape());
        newCaveButton.addActionListener(e -> caveComponent.generateNewCave());

       
        setSize(600, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }
/**
 * Starts the escape and returns if the escape is successful or not based on the user's entered depth.
 * Also handles errors if the user enters an invalid depth.
 */
    private void startEscape() {
        try {
            int depthRating = Integer.parseInt(depthField.getText());
            boolean escaped = caveComponent.findEscapeRoute(depthRating);
            if (escaped) {
                statusLabel.setText("Escape successful!");
            } else {
                JOptionPane.showMessageDialog(this, "No escape route found!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Please enter a valid depth rating.", "Input Error", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(CaveDivingGUI::new);
    }
}