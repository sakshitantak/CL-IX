//package serverPackage;

import java.io.*;
import java.util.*;
import java.net.*;
//import serverPackage.ClientHandler;


public class Server 
{
	public static void main(String[] args) throws IOException 
	{
		Scanner scan1 = new Scanner(System.in);// To converse with the client
		String receivedData = "";
		String dataToTransmit = "";
		System.out.print("Enter the port no. on which you wish to start server: ");
		int portNo = scan1.nextInt();// To accept the port number to start the server
		ServerSocket serverSocket = new ServerSocket(portNo); // Create a server socket with the said port no
		
		System.out.println("Server waiting on port no. " + portNo);
		Socket socket = serverSocket.accept();
		
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());// DataInputStream object with the concerned socket to accept the message from client
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());// DataOutputStream object with the concerned socket to send the message to client
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		
		while(!receivedData.equals("Exit"))
		{
			receivedData = dataInputStream.readUTF(); // Accept message from Client
			System.out.println("Client: " + receivedData);	// print
			System.out.print("Enter your response: ");	// Write the message in UTF format to send to client
			dataToTransmit = bufferedReader.readLine();
			dataOutputStream.writeUTF(dataToTransmit); // Send the message
			dataOutputStream.flush();
		}
//		Close the connections and streams
		dataInputStream.close();
		dataOutputStream.close();
		socket.close();
		serverSocket.close();
		scan1.close();
	}
}
