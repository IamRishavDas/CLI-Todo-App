package com.todo.Syntax;



public enum AttributeSyntax {
    FILTER          ("-s", "for filtering between COMPLETE && INCOMPLETE tasks", ".*\\s-s\\b.*"),
    DESCRIPTION     ("-d", "for adding description to a task",                   ".*\\s-d\\b.*"),
    PRIORITY        ("-p", "for adding priority to a task",                      ".*\\s-p\\b.*");

    private String attributeName;
    private String attributeProperty;
    private String regularExpression;

    private AttributeSyntax(String attributeName, String attributeProperty, String regularExpression){
        this.attributeName = attributeName;
        this.attributeProperty = attributeProperty;
        this.regularExpression = regularExpression;
    }

    public String getAttributeName(){return this.attributeName;}
    public String getAttributeProperty(){return this.attributeProperty;}
    public String getRegularExpression(){return this.regularExpression;}
}
