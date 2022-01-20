# Client-Server-Chat-Program
A web chat program built using Java

**Program in action**

![Alt Text](https://media.giphy.com/media/C0vQ31nNKorRkb3A1m/giphy.gif)

**Instructions**

- Run ChatServer.java
- Once server is up and running run ClientServer.java
- Type messages into the console window and press Enter after each message
- To leave the chat session from either side enter “/q”

Requirements: Eclipse Version: 2020-12 (4.18.0) or later

**Design**

Threads

This application involved the use of threads. In java, the splitting up of code into two or more threads (multithreading) allows for the concurrent execution of separate sections of code within a program. This java feature was vital to my program as it allowed for the client and server to simultaneously send and receive messages. If threads were not implemented in this application and the code was single threaded, the client and server programs would not be able to send a message until a new message is received from the other side and vice versa. In real life this would not suffice as chat applications need to be able to send and receive messages in real time without any delay and the use of threads allowed me to bypass this issue.
In Java, threads can be implemented by creating thread subclasses or by implementing Runnable interfaces. For this application I chose to do the latter and declared an anonymous implementation of Runnable for both the input and output functionalities of both Server and Client classes. [1]

Console Window

The console window on both the client and server sides presents the user with instructions on how to use the application and how to terminate the program. The sent and received messages are all displayed on separate lines and laid out in a neat manner. As well as this, if the other side leaves the chat the console displays a message which lets the user know that this has occurred.

Input/Output

User input is taken in via the console window. Internally, the application processes this input using the Scanner class. This message is then sent to the recipient using the OutputStreamWriter and on the opposite side this message is received using a BufferedReader before being displayed to the screen by a System.out() call. [2]

Thread.UncaughtExceptionHandler

The use of the Thread.UncaughtExceptionHandler interface in this application allows for the handling of uncaught exceptions that are thrown when a thread terminates abruptly.
If “/q” is entered without the uncaught exception handler, a thread exception will occur and the details of which will be outputted to the console. The use of this UncaughtExceptionHandler therefore allows for cleaner exit from the program after “/q” is entered. [3]

**References**

[1] Thread (Java Platform SE 7 ) (2022). Available at: https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.html (Accessed: 11 January 2022).

[2] java.io (Java Platform SE 7 ) (2022). Available at: https://docs.oracle.com/javase/7/docs/api/java/io/package-summary.html (Accessed: 11 January 2022).

[3] Thread.UncaughtExceptionHandler (Java Platform SE 7 ) (2022). Available at: https://docs.oracle.com/javase/7/docs/api/java/lang/Thread.UncaughtExceptionHandl er.html (Accessed: 11 January 2022).
