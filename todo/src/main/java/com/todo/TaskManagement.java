package com.todo;

import java.util.List;
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
