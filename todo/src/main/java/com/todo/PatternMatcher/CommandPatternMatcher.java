package com.todo.PatternMatcher;

import java.util.regex.Pattern;

import com.todo.Syntax.CommandSyntax;

public class CommandPatternMatcher {
    public static String getCommandString(String command) {

        if (Matcher.isPatternMatched(CommandSyntax.ADD.getRegularExpression(), Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.ADD.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.REMOVE.getRegularExpression(), Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.REMOVE.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.SHOW.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.SHOW.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.MARK.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.MARK.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.EXIT.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.EXIT.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.SAVE.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.SAVE.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.LOAD.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.LOAD.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.DELETE.getRegularExpression(), Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.DELETE.getCommandName();
        } else if (Matcher.isPatternMatched(CommandSyntax.CLEAR.getRegularExpression(),  Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.CLEAR.getCommandName();
        } else {
            return "!ILLEGAL MODIFIER GIVEN!";
        }
        
    }
}
