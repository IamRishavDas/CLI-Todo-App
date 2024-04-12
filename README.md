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





https://github.com/IamRishavDas/CLI-Todo-App/assets/140265067/fd493cae-080d-4686-8c64-dc088967463a



To add your myapp.bat file to your environment variables on Windows, follow these steps:

Locate the Directory:
First, ensure that your myapp.bat file is saved in a directory of your choice. For example, you might have it in C:\Program Files\myapp.
Edit Environment Variables:
Press the Windows key + R to open the Run dialog.
Type sysdm.cpl and hit Enter. This opens the System Properties window.
Go to the Advanced tab and click the Environment Variables button.
Add to User or System Variables:
In the Environment Variables window, you’ll see two sections: User variables and System variables.
Choose the appropriate section based on your preference:
User variables: These apply only to your user account.
System variables: These apply to all users on the system.
Click New to add a new variable.
Set Variable Name and Value:
For Variable name, enter a name for your application (e.g., MYAPP).
For Variable value, enter the full path to your myapp.bat file (e.g., C:\Program Files\myapp\myapp.bat).
Apply Changes:
Click OK to close the Environment Variables window.
Close any open command prompts or terminals and reopen them to apply the changes.
Test the Environment Variable:
Open a new command prompt or PowerShell window.
Type myapp and hit Enter. If your myapp.bat script runs successfully, the environment variable is set up correctly.


