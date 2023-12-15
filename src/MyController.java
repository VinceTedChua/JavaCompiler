import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class MyController {
    private MyModel model;
    private MyViewPanel view;
    private File selectedFile; // Declare selectedFile at the class level

    public MyController(MyModel model, MyViewPanel view) {
        this.model = model;
        this.view = view;

        ArrayList<JButton> buttons = view.getButtons();
        ArrayList<JPanel> panels = view.getPanels();
        JLabel headerMainPanel = view.getTitle();

        for (JButton button : buttons) {
            button.addActionListener((ActionEvent e) -> handleButtonClick(button.getText()));
        }
    }

    private boolean lexicalAnalysisPassed = false;
    private boolean syntaxAnalysisPassed = false;

    private void handleButtonClick(String buttonText) {
        switch (buttonText) {
            case "Open File":
                handleOpenFileAction();
                break;
            case "Lexical Analysis":
                if (selectedFile != null) {
                    handleLexicalAnalysisAction();
                } else {
                    JOptionPane.showMessageDialog(null, "Please open a file first.", "File Not Selected", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "Syntax Analysis":
                if (selectedFile != null && lexicalAnalysisPassed) {
                    handleSyntaxAnalysisAction();
                } else {
                    JOptionPane.showMessageDialog(null, "Please perform lexical analysis first or the Lexical Analysis should be passed.", "Analysis Not Completed", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "Semantic Analysis":
                if (selectedFile != null && lexicalAnalysisPassed && syntaxAnalysisPassed) {
                    handleSemanticAnalysisAction();
                } else {
                    JOptionPane.showMessageDialog(null, "Please perform lexical and syntax analyses first or the Lexical and syntax Analysis should be passed.", "Analysis Not Completed", JOptionPane.INFORMATION_MESSAGE);
                }
                break;
            case "Clear":
                handleClearAction();
                break;
        }
    }

    private void handleOpenFileAction() {
        JFileChooser fileChooser = new JFileChooser();

        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Java Files (*.java)", "java"));

        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            selectedFile = fileChooser.getSelectedFile(); // Assign the selected file to the class variable

            if (selectedFile.getName().toLowerCase().endsWith(".java")) {
                // Now you have the selected .java file
                // You can perform actions specific to .java files here
                try {
                    view.SetFileName(selectedFile.getName());
                    readAndPrintJavaFileContents(selectedFile);
                } catch (Exception e) {
                    handleFileReadError(e);
                }
            } else {
                handleInvalidFileExtensionError();
            }
        }
    }

    private void handleLexicalAnalysisAction() {
        System.out.println("Clicked Lexical Analysis button");
        lexicalAnalysisPassed = model.performLexicalAnalysis(selectedFile);

        // Update the title message based on the lexical analysis result
        if (lexicalAnalysisPassed) {
            view.SetTitleMessage("Lexical Analysis Passed", Color.GREEN);
        } else {
            view.SetTitleMessage("Lexical Analysis Failed", Color.RED);
        }
    }

    private void handleSyntaxAnalysisAction() {
        System.out.println("Clicked Syntax Analysis button");
        syntaxAnalysisPassed = model.performSyntaxAnalysis(selectedFile);

        // Update the title message based on the syntax analysis result
        if (syntaxAnalysisPassed) {
            view.SetTitleMessage("Syntax Analysis Passed", Color.GREEN);
        } else {
            view.SetTitleMessage("Syntax Analysis Failed", Color.RED);
        }
    }

    private void handleSemanticAnalysisAction() {
        System.out.println("Clicked Semantic Analysis button");
        boolean semanticAnalysisPassed = model.performSemanticAnalysis(selectedFile);
        // Update the title message based on the lexical analysis result
        if (semanticAnalysisPassed) {
            view.SetTitleMessage("Semantic Analysis Passed", Color.GREEN);
        } else {
            view.SetTitleMessage("Semantic Analysis Failed", Color.RED);
        }
    }

    private void handleClearAction() {
        // Clear selected file
        selectedFile = null;

        // Reset analysis flags
        lexicalAnalysisPassed = false;
        syntaxAnalysisPassed = false;

        view.SetFileContent("");
        view.SetTitleMessage("Open New File",Color.WHITE);
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

    //read and print
    private void readAndPrintJavaFileContents(File javaFile) {
        try (BufferedReader reader = new BufferedReader(new FileReader(javaFile))) {
            StringBuilder content = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                content.append(line).append("\n");
            }

            // Set the content to the JTextArea in MainPanel
            view.SetFileContent(content.toString());

        } catch (Exception e) {
            handleFileReadError(e);
        }
    }

    //
    private void handleFileReadError(Exception e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(null, "Error reading file: " + e.getMessage(), "File Read Error", JOptionPane.ERROR_MESSAGE);
    }

    private void handleInvalidFileExtensionError() {
        JOptionPane.showMessageDialog(null, "Please select a .java file.", "Invalid File", JOptionPane.ERROR_MESSAGE);
    }
}
