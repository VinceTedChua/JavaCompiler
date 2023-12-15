import javax.swing.*;

public class Main {
    public static void main(String args[]){
        // Set the system look and feel
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Launch your Swing application
        SwingUtilities.invokeLater(MyWindow::new);
    }
}