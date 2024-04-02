package com.todo.PatternMatcher;

import java.util.regex.Pattern;

import com.todo.Syntax.AttributeSyntax;

public class AttributePatternMatcher {
    public static String getAttributeString(String command, AttributeSyntax attribute){
        if(Matcher.isPatternMatched(attribute.getRegularExpression(), Pattern.CASE_INSENSITIVE, command)){
            return attribute.getAttributeName();
        } else {
            return "INVALID ATTRIBUTE!";
        }
    }
}
