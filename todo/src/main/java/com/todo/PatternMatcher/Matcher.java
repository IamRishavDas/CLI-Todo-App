package com.todo.PatternMatcher;

import java.util.regex.Pattern;

public class Matcher {
    
    public static boolean isPatternMatched(String regex, int sensitivity, String command) {
        return Pattern.compile(regex, sensitivity).matcher(command).find();
    }
}
