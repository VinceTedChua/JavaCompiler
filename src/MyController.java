import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

public class MyController {
    private MyModel model;
    private MyView view;

    public MyController(MyModel model, MyView view) {
        this.model = model;
        this.view = view;

        ArrayList<JButton> buttons = view.getButtons();
        ArrayList<JPanel> panels = view.getPanels();
        JLabel headerMainPanel = view.getTitle();

        for (JButton button : buttons) {
            button.addActionListener((ActionEvent e) -> {
                switch (button.getText()) {
                    case "Open File":
                        // Add code for "Open File" action
                        System.out.println("Open File button");
                        break;
                    case "Lexical Analysis":
                        System.out.println("Clicked Lexical Analysis button");
                        // Add code for Lexical Analysis
                        break;
                    case "Syntax Analysis":
                        System.out.println("Clicked Syntax Analysis button");
                        // Add code for Syntax Analysis
                        break;
                    case "Semantic Analysis":
                        System.out.println("Clicked Semantic Analysis button");
                        // Add code for Semantic Analysis
                        break;
                    case "Clear":
                        System.out.println("Clicked Clear button");
                        removeAllComponents(panels);
                        break;
                }
            });

        }
    }
    public static void removeAllComponents(ArrayList<JPanel> panels) {
        for (JPanel panel : panels) {
            JPanel topPanel = (JPanel) panel.getComponent(0);
            JPanel botPanel = (JPanel) panel.getComponent(1);
            JLabel botLabel = (JLabel) botPanel.getComponent(0);

            topPanel.removeAll();

            //GUI
            panel.revalidate();
            panel.repaint();

            botLabel.setText("");
        }
    }
}
