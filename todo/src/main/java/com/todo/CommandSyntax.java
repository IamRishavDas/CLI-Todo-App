package com.todo;

public enum CommandSyntax {
    ADD    ("add",    "add <task name> <optional> -d <description> -p <priority = LOW || MEDIUM || HIGH>", "^\\s*\\bADD\\s*.*|^\\add\\s*.*"),
    REMOVE ("remove", "remove <task name>",                                                                "^\\s*\\bREMOVE\\s*.*|^\\remove\\s*.*"),
    MARK   ("mark",   "mark <task name> -s <COMPLETED || INCOMEPLETE>" ,                                   "^\\s*\\bMARK\\s*.*|^mark\\s*.*"),
    SHOW   ("show",   "show <optional>  -s <COMPLETED || INCOMEPLETE>",                                    "^\\s*\\bSHOW\\s*.*|^\\show\\s*.*"),
    EXIT   ("exit",   "no attribute needed",                                                               "^\\s*\\bexits?\\b$");

    private String commandName;
    private String syntax;
    private String regularExpression;

    private CommandSyntax(String commandName, String syntax, String regularExpression){
        this.commandName = commandName;
        this.syntax = syntax;
        this.regularExpression = regularExpression;
    }

    public String getCommandName(){return this.commandName;}
    public String getSyntax(){return this.syntax;}
    public String getRegularExpression(){return this.regularExpression;}
}
