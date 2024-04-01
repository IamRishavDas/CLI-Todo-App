package com.todo;

import java.util.List;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

public class TaskManagement {
    private static List<Task> tasks = new ArrayList<Task>(
        Arrays.asList(
            new Task("First", "Description1", Priority.LOW),
            new Task("Second", "Description2", Priority.LOW),
            new Task("Third", "Description3", Priority.HIGH),
            new Task("Forth", "Description4", Priority.MEDIUM)
        )
    );
    public List<Task> getTasks(){
        return TaskManagement.tasks;
    }

    public static void saveTasks(){
        final String filePath = "C:\\Users\\Risha\\Desktop\\CLI Todo App\\CLI-Todo-App\\todo\\src\\main\\resources\\";
        final String fileName = "Store.dat";
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filePath + fileName));
            out.writeObject(tasks);
            out.close();
        } catch(IOException ex){
            System.out.println("Problem while storing the data in the file!!");
            ex.printStackTrace();
        }
    }

    public static void addTask(String command){
        System.out.println("TODO: ADD IMPLEMENTED YET");
    }

    public static void removeTask(String command){
        System.out.println("TODO: REMOVE IMPLEMENTED YET");
    }

    public static void markTask(String command){
        System.out.println("TODO: MARK IMPLEMENTED YET");
    }

    public static void showTasks(String command){
        for(Task task: tasks){
            System.out.println(task);
        }
    }
}
