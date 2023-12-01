import javax.swing.*;
import javax.swing.border.AbstractBorder;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.ArrayList;

public class NavigationPanel extends JPanel {
    private ArrayList<JButton> buttons;
    public NavigationPanel(int width, int height) {
        Color navBorderColor = new Color(27, 128, 0);
        Border border = BorderFactory.createLineBorder(navBorderColor,3);

        setPreferredSize(new Dimension(width, height));
        setBackground(Color.BLACK);
        setLayout(new FlowLayout());
        setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(15, 15, 15, 15),  border));


        //LABEL
        JLabel label = new JLabel("<html><div style='text-align: center;'><i class='fas fa-icon'></i> Java Compiler <br> Syntax Analyzer </div></html>", JLabel.CENTER);
        label.setIconTextGap(20);
        label.setForeground(Color.WHITE); // Set the foreground color to white/ font
        label.setFont(new Font("Monospaced", Font.BOLD, 27));
        label.setBorder(BorderFactory.createCompoundBorder(
                new EmptyBorder(20,30 , 20, 30),
                new BorderExtension.BottomBorder(navBorderColor,3,25))
        );
        add(label);
        //BUTTONS ------------------------------
        Color buttonColor = Color.BLACK;
        Color buttonHoverColor = navBorderColor;

        String[] buttonLabels = {"Open File","Lexical Analysis","Syntax Analysis","Semantic Analysis","Clear"}; // String array
        buttons = new ArrayList<>();

        for (String btnLabel : buttonLabels) {
            JButton button = new JButton(btnLabel); // gagawa ng button
            button.setPreferredSize(new Dimension(220, 40)); // size ng button
            button.setForeground(Color.WHITE); // kulay ng font
            button.setBackground(buttonColor); // kulay ng background ng button

            button.setFocusPainted(false); //
            button.setBorderPainted(false);

            button.setFont(new Font("Monospaced", Font.PLAIN, 20));// font
            button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            button.setBorder(border); // border

            button.addMouseListener(new java.awt.event.MouseAdapter() {
                public void mouseEntered(java.awt.event.MouseEvent evt) {
                    button.setBackground(buttonHoverColor);
                    button.setForeground(Color.BLACK);
                }
                public void mouseExited(java.awt.event.MouseEvent evt) {
                    button.setBackground(buttonColor);
                    button.setForeground(Color.WHITE);
                }
            });

            buttons.add(button);
            add(button);
            }
    }
    public ArrayList<JButton> getButtons() {
        return this.buttons;
    }
}