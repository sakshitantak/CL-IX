import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//Define Bully class
class Bully{
	private int noOfProcess;
	private ArrayList<Boolean> state = new ArrayList<Boolean>();
	private int leader;

	//Create processes and push them in array list and set their state to active by making them true
	public Bully(int noOfProcess) {
		this.noOfProcess = noOfProcess;
		for(int i=0;i<noOfProcess;i++) {
			state.add(true);
		}
		System.out.println("\nCreated " + noOfProcess + " process");
	}

	public void up(int processId) {

		//check if the process is already active
		if(state.get(processId-1)) {
			System.out.println("\nProcess " + processId + " is already active");		
		}

		//if process is not active
		else {
			int highestActiveProcess = processId-1 ;
			//set the status of process to active
			state.set(processId-1,true);

			//process holds election
			System.out.println("\nProcess " + processId + " held election");
			
			//process sends election messages to all other processes
			for(int i = processId;i<noOfProcess;i++) {
				System.out.println("Election msg sent from " + processId + " to process " + (i+1));
			}

			System.out.println("\n");

			//other active processes respond to election message
			for(int i=processId;i<noOfProcess;i++) {
				if(state.get(i)) {
					System.out.println("Alive msg sent from process " + (i+1) + " to process " + processId);
					highestActiveProcess = i;
				}
			}

			leader = highestActiveProcess + 1;

			System.out.println("\nElected Leader : Prcess " + leader);
			
		}

	}

	public void down(int processId) {
		if(!state.get(processId-1)) {
			System.out.println("\nProcess " + processId + " is already inactive");		
		}
		else{
			state.set(processId-1,false);
			System.out.println("\nProcess " + processId + " went down");
			if(leader == processId) {
				holdElection();
			}
		}
	}

	public void holdElection() {

		Scanner sc = new Scanner(System.in);
		int processId;


		while(true) {
			//Ask user to pick co-ordination process
			System.out.println("\nCoordination Process : ");
			processId = sc.nextInt();

			//if that process is active, it is set as co-ordination process
			if(state.get(processId-1)) {
				break;
			}

			//if process is inactive, user is asked to choose another co-ordination process
			System.out.println("\nProcess " + processId + " is down...Select another process to hold election");
		}

		//co-ordination process holds election
		System.out.println("Process " + processId + " held election");

		int highestActiveProcess = processId-1;
		
		//co-ordination process sends election message to all other processes
		for(int i = processId;i<noOfProcess;i++) {
			System.out.println("Election msg sent from " + processId + " to process " + (i+1));
		}

		System.out.println("\n");

		//active processes respond to election message
		for(int i=processId;i<noOfProcess;i++) {
			if(state.get(i)) {
				System.out.println("Alive msg sent from process " + (i+1) + " to process " + processId);
				highestActiveProcess = i;
			}
		}

		leader = highestActiveProcess + 1;

		System.out.println("\nElected Leader : Process " + leader);

	}

}

public class BullyAlgo {
	public static void main(String[] args) {
		//ask user for no. of processes
		System.out.println("Enter no of processes : ");

		Scanner sc = new Scanner(System.in);
		int noOfProcess = sc.nextInt();

		//create bully object with n. of processes stated by user
		Bully bully = new Bully(noOfProcess);


		while(true) {
			System.out.println("\nEnter choice : \n1) Up process \n2) Down process \n3) Hold election \n4) Exit\nChoice : ");

			int choice = sc.nextInt();

			if(choice == 4)
				break;

			switch(choice) {

				case 1 : 
					System.out.println("Process Id : ");
					int temp = sc.nextInt();
					bully.up(temp);
					break;

				case 2 : 
					System.out.println("Process Id : ");
					int temp1 = sc.nextInt();
					bully.down(temp1);
					break;

				case 3 : 
					bully.holdElection();
			}

		}

	}
}