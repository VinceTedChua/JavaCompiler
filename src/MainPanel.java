import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;



public class MainPanel extends JPanel {

    ArrayList<JPanel> panelList;
    JLabel titleLabel;

    public MainPanel(int width, int height) {
        Color mainColor = new Color(27, 128, 0);
        int borderThickness = 4;
        Border border = BorderFactory.createLineBorder(mainColor, borderThickness);
        int borderPadding = 15;

        setPreferredSize(new Dimension(width * 2 / 3, height));
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        // Create a new FlowLayout with horizontal alignment
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);

        // Set the gap between components in the layout to 0
        flowLayout.setHgap(5);

        // Set the preferred size of each panel to be the same as the main panel width minus the total border padding, but with a fixed height of 40px
        Dimension panelSize = new Dimension((width * 2 / 3) - (2 * borderPadding) - (2 * borderThickness), 80);

        // Create a panel to hold all the individual panels
        JPanel innerPanel = new JPanel(new GridLayout(0, 1,0,10)); // Set the layout manager to GridLayout with one column
        innerPanel.setBackground(Color.BLACK);

        // Add a border to the inner panel
        innerPanel.setBorder(border);

        // Set the layout manager of the main panel to BorderLayout
        setLayout(new BorderLayout());

        // Add the scroll pane to the main panel and position it to the center
        add(innerPanel, BorderLayout.CENTER);

        // TITLE LABEL AND FOOTER LABEL ===============================================================================

        // Create a new panel to hold the title label
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.BLACK);

        // Create a new label for the title and add it to the title panel
        titleLabel = new JLabel("Java Compiler");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        titlePanel.add(titleLabel);

        // Create an empty border with padding for the top and bottom
        EmptyBorder titlePanelEmptyBorder = new EmptyBorder(15, 15, 15, 15);

        // Create a compound border with both the line border and empty border
        Border compoundBorder = BorderFactory.createCompoundBorder(border, titlePanelEmptyBorder);

        // Set the compound border for the title panel
        titlePanel.setBorder(compoundBorder);

        // Add margin at the bottom of the title panel
        titlePanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 0, 15, 0),  border));

        // Add the title panel to the main panel and position it at the top
        add(titlePanel, BorderLayout.NORTH);

        // Create a new panel to hold the footer label
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.BLACK);
        footerPanel.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Create a new label for the footer and add it to the footer panel
        JLabel footerLabel = new JLabel("Created By: ___________________________");
        footerLabel.setForeground(Color.WHITE);
        footerLabel.setFont(new Font("Monospaced", Font.BOLD, 18));
        footerPanel.add(footerLabel);

        // Add the footer panel to the main panel and position it to the bottom
        add(footerPanel, BorderLayout.SOUTH);
    }

    public ArrayList<JPanel> getPanels() {
        return this.panelList;
    }
}
