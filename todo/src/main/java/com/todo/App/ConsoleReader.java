package com.todo.App;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import com.todo.PatternMatcher.CommandPatternMatcher;
import com.todo.TaskOperations.TaskManagement;

public class ConsoleReader {
    public ConsoleReader() throws IOException{
        boolean exit = false;
        showWelcomeScreen();
        // TaskManagement.loadTasks();
        while (!exit) {
            String command = System.console().readLine(":");
            switch (CommandPatternMatcher.getCommandString(command)) {
                case "add"     -> TaskManagement.addTask(command);
                case "show"    -> TaskManagement.showTasks(command);
                case "mark"    -> TaskManagement.markTask(command);
                case "remove"  -> TaskManagement.removeTask(command);
                case "save"    -> TaskManagement.saveTasks();
                case "load"    -> TaskManagement.loadTasks();
                case "delete"  -> TaskManagement.deleteSavedTasks();
                case "clear"   -> TaskManagement.clearTerminal();
                case "exit"    -> exit = true;         
                default        -> System.out.println("NOT SUITABLE COMMAND FOUND!!");
            }
        }
    }
    public void showWelcomeScreen() throws FileNotFoundException, IOException{

        final String filePath = System.getProperty("user.dir") + File.separator + "todo" + File.separator + "src" + File.separator + "main" + File.separator +"java" + File.separator + "com" + File.separator + "todo"  + File.separator + "UtilityFiles" + File.separator + "Welcome.txt";

        File welcomeFile = new File(filePath);

        Scanner scanner = new Scanner(welcomeFile);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
