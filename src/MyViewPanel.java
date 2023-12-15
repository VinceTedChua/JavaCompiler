import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class MyViewPanel extends JPanel {
    private static final int NAVIGATION_PANEL_WIDTH_FACTOR = 3;
    private MyModel model;
    private int width;
    private int height;

    public MyViewPanel(MyModel model, int width, int height) {
        this.model = model;
        this.width = width;
        this.height = height;
        setPreferredSize(new Dimension(width, height));


        //Create a navigation panel that takes up 1/3 of the width of the MyView panel
        JPanel navigationPanel = new NavigationPanel(this.width/NAVIGATION_PANEL_WIDTH_FACTOR,this.height);

        // Create a main panel that takes up 2/3 of the width of the MyView panel
        MainPanel mainPanel = new MainPanel(this.width, this.height);


        // Add the navigation panel to the left side of the MyView panel
        setLayout(new BorderLayout());
        setBackground(Color.black);
        add(navigationPanel, BorderLayout.WEST);

        // Add the main panel to the right side of the MyView panel
        add(mainPanel, BorderLayout.CENTER);
    }

    public ArrayList<JButton> getButtons() {
        NavigationPanel navigationPanel = (NavigationPanel) getComponent(0);
        ArrayList<JButton> buttons = navigationPanel.getButtons();

        return buttons;
    }
    public ArrayList<JPanel> getPanels(){
         MainPanel mainPanel = (MainPanel) getComponent(1);
         ArrayList<JPanel> panelList = mainPanel.getPanels();

         return panelList;

    }

    public JLabel getTitle(){
        MainPanel mainPanel = (MainPanel) getComponent(1);
        return mainPanel.titleLabel;
    }
    public void SetFileContent(String content){
        MainPanel mainPanel = (MainPanel) getComponent(1);
        mainPanel.setFileContent(content);
    }
    public void SetFileName(String fileName){
        MainPanel mainPanel = (MainPanel) getComponent(1);
        mainPanel.setFileName(fileName);
    }
    public void SetTitleMessage(String message, Color color){
        MainPanel mainPanel = (MainPanel) getComponent(1);
        mainPanel.setTitleMessage(message,color);
    }

}
