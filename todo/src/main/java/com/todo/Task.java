package com.todo;

enum Status{
    COMPLETED,
    INCOMEPLETE
}

enum Priority{
    LOW,
    MEDIUM,
    HIGH
}

public class Task implements Comparable<Task>{

    private String name;
    private String description;
    private Priority priority = Priority.LOW;
    private Status isComplete = Status.INCOMEPLETE;

    public Task(String name){
        this.name = name;
    }
    public Task(String name, String description){
        this(name);
        this.description = description;
    }
    public Task(String name, String description, Priority priority){
        this(name, description);
        this.priority = priority;
    }

    public String getName() {return this.name;}
    public String getDescription() {return this.description;}
    public Priority getPriority() {return this.priority;}
    public Status getStatus() {return this.isComplete;}

    public void setPriority(Priority priority){
        this.priority = priority;
    }
    public void setStatus(Status status){
        this.isComplete = status;
    }

    @Override
    public int compareTo(Task task){
        if(this.getPriority() == Priority.LOW){
            if(task.getPriority() == Priority.MEDIUM || task.getPriority() == Priority.HIGH) {
                return 1;
            } else {
                return -1;
            }
        } else if(this.getPriority() == Priority.MEDIUM){
            if(task.getPriority() == Priority.HIGH){
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
