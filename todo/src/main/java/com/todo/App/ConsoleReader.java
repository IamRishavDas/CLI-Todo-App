package com.todo.App;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Scanner;

import com.todo.PatternMatcher.CommandPatternMatcher;
import com.todo.TaskOperations.TaskManagement;

public class ConsoleReader {
    public ConsoleReader() throws IOException {
        boolean exit = false;
        showWelcomeScreen();
        // TaskManagement.loadTasks();
        while (!exit) {
            String command = System.console().readLine(":");
            switch (CommandPatternMatcher.getCommandString(command)) {
                case "add" -> TaskManagement.addTask(command);
                case "show" -> TaskManagement.showTasks(command);
                case "mark" -> TaskManagement.markTask(command);
                case "remove" -> TaskManagement.removeTask(command);
                case "save" -> TaskManagement.saveTasks();
                case "load" -> TaskManagement.loadTasks();
                case "delete" -> TaskManagement.deleteSavedTasks();
                case "clear" -> TaskManagement.clearTerminal();
                case "exit" -> exit = true;
                default -> System.out.println("NOT SUITABLE COMMAND FOUND!!");
            }
        }
    }

    public void showWelcomeScreen() throws FileNotFoundException, IOException {

        ClassLoader classLoader = ConsoleReader.class.getClassLoader();

        try (InputStream inputStream = classLoader.getResourceAsStream("Welcome.txt")) {
            if (inputStream != null) {
                Scanner scanner = new Scanner(inputStream);
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    System.out.println(line);
                }
                scanner.close();
            } else {
                System.out.println("ERROR: WELCOME CONTENT IS MISSING!!");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
