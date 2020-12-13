package interpreter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Interpreter {
    public static final String commandList = "Use syntax help+command_name\n" +
            "stop help var let print show";
    public static final String reserved = commandList.substring(commandList.indexOf("\n"));

    public static void main(String[] args) {
        Interpreter interpreter = new Interpreter();
        interpreter.launch();
    }

    int line = 0;
    boolean running = true;

    private void launch() {
        HashMap<String, Object> variables = new HashMap<>();
        ArrayList<String> variablesNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        File codeFile = new File("last_code.txt");
        FileWriter writer = null;

        try {
            writer = new FileWriter(codeFile);
            System.out.println("Writing code into " + codeFile.getName());
        } catch (IOException exception) {
            System.out.println("File does not exit");
        }
        while (running) {
            System.out.print("in[" + line + "] ");
            String command = scanner.nextLine();
            ArrayList<String> commands = new ArrayList<>();
            try {
                writer.write("in[" + line + "] " + command);
            } catch (IOException ignored) {}
            do {
                commands.add(
                        command.substring(0, (command.contains(" ")?command.indexOf(" "):command.length()))
                );
                command = command.replace(command.substring(0,(command.contains(" ")?command.indexOf(" ")+1:command.length())),"");
            } while (!command.equals(""));
            switch (commands.get(0)) {
                case "stop" -> {
                    running = false;
                    out("Process finished with exit code 0");
                }
                case "help" -> {
                    if (commands.size()==1) {
                        out(commandList);
                    } else {
                        switch (commands.get(1)) {
                            case "help" -> out("Getting help with a reserved keyword, Use help+command_name");
                            case "stop" -> out("Stop the console");
                            case "let" -> out("Assign a value to a variable, Use let name value");
                            case "var" -> out("Declare a variable. Types are int, float, char, string, Use var type name");
                            case "print" -> out("Print a variable");
                            case "show" -> out("Show all the declared variable");
                            default -> out("This command is undefined");
                        }
                    }
                }

                case "var" -> {
                    String key = commands.get(2);
                    if (!reserved.contains(key)) switch (commands.get(1)) {
                        case "int" -> variables.put(key,new Integer(0) );
                        case "float" ->variables.put(key, new Float(0));
                        case "char" -> variables.put(key, new Character('0'));
                        case "string" -> variables.put(key,new String(""));
                    }
                    variablesNames.add(key);
                }
                case "let" -> {
                    if (!reserved.contains(commands.get(1))) {
                        Object variable = commands.get(2);
                        if (commands.get(1) instanceof String) variable = variables.replace("+"," ");
                        variables.put(commands.get(1), variable);
                    }
                }
                case "print" -> {
                    if (commands.size() == 2) {
                    if (variables.containsKey(commands.get(1))) {
                        out(String.valueOf(variables.get(commands.get(1))));
                    } else {
                        out(String.valueOf(commands.get(1)));
                    }} else out("error,not find 2 argument");
                }
                case "show" -> {
                    for(String s: variablesNames) {
                        out(s+" = "+ String.valueOf(variables.get(s)));
                    }
                }
                default -> out("Unknow command");
            } line++;
        }
    }
    private void out (String message) {
        System.out.println("out[" + line + "] " + message);
    }
}