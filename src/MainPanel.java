import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.util.*;

public class MainPanel extends JPanel {

    private ArrayList<JPanel> panelList;
    private JTextArea textArea;
    public JLabel titleLabel;
    public JLabel filenameLabel;



    public MainPanel(int width, int height) {
        Color mainColor = new Color(27, 128, 0);
        int borderThickness = 4;
        Border border = BorderFactory.createLineBorder(mainColor, borderThickness);
        int borderPadding = 15;

        setPreferredSize(new Dimension(width * 2 / 3, height));
        setBackground(Color.BLACK);
        setBorder(new EmptyBorder(15, 15, 15, 15));

        //region: Inner Panel
        JPanel innerPanel = new JPanel(new GridLayout(0, 1, 0, 10));
        innerPanel.setBackground(Color.BLACK);
        innerPanel.setBorder(border);
        setLayout(new BorderLayout());

        textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setBackground(Color.BLACK);
        textArea.setForeground(Color.WHITE);
        textArea.setFont(new Font("Monospaced", Font.PLAIN, 14));

        innerPanel.add(new JScrollPane(textArea));

        add(innerPanel, BorderLayout.CENTER);
        //endregion

        //region: Title Label
        JPanel titlePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        titlePanel.setBackground(Color.BLACK);
        titleLabel = new JLabel("Java Compiler");
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setFont(new Font("Monospaced", Font.BOLD, 20));
        titlePanel.add(titleLabel);
        EmptyBorder titlePanelEmptyBorder = new EmptyBorder(15, 15, 15, 15);
        Border compoundBorder = BorderFactory.createCompoundBorder(border, titlePanelEmptyBorder);
        titlePanel.setBorder(compoundBorder);
        titlePanel.setBorder(BorderFactory.createCompoundBorder(new EmptyBorder(0, 0, 15, 0), border));
        add(titlePanel, BorderLayout.NORTH);
        //endregion

        //region: Footer Label (File Name)
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        footerPanel.setBackground(Color.BLACK);
        footerPanel.setBorder(compoundBorder); // Use the same compound border as the title panel
        filenameLabel = new JLabel("File Name: ");
        filenameLabel.setForeground(Color.WHITE);
        filenameLabel.setFont(new Font("Monospaced", Font.PLAIN, 14));
        footerPanel.add(filenameLabel);
        add(footerPanel, BorderLayout.SOUTH);
        //endregion

    }

    public ArrayList<JPanel> getPanels() {
        return this.panelList;
    }

    public void setFileContent(String content) {
        textArea.setText(content);
    }

    public void setFileName(String fileName) {
        filenameLabel.setText("File Name: " + fileName);
    }

    public void setTitleMessage(String message, Color color) {
        titleLabel.setText(message);
        titleLabel.setForeground(color);
    }

}
