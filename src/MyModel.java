import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyModel {
    private ArrayList<String> matchedLines = new ArrayList<>();

    //region: Lexical Analysis
    public boolean performLexicalAnalysis(File javaFile) {
        // Clear the existing matched lines
        matchedLines.clear();
        matchedLines = GetLines(javaFile);

        // Check if matchedLines is empty
        if (matchedLines.isEmpty()) {
            System.out.println("No matched lines found.");
            return true; // Return true when matchedLines is empty
        }

        // Check each line using the hasMatch method
        for (String line : matchedLines) {
            if (!hasMatchLexical(line)) {
                System.out.println("Line does not match the pattern: " + line);
                return false;
            }
        }

        // If all lines matched the pattern, return true
        return true;
    }

    private boolean hasMatchLexical(String line) {

        // Split the line into words
        String[] words = line.trim().split("\\s+");

        if(words.length > 4){
            return false;
        }
        if(words.length == 2 && isDataType(words[0]) && isValidVariableName(words[1].substring(0,words[1].length() - 1))){
            return true;
        }

        if (words.length >= 3 && isDataType(words[0]) && isValidVariableName(words[1]) && isAssignment(words[2])) {
            return true;
        }

        return false;
    }

    private boolean isValidVariableName(String variableName) {
        // Check if the variable name contains only valid characters
        if (variableName.isEmpty() || !isValidStartCharacter(variableName.charAt(0))) {
            return false;
        }

        for (int i = 1; i < variableName.length(); i++) {
            char currentChar = variableName.charAt(i);
            if (!isValidCharacter(currentChar)) {
                return false;
            }
        }

        return true;
    }

    private boolean isValidStartCharacter(char firstChar) {
        // Check if the first character is a letter, underscore (_), or dollar sign ($)
        return Character.isLetter(firstChar) || firstChar == '_';
    }

    private boolean isValidCharacter(char currentChar) {
        // Check if the character is a letter, digit, underscore (_), or dollar sign ($)
        return Character.isLetterOrDigit(currentChar) || currentChar == '_';
    }

    private boolean isAssignment(String word) {
        // Check if the word is an equals sign "=" with optional spaces
        return word.matches("\\s*=\\s*");
    }

    private boolean isDataType(String word) {
        return word.equals("int") || word.equals("float") || word.equals("double") || word.equals("char")
                || word.equals("boolean") || word.equals("byte") || word.equals("short")
                || word.equals("long");
    }
    //endregion

    //region Syntax Analysis
    public boolean performSyntaxAnalysis(File javaFile) {
        matchedLines.clear();
        matchedLines = GetLines(javaFile);

        // Check if matchedLines is empty
        if (matchedLines.isEmpty()) {
            System.out.println("No matched lines found.");
            return true; // Return true when matchedLines is empty
        }

        // Check each line using the hasMatch method
        for (String line : matchedLines) {
            if (!hasMatchSyntax(line)) {
                System.out.println("Line does not match the pattern: " + line);
                return false;
            }
        }

        // If all lines matched the pattern, return true
        return true;
    }
    public boolean hasMatchSyntax(String code) {
        // Check for missing data type
        if (code.matches("\\b\\w+\\s*=\\s*\\d+;")) {
            System.out.println("Syntax Error: Missing data type in variable declaration.");
            return false;
        }

        // Check for missing semicolon
        if (!code.matches(".*;\\s*$")) {
            System.out.println("Syntax Error: Missing semicolon at the end of the statement.");
            return false;
        }

        // Check for incorrect data type usage
        if (code.matches("\\b(int|float|double|char|boolean)\\s+\\w+\\s*=\\s*\".*\";")) {
            System.out.println("Syntax Error: Incorrect usage of data type.");
            return false;
        }

        // Check for incorrect assignment operator
        if (code.matches("\\b\\w+\\s*:=\\s*\\d+;")) {
            System.out.println("Syntax Error: Incorrect assignment operator.");
            return false;
        }
        // No syntax errors found
        return true;
    }

    //endregion

    //region Semantic Analysis
    public boolean performSemanticAnalysis(File javaFile) {
        matchedLines.clear();
        matchedLines = GetLines(javaFile);

        // Check if matchedLines is empty
        if (matchedLines.isEmpty()) {
            System.out.println("No matched lines found.");
            return true; // Return true when matchedLines is empty
        }

        // Check each line using the hasMatch method
        for (String line : matchedLines) {
            if (!hasMatchSemantic(line)) {
                System.out.println("Line does not match the pattern: " + line);
                return false;
            }
        }

        // If all lines matched the pattern, return true
        return true;
    }

    public boolean hasMatchSemantic(String code) {
        // Check for incompatible assignment
        if (!isCompatibleAssignment(code)) {
            System.out.println("Syntax Error: Incompatible assignment of value to data type.");
            return false;
        }
        // No semantic errors found
        return true;
    }

    public boolean isCompatibleAssignment(String code) {
        String[] dataTypes = {"int", "float", "double", "char", "boolean"};

        for (String dataType : dataTypes) {
            if (isDataTypeMatch(code, dataType) && !isAssignmentCompatible(code, dataType)) {
                System.out.println("Syntax Error: Incompatible assignment of value to " + dataType + " data type.");
                return false;
            }
        }

        return true;
    }

    private boolean isDataTypeMatch(String code, String dataType) {
        return code.matches("\\b" + dataType + "\\s+\\w+\\s*=.*;");
    }

    private boolean isAssignmentCompatible(String code, String dataType) {
        switch (dataType) {
            case "int":
                return code.matches("\\b" + dataType + "\\s+\\w+\\s*=\\s*\\d+;");
            case "float":
            case "double":
                return code.matches("\\b" + dataType + "\\s+\\w+\\s*=\\s*(\\d+\\.\\d+|\\d+\\.?\\d*|\\.\\d+);");
            case "char":
                return code.matches("\\b" + dataType + "\\s+\\w+\\s*=\\s*'.*';");
            case "boolean":
                return code.matches("\\b" + dataType + "\\s+\\w+\\s*=\\s*(true|false);");
            default:
                return false;
        }
    }

    //endregion
    public ArrayList<String> GetLines(File javaFile) {
        try {
            try (BufferedReader br = new BufferedReader(new FileReader(javaFile))) {
                String line;
                while ((line = br.readLine()) != null) {
                    System.out.println("scanned line:" + line);
                    if (scanLine(line)) {
                        // If the line meets the criteria, add it to the list
                        matchedLines.add(line);
                        System.out.println("added line :" + line + "to matchedlines");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        return matchedLines;
    }

    private boolean scanLine(String line) {
        // Your scanning logic goes here

        // Check if the line meets the criteria for adding to the list
        boolean meetsCriteria = !line.contains("{") &&
                !line.contains("}") &&
                !line.contains("(")&&
                !line.contains(")");


        // Return the result for this line
        return meetsCriteria;
    }

    private boolean containsDataTypeKeyword(String line) {
        // Check if the line contains a Java data type keyword
        String dataTypePattern = "\\b(?:int|double|float|char|boolean|long|short|byte)\\b";
        Pattern pattern = Pattern.compile(dataTypePattern);
        Matcher matcher = pattern.matcher(line);
        return matcher.find();
    }
}
