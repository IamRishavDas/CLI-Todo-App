package com.todo.TaskOperations;

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
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class TaskManagement {

    final static String filePath = System.getProperty("user.dir") + File.separator + "todo" + File.separator + "src"
            + File.separator + "main" + File.separator + "resources" + File.separator;
    final static String fileName = "Store.dat";

    private static List<Task> tasks = new ArrayList<Task>();

    public static void saveTasks() {

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

        try {

            FileInputStream fileInputStream = new FileInputStream(filePath + fileName); // Store.dat File
            ObjectInputStream in = new ObjectInputStream(fileInputStream);

            tasks = (ArrayList<Task>) in.readObject();

            in.close();
            fileInputStream.close();
        } catch (EOFException eof) {
            System.out.println("WARNING: PREVIOUS SESSION DATA IS NOT STORED!!");
        } catch (FileNotFoundException fnf) {
            System.out.println("THE PREVIOUS SESSION FILE IS MISSING!!");
        } catch (IOException io) {
            System.out.println("WARNING: SOMETHING WRONG IS HAPPENING I DON'T KNOW WHAT IS THE ACTUAL PROBLEM");
        } catch (ClassNotFoundException ex) {
            System.out.println("WARING: THE FOLLOWING CLASS IS NOT FOUND!!");
        }

        return;
    }

    public static void deleteSavedTasks() {

        File datFile = new File(filePath + fileName); // Store.dat File

        if (datFile.exists()) {

            boolean deleted = datFile.delete();
            if (deleted) {
                System.out.println("PREVIOUS SESSION DELETED SUCCESSFULLY!!");
            } else {
                System.out.println("FAILED TO DELETE THE PREVIOUS SESSION!!");
            }
        } else {
            System.out.println("WARNING: FILE NOT EXIST (GO THROUGH THE GITHUB REPO OR REPORT A ISSUE)!!");
        }
        createNewDatFile();
        return;
    }


    private static void createNewDatFile() {

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
        } else if (isTaskNameFound && isPriorityFound) {
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

        boolean isAtleastOneTaskDeleted = false;
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.getName().equals(taskName)) {
                it.remove();
                isAtleastOneTaskDeleted = true;
            }
        }

        if (!isAtleastOneTaskDeleted)
            System.out.println("ERROR: NO SUCH TASK NAME FOUND IN TODO LIST!!");
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

        boolean isTaskForMarkedFound = false;
        TaskStatus taskStatus = getStatus(status);
        Iterator<Task> it = tasks.iterator();
        while (it.hasNext()) {
            Task task = it.next();
            if (task.getName().equals(taskName)) {
                task.setStatus(taskStatus);
                isTaskForMarkedFound = true;
            }
        }

        if(!isTaskForMarkedFound) System.out.println("ERROR: " + taskName + " NO SUCH TASK FOUND IN THE LIST!!");
    }

    private static TaskStatus getStatus(String status) {
        status = status.toUpperCase();
        if (status.equals("COMPLETED") || status.equals("\"COMPLETED\""))
            return TaskStatus.COMPLETED;
        else if (status.equals("INCOMPLETE") || status.equals("\"INCOMPLETED\""))
            return TaskStatus.INCOMEPLETE;
        else
            return TaskStatus.INCOMEPLETE;
    }

    public static void showTasks(String command) {

        if(tasks.size() == 0) {
            System.out.println("TASK LIST IS EMPTY!!");
            return;
        }

        if (AttributePatternMatcher.getAttributeString(command, AttributeSyntax.FILTER)
                .equals(AttributeSyntax.FILTER.getAttributeName())) {
            String filter = command.substring(command.indexOf(' ', command.indexOf("-s")) + 1, command.length());
            filter = filter.toUpperCase();
            // System.out.println(filter);
            if (filter.equals(TaskStatus.COMPLETED.getStatusName()) || filter.equals("\"COMPLETED\"")) {
                if(countTaskWithGivenStatus(TaskStatus.COMPLETED) == 0){
                    System.out.println("NO COMPLETED TASK FOUND");
                    return;
                }
                System.out.println();
                tasks.stream()
                        .filter(task -> task.getStatus() == TaskStatus.COMPLETED)
                        .sorted()
                        .forEach(System.out::println);
                System.out.println();
                return;
            } else if (filter.equals(TaskStatus.INCOMEPLETE.getStatusName()) || filter.equals("\"INCOMPLETE\"")) {
                if(countTaskWithGivenStatus(TaskStatus.INCOMEPLETE) == 0){
                    System.out.println("NO INCOMPLETE TASK FOUND");
                    return;
                }
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

    private static int countTaskWithGivenStatus(TaskStatus taskStatus){
        int countOccrance = 0;
        for(Task task: tasks){
            if(task.getStatus().equals(taskStatus)){
                countOccrance++;
            }
        }
        return countOccrance;
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
