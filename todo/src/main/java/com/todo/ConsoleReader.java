package com.todo;

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
    public void showWelcomeScreen() throws FileNotFoundException{
        final String filePath = "C:\\Users\\Risha\\Desktop\\CLI Todo App\\CLI-Todo-App\\todo\\src\\main\\resources\\";
        final String fileName = "Welcome.txt";
        File file = new File(filePath + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
