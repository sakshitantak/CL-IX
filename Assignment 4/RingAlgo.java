import java.io.*;
import java.util.*;

class Process {

	public Boolean state;
	public int processId;

	public Process(int processId, Boolean state) {
		this.state = state;
		this.processId = processId;
	}

	public int getProcessId() {
		return this.processId;
	}

} 

public class RingAlgo {

	static ArrayList<Process> processArray = new ArrayList<Process>();

	public static void holdElection() {

		Scanner sc = new Scanner(System.in);
		int processIndex;
		int noOfProcess = processArray.size();

		while(true) {

			System.out.println("\nCoordination Process index: ");
			processIndex = sc.nextInt();

			if(processArray.get(processIndex).state) {
				break;
			}

			System.out.println("\nProcess " + processArray.get(processIndex).processId + " is down...Select another process to hold election");
		}

		System.out.println("Process " + processArray.get(processIndex).processId + " held election");

		int i = processIndex;
		int j = (i+1)%noOfProcess;
		int leader = processArray.get(i).processId;

		while(!processArray.get(j).state) {
			j = (j+1)%noOfProcess;
		}

		while(j != processIndex) {
			System.out.println("\nProcess " + processArray.get(i).processId + " sent message to process " + processArray.get(j).processId);
			
			if(processArray.get(j).processId > leader) {
				leader = processArray.get(j).processId;
			}

			i = j;
			j = (i+1)%noOfProcess;
			while(!processArray.get(j).state) {
				j = (j+1)%noOfProcess;
			}

		}

		System.out.println("\nProcess " + processArray.get(i).processId + " sent message to process " + processArray.get(j).processId);
		System.out.println("\nProcess " + leader + " won the election");


	} 

	public static void main(String[] args) {

		System.out.println("\nEnter no of processes : ");

		Scanner sc = new Scanner(System.in);
		int noOfProcess = sc.nextInt();

		System.out.println("\nEnter process ids : ");

		for(int i=0;i<noOfProcess;i++) {

			int processId = sc.nextInt();

			Process process = new Process(processId,true);

			processArray.add(process);
		}

		//System.out.println("\nSorting processes based on processId ....");

		while(true) {
			System.out.println("\nEnter choice : \n1) Up process \n2) Down process \n3) Hold election \n4) Exit\nChoice : ");

			int choice = sc.nextInt();

			if(choice == 4) 
				break;

			switch(choice) {

				case 1 : 
					System.out.println("\nEnter process index : ");
					int temp = sc.nextInt();
					processArray.get(temp).state = true;
					System.out.println("\nChanged status of process " + processArray.get(temp).processId + " to active");
					break;

				case 2 : 
					System.out.println("\nEnter process index : ");
					int temp1 = sc.nextInt();
					processArray.get(temp1).state = false;
					System.out.println("\nChanged status of process " + processArray.get(temp1).processId + " to inactive");
					break;

				case 3 : 
					holdElection();
			}

		}

	}
}