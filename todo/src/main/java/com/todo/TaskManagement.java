package com.todo;

import java.util.List;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class TaskManagement {

    private static List<Task> tasks = new ArrayList<Task>(

        // temproray testing data
            Arrays.asList(
                    new Task("First",  "Description1",  Priority.LOW),
                    new Task("Second", "Description2",  Priority.LOW),
                    new Task("Third",  "Description3",  Priority.HIGH),
                    new Task("Forth",  "Description4",  Priority.MEDIUM))

    );

    public List<Task> getTasks() {
        return TaskManagement.tasks;
    }

    public static void saveTasks() {

        final String filePath = "C:\\Users\\Risha\\Desktop\\CLI Todo App\\CLI-Todo-App\\todo\\src\\main\\resources\\";
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

        final String filePath = "C:\\Users\\Risha\\Desktop\\CLI Todo App\\CLI-Todo-App\\todo\\src\\main\\resources\\";
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

        final String filePath = "C:\\Users\\Risha\\Desktop\\CLI Todo App\\CLI-Todo-App\\todo\\src\\main\\resources\\";
        final String fileName = "Store.dat";

        File datFile = new File(filePath + fileName); //Store.dat File

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

        final String filePath = "C:\\Users\\Risha\\Desktop\\CLI Todo App\\CLI-Todo-App\\todo\\src\\main\\resources\\";
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
        System.out.println("TODO: ADD IMPLEMENTED YET");
    }

    public static void removeTask(String command) {
        System.out.println("TODO: REMOVE IMPLEMENTED YET");
    }

    public static void markTask(String command) {
        System.out.println("TODO: MARK IMPLEMENTED YET");
    }

    public static void showTasks(String command) {
        Collections.sort(tasks);
        for (Task task : tasks) {
            System.out.println(task);
        }
    }

}
