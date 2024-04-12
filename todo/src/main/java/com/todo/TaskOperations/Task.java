package com.todo.TaskOperations;

import java.io.Serializable;


public class Task implements Comparable<Task>, Serializable{

    private String name;
    private String description;
    private TaskPriority priority = TaskPriority.LOW;
    private TaskStatus isComplete = TaskStatus.INCOMEPLETE;

    public Task(String name){
        this.name = name;
    }
    public Task(String name, String description){
        this(name);
        this.description = description;
    }
    public Task(String name, TaskPriority priority){
        this(name);
        this.priority = priority;
    }
    public Task(String name, String description, TaskPriority priority){
        this(name, description);
        this.priority = priority;
    }

    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public TaskPriority getPriority() {return this.priority;}
    public TaskStatus getStatus() {return this.isComplete;}

    public void setPriority(TaskPriority priority){
        this.priority = priority;
    }
    public void setStatus(TaskStatus status){
        this.isComplete = status;
    }

    @Override
    public int compareTo(Task task){
        if(this.getPriority() == TaskPriority.LOW){
            if(task.getPriority() == TaskPriority.MEDIUM || task.getPriority() == TaskPriority.HIGH) {
                return 1;
            } else {
                return -1;
            }
        } else if(this.getPriority() == TaskPriority.MEDIUM){
            if(task.getPriority() == TaskPriority.HIGH){
                return 1;
            } else {
                return -1;
            }
        } else {
            return -1;
        }
    }

    @Override
    public String toString(){
        return "  TASK_NAME = " + this.getName() + "  DESCRIPTION = " + this.getDescription() + "  PRIORITY = " + this.getPriority() + "  STATUS = " + this.getStatus();
    }

}
