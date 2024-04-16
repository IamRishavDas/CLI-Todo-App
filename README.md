# CLI-Todo-App
A generic purpose Todo App with command Line interface





# Commands
## ⚪add: for adding a new task
add syntax:
```
add "task name"
```
## attributes: 
### -d: description
```
add "task name" -d "description"
```
### -p: priority (high || low || medium)
```
add "task name" -d "description" -p priority
```

## ⚪remove: remove an existing task
remove syntax:
```
remove "task name"
```

## ⚪mark: for marking a task as completed or incomplete
mark syntax:
```
mark "task name" -s "completed"
```

## ⚪show: show all the tasks present in the live session
show syntax:
```
show
```
## attributes:
### -s: show the completed or incomplete task
```
show -s "completed"
```

## ⚪save: save all tasks present in the current session for further use
save syntax:
```
save
```

## ⚪load: load all tasks from the previous session
load syntax:
```
load
```

## ⚪delete: delete the current saved session or the data of the previously saved session
save syntax:
```
delete
```

## ⚪clear: clear the console window as command "cls"
save syntax:
```
clear
```

## ⚪exit: exit the application
save syntax:
```
exit
```




# Adding a JAR File to Environment Variables (Windows)
When working with Java applications, you might want to run JAR files directly from the command line without specifying the full path. To achieve this, follow these steps:

## 1. Create a Batch Script:
## Create a batch script (let’s call it myapp.bat) that runs your JAR file using the java -jar command. For example:
```
@echo off
java -jar "C:\path\to\your\myapp.jar"
```

Save this batch file in a directory of your choice.
## 2. Update the PATH Environment Variable:
Add the directory containing your myapp.bat file to the system’s PATH environment variable:
Open the Control Panel.
Navigate to System and Security > System > Advanced system settings > Environment Variables.
In the System variables section, find the PATH variable and click Edit.
Add the path to the directory where your myapp.bat file is located (e.g., C:\path\to\your\directory).
Click OK to save the changes.
## 3. Test Your Setup:
Open a new command prompt or PowerShell window.
Type myapp and hit Enter. If everything is set up correctly, your JAR file should execute.
## 4. Optional Recommendations:
Consider adding a user environment variable named JAVA_TOOL_OPTIONS with the value -Dfile.encoding="UTF-8". This ensures consistent character encoding for Java applications.
Remove the entry C:\ProgramData\Oracle\Java\javapath; from the system’s PATH variable to maintain a consistent environment.
Remember to replace myapp.jar and the paths with your actual JAR file and directory. This approach allows you to run your Java application by simply typing its name in the command prompt or PowerShell.


