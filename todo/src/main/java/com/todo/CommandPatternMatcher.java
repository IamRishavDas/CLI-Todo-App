package com.todo;

import java.util.regex.Pattern;

public class CommandPatternMatcher {
    public static String getCommandString(String command) {

        if (isPatternMatched(CommandSyntax.ADD.getRegularExpression(), Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.ADD.getCommandName();
        } else if (isPatternMatched(CommandSyntax.REMOVE.getRegularExpression(), Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.REMOVE.getCommandName();
        } else if (isPatternMatched(CommandSyntax.SHOW.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.SHOW.getCommandName();
        } else if (isPatternMatched(CommandSyntax.MARK.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.MARK.getCommandName();
        } else if (isPatternMatched(CommandSyntax.EXIT.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.EXIT.getCommandName();
        } else if (isPatternMatched(CommandSyntax.SAVE.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.SAVE.getCommandName();
        } else if (isPatternMatched(CommandSyntax.LOAD.getRegularExpression(),   Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.LOAD.getCommandName();
        } else if (isPatternMatched(CommandSyntax.DELETE.getRegularExpression(), Pattern.CASE_INSENSITIVE, command)) {
            return CommandSyntax.DELETE.getCommandName();
        } else {
            return "!ILLEGAL MODIFIER GIVEN!";
        }

    }

    public static boolean isPatternMatched(String regex, int sensitivity, String command) {
        return Pattern.compile(regex, sensitivity).matcher(command).find();
    }
}
