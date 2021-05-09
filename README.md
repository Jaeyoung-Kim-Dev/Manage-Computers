<h1 align="center">Welcome to Manage Computers 👋</h1>

## What project is it?

> This is a console-based (text-based) Java application that I did in the fourth semester of college <a href="https://www.sait.ca/programs-and-courses/diplomas/information-technology" target='_blank'>(SAIT)</a>. This application includes several ways to **_enhance Java security_**.

## What is the purpose of the project?

> It manages information about computers for a company. The company owns laptop and desktop computers and needs to store, display and manage the data about the specifications for the machines.

## Keynote (General Requirements)

- No subclassing allowed (code reuse is to be done via composition)
- Classes are only instantiated using static constructor methods, e.g. getInstance(). Normal constructors are declared as private.
- Objects are immutable (editing an object means replacing it with a new instance)
- User input to the application are validated. It is not possible to store invalid attribute values in objects used in the system. Attempting to do so display an error on-screen, and redisplay the menu to the user
- The application runs under the supervision of a SecurityManager with an appropriate policy file to allow data saving and loading
- The BaseComputer, LaptopComputer and DesktopComputer classes are accessed from a sealed .jar file called Domain.jar when the application is run
- Exceptions are sanitized of sensitive data, e.g. file names and paths

## Functionality

- Display a menu of options to the user and accept their selection
- Listing/displaying the details of all the computers stored in the system
- Adding a new computer to the system
- Editing an existing computer’s data
- Delete an existing computer from the system
- Load the data on all of the computer from a drive
- Save the data for all the computers (stored in memory) to the drive
- Exit the application

## Menu

| No  | Menu   | Note                                                                                                                                                                                     |
| :-: | ------ | ---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------- |
|  1  | Load   | Load computer data from drive. It will load the data for those computers into memory, replacing any computer data that was already in memory with the data loaded from the drive.        |
|  2  | Save   | Save computer data to a drive. It will save copies of the data for each computer (held in memory) in separate serialized data files on the drive                                         |
|  3  | List   | Listing the computers. In the event that there is data for a computer(s) in the system then it displays. The computers are numbered based upon the order they were stored in the system. |
|  4  | Add    | Add a computer. The prompts show the only valid options for each item, e.g. CPU’s can only be “i5” or “i7”, etc. Any other values are rejected and an error message displayed.           |
|  5  | Delete | Delete an existing computer. Enter the number of the computer to delete as shown in the computer listing (by entering “1”)                                                               |
|  6  | Edit   | Edit the data for an existing computer. Enter the number of the computer to edit as shown in the computer listing (by entering “1”)                                                      |
|  7  | Exit   | It terminates the application and drops the user back to the command prompt. Exiting from the application does not auto-save any unsaved computer data in memory.                        |

## Application Structure

- ManageComputers.java: this contains the main() method for your application, and other methods as required:

  When run the main() method creates the ArrayList used to store the computer data for the application. This is where user-entered data, and data loaded from a file, is stored and accessed by the application

- domain package:
  1.  BaseComputer.java: holds the CPU type (String), RAM size (int) and disk size (int) for any computer
  2.  LaptopComputer.java: holds an instance of BaseComputer, and additionally the screen size (int) for the laptop
  3.  DesktopComputer.java: holds an instance of BaseComputer, and additionally the GPU type (String) for the desktop
- MANIFEST.MF: contains the information used to seal the domain package
- security.policy: the SecurityManager policy file containing permissions allowing the application to work with files in the /root/assign1Data directory

```java
//Security.Policy
grant codeBase "file:/root/ManageComputers/\*" {
    permission java.io.FilePermission "/root/assign1Data", "read,write";
};
grant codeBase "file:/root/ManageComputers/\*" {
    permission java.io.FilePermission "/root/assign1Data/*", "read,write,delete";
};
```

## Data Storage and Retrieval

- Computer data (i.e. for LaptopComputer and DesktopComputer objects) is stored in serialized form on a drive. The data is held in files in /root/assign1Data. Each laptop and desktop object in the ArrayList maintained by the application is stored separately in its own serialized file in the assign1Data directory (named 1.txt, 2.txt, 3.txt etc.). All String attribute data are encrypted so that it cannot be easily read in the serialized data files. Numeric attribute data is not easily readable in serialized files.
- Unsaved data in memory will be lost if the user closes the application without saving it.
- Data in memory will be completely replaced with data loaded from the drive when the user chooses to load data.
- The assign1Data directory will be completely cleared of any existing files, and have new files written into it from the ArrayList, when the user chooses to save the in-memory application data.

## Object String Data Encryption

- When serializing objects (during a save operation), all String attribute data are encrypted by shifting all of the characters in the String one place to the right in the alphabet (a “Caesar Cipher” approach to data encryption). Looking directly at the contents of the serialized object files on the drive are not show the unencrypted String data in the files.
- When deserializing object data (during a load operation) the String character data loaded from the files is shifted one place to the left (to decrypt the String data).

## How to run the program

> Run this command below in source folder

#### For Linux and macOS:

```sh
> java -cp .:Domain.jar -Djava.security.manager -Djava.security.policy=security.policy ManageComputers
```

#### For Windows:

```sh
> java -cp .;Domain.jar -Djava.security.manager -Djava.security.policy=security.policy ManageComputers
```

## Languages

<p align="left"> <a href="https://www.java.com" target="_blank"> <img src="https://raw.githubusercontent.com/devicons/devicon/master/icons/java/java-original.svg" alt="java" width="40" height="40"/> </a><a href="https://git-scm.com/" target="_blank"> <img src="https://www.vectorlogo.zone/logos/git-scm/git-scm-icon.svg" alt="git" width="40" height="40"/> </a> </p>

## Screen Shots

![screenshot1](./screenshots/screenshot1.jpg?raw=true)

![screenshot2](./screenshots/screenshot2.jpg?raw=true)

## Author

👤 **Jaeyoung Kim**

- Website: https://www.jaeyoungkim.ca/
- Github: [@jaeyoung-kim-dev](https://github.com/jaeyoung-kim-dev)
- LinkedIn: [@jaeyoung-kim-dev](https://www.linkedin.com/in/jaeyoung-kim-dev/)
- Medium(Blog): [@jaeyoung-kim-dev](https://jaeyoung-kim-dev.medium.com/)
- Email: jaeyong.kim.dev@gmail.com
