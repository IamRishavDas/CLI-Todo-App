package com.todo.TaskOperations;

public enum TaskStatus {
    COMPLETED("COMPLETED"),
    INCOMEPLETE("INCOMPLETE");

    private String statusName;

    private TaskStatus(String statusName){
        this.statusName = statusName;
    }

    public String getStatusName(){
        return this.statusName;
    }
}
