package ie.gmit.dip;




import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient {

	public static final int DAYTIME_PORT = 13;

	public static void main(String[] args){

		try {
			String hostname = args.length > 0 ? args[0] : "localhost";
			Socket socket = new Socket(hostname,DAYTIME_PORT);
			Scanner scanner = new Scanner(System.in);
			BufferedReader inputReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			OutputStreamWriter outputWriter = new OutputStreamWriter(socket.getOutputStream());



			System.out.println("You are connected to server");
			System.out.println("Type messages to server below (enter /q to quit)");



			Runnable output = new Runnable()
			{
				String outputMessage;

				@Override
				public void run() {
					while(true){
						outputMessage = scanner.nextLine();
						try {
							outputWriter.write(outputMessage + "\r\n");
							outputWriter.flush();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			};



			Runnable input = new Runnable() {
				String inputMessage;
				@Override
				public void run() {
					try {
						inputMessage = inputReader.readLine();
						while(!(inputMessage.equals("/q")))
						{

							System.out.println("Server : "+ inputMessage);
							inputMessage = inputReader.readLine();
						}
						System.out.println("Server left the chat");
						outputWriter.close();
						socket.close();
					} catch (IOException  e) {

						e.printStackTrace();
					} catch (NullPointerException e2) {
						try {
							outputWriter.close();
							socket.close();
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
				public void uncaughtException(Thread thread, Throwable exception) {
					//ignore
				}
			};

			Thread outputThread = new Thread(output);
			Thread inputThread = new Thread(input);
			outputThread.setUncaughtExceptionHandler(exceptionHandler);
			inputThread.setUncaughtExceptionHandler(exceptionHandler);
			outputThread.start();
			inputThread .start();






		}catch (IOException e){
			//ignore
		}
	}
}