package ie.gmit.dip;




import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class ChatServer {
	public final static int PORT = 13;
	public static void main(String[] args){


		try {
			ServerSocket serverSocket = new ServerSocket(PORT);
			Socket connection = serverSocket.accept();
			Scanner sc = new Scanner(System.in);
			OutputStreamWriter outputWriter = new OutputStreamWriter(connection.getOutputStream());
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));





			System.out.println("You are connected to client");
			System.out.println("Type messages to client below (enter /q to quit)");


			Runnable output = new Runnable()
			{
				String outputMessage; //variable that will contains the data writter by the user
				@Override   // annotation to override the run method
				public void run() {
					while(true){
						outputMessage = sc.nextLine(); //reads data from user's keybord
						try {
							outputWriter.write(outputMessage + "\r\n" );
							outputWriter.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}    // write data stored in msg in the clientSocket
						// forces the sending of the data
					}
				}
			};


			Runnable input = new Runnable() {
				String inputMessage ;

				@Override
				public void run() {
					try {


						inputMessage = (inputReader.readLine()).toString();
						//tant que le client est connecté
						while(!(inputMessage.equals("/q"))){
							System.out.println("Client : "+inputMessage);
							inputMessage = (inputReader.readLine()).toString();

						}

						System.out.println("Client left the chat");

						outputWriter.close();
						connection.close();
						serverSocket.close();
					} catch (IOException e) {

						e.printStackTrace();
					}
					catch (NullPointerException e2) {
						try {
							outputWriter.close();
							connection.close();
							System.out.println("You have successfully left the chat");
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}


					}
				}
			};

			Thread.UncaughtExceptionHandler exceptionHandler = new Thread.UncaughtExceptionHandler() {
				@Override
				public void uncaughtException(Thread thread, Throwable throwable) {
					//ignore
				}
			};

			Thread outputThread = new Thread(output);
			Thread inputThread = new Thread(input);
			outputThread.setUncaughtExceptionHandler(exceptionHandler);
			inputThread.setUncaughtExceptionHandler(exceptionHandler);

			outputThread.start();
			inputThread.start();
		} catch (IOException e) {
			//ignore
		}


	}
}