//package clientPackage;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.*;
import java.util.*;

class Client 
{
	public static void main(String[] args) throws Exception
	{
		Integer portNo;	// To accept the port number to connect the client
		Scanner scan1 = new Scanner(System.in);	// To converse with the server
		String toSend = "";
		String recieved = "";
		BufferedReader bufferReader = new BufferedReader(new InputStreamReader(System.in)); // To read the lines in a buffer to send
		
		System.out.print("Hi I am the Client. Enter the port no on which you want to connect: ");
		portNo = scan1.nextInt();	// Accept port no.
		Socket socket = new Socket("localhost", portNo);	// Create a socket with the selected host and port
		
		DataInputStream dataInputStream = new DataInputStream(socket.getInputStream());	// DataInputStream object with the concerned socket to accept the message from server
		DataOutputStream dataOutputStream = new DataOutputStream(socket.getOutputStream());	// DataOutputStream object with the concerned socket to send the message to server
		
		// Run while the client does not send "Exit
		while(!toSend.equals("Exit"))
		{
			System.out.print("Client : ");	// Accept message from Server
			toSend = bufferReader.readLine();
			dataOutputStream.writeUTF(toSend); // Write the message in UTF format to send to server
			dataOutputStream.flush();	// Clear the dos for further communication
			recieved = dataInputStream.readUTF();	// receive the response from the server
			System.out.println("Server: " + recieved);	// print the response
		}
		// Close all of open sockets, scanner and stream objects
		dataOutputStream.close();
		dataInputStream.close();
		socket.close();
		scan1.close();
	}
}
