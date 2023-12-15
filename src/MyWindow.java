import javax.swing.*;

public class MyWindow extends JFrame {

    private MyModel model;
    private MyViewPanel view;
    private MyController controller;
    public MyWindow(){ // 0 width 0 height
        super("My Window"); // Set the window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        model = new MyModel();
        view = new MyViewPanel(model, 1000, 500);
        controller = new MyController(model, view);

        getContentPane().add(view);
        pack();
        setLocationRelativeTo(null); // Center the window
        setResizable(false); // Make the window not resizable
        setVisible(true); // Show the window
    }
}
