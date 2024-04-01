package com.todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class ConsoleReader {
    public ConsoleReader() throws FileNotFoundException{
        boolean exit = false;
        showWelcomeScreen();
        while (!exit) {
            String command = System.console().readLine(":");
            switch (CommandPatternMatcher.getCommandString(command)) {
                case "add"     -> TaskManagement.addTask(command);
                case "show"    -> TaskManagement.showTasks(command);
                case "mark"    -> TaskManagement.markTask(command);
                case "remove"  -> TaskManagement.removeTask(command);
                case "exit"    -> exit = true;         
                default        -> System.out.println("NOT SUITABLE COMMAND FOUND!!");
            }
        }
    }
    public void showWelcomeScreen() throws FileNotFoundException{
        String filePath = "C:\\Users\\Risha\\Desktop\\CLI Todo App\\CLI-Todo-App\\todo\\src\\main\\resources\\";
        String fileName = "Welcome.txt";
        File file = new File(filePath + fileName);
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            System.out.println(scanner.nextLine());
        }
        scanner.close();
    }
}
