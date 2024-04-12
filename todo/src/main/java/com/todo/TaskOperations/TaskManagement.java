package com.todo.TaskOperations;

import java.util.List;

import com.todo.PatternMatcher.AttributePatternMatcher;
import com.todo.Syntax.AttributeSyntax;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskManagement {

    private static List<Task> tasks = new ArrayList<Task>();

    public List<Task> getTasks() {
        return TaskManagement.tasks;
    }

    public static void saveTasks() {

        final String filePath = System.getProperty("user.dir") + File.separator + "todo" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        final String fileName = "Store.dat";

        try {

            FileOutputStream fileOutputStream = new FileOutputStream(filePath + fileName); // Store.dat File
            ObjectOutputStream out = new ObjectOutputStream(fileOutputStream);

            out.writeObject(tasks);

            out.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @SuppressWarnings("unchecked")
    public static void loadTasks() {

        final String filePath = System.getProperty("user.dir") + File.separator + "todo" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        final String fileName = "Store.dat";

        try {

            FileInputStream fileInputStream = new FileInputStream(filePath + fileName); // Store.dat File
            ObjectInputStream in = new ObjectInputStream(fileInputStream);

            tasks = (ArrayList<Task>) in.readObject();

            in.close();
            fileInputStream.close();
        } catch (EOFException eof) {
            System.out.println("WARNING: PREVIOUS SESSION DATA IS NOT STORED!!");
        } catch (FileNotFoundException e) {
            System.out.println("THE PREVIOUS SESSION FILE IS MISSING!!");
        } catch (IOException e) {
            System.out.println("WARNING: SOMETHING WRONG IS HAPPENING I DON'T KNOW WHAT IS THE ACTUAL PROBLEM");
        } catch (ClassNotFoundException ex) {
            System.out.println("WARING: THE FOLLOWING CLASS IS NOT FOUND!!");
        }

        return;
    }

    public static void deleteSavedTasks() {

        final String filePath = System.getProperty("user.dir") + File.separator + "todo" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        final String fileName = "Store.dat";

        File datFile = new File(filePath + fileName); // Store.dat File

        if (datFile.exists()) {

            boolean deleted = datFile.delete();
            if (deleted) {
                System.out.println("PREVIOUS SESSION DELETED SUCCESSFULLY!!");
            } else {
                System.out.println("FAILED TO DELETE THE PREVIOUS SESSION!!");
            }
        } else {
            System.out.println("WARNING: FILE NOT EXIST!!");
        }
        createNewDatFile();
        return;
    }

    private static void createNewDatFile() {

        final String filePath = System.getProperty("user.dir") + File.separator + "todo" + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
        final String fileName = "Store.dat";

        File newDatFile = new File(filePath + fileName);

        try {
            boolean created = newDatFile.createNewFile();
            if (created) {
                System.out.println("NEW SESSION FILE CREATE SUCCESSFULLY!!");
            } else {
                System.out.println("WARNING: PREVIOUS SESSION IS NOT DELETED");
            }
        } catch (IOException e) {
            System.out.println("ERROR: WHILE CREATING NEW SESSION FILE -> " + e.getMessage());
        }
        return;
    }

    public static void addTask(String command) {
        String taskName = "";
        boolean isTaskNameFound = false;

        for (int i = 0; i < command.length() && !isTaskNameFound; i++) {
            if (command.charAt(i) == '\"') {
                for (int startParsingName = i + 1; startParsingName < command.length(); startParsingName++) {
                    if (command.charAt(startParsingName) == '\"')
                        break;
                    taskName += command.charAt(startParsingName);
                }
                isTaskNameFound = true;
            }
        }

        if (!isTaskNameFound) {
            System.out.println("ERROR: CHECK THE COMMAND SYNTAX!! NAME NOT FOUND");
            return;
        }

        String taskDescription = "";
        boolean isDescriptionFound = false;

        if (AttributePatternMatcher.getAttributeString(command, AttributeSyntax.DESCRIPTION)
                .equals(AttributeSyntax.DESCRIPTION.getAttributeName())) {
            int descriptonIndex = command.indexOf(AttributeSyntax.DESCRIPTION.getAttributeName());
            for (int i = descriptonIndex; i < command.length() && !isDescriptionFound; i++) {
                if (command.charAt(i) == '\"') {
                    for (int parseDescription = i + 1; parseDescription < command.length(); parseDescription++) {
                        if (command.charAt(parseDescription) == '\"')
                            break;
                        taskDescription += command.charAt(parseDescription);
                    }
                    isDescriptionFound = true;
                }
            }
        }

        String taskPriorityString = "";
        boolean isPriorityFound = false;

        if (AttributePatternMatcher.getAttributeString(command, AttributeSyntax.PRIORITY)
                .equals(AttributeSyntax.PRIORITY.getAttributeName())) {
            int priorityIndex = command.indexOf(AttributeSyntax.PRIORITY.getAttributeName());
            for (int i = priorityIndex; i < command.length() && !isPriorityFound; i++) {
                if (command.charAt(i) == '\"') {
                    for (int parsePriority = i + 1; parsePriority < command.length(); parsePriority++) {
                        if (command.charAt(parsePriority) == '\"')
                            break;
                        taskPriorityString += command.charAt(parsePriority);
                    }
                    isPriorityFound = true;
                }
            }
        }

        TaskPriority taskPriority = isIn(taskPriorityString);

        if (isTaskNameFound && isDescriptionFound && isPriorityFound) {
            tasks.add(new Task(taskName, taskDescription, taskPriority));
            return;
        } else if (isTaskNameFound && isDescriptionFound) {
            tasks.add(new Task(taskName, taskDescription));
            return;
        } else if (isTaskNameFound && isPriorityFound){
            tasks.add(new Task(taskName, taskPriority));
            return;
        } else if (isTaskNameFound) {
            tasks.add(new Task(taskName));
            return;
        }

    }

    private static TaskPriority isIn(String input) {
        if (input != null) {
            input = input.toUpperCase();

            if (input.equals("MEDIUM"))
                return TaskPriority.MEDIUM;
            else if (input.equals("HIGH"))
                return TaskPriority.HIGH;
            else
                return TaskPriority.LOW;
        } else
            return TaskPriority.LOW;
    }

    public static void removeTask(String command) {
        String taskName = "";
        boolean isTaskNameFound = false;

        for (int i = 0; i < command.length() && !isTaskNameFound; i++) {
            if (command.charAt(i) == '\"') {
                for (int startParsingName = i + 1; startParsingName < command.length(); startParsingName++) {
                    if (command.charAt(startParsingName) == '\"')
                        break;
                    taskName += command.charAt(startParsingName);
                }
                isTaskNameFound = true;
            }
        }

        if (!isTaskNameFound) {
            System.out.println("ERROR: CHECK THE COMMAND SYNTAX!! TASK NAME NOT FOUND");
            return;
        }

        Iterator<Task> it = tasks.iterator();
        while(it.hasNext()){
            Task task = it.next();
            if(task.getName().equals(taskName)){
                it.remove();
            }
        }
    }

    public static void markTask(String command) {
    
        String taskName = "";
        boolean isTaskNameFound = false;

        for (int i = 0; i < command.length() && !isTaskNameFound; i++) {
            if (command.charAt(i) == '\"') {
                for (int startParsingName = i + 1; startParsingName < command.length(); startParsingName++) {
                    if (command.charAt(startParsingName) == '\"')
                        break;
                    taskName += command.charAt(startParsingName);
                }
                isTaskNameFound = true;
            }
        }

        if (!isTaskNameFound) {
            System.out.println("ERROR: CHECK THE COMMAND SYNTAX!! NAME NOT FOUND");
            return;
        }

        String status = command.substring(command.indexOf(' ', command.indexOf("-s")) + 1, command.length());
        status = status.toUpperCase();

        TaskStatus taskStatus = getStatus(status);
        Iterator<Task> it = tasks.iterator();
        while(it.hasNext()){
            Task task = it.next();
            if(task.getName().equals(taskName)){
                task.setStatus(taskStatus);
            }
        }
    }

    private static TaskStatus getStatus(String status) {
        status = status.toUpperCase();
        if(status.equals("COMPLETED") || status.equals("\"COMPLETED\""))         return TaskStatus.COMPLETED;
        else if(status.equals("INCOMPLETE") || status.equals("\"INCOMPLETED\"")) return TaskStatus.INCOMEPLETE;
        else                                                                                       return TaskStatus.INCOMEPLETE;
    }

    public static void showTasks(String command) {
        if (AttributePatternMatcher.getAttributeString(command, AttributeSyntax.FILTER)
                .equals(AttributeSyntax.FILTER.getAttributeName())) {
            String filter = command.substring(command.indexOf(' ', command.indexOf("-s")) + 1, command.length());
            filter = filter.toUpperCase();
            if (filter.equals(TaskStatus.COMPLETED.getStatusName())) {
                System.out.println();
                tasks.stream()
                        .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                        .sorted()
                        .forEach(System.out::println);
                System.out.println();
                return;
            } else if (filter.equals(TaskStatus.INCOMEPLETE.getStatusName())) {
                System.out.println();
                tasks.stream()
                        .filter(task -> task.getStatus() == TaskStatus.INCOMEPLETE)
                        .sorted()
                        .forEach(System.out::println);
                System.out.println();
                return;
            } else {
                System.out.println("WARNING: WRONG ATTRIBUTE OR WRONG SYNTAX!!");
                return;
            }
        }
        Collections.sort(tasks);
        System.out.println();
        for (Task task : tasks) {
            System.out.println(task);
        }
        System.out.println();
    }

    @SuppressWarnings("deprecation")
    public static void clearTerminal() {
        final String os = System.getProperty("os.name");
        try {
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (Exception e) {
            System.out.println("ERROR: INTERRUPTED EXCEPTION!!");
        }
    }

}
